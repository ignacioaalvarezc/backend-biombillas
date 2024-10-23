package com.sys.bio.back.utils.report.excel;

import com.sys.bio.back.models.reception.Reception;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class ReceptionExporterExcel {

    private XSSFWorkbook book;
    private XSSFSheet sheet;

    private List<Reception> receptionList;

    public ReceptionExporterExcel(List<Reception> filteredReceptions) {
        this.receptionList = filteredReceptions;
        book = new XSSFWorkbook();
        sheet = book.createSheet("Recepciones");
    }

    private void writeHeaderTable() {
        Row file = sheet.createRow(0);
        CellStyle style = book.createCellStyle();
        XSSFFont font = book.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);

        Cell cell = file.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);

        cell = file.createCell(1);
        cell.setCellValue("Responsable");
        cell.setCellStyle(style);

        cell = file.createCell(2);
        cell.setCellValue("Fardos aceptados");
        cell.setCellStyle(style);

        cell = file.createCell(3);
        cell.setCellValue("Fardos rechazados");
        cell.setCellStyle(style);

        cell = file.createCell(4);
        cell.setCellValue("Observaciones");
        cell.setCellStyle(style);

        cell = file.createCell(5);
        cell.setCellValue("Fecha");
        cell.setCellStyle(style);

        cell = file.createCell(6);
        cell.setCellValue("Hora");
        cell.setCellStyle(style);
    }

    private void writeTableData() {
        int fileNumber = 1;

        CellStyle style = book.createCellStyle();
        XSSFFont font = book.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        for(Reception reception : receptionList) {
            Row row = sheet.createRow(fileNumber ++);

            Cell cell = row.createCell(0);
            Long receptionId = reception.getReceptionId();
            cell.setCellValue((receptionId != null) ? receptionId.toString() : "null");
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            String responsibleName = (reception.getResponsible() != null) ? reception.getResponsible().getName() : "null";
            cell.setCellValue(responsibleName);
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            String accepted = (reception.getAcceptedBales() != null) ? reception.getAcceptedBales().toString() : "null";
            cell.setCellValue(accepted);
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            String rejected = (reception.getRejectedBales() != null) ? reception.getRejectedBales().toString() : "null";
            cell.setCellValue(rejected);
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            String observations = (reception.getReasonRejected() != null) ? reception.getReasonRejected() : "null";
            cell.setCellValue(observations);
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            Date date = reception.getDate();
            String dateString = (date != null) ? formatDate(date) : "null";
            cell.setCellValue(dateString);
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(6);
            LocalTime hour = reception.getHour();
            String hourString = (hour != null) ? hour.toString() : "null";
            cell.setCellValue(hourString);
            sheet.autoSizeColumn(6);
            cell.setCellStyle(style);
        }
    }

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }



    public void export(HttpServletResponse response) throws IOException {
        writeHeaderTable();
        writeTableData();
        ServletOutputStream outputStream = response.getOutputStream();
        book.write(outputStream);
        book.close();
        outputStream.close();
    }

}
