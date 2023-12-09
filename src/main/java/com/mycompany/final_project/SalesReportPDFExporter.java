import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts; // Import the Standard14Fonts

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesReportPDFExporter {

    private static final String PDF_FILE_PATH = "SalesReport.pdf";

    public static void exportTextFileToPDF(String textFilePath) {
        try {
            PDDocument document = new PDDocument();

            // Add a blank page for the sales report
            PDPage page = new PDPage();
            document.addPage(page);

            // Create a content stream for adding text to the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Load a standard Type 1 font
            PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD); // Example with HELVETICA_BOLD

            // Set the font and font size
            contentStream.setFont(font, 12);

            // Read content from the text file and add it to the PDF
            addTextFileContentToPDF(contentStream, textFilePath);

            // Close the content stream
            contentStream.close();

            // Save the PDF document
            document.save(PDF_FILE_PATH);

            // Close the document
            document.close();

            System.out.println("Text file exported to PDF successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTextFileContentToPDF(PDPageContentStream contentStream, String textFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(textFilePath))) {
            String line;
            float yPosition = 700; // Set the starting position

            while ((line = reader.readLine()) != null) {
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText(line);
                contentStream.endText();
                yPosition -= 12; // Adjust the line spacing
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
