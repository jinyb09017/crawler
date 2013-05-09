/**
 * 
 */
package com.hundsun.jinyb.action;

import java.util.Date;
import java.util.List;
import com.jinyb.crawler.dao.ArticleDao;
import com.jinyb.crawler.dao.ColumnDao;
import com.jinyb.crawler.entity.Article;
import com.jinyb.crawler.entity.Column;



/**
 * @author jinyb09017
 *
 */
public class ArticleAction extends BaseAction{
	private ArticleDao articleDao;
	private ColumnDao columnDao;
	private Article article;
	private Column column;
	private List<Column> columnList;
	public void setArticle(Article article)
	{
		this.article = article;
	}
	public Article getArticle()
	{
		return article;
	}

	/**
	 * @return the column
	 */
	public Column getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(Column column) {
		this.column = column;
	}
	public ArticleDao getArticleDao() {
		return articleDao;
	}
	/**
	 * @param articleDao the articleDao to set
	 */
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
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

		List<Article> articleList=null;
		columnList = columnDao.queryList();
		if(null!=column&&null!=column.getId())
		{
			articleList = articleDao.queryByCondition("from Article a where a.tid="+column.getId());
		}
		else
		{
            articleList = articleDao.queryList();
		}
		httpRequest.setAttribute("articleList", articleList);
		httpRequest.setAttribute("columnList", columnList);
		return "list";
		
		
			
		
	}
	public String edit()
	{
		System.out.println("edit输出"+article);
		Article n=articleDao.queryById(this.article.getId());
		httpRequest.setAttribute("article", n);
		String clmn=columnDao.queryById(n.getTid()).getColumn();
		httpRequest.setAttribute("clmn", clmn);
		return	"edit";
	}
	public String del()
	{
		System.out.println("是否已经得到articleid"+article.getId());
		articleDao.delete(article.getId());
		return	"del";
	}
	public String forwardAdd()
	{
		columnList = columnDao.queryList();
		httpRequest.setAttribute("columnList", columnList);
		return "forwardAdd";
	}
	public String add()
	{


		this.article.setHits(0);
		this.article.setTime(new Date());
		this.article.setAid(1);
		articleDao.save(this.article);
		return "add";
	}
	public String upd()
	{
		Article n=articleDao.queryById(this.article.getId());
		n.setContent(this.article.getContent());
		n.setTitle(this.article.getTitle());
		n.setAid(1);//用户的修改,栏目不能修改
		n.setTime(new Date());
		n.setRecomend(this.article.isRecomend());
		articleDao.update(n);
		return "upd";
	}
	public String detail()
	{
		Article n=articleDao.queryById(article.getId());
		httpRequest.setAttribute("article", n);
		return "detail";
	}

}
