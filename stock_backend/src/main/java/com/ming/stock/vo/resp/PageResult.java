package com.ming.stock.vo.resp;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页工具类
 */
@Schema(description = "分页实体类")
@Data
public class PageResult<T> implements Serializable {
    @Schema(description = "总行数")
    private Long totalRows;

    @Schema(description = "总页数")
    private Integer totalPages;

    @Schema(description = "当前页")
    private Integer pageNum;

    @Schema(description = "每页大小")
    private Integer pageSize;

    @Schema(description = "当前页大小")
    private Integer size;

    @Schema(description = "结果集")
    private List<T> rows;

    /**
     * 分页数据组装
     * @param pageInfo
     * @return
     */
    public PageResult(PageInfo<T> pageInfo){
        totalRows = pageInfo.getTotal();
        totalPages = pageInfo.getPages();
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        size = pageInfo.getSize();
        rows = pageInfo.getList();
    }


}
