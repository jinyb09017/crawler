package com.hundsun.jinyb.action;

import java.util.List;

import com.jinyb.crawler.constants.Constant;
import com.jinyb.crawler.dao.WordDao;
import com.jinyb.crawler.entity.Word;
import com.jinyb.crawler.entity.Page;
import com.jinyb.crawler.service.WordService;

public class WordAction extends BaseAction {
	private Word word;
	private WordService wordService;
	private WordDao wordDao;
	public WordDao getWordDao() {
		return wordDao;
	}
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	private Page page;
	
	/**
	 * @return the wordService
	 */
	public WordService getWordService() {
		return wordService;
	}
	/**
	 * @param wordService the wordService to set
	 */
	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}
	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
	public String list()
	{
		String hql="from Word";
		
		Page p=wordService.getPage(hql,new Object[]{});// this two hql are different
		if(page!=null)//first to list page when page is null.and initiate the page;
		{
			p.setCurrentPage(page.getCurrentPage());
		}
		
		
		System.out.println(p);
		List<Word> wordList=wordService.ListByPage(p, hql);
		httpRequest.setAttribute("page", p);
	    httpRequest.setAttribute("wordList", wordList);
	    return "list";
	}
	
	/**
	 * @return the word
	 */
	public Word getWord() {
		return word;
	}
	/**
	 * @param word the word to set
	 */
	public void setWord(Word word) {
		this.word = word;
	}
	/**
	 * @return the newService
	 */

	

}
