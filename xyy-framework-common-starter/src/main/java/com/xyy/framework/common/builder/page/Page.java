package com.xyy.framework.common.builder.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 分页
 *
 * @author chentudong
 * @date 2019/9/14 13:35
 * @since 1.0
 */
public class Page<T>
{
    /**
     * page
     */
    private Integer page;
    /**
     * size
     */
    private Integer size;
    /**
     * totalPage
     */
    private Integer totalPage;
    /**
     * totalCount
     */
    private Long totalCount;

    /**
     * result
     */
    private List<T> result = Collections.emptyList();

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

    public List<T> getResult()
    {
        return result;
    }

    public void setResult(List<T> result)
    {
        this.result = result;
    }

    /**
     * 初始化分页对象
     *
     * @param page page
     * @param size size
     * @param <T>  <T>
     * @return Page<T>
     */
    public static <T> Page<T> init(Integer page, Integer size)
    {
        Page<T> result = new Page<>();
        result.setTotalCount(0L);
        result.setPage(Objects.isNull(page) ? 0 : size);
        result.setSize(Objects.isNull(size) ? 10 : size);
        result.setTotalPage(0);
        result.setResult(new ArrayList<T>());
        return result;
    }
}
