package mk1;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import yahoofinance.histquotes.HistQuotesRequest;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxQuotesRequest;
import yahoofinance.quotes.stock.StockQuotesData;
import yahoofinance.quotes.stock.StockQuotesRequest;
import java.util.concurrent.TimeUnit;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class FinanceTest {

	public static void main(String[]args) throws IOException{
		ArrayList <Double> tenminprice = new ArrayList<Double>();
		Stock stock = YahooFinance.get("INTC");
		 /*
		BigDecimal price = stock.getQuote().getPrice();
		BigDecimal change = stock.getQuote().getChangeInPercent();
		BigDecimal peg = stock.getStats().getPeg();
		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
		 */
		
		for(int i = 0; i < 10; i++){
			String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
			Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
			Stock intel = stocks.get("INTC");
			Stock airbus = stocks.get("AIR.PA");
			BigDecimal price = intel.getQuote().getPrice();
			System.out.println(price.doubleValue());
			tenminprice.add(price.doubleValue());
			try {
				TimeUnit.MINUTES.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.print(Arrays.toString(tenminprice.toArray()));
	}
}
