package webCrawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class finalSearch {

	static String[] google1 = new String[9];
	static String[] google2 = new String[9];

	static ArrayList<String> positive = new ArrayList<String>();
	static ArrayList<String> negative = new ArrayList<String>();
	static ArrayList<String> neutral = new ArrayList<String>();
	
	//AAPL
	public static final String GOOGLE_SEARCH_URL1 = "https://www.google.com/search?q=aapl+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	//INTC
	public static final String GOOGLE_SEARCH_URL2 = "https://www.google.com/search?q=intc+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	//DOW
	public static final String GOOGLE_SEARCH_URL3 = "https://www.google.com/search?q=dow+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	//TSLA
	public static final String GOOGLE_SEARCH_URL4 = "https://www.google.com/search?q=tsla+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	//MSFT
	public static final String GOOGLE_SEARCH_URL5 = "https://www.google.com/search?q=msft+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	//GE
	public static final String GOOGLE_SEARCH_URL6 = "https://www.google.com/search?q=ge+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	//JPM
	public static final String GOOGLE_SEARCH_URL7 = "https://www.google.com/search?q=jpm+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	//AMZN
	public static final String GOOGLE_SEARCH_URL8 = "https://www.google.com/search?q=amzn+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	//XOM
	public static final String GOOGLE_SEARCH_URL9 = "https://www.google.com/search?q=xom+news&rlz=1C1CHBF_enUS704US704&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjIjfTG2JXUAhWs64MKHeaYC5MQ_AUICigB&biw=674&bih=762";
	
	private static void setter(){
		google1[0] = GOOGLE_SEARCH_URL1;
		google1[1] = GOOGLE_SEARCH_URL2;
		google1[2] = GOOGLE_SEARCH_URL3;
		google1[3] = GOOGLE_SEARCH_URL4;
		google1[4] = GOOGLE_SEARCH_URL5;
		google1[5] = GOOGLE_SEARCH_URL6;
		google1[6] = GOOGLE_SEARCH_URL7;
		google1[7] = GOOGLE_SEARCH_URL8;
		google1[8] = GOOGLE_SEARCH_URL9;
		google2[0] = "APPLE";
		google2[1] = "INTEL";
		google2[2] = "DOW JONES";
		google2[3] = "TESLA";
		google2[4] = "MICROSOFT";
		google2[5] = "GENERAL ELECTRIC";
		google2[6] = "JPMORGAN";
		google2[7] = "AMAZON";
		google2[8] = "EXXON MOBILE";
	}
	
	
	/**
	 * Search for one word in phrase. The search is not
	 *  case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param headline
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         headline or -1 if it's not found
	 */
	private static int findKeyword(String statement, String goal, int startPos){
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0){
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0){
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length()){
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	public static void yesOrNo(String headline){
		if (findKeyword(headline, "good",0) >= 0
			|| findKeyword(headline, "better",0) >= 0
			|| findKeyword(headline, "accelerate",0) >= 0
			|| findKeyword(headline, "grow",0) >= 0
			|| findKeyword(headline, "strong",0) >= 0
			|| findKeyword(headline, "profitable",0) >= 0				
			|| findKeyword(headline, "advantage",0) >= 0
			|| findKeyword(headline, "gain",0) >= 0
			|| findKeyword(headline, "rise",0) >= 0){
			positive.add(headline);
		}
		else if (findKeyword(headline, "bad",0) >= 0
				|| findKeyword(headline, "decline",0) >= 0
				|| findKeyword(headline, "worse",0) >= 0
				|| findKeyword(headline, "slow",0) >= 0
				|| findKeyword(headline, "weak",0) >= 0
				|| findKeyword(headline, "losing",0) >= 0				
				|| findKeyword(headline, "lose",0) >= 0
				|| findKeyword(headline, "stuck",0) >= 0
				|| findKeyword(headline, "worry",0) >= 0
				|| findKeyword(headline, "drop",0) >= 0){
				negative.add(headline);
		}
		else{
			neutral.add(headline);
		}
	}
	
	public static ArrayList<String> goodOrBad(double slope)
	{
		if (slope > 0){
			return positive;
		}
		else if (slope < 0){
			return negative;
		}
		else{
			return neutral;
		}
	}

	public static void main(String[] args) throws IOException {
		finalSearch.setter();
		int counter = 0;
		int num = 10;
		
		for (String i : google1){

			System.out.println(google2[counter]+" NEWS");
			counter++;
			String searchURL = i + "?q="+i+"&num="+num;
			//without proper User-Agent, we will get 403 error
			Document doc = Jsoup.connect(searchURL).userAgent("Chrome").get();
			
			//below will print HTML data, save it to a file and open in browser to compare
			//System.out.println(doc.html());
		
			Elements results = doc.select("h3.r > a");

			for (Element result : results) {
				String linkHref = result.attr("href");
				String linkText = result.text();
				System.out.println("Text:: " + linkText + ", URL:: " + linkHref.substring(6, linkHref.indexOf("&")));
			}
			System.out.println();
		}
		
	}

}