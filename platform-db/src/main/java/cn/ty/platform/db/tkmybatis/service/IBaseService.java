package cn.ty.platform.db.tkmybatis.service;

import java.util.List;

import cn.ty.platform.common.model.page.PageVO;

public interface IBaseService<T> {
	
	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	int saveBase(T entity);

	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	int deleteBase(T entity);
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	int updateBase(T entity);
	
	
	/************************查询*******************************/
	/**
	 * 实体单个信息查询
	 * @param entity
	 * @return
	 */
	T get(T entity);
	
	/**
	 * 实体列表信息查询
	 * 属性加@ColumnLike模糊匹配,多个属性关系为and
	 *属性加@ColumnOrder默认排序字段orderType(asc,desc)默认desc
	 *@param entity
	 *@return
	 * @throws IllegalAccessException 
	 */
	List<T> listBase(T entity)  throws IllegalAccessException;
	
	/**
	 * 分页查询
	 * 属性加@ColumnLike模糊匹配,多个属性关系为and
	 * 属性加@ColumnOrder默认排序字段orderType(asc,desc)默认desc
	 * @param page 分页实体类,前端可以通过传入page.Order实现排序字段
	 * @param entity
	 * @return
	 * @throws IllegalAccessException 
	 */
	PageVO<T> listPageBase(PageVO page,T entity) throws IllegalAccessException;
	
	/*********************批量操作s****************************/
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	boolean insertList(List<T> list);
	
}
