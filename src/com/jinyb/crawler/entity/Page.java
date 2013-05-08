/**
 * 
 */
package com.jinyb.crawler.entity;

/**
 * @author jinyb09017
 *
 */
public class Page {
	private	int	pageSize=20;//默认分页数为10
	private	int totalPage;
	private int allRows;
	private int currentPage;
	
	private boolean hasPrePage;
	private boolean hasNextPage;
//	private boolean isFirst;
//	private boolean isLast;
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * @return the allRows
	 */
	public int getAllRows() {
		return allRows;
	}
	/**
	 * @param allRows the allRows to set
	 */
	public void setAllRows(int allRows) {
		this.allRows = allRows;
	}
	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the hasPrePage
	 */
	public boolean isHasPrePage() {
		return currentPage!=1;//如果当前页面为1的话，肯定没有前页了
	}
	/**
	 * @param hasPrePage the hasPrePage to set
	 */
	/**
	 * @return the hasNextPage
	 */
	public boolean isHasNextPage() {
		return currentPage!=totalPage&&totalPage!=0;//如果是最后一页了，肯定没有后页了.并且排除页数为0的时候。
	}
	/**
	 * @param hasPrePage the hasPrePage to set
	 */
	public void init()
	{
		this.hasNextPage = isHasNextPage();
		this.hasPrePage = isHasPrePage();
	}
	/**
	 * @param hasNextPage the hasNextPage to set
	 */
	public  int getOffsetPage()
	{
		return pageSize*(currentPage-1);//因为初始记录是从0开始的
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", allRows=" + allRows + ", currentPage=" + currentPage
				+ ", hasPrePage=" + hasPrePage + ", hasNextPage=" + hasNextPage
				+ "]";
	}
	

}
