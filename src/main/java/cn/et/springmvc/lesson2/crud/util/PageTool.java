package cn.et.springmvc.lesson2.crud.util;

import java.util.List;

public class PageTool {
	/**
	 * 初始化参数
	 * @param curPage  页面传入的当前页
	 * @param totalCount  数据库查询的总记录数
	 * @param pageCount	每页显示的条数
	 */
	public PageTool(Integer curPage,Integer totalCount,Integer pageCount) {
		//当前页
		this.curPage=curPage;
		//总记录数（从数据库查询）
		this.totalCount=totalCount;
		//每页显示条数 默认是10条数据
		this.pageCount=pageCount==null?this.pageCount:pageCount;
		//上一页
		this.prePage=(curPage==1?1:curPage-1);
		//总页数
		this.totalPage=totalCount%this.pageCount==0?totalCount/this.pageCount:totalCount/this.pageCount+1;
		//下一页
		this.nextPage=(curPage==totalPage)?totalPage:(curPage+1);
		//开始索引
		this.startIndex=(curPage-1)*this.pageCount+1;
		//结束索引
		this.endIndex=curPage*this.pageCount ; 
	}

	/**
	 * 上一页
	 * 判断 只要当前页不是第一页 就把当前页减一 ，如果当前页是第一页它的上一页也就是当前页
	 * prePage=(curPage==1?1:curPage-1);
	 * 举例:
	 * 如果当前页是第三页它的上一页就是第二页，如果当前页是第二页它的上一页就是第一页，如果当前页是第一页它的上一页也就是当前页（也就是第一页）
	 * curPage(当前页)		prePage(上一页)
	 * 		3		   			2
	 * 		2		   			1
	 * 		1		   			1
	 */
	private Integer prePage;
	
	/**
	 * 当前页 （动态的 由页面传递的）
	 */
	private Integer curPage;
	
	/**
	 * 下一页
	 * 判断 只要当前页不是最后一页 就把当前页加一，如果当前页是最后一页它的下一页也就是最后一页（总页数）
	 * nextPage=(curPage==totalPage)?totalPage:(curPage+1);
	 * curPage(当前页)		totalPage(总页数)		nextPage(下一页)
	 * 		1					3					2
	 * 		2					3					3
	 * 		3					3					3
	 */
	private Integer nextPage;
	
	/**
	 * 每页显示条数 默认是10条数据
	 */
	private Integer pageCount=10;
	
	/**
	 * 总页数
	 * totalPage=totalCount%pageCount==0?totalCount/pageCount:totalCount/pageCount+1;
	 * pageCount(每页显示的条数)			totalCount(总记录数(数据库表中的总共有多少条数据))      totalPage总页数
	 * 		10							20											2
	 * 		10							21											3
	 */
	private Integer totalPage;	
	
	/**
	 * 总记录数（从数据库查询）
	 */
	private Integer totalCount;
	
	/**
	 * 存储最终查询的数据
	 */
	private List data;
	
	/**
	 * 开始索引
	 * startIndex=(curPage-1)*pageCount+1;
	 * endIndex=curPage*pageCount;
	 * 例:
	 *	curPage(当前页)		pageCount(每页显示条数)		startIndex(开始索引)----endIndex(结束索引)
	 *		1 					10						1	------	10
	 *		2					10						11	------	20
	 */
	private Integer startIndex;
	
	/**
	 * 结束索引
	 * endIndex=curPage*pageCount;
	 */
	private Integer endIndex;

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	@Override
	public String toString() {
		return "PageTool [prePage=" + prePage + ", curPage=" + curPage + ", nextPage=" + nextPage + ", pageCount="
				+ pageCount + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", data=" + data
				+ ", startIndex=" + startIndex + ", endIndex=" + endIndex + "]";
	}
}
