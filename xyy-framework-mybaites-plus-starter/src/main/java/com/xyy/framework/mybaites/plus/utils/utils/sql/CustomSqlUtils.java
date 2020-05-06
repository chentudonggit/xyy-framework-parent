package com.xyy.framework.mybaites.plus.utils.utils.sql;

import com.baomidou.mybatisplus.plugins.Page;
import com.xyy.framework.common.builder.page.PageVO;
import com.xyy.framework.common.helper.BeanHelper;
import com.xyy.framework.common.utils.assertion.AssertUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CustomSqlUtils
 *
 * @author chentudong
 * @date 2020/3/28 12:30
 * @since 1.0
 */
public class CustomSqlUtils
{
    /**
     * 实体转换
     *
     * @param s      源
     * @param tClass 目标
     * @param <T>    T
     * @param <S>    S
     */
    public static <T, S> PageVO<T> convert(Page<S> s, Class<T> tClass)
    {
        AssertUtils.isNull(s, "page is null");
        return convert(s.getCurrent(), s.getSize(), s.getPages(), s.getTotal(), s.getRecords(), tClass);
    }

    /***
     * convert
     * @param current current
     * @param pageSize pageSize
     * @param pages pages
     * @param total total
     * @param records records
     * @param tClass tClass
     * @param <T> <T>
     * @param <S> <S>
     * @return PageVO<T>
     */
    public static <T, S> PageVO<T> convert(Integer current, Integer pageSize, Integer pages, Integer total, List<S> records, Class<T> tClass)
    {
        PageVO<T> result = new PageVO<>();
        result.setData(new ArrayList<>());
        if (Objects.nonNull(records))
        {
            for (S record : records)
            {
                result.getData().add(BeanHelper.convert(record, tClass));
            }
        }
        result.setPage(AssertUtils.isNullReturnParam(current, 0));
        result.setSize(AssertUtils.isNullReturnParam(pageSize, 10));
        result.setTotalPage(pages);
        total = AssertUtils.isNullReturnParam(total, 0);
        result.setTotalCount(Long.valueOf(total));
        return result;
    }
}
