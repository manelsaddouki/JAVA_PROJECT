import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

public class SalesChart {
    private List<Transaction> salesTransactions;

    // Constructor
    public SalesChart(List<Transaction> salesTransactions) {
        this.salesTransactions = salesTransactions;
    }

    // Generate a bar chart for sales trends
    public void generateSalesTrendsChart() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createBarChart(dataset);

        // Display the chart in a Swing frame
        JFrame frame = new JFrame("Sales Trends Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Helper method to create a dataset for the bar chart
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Transaction transaction : salesTransactions) {
            dataset.addValue(transaction.getTransactionQuantity(), "Sales", transaction.getDate().toString());
        }

        return dataset;
    }

    // Helper method to create a bar chart
    private JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Sales Trends Over Time",
                "Date",
                "Quantity Sold",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }
}
