/**
 * 
 */
package com.hundsun.jinyb.searchIndex;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import com.jinyb.crawler.analyzer.ICTCLASAnalyzer;



/**
 * @author Administrator
 *
 */
public class MySearch {
	private Directory directory;
	
	
	 public Directory getDirectory() {
		return directory;
	}


	public void setDirectory(Directory directory) {
		this.directory = directory;
	}


	public void searchFile() 
	    {
	    	IndexReader ireader=null;
			try {
				ireader = IndexReader.open(directory);
				IndexSearcher isearcher = new IndexSearcher(ireader);
			    // Parse a simple query that searches for "text":
			    QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, "content", new ICTCLASAnalyzer());
			    Query query = parser.parse("’…∑Ú");
			    ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
			   // assertEquals(1, hits.length);
			    // Iterate through the results:
			    for (int i = 0; i < hits.length; i++) {
			      Document hitDoc = isearcher.doc(hits[i].doc);
			  //    assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
			      System.out.println(hitDoc.toString());
			    }
			    isearcher.close();
			    ireader.close();
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // read-only=true
			
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		   

	    }

}
