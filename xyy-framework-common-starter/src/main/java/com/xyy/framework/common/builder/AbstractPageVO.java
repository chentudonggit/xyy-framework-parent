package com.xyy.framework.common.builder;

import com.alibaba.fastjson.annotation.JSONField;
import com.xyy.framework.common.builder.page.PageVO;

import java.util.Collection;

/**
 * AbstractPageVO
 *
 * @author chentudong
 * @date 2019/11/17 12:06
 * @since 1.0
 */
public abstract class AbstractPageVO<T>
{
    /**
     * page
     */
    @JSONField(name = "page")
    protected Integer page;
    /**
     * size
     */
    @JSONField(name = "size")
    protected Integer size;
    /**
     * totalPage
     */
    @JSONField(name = "total_page")
    protected Integer totalPage;
    /**
     * totalCount
     */
    @JSONField(name = "total_count")
    protected Long totalCount;

    /**
     * result
     */
    @JSONField(name = "data")
    protected Collection<T> data;

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
        totalPage = this.getSize() == 0 ? 1 : (int) Math.ceil((double) this.totalCount / (double) this.getSize());
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
     * 初始化
     *
     * @param page page
     * @param size size
     * @return AbstractPageVO<T>
     */
    protected abstract PageVO<T> init(Integer page, Integer size);
}
