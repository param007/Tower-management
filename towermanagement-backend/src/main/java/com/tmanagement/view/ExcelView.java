package com.tmanagement.view;

import com.tmanagement.model.Complaint;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"admin-report.xls\"");

        @SuppressWarnings("unchecked")
        List<Complaint> complaints = (List<Complaint>) model.get("complaint");
        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Complaint Details");
        sheet.setDefaultColumnWidth(30);
        
        int rowCount = 0;
	    Row data = sheet.createRow(rowCount++);
	    data.createCell(0).setCellValue("Generated on");
	    data.createCell(1).setCellValue(LocalDateTime.now().toString());
	    data = sheet.createRow(rowCount++);
	    data.createCell(0).setCellValue("Complaint Report of Tower ID");
	    data.createCell(1).setCellValue(complaints.get(0).getTower().getTowerId());
	    data = sheet.createRow(rowCount++);
	    data.createCell(0).setCellValue("Tower Circle");
	    data.createCell(1).setCellValue(complaints.get(0).tower.getCircle());
	    data = sheet.createRow(rowCount++);
	    data.createCell(0).setCellValue("Tower Status");
	    data.createCell(1).setCellValue((complaints.get(0).tower.isStatus())?"Active":"Under Maintainence");
	    rowCount++;
	    
        
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(rowCount++);
        header.createCell(0).setCellValue("Complaint ID");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Filed By");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Complaint Type");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Remarks");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Issue Date");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Approval Date");
        header.getCell(5).setCellStyle(style);
        
        for(Complaint complaint : complaints){
            Row compRow =  sheet.createRow(rowCount++);
            compRow.createCell(0).setCellValue(complaint.getComplaintId());
            compRow.createCell(1).setCellValue(complaint.getCsa().getName());
            compRow.createCell(2).setCellValue(complaint.getType().toString());
            compRow.createCell(3).setCellValue(complaint.getDescription());
            compRow.createCell(4).setCellValue(complaint.getDateOfIssue().toString());
            compRow.createCell(5).setCellValue(complaint.getDateOfApproval().toString());
        }

    }

}