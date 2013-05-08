/**
 * 
 */
package com.jinyb.crawler.service;

import java.util.List;



import com.jinyb.crawler.entity.Word;
import com.jinyb.crawler.entity.Page;

/**
 * @author jinyb09017
 *
 */
public interface WordService {
   public abstract void addWord(Word word);
	
	public abstract void deleteWord(int id);
	
	public abstract void updateWord(Word word);
	
	public abstract Word queryById(int id);
	
	public abstract List<Word> ListWord();
	
	public abstract List<Word> ListWordByColumnId(int id);
	
	public abstract List<Word> ListWordByConditon(String con,Object[] o);
	
	public abstract List<Object> ListWordByConditions(String con);
	
    public abstract List<Word> ListByPage(Page page,String hql);
	
	public abstract Page getPage(String hql,Object[] obj);
	
}
