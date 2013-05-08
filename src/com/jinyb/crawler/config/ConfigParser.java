package com.jinyb.crawler.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

;

public class ConfigParser {
	//static String dir=System.getProperty("user.dir");//
	private String dir="G:/eclipse/eclipse¹¤³Ì/ThemeCrawler/src/net/xicp/tarbitrary/xml/config.xml";
	
	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}
	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	public  Document getDocument(String dir)
	{
		try
		{
			
			SAXReader reader=new SAXReader();
			Document doc=reader.read(new File(dir));
			return doc;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public  Config getConfig()//
	{
		Config con=new Config();
		List seeds=new ArrayList();
		
		//String path=;
		//path=path+dir;
		Document doc=getDocument(dir);
		List list=doc.selectNodes("//config");
		Iterator it=list.iterator();
		while(it.hasNext())
		{
			Element element=(Element)it.next();
			Iterator iterator=element.elementIterator("seeds");
			while(iterator.hasNext())
			{
				Element el=(Element)iterator.next();
				Iterator siteIt=el.elementIterator();
				while(siteIt.hasNext())//
				{
					Element siteEl=(Element)siteIt.next();

					seeds.add(siteEl.getText());
					//map.put(siteEl.getName(), siteEl.getText());
				}
				con.setSeeds(seeds);
				
			}
			Iterator threadIt=element.elementIterator("thread");
			while(threadIt.hasNext())
			{
				Element el=(Element)threadIt.next();
				con.setThread(Integer.parseInt(el.getText()));
				
			}
			Iterator deepIt=element.elementIterator("deep");
			while(deepIt.hasNext())
			{
				Element el=(Element)deepIt.next();
				con.setDeep(Integer.parseInt(el.getText()));
				
			}
			Iterator dirIt=element.elementIterator("outputDir");
			while(dirIt.hasNext())
			{
				Element el=(Element)dirIt.next();
				con.setOutputDir(el.getText());
				
			}
			
		}
		return con;
		
	}
	public  void writeXml(Document doc) 
	{
		//String path=System.getProperty("user.dir");
	//	path=path+dir;

		XMLWriter out;
		try {
			out = new XMLWriter(new FileWriter(new File(dir)));
			out.write(doc);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public  void writeConfig(Config con)
	{
		List  seedsList=con.getSeeds();
//		String path=System.getProperty("user.dir");
//		path=path+dir;
		Document doc=getDocument(dir);
		List list=doc.selectNodes("//config");
		Iterator it=list.iterator();
		int i=0;
		while(it.hasNext())
		{
			Element element=(Element)it.next();
			Iterator iterator=element.elementIterator("seeds");
			
			while(iterator.hasNext())
			{
				Element el=(Element)iterator.next();
				element.remove(el);//
				
			}
			Element root=doc.getRootElement();
			Element seeds=root.addElement("seeds");
//			for(Object s:map.keySet().toArray())
//			{
//				Element el=seeds.addElement((String)s);
//				el.setText(map.get((String)s));
//				
//			}
			for(Object w:seedsList)
			{
				Element el=seeds.addElement("site"+i++);
				el.setText((String)w);
			}
			Iterator threadIt=element.elementIterator("thread");
			while(threadIt.hasNext())
			{
				Element el=(Element)threadIt.next();
				el.setText(con.getThread()+"");
				
			}
			Iterator deepIt=element.elementIterator("deep");
			while(deepIt.hasNext())
			{
				Element el=(Element)deepIt.next();
				el.setText(con.getDeep()+"");
				
			}
			Iterator dirIt=element.elementIterator("outputDir");
			while(dirIt.hasNext())
			{
				Element el=(Element)dirIt.next();
				el.setText(con.getOutputDir());
				
			}
			
		}
		writeXml(doc);
	}
	public static void main(String args[])
	{
	//	System.out.println(path.toString());
		//Config con=new ConfigParser().getConfig();
//		System.out.println(con.getDeep()+""+con.getThread()+""+con.getSeeds().get(0).getName()+con.getOutputDir());
//		for(WebSite w:con.getSeeds())
//		{
//			System.out.println("id"+w.getId()+"   -----name:"+w.getName());
//		}
		//Config con=new Config();
		//new ConfigParser().writeConfig(con);
		
	}

}
