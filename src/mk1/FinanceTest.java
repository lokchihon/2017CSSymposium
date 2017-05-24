package mk1;

import java.io.IOException;
import java.math.BigDecimal;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class FinanceTest {

	public void main(String[]args) throws IOException{
		Stock stock = YahooFinance.get("DOW");
		 
		BigDecimal price = stock.getQuote().getPrice();
		BigDecimal change = stock.getQuote().getChangeInPercent();
		BigDecimal peg = stock.getStats().getPeg();
		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
		 
		stock.print();
	}
}