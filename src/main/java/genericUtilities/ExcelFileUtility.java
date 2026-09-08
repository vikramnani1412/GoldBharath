package genericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains excel file specific methods
 * @author vikra
 *
 */
public class ExcelFileUtility {

	/**
	 * This method will read data from excel
	 * @param SheetName
	 * @param RowNum
	 * @param CellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readDataFromExcel(String SheetName,int RowNum,int CellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(ConstantsUtility.excelfilepath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row rw = sh.getRow(RowNum);
		Cell ce = rw.getCell(CellNum);
		String value = ce.getStringCellValue();
		wb.close();
		return value;
	}
	
	/**
	 * This method will set data into excel
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String sheetname,int rownum,int cellnum,String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(ConstantsUtility.excelfilepath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row rw = sh.createRow(rownum);
		Cell ce = rw.createCell(cellnum);
		ce.setCellValue(value);
		
		FileOutputStream fos=new FileOutputStream(ConstantsUtility.excelfilepath);
		wb.write(fos);
		wb.close();
	}
	
	
	public Object[][] readMultipleDataFromExcel(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(ConstantsUtility.excelfilepath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow=sh.getLastRowNum();
		int lastCel=sh.getRow(0).getLastCellNum();
		
		Object data[][]=new Object[lastRow][lastCel];
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastCel;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
		
		
	}
	
	public static String[] getSingleColumnData(String filePath, String sheetNamee, int columnIndex) 
	{
        String[] dataa = null;
        try 
        {
            FileInputStream fis = new FileInputStream(ConstantsUtility.excelfilepath);
            Workbook book = WorkbookFactory.create(fis);
            Sheet sheet = book.getSheet(sheetNamee);

            int rowCount = sheet.getLastRowNum();

            dataa = new String[rowCount];

            for (int i = 1; i <= rowCount; i++) 
            {
                Row row = sheet.getRow(i);
                dataa[i - 1] = row.getCell(columnIndex).toString();
            }
            book.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return dataa;
    }
		
		
	

}
