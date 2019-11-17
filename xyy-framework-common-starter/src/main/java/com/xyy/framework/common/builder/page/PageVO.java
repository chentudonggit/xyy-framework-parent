package com.xyy.framework.common.builder.page;

import com.xyy.framework.common.builder.AbstractPageVO;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 分页
 *
 * @author chentudong
 * @date 2019/9/14 13:35
 * @since 1.0
 */
public class PageVO<T> extends AbstractPageVO<T>
{
    /**
     * 初始化分页对象
     *
     * @param page page
     * @param size size
     * @return Page<T>
     */
    @Override
    public PageVO<T> init(Integer page, Integer size)
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
