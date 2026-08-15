本项目遵循 Conventional Commits 规范，提交格式如下：

<type>(<scope>): <subject>

Scope 定义列表：

1. 按模块划分（Module Scope - 涉及特定子工程改动）
- backend: stock_backend 模块（Web 接口、业务 Controller/Service、权限拦截等）
- job: stock_job 模块（定时任务调度、外源数据爬取与数据清洗入库）
- common: stock_common 模块（公共实体 Entity/VO/DTO、Mapper 接口与 XML、通用工具类）
- root: 父工程根目录（顶层 pom.xml 依赖版本管理、全局配置）

2. 按业务领域划分（Domain Scope - 对应具体业务功能）
- market: 大盘指数、大盘全局统计指标
- sector: 行业/概念板块行情与统计
- stock: 个股实时行情、K线、分时图、股票字典表
- user: 用户管理、登录鉴权、个人中心、自选股
- config: 中间件配置（MySQL、Redis、RestTemplate、Swagger 等）

常用提交示例：
- 新增板块数据定时采集: feat(job): 实现行业板块实时行情采集与分批入库
- 新增个股/大盘 Web 接口: feat(backend): 增加个股分时图数据查询接口
- 优化公共 Mapper 与 XML: fix(common): 优化 StockRtInfoMapper 批量插入支持主键幂等更新
- 调整公共时间工具类: refactor(common): 完善 DateTimeUtil 开闭市时间判定
- 升级父工程依赖版本: chore(root): 升级 Hutool 与 MyBatis 依赖版本