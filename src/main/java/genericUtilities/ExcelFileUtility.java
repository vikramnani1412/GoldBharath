package genericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Utility class for Excel operations and Word document generation.
 */
public class ExcelFileUtility {

	// Logger is used to print messages in console/log file
	private static final Logger log = LoggerFactory.getLogger(ExcelFileUtility.class);

	// DataFormatter converts any cell type (String, Number, Boolean, Formula) to String
	private final DataFormatter formatter = new DataFormatter();

	// =========================================================
	// Helper Methods
	// =========================================================

	/**
	 * Opens the Excel workbook from the given file path.
	 */
	private Workbook openWorkbook(String fileName) throws EncryptedDocumentException, IOException {
	    // Load from src/test/resources
	    try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
	        if (is == null) {
	            throw new IOException("Excel file not found in resources: " + fileName);
	        }
	        return WorkbookFactory.create(is);
	    }
	}

	/**
	 * Returns the sheet object. If sheet is not found, throws exception.
	 */
	private Sheet getSheetOrThrow(Workbook wb, String sheetName) {
		Sheet sh = wb.getSheet(sheetName);

		if (sh == null) {
			throw new IllegalArgumentException("Sheet not found : " + sheetName);
		}

		return sh;
	}

	// =========================================================
	// Read Single Cell Data
	// =========================================================

	/**
	 * Reads data from a single cell.
	 */
	public String readDataFromExcel(String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {

		// Open workbook using try-with-resources (auto closes workbook)
		try (Workbook wb = openWorkbook("OPHC Automation Excel.xlsx")) {

			// Get sheet
			Sheet sh = getSheetOrThrow(wb, sheetName);

			// Get row
			Row rw = sh.getRow(rowNum);

			if (rw == null) {
				throw new IllegalArgumentException(
						"Row " + rowNum + " not found in sheet " + sheetName);
			}

			// Get cell
			Cell ce = rw.getCell(cellNum);

			if (ce == null) {
				throw new IllegalArgumentException(
						"Cell " + cellNum + " not found in row " + rowNum);
			}

			// Convert cell value to String and remove extra spaces
			return formatter.formatCellValue(ce).trim();
		}
	}

	// =========================================================
	// Write Data Into Excel
	// =========================================================

	/**
	 * Writes data into a specific cell.
	 */
	public synchronized void writeDataIntoExcel(String sheetName, int rowNum, int cellNum, String value)
			throws EncryptedDocumentException, IOException {

		// Open workbook
		Workbook wb = openWorkbook(ConstantsUtility.excelfilepath);

		try {
			// Get sheet
			Sheet sh = getSheetOrThrow(wb, sheetName);

			// Get row, create if it does not exist
			Row rw = sh.getRow(rowNum);

			if (rw == null) {
				rw = sh.createRow(rowNum);
			}

			// Get cell, create if it does not exist
			Cell ce = rw.getCell(cellNum);

			if (ce == null) {
				ce = rw.createCell(cellNum);
			}

			// Set value into cell
			ce.setCellValue(value);

			// Save workbook changes
			try (FileOutputStream fos = new FileOutputStream(ConstantsUtility.excelfilepath)) {
				wb.write(fos);
			}

		} finally {
			// Close workbook
			wb.close();
		}
	}

	// =========================================================
	// Read Multiple Rows (DataProvider)
	// =========================================================

	/**
	 * Reads all rows except header row and returns 2D array.
	 */
	public Object[][] readMultipleDataFromExcel(String sheetName)
			throws EncryptedDocumentException, IOException {

		try (Workbook wb = openWorkbook(ConstantsUtility.excelfilepath)) {

			Sheet sh = getSheetOrThrow(wb, sheetName);

			// Header row
			Row headerRow = sh.getRow(0);

			if (headerRow == null) {
				throw new IllegalArgumentException("Header row missing in sheet " + sheetName);
			}

			// Total rows and columns
			int lastRowNum = sh.getLastRowNum();
			int lastCellNum = headerRow.getLastCellNum();

			// Create 2D array
			Object[][] data = new Object[lastRowNum][lastCellNum];

			// Loop through rows
			for (int i = 0; i < lastRowNum; i++) {

				Row row = sh.getRow(i + 1);

				// Loop through columns
				for (int j = 0; j < lastCellNum; j++) {

					if (row != null && row.getCell(j) != null) {
						data[i][j] = formatter.formatCellValue(row.getCell(j)).trim();
					} else {
						data[i][j] = "";
					}
				}
			}

			return data;
		}
	}

	// =========================================================
	// Read Single Column Data
	// =========================================================

	/**
	 * Reads one column from excel and returns String array.
	 */
	public String[] getSingleColumnData(String filePath, String sheetName, int columnIndex)
			throws EncryptedDocumentException, IOException {

		try (Workbook wb = openWorkbook(filePath)) {

			Sheet sheet = getSheetOrThrow(wb, sheetName);

			int rowCount = sheet.getLastRowNum();

			String[] data = new String[rowCount];

			// Read values row by row
			for (int i = 1; i <= rowCount; i++) {

				Row row = sheet.getRow(i);

				if (row != null && row.getCell(columnIndex) != null) {
					data[i - 1] = formatter.formatCellValue(row.getCell(columnIndex)).trim();
				} else {
					data[i - 1] = "";
				}
			}

			return data;
		}
	}

	/**
	 * Generates a sample medical report as a .png image with mock patient
	 * data, unique per call (timestamped filename), and writes the generated
	 * file's path back into the given Excel cell.
	 * @param folderPath folder where the file should be created e.g. "C:\\TestData\\"
	 * @param fileNamePrefix prefix for the file name e.g. "MedicalReport"
	 * @param sheetName sheet to log the generated path into
	 * @param rowNum row to log the generated path into
	 * @param pathColNum column to log the generated path into
	 * @return full path of the newly created image
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 */
	public String generateSampleMedicalReport(String folderPath, String fileNamePrefix,
			String sheetName, int rowNum, int pathColNum) throws IOException {

		File dir = new File(folderPath);
		if (!dir.exists() && !dir.mkdirs()) {
			throw new IOException("Failed to create directory: " + folderPath);
		}

		String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss_SSS").format(new Date());
		String fileName = fileNamePrefix + "_" + timestamp + ".png";
		File imageFile = new File(dir, fileName);

		BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();

		try {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 800, 600);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 22));
			g.drawString("MEDICAL REPORT", 280, 50);

			g.setFont(new Font("Arial", Font.PLAIN, 16));
			g.drawString("Patient Name : Test User", 50, 120);
			g.drawString("Age          : 30 Years", 50, 160);
			g.drawString("Gender       : Male", 50, 200);
			g.drawString("Report Type  : General Checkup", 50, 240);
			g.drawString("Doctor       : Dr. Automation", 50, 280);
			g.drawString("Hospital     : ABC Hospital", 50, 320);
			g.drawString("Status       : Fit", 50, 360);
			g.drawString("Generated On : " + timestamp, 50, 420);
		} finally {
			g.dispose();
		}

		if (!ImageIO.write(image, "png", imageFile)) {
			throw new IOException("No PNG writer available to save the image: " + imageFile.getAbsolutePath());
		}

		String absolutePath = imageFile.getAbsolutePath();

		// log the generated file path back into Excel
		writeDataIntoExcel(sheetName, rowNum, pathColNum, absolutePath);

		log.info("Medical report generated and logged to Excel: {}", absolutePath);
		return absolutePath;
	}
}