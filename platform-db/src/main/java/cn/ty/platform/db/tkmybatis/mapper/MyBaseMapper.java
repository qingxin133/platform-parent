package cn.ty.platform.db.tkmybatis.mapper;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @deprecated:继续了tkmybatis多个mapper的mapper基础
 * @author tianyang
 * @Date 2020-01-14 15:14:00
 */
public interface MyBaseMapper<T> extends BaseMapper<T>,ConditionMapper<T>,IdsMapper<T>,InsertListMapper<T>,ExampleMapper<T> {

}
