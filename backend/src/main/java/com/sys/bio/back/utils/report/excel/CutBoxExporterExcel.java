package com.sys.bio.back.utils.report.excel;

import com.sys.bio.back.models.cutting.CutBox;
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
public class CutBoxExporterExcel {

    private XSSFWorkbook book;
    private XSSFSheet sheet;
    private List<CutBox> cutBoxList;

    public CutBoxExporterExcel(List<CutBox> filteredCutBoxes) {
        this.cutBoxList = filteredCutBoxes;
        book = new XSSFWorkbook();
        sheet = book.createSheet("Cortes");
    }

    private void writeHeaderTable() {
        Row file = sheet.createRow(0);
        CellStyle style = book.createCellStyle();
        XSSFFont font = book.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);

        Cell cell = file.createCell(0);
        cell.setCellValue("Proceso");
        cell.setCellStyle(style);

        cell = file.createCell(1);
        cell.setCellValue("ID");
        cell.setCellStyle(style);

        cell = file.createCell(2);
        cell.setCellValue("Operador");
        cell.setCellStyle(style);

        cell = file.createCell(3);
        cell.setCellValue("Tipo de corte");
        cell.setCellStyle(style);

        cell = file.createCell(4);
        cell.setCellValue("Peso");
        cell.setCellStyle(style);

        cell = file.createCell(5);
        cell.setCellValue("Factor");
        cell.setCellStyle(style);

        cell = file.createCell(6);
        cell.setCellValue("Cantidad de bíombillas");
        cell.setCellStyle(style);

        cell = file.createCell(7);
        cell.setCellValue("Fecha");
        cell.setCellStyle(style);

        cell = file.createCell(8);
        cell.setCellValue("Hora");
        cell.setCellStyle(style);

        cell = file.createCell(9);
        cell.setCellValue("Observaciones");
        cell.setCellStyle(style);
    }

    private void writeTableData() {
        int fileNumber = 1;

        CellStyle style = book.createCellStyle();
        XSSFFont font = book.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        for(CutBox cutBox : cutBoxList) {
            Row row = sheet.createRow(fileNumber ++);

            Cell cell = row.createCell(0);
            Long cuttingId = cutBox.getCutting().getCuttingId();
            cell.setCellValue((cuttingId != null) ? cuttingId.toString() : "null");
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            Long cutBoxId = cutBox.getCutBoxId();
            cell.setCellValue((cutBoxId != null) ? cutBoxId.toString() : "null");
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            String responsibleName = (cutBox.getResponsible() != null) ? cutBox.getResponsible().getName() : "null";
            cell.setCellValue(responsibleName);
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            String cutType = (cutBox.getCutType() != null) ? cutBox.getCutType().getName() : "null";
            cell.setCellValue(cutType);
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            String weight = (cutBox.getWeight() != null) ? cutBox.getWeight().toString() : "null";
            cell.setCellValue(weight);
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            String factor = (cutBox.getCutType().getFactor() != null) ? cutBox.getCutType().getFactor().toString() : "null";
            cell.setCellValue(factor);
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(6);
            String amount = (cutBox.getAmount() != null) ? cutBox.getAmount().toString() : "null";
            cell.setCellValue(amount);
            sheet.autoSizeColumn(6);
            cell.setCellStyle(style);

            cell = row.createCell(7);
            Date date = cutBox.getDate();
            String dateString = (date != null) ? formatDate(date) : "null";
            cell.setCellValue(dateString);
            sheet.autoSizeColumn(7);
            cell.setCellStyle(style);

            cell = row.createCell(8);
            LocalTime hour = cutBox.getHour();
            String hourString = (hour != null) ? hour.toString() : "null";
            cell.setCellValue(hourString);
            sheet.autoSizeColumn(8);
            cell.setCellStyle(style);

            cell = row.createCell(9);
            String observationString = (cutBox.getCutting() != null) ? cutBox.getCutting().getObservations() : "null";
            cell.setCellValue(observationString);
            sheet.autoSizeColumn(9);
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
