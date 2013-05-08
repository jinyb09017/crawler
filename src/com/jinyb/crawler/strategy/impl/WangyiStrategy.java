package com.jinyb.crawler.strategy.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.strategy.ParserStrategy;
public class WangyiStrategy implements ParserStrategy {
	private String prefix="http://news.163.com";
	private Parser parser;
	private NodeList nodelist = null;

	@Override
	public News parseWeb(String web) {
		// TODO Auto-generated method stub
		News n=new News();
		String contentPart=getContentPart(web);
		n.setNewsTitle(getTitle(web));
		n.setNewsContent(getContent(contentPart));
		n.setNewsPublishtime(getDate(contentPart));
		n.setNewsResource(getResource(contentPart));
		n.setNewsSite("网易新闻");
		return n;
	}


	public String getContentPart(String html)// 获得正文部分的内容
	{

		StringBuffer sb = new StringBuffer(html);
		sb = ParserContext.delTag("script", sb);// 去除标签script的内容
		sb = ParserContext.delTag("style", sb);
		parser = Parser.createParser(new String(sb), "gbk");
		NodeFilter filterDiv = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("class", "ep-content-main"));
		try {
			nodelist = parser.parse(filterDiv);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String contentPart = nodelist.toHtml();
		return contentPart;

	}

	public String getContent(String contentPart)// 获得正文内容
	{
		parser = Parser.createParser(contentPart, "gbk");
		NodeFilter filterDiv = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("id", "endText"));
		// NodeFileter filterPa=new Fileter(new TagNameFilter("p"));
		try {
			nodelist = parser.parse(filterDiv);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  //  System.err.println("++++++++++++++++++++++++++++++"+nodelist.toHtml()+"++++++++++++++++++");
		Node node = nodelist.elementAt(0);
		nodelist = node.getChildren();
		Node[] nodes = nodelist.toNodeArray();
		StringBuffer line = new StringBuffer();

		for (int i = 0; i < nodes.length; i++) {
			if ((nodes[i] instanceof ParagraphTag)) {

				line.append(nodes[i].toPlainTextString().trim() + "\r\n");//取得纯文本

			}

		}

		return line.toString().replace("&nbsp;", "");//去年网页空格
	}

	public String getTitle(String html) {
		parser = Parser.createParser(html, "gbk");
		HtmlPage visitor = new HtmlPage(parser);
		try {
			parser.visitAllNodesWith(visitor);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = visitor.getTitle();
		title = title.substring(0, title.indexOf("_"));
		// System.out.println("这是文章的标题："+textInPage);
		// NodeList nodelist;
		// nodelist = visitor.getBody();
		return title;
	}

	public String getResource(String contentPart) {
		parser = Parser.createParser(contentPart, "gbk");
		NodeFilter filterDiv = new AndFilter(new TagNameFilter("a"),
				new HasAttributeFilter("rel", "nofollow"));
		try {
			nodelist = parser.parse(filterDiv);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Node[] nodes = nodelist.toNodeArray();

		String resource = nodes[0].toPlainTextString();
		//resource=resource.substring(resource.indexOf("："),resource.length());//处理掉“本文来源”
		return resource;

	}

	public Date getDate(String contentPart) {
		parser = Parser.createParser(contentPart, "gbk");
		NodeFilter filterDiv = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("class", "left"));
		try {
			nodelist = parser.parse(filterDiv);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Node[] nodes = nodelist.toNodeArray();

		String date =nodes[0].toPlainTextString();

		System.out.println(date);
        date=date.substring(0,date.length()).trim();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;

	}

//	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm");
//		Date date = sdf.parse("2013年04月05日05:40");
//		System.out.println(date.toLocaleString());
//	}


	@Override
	public List<String> getCrawlUrl(String html) {
		// TODO Auto-generated method stub
				List<String> list = new ArrayList<String>();
				String regUrl = prefix
						+ "/[0-9]{2}/[0-9]{4}/[0-9]{2}/\\w+.shtml";
				Pattern p = Pattern.compile(regUrl, Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(html);
				boolean blnp = m.find();
				// int i = 0;
				while (blnp)// 多次匹配查找
				{
					if(!list.contains(m.group(0)))//如果不包含就增加，去除重复的url
					{
						list.add(m.group(0));
					}
					html = html.substring(m.end(), html.length());
					m = p.matcher(html);
					blnp = m.find();
				}

				return list;
			}

			public static void main(String[] args) {
				String prefix = "http://news.163.com";
				String regUrl = prefix
						+ "/[0-9]{2}/[0-9]{4}/[0-9]{2}/\\w+.html";
				Pattern p = Pattern.compile(regUrl, Pattern.CASE_INSENSITIVE);
				Matcher m = p
						.matcher("http://news.163.com/13/0320/11/8QDGN70I00014JB6.html");
				if (m.find())
					System.out.println("right");
				else {
					System.out.println("wrong");
				}
			}

}
