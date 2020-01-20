package cn.ty.platform.db.tkmybatis.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import cn.ty.platform.common.annotation.ColumnLike;
import cn.ty.platform.common.annotation.ColumnOrder;
import cn.ty.platform.common.constant.PlatformConstantBase;
import cn.ty.platform.common.constant.exception.PlatformExceptionConstant;
import cn.ty.platform.common.exception.PlatformException;
import cn.ty.platform.common.exception.status.BaseStatusCode;
import cn.ty.platform.common.model.page.PageVO;
import cn.ty.platform.db.tkmybatis.mapper.MyBaseMapper;
import cn.ty.platform.db.tkmybatis.service.IBaseService;
import tk.mybatis.mapper.entity.Example;

/**
 * @description:通用CRUD的serviceImpl方法
 * @author: tianyang
 * @date: 2020年1月14日 下午5:04:09
 */
public class BaseServiceImpl<T> implements IBaseService<T> {

	/**
	 * 通用mapper
	 */
	@Autowired
	protected MyBaseMapper<T> mapper;

	/**
	 * 
	 */
	private StringBuffer columnValueSb;

	/**
	 * 注入T
	 * @return
	 */
	protected Class<?> getEntityClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		if (null == clazz) {
			throw new PlatformException(BaseStatusCode.DB_ERROR_IMP_ENTITY);
		}
		return clazz;
	}
	
	/***********************组装方法开始************************************/
	/**
	 * mysql like里面下划线问题解决方案
	 * @param value
	 * @return
	 */
	protected Object replaceMysqlUnderLine(Object value) {
		if (value != null && value.toString().indexOf("\\_") != -1) {
			value = value.toString().replace("_", "\\_");
		}
		return value;
	}

	/**
	 * 拼接属性值Like里面的字符串
	 * @param val
	 * @return
	 */
	protected String likeValue(Object val) {
		columnValueSb = new StringBuffer();
		columnValueSb.append(PlatformConstantBase.P_PERCENT).append(val).append(PlatformConstantBase.P_PERCENT);
		return columnValueSb.toString();
	}

	/**
	 * 模糊查询组装参数
	 * @param vClass
	 * @param entity
	 * @param criteria
	 * @throws IllegalAccessException
	 * @throws IllegalAccessException
	 */
	protected void makeEntityOrValue(Class<?> vClass, T entity, Example.Criteria criteria)
			throws IllegalAccessException, IllegalAccessException {
		Field[] fs = vClass.getDeclaredFields();
		if (fs == null)
			throw new PlatformException(BaseStatusCode.ERROR);
		for (Field f : fs) {
			f.setAccessible(Boolean.TRUE);
			if (f.isAnnotationPresent(javax.persistence.Column.class)) {
				String name = f.getName();
				Object val = f.get(entity);
				if (val != null && !val.toString().trim().equalsIgnoreCase(PlatformExceptionConstant.V_NULL)) {
					criteria.orLike(name, likeValue(val));
				}
			}
		}
	}

	/**
	 * 组装实体类的值
	 * 不同属性之间是and关系
	 * 注解@ColumnLike的属性会用like处理
	 * 注解@Column属性以等于处理
	 * @param vClass 实体class
	 * @param entity 实体
	 * @param criteria tk.mybatis.mapper.entity.Example.Criteria
	 * @throws IllegalAccessException
	 * @throws IllegalAccessException
	 */
	protected void makeEntityAndValue(Class<?> vClass, T entity, Example.Criteria criteria)
			throws IllegalAccessException, IllegalAccessException {
		Field[] fs = vClass.getDeclaredFields();
		if (fs == null)
			throw new PlatformException(BaseStatusCode.ERROR);
		for (Field f : fs) {
			f.setAccessible(Boolean.TRUE);
			String name = f.getName();
			Object val = f.get(entity);
			if (val != null && !val.toString().trim().equalsIgnoreCase(PlatformExceptionConstant.V_NULL)) {
				if (f.isAnnotationPresent(ColumnLike.class)) {
					val = replaceMysqlUnderLine(val);
					criteria.andLike(name, likeValue(val));
					continue;
				}
				if (f.isAnnotationPresent(javax.persistence.Column.class)) {
					criteria.andEqualTo(name, val);
					continue;
				}
			}
		}
	}

	/**
	 * 拼接排序字段
	 * 分为在pageVO对象当中的排序字段和注解当中的排序字段,两者不共用
	 * @param vClass
	 * @param page 如果没有传page,可以传null值使用
	 * @param example
	 */
	protected void makeEntityOrder(Class<?> vClass,PageVO page,Example example) {
		if(page!=null && StringUtils.isNotBlank(page.getOrderStr())) {
			example.setOrderByClause(page.getOrderStr());
			return;
		}
		for(Field f:vClass.getDeclaredFields()) {
			f.setAccessible(Boolean.TRUE);
			//如果是有ColumnOrder注解
			if(f.isAnnotationPresent(ColumnOrder.class)) {
				ColumnOrder columnOrder = f.getAnnotation(ColumnOrder.class);
				columnValueSb = new StringBuffer();
				columnValueSb.append(f.getName()).append(PlatformConstantBase.P_SPACE).append(columnOrder.orderType());
			    example.setOrderByClause(columnValueSb.toString());
			}
		}
		
	}
	
	
	/***********************组装方法结束************************************/

	/**
	 * 保存单个对象
	 */
	@Override
	public int saveBase(T entity) {
		return mapper.insertSelective(entity);
	}

	/**
	 * 删除单个对象
	 */
	@Override
	public int deleteBase(T entity) {
		return mapper.delete(entity);
	}

	/**
	 * 修改
	 */
	@Override
	public int updateBase(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 获取单个对象
	 */
	@Override
	public T get(T entity) {
		return mapper.selectOne(entity);
	}

	/**
	 * 查询列表
	 */
	@Override
	public List<T> listBase(T entity) throws IllegalAccessException{
		Class<?> vClass = getEntityClass();
		Example example = new Example(vClass);
		Example.Criteria criteria = example.createCriteria();
		
		//值
		makeEntityAndValue(vClass, entity, criteria);
		//排序
		makeEntityOrder(vClass,null,example);
		List<T> list = mapper.selectByExample(example);
		return list;
	}

	/**
	 * 查询列表-分页
	 */
	@Override
	public PageVO<T> listPageBase(PageVO page, T entity) throws IllegalAccessException {
		if(page==null) {
			page = new PageVO<T>();
		}
		Class<?> vClass = getEntityClass();
		Example example = new Example(vClass);
		Example.Criteria criteria = example.createCriteria();
		
		//值
		makeEntityAndValue(vClass, entity, criteria);
		//排序
		makeEntityOrder(vClass,page,example);
		List<T> list = mapper.selectByExample(example);
		page.setPageData(list);
		return page;
	}

	/**
	 * 批量插入实体
	 */
	@Override
	public boolean insertList(List<T> list) {
		return mapper.insertList(list)>0?true:false;
	}

}
