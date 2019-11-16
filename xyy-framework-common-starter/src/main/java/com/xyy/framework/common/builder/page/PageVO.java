package com.xyy.framework.common.builder.page;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * 分页
 *
 * @author chentudong
 * @date 2019/9/14 13:35
 * @since 1.0
 */
public class PageVO<T>
{
    /**
     * page
     */
    @JSONField(name="page")
    private Integer page;
    /**
     * size
     */
    @JSONField(name="size")
    private Integer size;
    /**
     * totalPage
     */
    @JSONField(name="total_page")
    private Integer totalPage;
    /**
     * totalCount
     */
    @JSONField(name="total_count")
    private Long totalCount;

    /**
     * result
     */
    @JSONField(name="data")
    private Collection<T> data;

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }

    public Integer getTotalPage()
    {
        totalPage = this.getSize() == 0 ? 1 : (int)Math.ceil((double)this.totalCount / (double)this.getSize());
        return totalPage;
    }

    public void setTotalPage(Integer totalPage)
    {
        this.totalPage = totalPage;
    }

    public Long getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(Long totalCount)
    {
        this.totalCount = totalCount;
    }

    public Collection<T> getData()
    {
        return data;
    }

    public void setData(Collection<T> data)
    {
        this.data = data;
    }

    /**
     * 初始化分页对象
     *
     * @param page page
     * @param size size
     * @param <T>  <T>
     * @return Page<T>
     */
    public static <T> PageVO<T> init(Integer page, Integer size)
    {
        PageVO<T> result = new PageVO<>();
        result.setTotalCount(0L);
        result.setPage(Objects.isNull(page) ? 0 : size);
        result.setSize(Objects.isNull(size) ? 10 : size);
        result.setTotalPage(0);
        result.setData(new ArrayList<>());
        return result;
    }
}
