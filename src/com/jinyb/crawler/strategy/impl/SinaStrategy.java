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
public class SinaStrategy implements ParserStrategy {
	private String prefix = "http://news.sina.com.cn";
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
		n.setNewsSite("��������");
		return n;
	}

	@Override
	public List<String> getCrawlUrl(String html) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		String regUrl = prefix
				+ "/(c|s)/([0-9]{4})-([0-9]{2})-([0-9]{2})/([0-9]+).shtml";
		Pattern p = Pattern.compile(regUrl, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(html);
		boolean blnp = m.find();
		// int i = 0;
		while (blnp)// ���ƥ�����
		{
			if(!list.contains(m.group(0)))//��������������ӣ�ȥ���ظ���url
			{
				list.add(m.group(0));
			}
			html = html.substring(m.end(), html.length());
			m = p.matcher(html);
			blnp = m.find();
		}

		return list;
	}

	public String getContentPart(String html)// ������Ĳ��ֵ�����
	{

		StringBuffer sb = new StringBuffer(html);
		sb = ParserContext.delTag("script", sb);// ȥ����ǩscript������
		sb = ParserContext.delTag("style", sb);
		parser = Parser.createParser(new String(sb), "gbk");
		NodeFilter filterDiv = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("class", "blkContainerSblk"));
		try {
			nodelist = parser.parse(filterDiv);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String contentPart = nodelist.toHtml();
		return contentPart;

	}

	public String getContent(String contentPart)// �����������
	{
		parser = Parser.createParser(contentPart, "gbk");
		NodeFilter filterDiv = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("id", "artibody"));
		// NodeFileter filterPa=new Fileter(new TagNameFilter("p"));
		try {
			nodelist = parser.parse(filterDiv);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   // System.err.println("++++++++++++++++++++++++++++++"+nodelist.toHtml()+"++++++++++++++++++");
		Node node = nodelist.elementAt(0);
		nodelist = node.getChildren();
		Node[] nodes = nodelist.toNodeArray();
		StringBuffer line = new StringBuffer();

		for (int i = 0; i < nodes.length; i++) {
			if ((nodes[i] instanceof ParagraphTag)) {

				line.append(nodes[i].toPlainTextString().trim() + "\r\n");

			}

		}

		return line.toString().replace("&nbsp;", "");//ȥ����ҳ�ո�
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
		// System.out.println("�������µı��⣺"+textInPage);
		// NodeList nodelist;
		// nodelist = visitor.getBody();
		return title;
	}

	public String getResource(String contentPart) {
		parser = Parser.createParser(contentPart, "gbk");
		NodeFilter filterDiv = new AndFilter(new TagNameFilter("span"),
				new HasAttributeFilter("id", "media_name"));
		try {
			nodelist = parser.parse(filterDiv);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Node[] nodes = nodelist.toNodeArray();

		String resource = null;
		for (int i = 0; i < nodes.length; i++) {

			Node node = nodes[i];
			resource = node.toPlainTextString();

		}
		return resource;

	}

	public Date getDate(String contentPart) {
		parser = Parser.createParser(contentPart, "gbk");
		NodeFilter filterDiv = new AndFilter(new TagNameFilter("span"),
				new HasAttributeFilter("id", "pub_date"));
		try {
			nodelist = parser.parse(filterDiv);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Node[] nodes = nodelist.toNodeArray();

		String date = null;
		for (int i = 0; i < nodes.length; i++) {

			Node node = nodes[i];
			date = node.toPlainTextString();

		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HH:mm");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;

	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HH:mm");
		Date date = sdf.parse("2013��04��05��05:40");
		System.out.println(date.toLocaleString());
	}

}
