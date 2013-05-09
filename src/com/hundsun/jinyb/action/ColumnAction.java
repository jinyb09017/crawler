/**
 * 
 */
package com.hundsun.jinyb.action;

import java.util.List;

import com.jinyb.crawler.dao.ColumnDao;
import com.jinyb.crawler.entity.Column;



/**
 * @author jinyb09017
 *
 */
public class ColumnAction extends BaseAction{
	private Column myCol;
	private ColumnDao columnDao;
//	private Integer id;//������Ҫһ��setid���������url��������id�����������޷���ð���
	private String column;
public Column getMyCol() {
		return myCol;
	}
	public String getColumn() {
		return column;
	}
	//	public void setId(Integer id)
//	{
//		this.id=id;
//	}
	public void setColumn(String column)
	{
		this.column=column;
	}
//	
	/**
	 * @return the column
	 */
//	public Column getMycol() {
//		return myCol;
//	}
	/**
	 * @param column the column to set
	 */
	public void setMyCol(Column myCol) {
		this.myCol = myCol;
	}
	/**
	 * @return the columnDao
	 */
	public ColumnDao getColumnDao() {
		return columnDao;
	}
	/**
	 * @param columnDao the columnDao to set
	 */
	public void setColumnDao(ColumnDao columnDao) {
		this.columnDao = columnDao;
	}
	
	public String list()
	{
		List<Column> columnList=columnDao.queryList();
		httpRequest.setAttribute("columnList", columnList);
		return "list";
	}
	public String upd()
	{

		this.myCol.setColumn(this.column);

		columnDao.update(this.myCol);
		return "upd";
	}
	public String del()
	{
		//���Ҳ���Դ�ע���user�Ƕ���getId������ô�������idֵ��
		columnDao.delete(this.myCol.getId());
		return "del";
	}
	public String search()
	{
		Column col=columnDao.queryById(this.myCol.getId());
		httpRequest.setAttribute("col", col);
		return "edit";
	}
	public String add()
	{
//		Column col=new Column();
//		col.setColumn(this.column);
		System.out.println(this.myCol);
		columnDao.save(this.myCol);
		return "add";
	}
	

}
