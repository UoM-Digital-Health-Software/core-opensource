package uk.ac.herc.dhs.pdf;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.pdfjet.Compliance;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Paragraph;
import com.pdfjet.PlainText;
import com.pdfjet.Text;
import com.pdfjet.TextLine;

import org.springframework.core.io.ClassPathResource;

public class ConsentPdf {

    private Font regularFont;
    private Font boldFont;

    public byte[] generate(List<String> statements) throws Exception {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PDF pdf = new PDF(byteArrayOutputStream, Compliance.PDF_UA);
        Page page = new Page(pdf, com.pdfjet.Letter.PORTRAIT);
        loadFonts(pdf);

        Float top = ConsentPdfConstants.MARGIN_TOP;

        drawIntro(pdf, page, top);
        top += ConsentPdfConstants.INTRO_HEIGHT;

        drawStatements(statements, pdf, page, top);

        pdf.close();

        return byteArrayOutputStream.toByteArray();
    }

    private void loadFonts(PDF pdf) throws Exception {
        InputStream regularFontStream = new ClassPathResource(ConsentPdfConstants.OPEN_SANS_REGULAR_FONT_FILE)
                .getInputStream();

        regularFont = new Font(pdf, regularFontStream, Font.STREAM);
        regularFont.setSize(ConsentPdfConstants.FONT_SIZE);

        InputStream boldFontStream = new ClassPathResource(ConsentPdfConstants.OPEN_SANS_BOLD_FONT_FILE)
                .getInputStream();

        boldFont = new Font(pdf, boldFontStream, Font.STREAM);
        boldFont.setSize(ConsentPdfConstants.FONT_SIZE);
    }

    private void drawIntro(PDF pdf, Page page, Float top) throws Exception {

        String[] titleSection = new String[] {
                ConsentPdfConstants.TITLE,
                ConsentPdfConstants.EMPTY_LINE,
                ConsentPdfConstants.CONSENTED,
        };
        new PlainText(boldFont, titleSection)
                .setLocation(ConsentPdfConstants.MARGIN_LEFT, top)
                .drawOn(page);
    }

    private void drawStatements(List<String> statements, PDF pdf, Page page, Float top) throws Exception {

        List<Paragraph> paragraphs = new ArrayList<Paragraph>();
        for (String statement : statements) {
            paragraphs.add(
                    new Paragraph()
                            .add(new TextLine(regularFont, statement)));
        }

        Text textArea = new Text(paragraphs);
        textArea.setLocation(ConsentPdfConstants.MARGIN_LEFT, top);
        textArea.setWidth(ConsentPdfConstants.PAGE_WIDTH);
        textArea.drawOn(page);
    }
}
