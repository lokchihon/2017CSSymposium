package webCrawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class finalSearch {

	public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=aapl+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	public static void main(String[] args) throws IOException {
	System.out.println("APPLE NEWS");
	String searchTerm = "AAPL";
	int num = 10;
		
	String searchURL = GOOGLE_SEARCH_URL + "?q="+searchTerm+"&num="+num;
	//without proper User-Agent, we will get 403 error
	Document doc = Jsoup.connect(searchURL).userAgent("Chrome").get();
		
	//below will print HTML data, save it to a file and open in browser to compare
	//System.out.println(doc.html());
	
	Elements results = doc.select("h3.r > a");

	for (Element result : results) {
		String linkHref = result.attr("href");
		String linkText = result.text();
		System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
		}

	}

}