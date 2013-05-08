/**
 * 
 */
package com.jinyb.crawler.service.impl;

import java.util.List;

import com.jinyb.crawler.dao.WordDao;
import com.jinyb.crawler.entity.Word;
import com.jinyb.crawler.entity.Page;
import com.jinyb.crawler.service.WordService;

/**
 * @author jinyb09017
 *
 */
public class WordServiceImpl implements WordService {
	private WordDao wordDao;
	
	public void setWordDao(WordDao wordDao)
	{
		this.wordDao=wordDao;
	}


	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.WordService#addWord(com.hundsun.futures.entity.Word)
	 */
	public void addWord(Word word) {
		// TODO Auto-generated method stub
		wordDao.save(word);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.WordService#deleteWord(int)
	 */
	public void deleteWord(int id) {
		// TODO Auto-generated method stub
		wordDao.delete(id);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.WordService#updateWord(com.hundsun.futures.entity.Word)
	 */
	public void updateWord(Word word) {
		// TODO Auto-generated method stub
		wordDao.update(word);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.WordService#queryById(int)
	 */
	public Word queryById(int id) {
		// TODO Auto-generated method stub
		return wordDao.queryById(id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.WordService#ListWord()
	 */
	public List<Word> ListWord() {
		// TODO Auto-generated method stub
		return wordDao.queryList();
	}

	public List<Word> ListWordByColumnId(int id) {
		// TODO Auto-generated method stub
		return wordDao.queryByCondition("from Word word where word.tid=?",id);
	}


	@Override
	public List<Word> ListWordByConditon(String con, Object[] o) {
		// TODO Auto-generated method stub
		return wordDao.queryByConditions(con, o);
	}


	@Override
	public List<Object> ListWordByConditions(String con) {
		// TODO Auto-generated method stub
		return wordDao.queryColumn(con);
	}


	@Override
	public List<Word> ListByPage(Page page, String hql) {
		// TODO Auto-generated method stub
		return wordDao.pageQuery(page, hql);
	}


	@Override
	public Page getPage(String hql, Object[] obj) {
		// TODO Auto-generated method stub
		List<Word> wordList=wordDao.queryByConditions(hql, obj);
		Page page=new Page();
		page.setAllRows(wordList.size());
		page.setCurrentPage(1);
		int pageTotal=wordList.size()%page.getPageSize()==0?wordList.size()/page.getPageSize():wordList.size()/page.getPageSize()+1;
		page.setTotalPage(pageTotal);
		page.init();
		return page;
	}

}
