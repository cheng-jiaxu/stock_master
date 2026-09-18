# ARM 服务器运行 AMD64 Docker 镜像排障

## 1. 问题现象

先确认服务器 CPU 架构：

```bash
uname -m
```

如果输出：

```text
aarch64
```

说明服务器是 ARM 架构。运行 AMD64 镜像时，如果出现：

```text
exec /bin/uname: exec format error
```

通常是内核没有注册 `qemu-x86_64` 的 `binfmt` 模拟器，导致 ARM 无法执行 AMD64 镜像中的程序。

## 2. 加载并挂载 binfmt_misc

在服务器执行：

```bash
modprobe binfmt_misc

mountpoint -q /proc/sys/fs/binfmt_misc || \
mount -t binfmt_misc binfmt_misc /proc/sys/fs/binfmt_misc
```

## 3. 注册 AMD64 模拟器

```bash
docker run --privileged --rm tonistiigi/binfmt --install amd64
```

成功时通常会显示：

```text
installing: amd64 OK
```

## 4. 确认 qemu-x86_64 已注册

```bash
cat /proc/sys/fs/binfmt_misc/qemu-x86_64
```

正常应包含：

```text
enabled
interpreter /usr/bin/qemu-x86_64
```

若提示文件不存在，说明模拟器尚未成功注册；不要立刻重启业务容器。

## 5. 测试 AMD64 容器

```bash
docker run --rm --platform linux/amd64 alpine uname -m
```

输出：

```text
x86_64
```

表示 ARM 主机已经可以通过 QEMU 运行 AMD64 容器。

## 6. 重启业务容器

只有第 5 步通过后，再启动或重启 AMD64 容器。例如 XXL-JOB Admin：

```bash
docker restart xxl-job-admin
docker logs -f --tail 100 xxl-job-admin
```

## 7. 仍无法运行时

| 现象 | 处理方式 |
| --- | --- |
| `modprobe binfmt_misc` 提示模块不存在 | 当前 VM 内核可能不支持 `binfmt_misc`；联系云厂商或改用 ARM64 镜像/Java 部署。 |
| `qemu-x86_64` 文件不存在 | 重新执行第 2、3 步，并确认 Docker 以 root 权限运行。 |
| 测试命令仍报 `exec format error` | 当前内核没有正确启用跨架构模拟，优先使用 ARM64 版本镜像。 |

> QEMU 跨架构模拟会有性能损耗。生产环境优先选择 ARM64 镜像，或直接部署对应的 Java 应用。
