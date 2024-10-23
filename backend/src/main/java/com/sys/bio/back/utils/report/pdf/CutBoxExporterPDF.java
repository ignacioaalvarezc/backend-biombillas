package com.sys.bio.back.utils.report.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.user.Responsible;
import com.sys.bio.back.models.cutting.CutType;
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
public class CutBoxExporterPDF {

    private List<CutBox> cutBoxesList;



    private void writeHeaderTable(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase((new Phrase("Proceso", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("ID", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Operador", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Tipo de corte", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Peso", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Factor", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Cantidad de bíombillas", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Fecha", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Hora", font)));
        table.addCell(cell);

        cell.setPhrase((new Phrase("Observaciones", font)));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for(CutBox cutBox : cutBoxesList) {

            Long cuttingId = cutBox.getCutting().getCuttingId();
            String idString = (cuttingId != null) ? cuttingId.toString() : "null";
            table.addCell(idString);

            Long cutBoxId = cutBox.getCutBoxId();
            String idCutString = (cutBoxId != null) ? cutBoxId.toString() : "null";
            table.addCell(idCutString);

            Responsible responsible = cutBox.getResponsible();
            String responsibleName = (responsible != null) ? responsible.getName() : "null";
            table.addCell(responsibleName);

            CutType cutType = cutBox.getCutType();
            String cutTypeName = (cutType != null) ? cutType.getName() : "null";
            table.addCell(cutTypeName);

            Double weight = cutBox.getWeight();
            String weightString = (weight != null) ? weight.toString() : "null";
            table.addCell(weightString);

            Double factor = cutBox.getCutType().getFactor();
            String factorString = (factor != null) ? factor.toString() : "null";
            table.addCell(factorString);

            Integer amount = cutBox.getAmount();
            String amountString = (amount != null) ? amount.toString() : "null";
            table.addCell(amountString);

            Date date = cutBox.getDate();
            String dateString = (date != null) ? formatDate(date) : "null";
            table.addCell(dateString);

            LocalTime hour = cutBox.getHour();
            String hourString = (hour != null) ? hour.toString() : "null";
            table.addCell(hourString);

            String observations = cutBox.getCutting().getObservations();
            String observationString = (observations != null) ? observations : "null";
            table.addCell(observationString);
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

        Paragraph title = new Paragraph("Lista de cajas de cortes", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[] {1.5f,0.5f,1.1f,1f,0.6f,0.7f,1.5f,1.5f,0.7f,1f});
        table.setWidthPercentage(110);

        writeHeaderTable(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
