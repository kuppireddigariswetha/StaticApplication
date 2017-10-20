package com.example.roja_pc.todoproject;

import android.content.ContentValues;
import android.util.Log;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

/**
 * Created by Roja-PC on 11-01-2017.
 */
public class Excel2SQLiteHelper {

    public static final String Tablename = "MyTable1";
    public static final String id = "_id";// 0 integer
    public static final String Studentname = "Studentname";// 1 text(String)
    public static final String Uniqueid = "uniqueid";// 2 text(String)
    public static final String Fathername = "Price";// 3 text(String)
    public static final String School = "School";// 4 text(String)
    public static final String District = "district";// 5 text(String)
    public static final String Dob = "Dob";// 6 text(String)
    public static final String Class = "Class";// 7 text(String)
    public static final String Section = "Section";// 8 text(String)
    public static final String Mobile = "Mobile";// 9 text(String)

    public static void insertExcelToSqlite(XlsxCon dbAdapter, Sheet sheet) {
        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {
            Row row = rit.next();
            ContentValues contentValues = new ContentValues();
            row.getCell(0, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(4, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(5, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(6, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(7, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(8, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);

            contentValues.put(Studentname, row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(Uniqueid, row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(Fathername, row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(School, row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(District, row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(Dob, row.getCell(5, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(Class, row.getCell(6, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(Section, row.getCell(7, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(Mobile, row.getCell(8, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
          try {
                if (dbAdapter.insert("MyTable1", contentValues) < 0) {
                    return;
                }
            } catch (Exception ex) {
                Log.d("Exception in importing", ex.getMessage().toString());
            }
        }
    }

}
