package cn.ty.platform.common.model.page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.github.pagehelper.Page;

import cn.ty.platform.common.exception.PlatformException;
import cn.ty.platform.common.model.msg.ResultStatusBase;

/**
 * 分页基础实体
 * @description:
 * @author: tianyang
 * @date: 2020年1月14日 下午3:09:35
 */
public class PageVO<T> implements Serializable {



	private static final long serialVersionUID = 8753937910389557380L;
	/**
	 * 默认页码
	 */
	private static final int DEFAULT_PAGE_INDEX = 1;
	/**
	 * 默认行数
	 */
	private static final int DEFAULT_PAGE_COUNT= 10;

	/**
	 * 页码
	 */
	private Integer page = DEFAULT_PAGE_INDEX;
	
	/**
	 * 行数
	 */
	private Integer count = DEFAULT_PAGE_COUNT;
	
	/**
	 * 总行数
	 */
	private long totalCount;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 排序
	 */
	private String orderStr;
	
	/**
	 * 数据集
	 */
	private List<T> pageData;
	
	/**
	 * 根据pageHelp查询的结果来分页
	 * @param list
	 */
	public void setPageData(List<T> list) {
		if(list instanceof Page) {
			Page pageHelp = (Page) list;
			if(pageHelp.getPages() != 0 && this.getPage() > pageHelp.getPages()) {
				throw new PlatformException(ResultStatusBase.PAGE_ERROR);
			}
			
			this.setPage(pageHelp.getPageNum()>0?pageHelp.getPageNum():1);
			this.setCount(pageHelp.getPageSize());
			this.setTotalPage(pageHelp.getPages());
			this.setTotalCount(pageHelp.getTotal());
			this.pageData= list;
		}else if(list instanceof Collection) {
			this.setPage(DEFAULT_PAGE_INDEX);
			this.setCount(DEFAULT_PAGE_COUNT);
			this.setTotalPage(1);
			this.setTotalCount(list.size());
			this.pageData = list;
		}
	}
	
	/**
	 * 根据传入data自行处理分页
	 * @param list
	 * @param pageVO
	 */
	public void setPageData(List<T> list,PageVO pageVO) {
		if(pageVO!=null) {
			this.page = pageVO.getPage();
			this.count = pageVO.getCount();
			this.totalCount = pageVO.getTotalCount();
			Long totalPage = 1l;
			if(totalCount%count >0) {
				totalPage = totalCount/count+1;
			}else {
				totalPage = totalCount/count;
			}
			this.totalPage = totalPage.intValue();
		}
		this.pageData = list;
	}
	
	public List<T> getPageData() {
		return pageData;
	}

	
	public Integer getPage() {
		return page;
	}
	
	/**
	 * 非分页类型的data自行传入
	 * @param list
	 */
	public void setVData(List list) {
		this.pageData = list;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
 
	
}
