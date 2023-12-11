import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities; // Corrected import statement
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesChart {
    private List<Transaction> salesTransactions;

    // Constructor
    public SalesChart(List<Transaction> salesTransactions) {
        this.salesTransactions = salesTransactions;
    }

    // Generate a bar chart for sales trends and export it to a PDF file
    public void generateSalesTrendsChartToPDF(String pdfFilePath) {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createBarChart(dataset);

        // Save the chart to a PDF file
        saveChartToPDF(chart, pdfFilePath);
    }

    // Helper method to create a dataset for the bar chart
    // Helper method to create a dataset for the bar chart
private CategoryDataset createDataset() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Map to store aggregated total quantity for each date
    Map<String, Integer> totalQuantityByDate = new HashMap<>();

    for (Transaction transaction : salesTransactions) {
        String date = transaction.getDate().toString();
        int quantity = transaction.getTransactionQuantity();

        // Update the total quantity for the date
        totalQuantityByDate.put(date, totalQuantityByDate.getOrDefault(date, 0) + quantity);
    }

    // Sort the dates in ascending order
    List<String> sortedDates = totalQuantityByDate.keySet().stream()
            .sorted()
            .toList();

    // Add data to the dataset in ascending order
    for (String date : sortedDates) {
        dataset.addValue(totalQuantityByDate.get(date), "Sales", date);
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

    // Helper method to save the chart to a PDF file
    private void saveChartToPDF(JFreeChart chart, String pdfFilePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Load a standard Type 1 font
            PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD); // Example with HELVETICA_BOLD
            contentStream.setFont(font, 12);

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Sales Trends Over Time");
            contentStream.endText();

            contentStream.drawImage(PDImageXObject.createFromByteArray(document,
                    ChartUtilities.encodeAsPNG(chart.createBufferedImage(600, 400)),
                    "SalesChart"), 50, 250, 500, 300);

            contentStream.close();

            document.save(pdfFilePath);
            document.close();

            System.out.println("Sales chart exported to PDF successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
