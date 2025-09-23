package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

public class ExcelUtilities {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public XSSFCellStyle Style;
    String path;

    public ExcelUtilities(String path)
    {

        this.path=path;
    }

    public int getRowNum(String sheetName) throws IOException {
      fi = new FileInputStream(path);
      workbook = new XSSFWorkbook(fi);
      sheet=workbook.getSheet(sheetName);
      int rowCount=sheet.getLastRowNum();
      workbook.close();
      fi.close();
      return rowCount;

    }

    public int getCellCount(String sheetName , int rownum) throws IOException {
        fi=new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        int columnCount=row.getLastCellNum();
        workbook.close();
        fi.close();
        return columnCount;
    }

    public String getcellData(String sheetName , int rownum, int columnnum) throws IOException {
        fi=new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(columnnum);

        DataFormatter formatter = new DataFormatter();
        String data;

        try{
            data=formatter.formatCellValue(cell);
        }
        catch(Exception e){
            data="";
        }
        workbook.close();
        fi.close();
        return data;
    }

    public String getCellValue(String sheetName , int rownum , int columnnum) throws IOException {
        fi=new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(columnnum);
        Style=workbook.createCellStyle();
        Style.cloneStyleFrom(cell.getCellStyle());
        cell.setCellStyle(Style);
        return cell.getStringCellValue();
    }

    public  void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile=new File(path);
        if(!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);

        }

        fi=new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if(workbook.getSheetIndex(sheetName)==-1) //If sheet not exists then create new sheet
            workbook.createSheet(sheetName);
        sheet =  workbook.getSheet(sheetName);

        if(sheet.getRow(rownum)==null)
            sheet.createRow(rownum);
        row=sheet.getRow(rownum);

        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }
    public  void setGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fi=new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet= workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        Style = workbook.createCellStyle();

        Style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        Style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(Style);
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    public  void setRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fi=new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet= workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        Style = workbook.createCellStyle();

        Style.setFillForegroundColor(IndexedColors.RED.getIndex());
        Style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(Style);
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();

}


}


