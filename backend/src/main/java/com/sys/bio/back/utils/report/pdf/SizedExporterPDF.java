package com.sys.bio.back.utils.report.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sys.bio.back.models.user.Responsible;
import com.sys.bio.back.models.sized.StrawType;
import com.sys.bio.back.models.sized.SizedBox;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class SizedExporterPDF {

    private List<SizedBox> sizedBoxList;

    private void writeHeaderTable(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase((new Phrase("ID", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Responsable", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Tipo de bíombilla", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Peso", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Cantidad", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Fecha", font)));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for(SizedBox sizedBox : sizedBoxList) {

            Long sizedBasketId = sizedBox.getSizedBoxId();
            String idString = (sizedBasketId != null) ? sizedBasketId.toString() : "null";
            table.addCell(idString);

            Responsible responsible = sizedBox.getResponsible();
            String responsibleName = (responsible != null) ? responsible.getName() : "null";
            table.addCell(responsibleName);

            StrawType strawType = sizedBox.getStrawType();
            String strawName = (strawType != null) ? strawType.getName() : "null";
            table.addCell(strawName);

            Integer accepted = sizedBox.getWeight();
            String acceptedString = (accepted != null) ? accepted.toString() : "null";
            table.addCell(acceptedString);

            Integer amount = sizedBox.getAmount();
            String amountString = (amount != null) ? amount.toString() : "null";
            table.addCell(amountString);


            Date date = sizedBox.getDate();
            String dateString = (date != null) ? formatDate(date) : "null";
            table.addCell(dateString);
        }
    }

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLUE);
        font.setSize(18);

        Paragraph title = new Paragraph("Lista de dimensionados", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[] {1f, 3f,2.3f,2.5f,6f, 2.3f, 2.3f});
        table.setWidthPercentage(110);

        writeHeaderTable(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
