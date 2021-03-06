package mk1;

/**
 * Desmond Aska Maddox
 * Jack Dai
 * Darren Kong
 * Lok Chi Hon
 * CS Final Project
 */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import webCrawler.finalSearch;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class GraphPanel extends JPanel {
	
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
	
	static double[] scoring;

    private int width = 800;
    private int heigth = 400;
    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private List<Double> scores;

    public GraphPanel(List<Double> scores) {
    	this.scores = scores;
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                	g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axis 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(width, heigth);
//    }
    
    private double getMinScore() {
    	double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }

    public List<Double> getScores() {
    	return scores;
    }
    
    public static double findSlope() {
    	if (scoring.length == 0) {
    		return 0;
    	} 
    	else {
    		return (scoring[scoring.length-1] - scoring[0])/(scoring.length);
    	}
    }

    private static void createAndShowGui() {
    	List<Double> scores = new ArrayList<>();
        for (int i = 0; i < scoring.length; i++) {
            scores.add((double) scoring[i]);
//            scores.add((double) i);
        }
        GraphPanel mainPanel = new GraphPanel(scores);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Finance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
    	
    	finalSearch.setter();
    	finalSearch.whichArticle("INTC");
    	
		ArrayList <Double> tenminprice = new ArrayList<Double>();
		Stock stock = YahooFinance.get("INTC");
		 /*
		BigDecimal price = stock.getQuote().getPrice();
		BigDecimal change = stock.getQuote().getChangeInPercent();
		BigDecimal peg = stock.getStats().getPeg();
		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
		 */
		
		for(int x = 0; x < 10; x++){
			String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
			Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
			Stock intel = stocks.get("INTC");
			//Stock airbus = stocks.get("AIR.PA");
			BigDecimal price = intel.getQuote().getPrice();
			System.out.println(price.doubleValue());
			tenminprice.add(price.doubleValue());
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.print(Arrays.toString(tenminprice.toArray()));
    	
    	double[] arry = {2.5, 3.5,10.3, 5.0, 7.1};
    	scoring = arry;
    	double k = findSlope();
    	
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			createAndShowGui();
    		}
    	});
    	
    }
}