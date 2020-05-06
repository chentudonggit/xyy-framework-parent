package com.xyy.framework.mybaites.plus.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyy.framework.mybaites.plus.domain.base.BaseEntity;
import com.xyy.framework.mybaites.plus.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SuperServiceImpl
 *
 * @author chentudong
 * @date 2020/5/6 23:01
 * @since 1.0
 */
public class SuperServiceImpl<M extends BaseMapper<T>, T extends BaseEntity<T>> extends ServiceImpl<M, T> implements SuperService<T>
{
    @Autowired
    protected M mapper;
}
