package com.sys.bio.back.utils.report.excel;

import com.sys.bio.back.models.sized.SizedBox;
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
public class SizedExporterExcel {

    private XSSFWorkbook book;
    private XSSFSheet sheet;

    private List<SizedBox> sizedBoxList;

    public SizedExporterExcel(List<SizedBox> filteredSizedBoxes) {
        this.sizedBoxList = filteredSizedBoxes;
        book = new XSSFWorkbook();
        sheet = book.createSheet("Dimensionados");
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

        cell = file.createCell(1);
        cell.setCellValue("Tipo de bíombilla");
        cell.setCellStyle(style);

        cell = file.createCell(2);
        cell.setCellValue("Peso");
        cell.setCellStyle(style);

        cell = file.createCell(3);
        cell.setCellValue("Cantidad");
        cell.setCellStyle(style);

        cell = file.createCell(5);
        cell.setCellValue("Fecha");
        cell.setCellStyle(style);

    }

    private void writeTableData() {
        int fileNumber = 1;

        CellStyle style = book.createCellStyle();
        XSSFFont font = book.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        for (SizedBox sizedBox : sizedBoxList) {
            Row row = sheet.createRow(fileNumber++);

            Cell cell = row.createCell(0);
            Long sizedBasketId = sizedBox.getSizedBoxId();
            cell.setCellValue((sizedBasketId != null) ? sizedBasketId.toString() : "null");
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            String responsibleName = (sizedBox.getResponsible() != null) ? sizedBox.getResponsible().getName() : "null";
            cell.setCellValue(responsibleName);
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            String strawName = (sizedBox.getStrawType() != null) ? sizedBox.getStrawType().getName() : "null";
            cell.setCellValue(strawName);
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            String weight = (sizedBox.getWeight() != null) ? sizedBox.getWeight().toString() : "null";
            cell.setCellValue(weight);
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            String amount = (sizedBox.getAmount() != null) ? sizedBox.getAmount().toString() : "null";
            cell.setCellValue(amount);
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            Date date = sizedBox.getDate();
            String dateString = (date != null) ? formatDate(date) : "null";
            cell.setCellValue(dateString);
            sheet.autoSizeColumn(5);
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