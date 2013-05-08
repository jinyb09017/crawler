/**
 * 
 */
package com.jinyb.crawler.entity;

/**
 * @author jinyb09017
 *
 */
public class Page {
	private	int	pageSize=20;//Ĭ�Ϸ�ҳ��Ϊ10
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
		return currentPage!=1;//�����ǰҳ��Ϊ1�Ļ����϶�û��ǰҳ��
	}
	/**
	 * @param hasPrePage the hasPrePage to set
	 */
	/**
	 * @return the hasNextPage
	 */
	public boolean isHasNextPage() {
		return currentPage!=totalPage&&totalPage!=0;//��������һҳ�ˣ��϶�û�к�ҳ��.�����ų�ҳ��Ϊ0��ʱ��
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
		return pageSize*(currentPage-1);//��Ϊ��ʼ��¼�Ǵ�0��ʼ��
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
