package cn.ty.platform.db.tkmybatis.conntroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import cn.ty.platform.common.model.msg.ResultMsg;
import cn.ty.platform.common.model.page.PageVO;
import cn.ty.platform.db.tkmybatis.service.IBaseService;

/**
 * @description: 单表CRUD通用CONTROLLER
 * @author: tianyang   
 * @date: 2020年1月15日 下午12:17:24
 */
public class BaseController<T> {
	
	@Autowired
	private IBaseService<T> baseService;
	
	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	@PostMapping(value="/save",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResultMsg<T>  saveBase(T entity){
		return ResultMsg.success(baseService.saveBase(entity));
	}

	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	@PostMapping(value="/delete",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResultMsg<T> deleteBase(T entity){
		return ResultMsg.success(baseService.deleteBase(entity));
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@PostMapping(value="/update",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResultMsg<T> updateBase(T entity){
		return ResultMsg.success(baseService.updateBase(entity));
	}
	
	
	/************************查询*******************************/
	/**
	 * 实体单个信息查询
	 * @param entity
	 * @return
	 */
	@PostMapping(value="/get",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResultMsg<T> get(T entity){
		return ResultMsg.success(baseService.get(entity));
	}
	
	/**
	 * 实体列表信息查询
	 * 属性加@ColumnLike模糊匹配,多个属性关系为and
	 *属性加@ColumnOrder默认排序字段orderType(asc,desc)默认desc
	 *@param entity
	 *@return
	 * @throws IllegalAccessException 
	 */
	@PostMapping(value="/list",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResultMsg<T> listBase(T entity)  throws IllegalAccessException{
		return ResultMsg.success(baseService.listBase(entity));
	}
	
	/**
	 * 分页查询
	 * 属性加@ColumnLike模糊匹配,多个属性关系为and
	 * 属性加@ColumnOrder默认排序字段orderType(asc,desc)默认desc
	 * @param page 分页实体类,前端可以通过传入page.Order实现排序字段
	 * @param entity
	 * @return
	 * @throws IllegalAccessException 
	 */
	@PostMapping(value="/listPage",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResultMsg<T> listPageBase(PageVO page,T entity) throws IllegalAccessException{
		return ResultMsg.success(baseService.listPageBase(page,entity));
	}
	
	/*********************批量操作s****************************/
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	@PostMapping(value="/insertList",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResultMsg<T> insertList(List<T> entity) {
		return ResultMsg.success(baseService.insertList(entity));
	}

}
