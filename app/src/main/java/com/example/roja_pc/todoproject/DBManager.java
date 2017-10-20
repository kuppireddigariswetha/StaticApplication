package com.example.roja_pc.todoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

	private DatabaseHelper dbHelper;
	
	private Context context;
	
	private SQLiteDatabase database;



	public DBManager(Context c) {
		context = c;
	}

	public DBManager open() throws SQLException {
		dbHelper = new DatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public void insert(String uid, String name, String fathername, String schoolname, String district, String dateofbirth,
					   String mobile, String adno, String section, String classes, String dateofexam, String height,
					   String weight, String bmi, String pulse, String bp, String hb, String bloodgroup, String description1,
					   String advice, String withoutr, String withoutl, String withr, String withl, String rg1,
					   String rg2, String description2, String rg3, String rg4, String description3, String rg5,
					   String rg6, String rg7, String rg8, String rg9, String description4, String abnormalities,
					   String ortho, String postural, String defectsatbirth, String defiencies, String childwooddiseases, String dddisability,
					   String speechscreening, String nad, String vsref, String asref, String dentalref, String dc11, String dc12, String dc13,
					   String dc14, String imagepath , String genaral, String dental, String optho, String audio, String history1,String dateandtime,String advise)
	{
		ContentValues contentValue = new ContentValues();
		contentValue.put(DatabaseHelper.TODO_UID, uid);
		contentValue.put(DatabaseHelper.TODO_NAME, name);
		contentValue.put(DatabaseHelper.TODO_FATHERNAME, fathername);
		contentValue.put(DatabaseHelper.TODO_SCHOOLNAME, schoolname);
		contentValue.put(DatabaseHelper.TODO_DISTRICT, district);
		contentValue.put(DatabaseHelper.TODO_DATEOFBIRTH, dateofbirth);
		contentValue.put(DatabaseHelper.TODO_MOBILE, mobile);
		contentValue.put(DatabaseHelper.TODO_ADNO, adno);
		contentValue.put(DatabaseHelper.TODO_SECTION, section);
		contentValue.put(DatabaseHelper.TODO_CLASS, classes);
		contentValue.put(DatabaseHelper.TODO_DATEOFEXAM, dateofexam);
		contentValue.put(DatabaseHelper.TODO_HEIGHT, height);
		contentValue.put(DatabaseHelper.TODO_WEIGHT, weight);
		contentValue.put(DatabaseHelper.TODO_BMI,bmi);
		contentValue.put(DatabaseHelper.TODO_PULSE, pulse);
		contentValue.put(DatabaseHelper.TODO_BP, bp);
		contentValue.put(DatabaseHelper.TODO_HB, hb);
		contentValue.put(DatabaseHelper.TODO_BLOODGROUP, bloodgroup);
		contentValue.put(DatabaseHelper.TODO_DESCRIPTION1, description1);
		contentValue.put(DatabaseHelper.TODO_ADVICE, advice);
		contentValue.put(DatabaseHelper.TODO_WITHOUTR, withoutr);
		contentValue.put(DatabaseHelper.TODO_WITHOUTL, withoutl);
		contentValue.put(DatabaseHelper.TODO_WITHR, withr);
		contentValue.put(DatabaseHelper.TODO_WITHL, withl);
		contentValue.put(DatabaseHelper.TODO_RG1, rg1);
		contentValue.put(DatabaseHelper.TODO_RG2,rg2 );
		contentValue.put(DatabaseHelper.TODO_DESCRIPTION2, description2);
		contentValue.put(DatabaseHelper.TODO_RG3, rg3);
		contentValue.put(DatabaseHelper.TODO_RG4, rg4);
		contentValue.put(DatabaseHelper.TODO_DESCRIPTION3, description3);
		contentValue.put(DatabaseHelper.TODO_RG5, rg5);
		contentValue.put(DatabaseHelper.TODO_RG6,rg6 );
		contentValue.put(DatabaseHelper.TODO_RG7, rg7);
		contentValue.put(DatabaseHelper.TODO_RG8,rg8 );
		contentValue.put(DatabaseHelper.TODO_RG9, rg9);
		contentValue.put(DatabaseHelper.TODO_DESCRIPTION4,description4 );
		contentValue.put(DatabaseHelper.TODO_ABNORMALITIES,abnormalities);
		contentValue.put(DatabaseHelper.TODO_ORTHO, ortho);
		contentValue.put(DatabaseHelper.TODO_POSTURAL, postural);
		contentValue.put(DatabaseHelper.TODO_DEFECTSATBIRTH, defectsatbirth);
		contentValue.put(DatabaseHelper.TODO_DEFICIENCIES, defiencies);
		contentValue.put(DatabaseHelper.TODO_CHILDHOODDISEASES,childwooddiseases );
		contentValue.put(DatabaseHelper.TODO_DDDISABILITY, dddisability);
		contentValue.put(DatabaseHelper.TODO_SPEECHSCREENING,speechscreening );
		contentValue.put(DatabaseHelper.TODO_NAD, nad);
		contentValue.put(DatabaseHelper.TODO_DDDISABILITY, dddisability);
		contentValue.put(DatabaseHelper.TODO_SPEECHSCREENING,speechscreening );
		contentValue.put(DatabaseHelper.TODO_VISIONSCREEN, vsref);
		contentValue.put(DatabaseHelper.TODO_AUDITORYSCREEN,asref );
		contentValue.put(DatabaseHelper.TODO_DENTALRCHECK,dentalref );
		contentValue.put(DatabaseHelper.TODO_DC11,dc11);
		contentValue.put(DatabaseHelper.TODO_DC12,dc12);
		contentValue.put(DatabaseHelper.TODO_DC13,dc13);
		contentValue.put(DatabaseHelper.TODO_DC14,dc14);
		contentValue.put(DatabaseHelper.TODO_IMAGEPATH,imagepath);
		contentValue.put(DatabaseHelper.TODO_GENERAL,genaral);
		contentValue.put(DatabaseHelper.TODO_DENTAL,dental);
		contentValue.put(DatabaseHelper.TODO_OPTHO,optho);
		contentValue.put(DatabaseHelper.TODO_AUDIO,audio);
		contentValue.put(DatabaseHelper.TODO_HISTORY,history1);
		contentValue.put(DatabaseHelper.TODO_DATEANDTIME,dateandtime );
		contentValue.put(DatabaseHelper.TODO_ADVISE,advise);

		database.insert (DatabaseHelper.TABLE_NAME, null, contentValue);
	}


	public Cursor fetch() {
		String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TODO_UID, DatabaseHelper.TODO_NAME, DatabaseHelper.TODO_FATHERNAME,
				DatabaseHelper.TODO_SCHOOLNAME, DatabaseHelper.TODO_DISTRICT, DatabaseHelper.TODO_DATEOFBIRTH,
				DatabaseHelper.TODO_MOBILE, DatabaseHelper.TODO_ADNO, DatabaseHelper.TODO_SECTION, DatabaseHelper.TODO_CLASS,
				DatabaseHelper.TODO_DATEOFEXAM, DatabaseHelper.TODO_HEIGHT, DatabaseHelper.TODO_WEIGHT, DatabaseHelper.TODO_BMI,
				DatabaseHelper.TODO_PULSE, DatabaseHelper.TODO_BP, DatabaseHelper.TODO_HB, DatabaseHelper.TODO_BLOODGROUP,
				DatabaseHelper.TODO_DESCRIPTION1, DatabaseHelper.TODO_ADVICE, DatabaseHelper.TODO_WITHOUTR,
				DatabaseHelper.TODO_WITHOUTL, DatabaseHelper.TODO_WITHR, DatabaseHelper.TODO_WITHL,
				DatabaseHelper.TODO_RG1, DatabaseHelper.TODO_RG2, DatabaseHelper.TODO_DESCRIPTION2, DatabaseHelper.TODO_RG3,
				DatabaseHelper.TODO_RG4,  DatabaseHelper.TODO_DESCRIPTION3 ,DatabaseHelper.TODO_RG5, DatabaseHelper.TODO_RG6,
				DatabaseHelper.TODO_RG7,  DatabaseHelper.TODO_RG8 ,DatabaseHelper.TODO_RG9, DatabaseHelper.TODO_DESCRIPTION4, DatabaseHelper.TODO_ABNORMALITIES,
				DatabaseHelper.TODO_ORTHO,  DatabaseHelper.TODO_POSTURAL ,DatabaseHelper.TODO_DEFECTSATBIRTH, DatabaseHelper.TODO_DEFICIENCIES, DatabaseHelper.TODO_CHILDHOODDISEASES,
				DatabaseHelper.TODO_DDDISABILITY, DatabaseHelper.TODO_SPEECHSCREENING ,DatabaseHelper.TODO_NAD, DatabaseHelper.TODO_VISIONSCREEN, DatabaseHelper.TODO_AUDITORYSCREEN,
				DatabaseHelper.TODO_DENTALRCHECK, DatabaseHelper.TODO_DC11, DatabaseHelper.TODO_DC12,  DatabaseHelper.TODO_DC13,  DatabaseHelper.TODO_DC14, DatabaseHelper.TODO_IMAGEPATH,
				DatabaseHelper.TODO_GENERAL, DatabaseHelper.TODO_DENTAL, DatabaseHelper.TODO_OPTHO,  DatabaseHelper.TODO_AUDIO,  DatabaseHelper.TODO_HISTORY, DatabaseHelper.TODO_DATEANDTIME, DatabaseHelper.TODO_ADVISE};

		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null,null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}
	public void delete()
	{
        database.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + DatabaseHelper.TABLE_NAME + "'");
		database.delete(DatabaseHelper.TABLE_NAME,null,null);
		//database.execSQL("DELETE FROM "+DatabaseHelper.TABLE_NAME);
		database.close();

	}
	public void deleteRecord()
	{
		database.execSQL("DROP TABLE IF EXISTS "+DatabaseHelper.TABLE_NAME);
		database.close();
	}
	public boolean checkForTables(){

		Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " +DatabaseHelper.TABLE_NAME, null);

		if(cursor != null){

			cursor.moveToFirst();

			int count = cursor.getInt(0);

			if(count > 0){
				return true;
			}

			cursor.close();
		}
		return false;
	}

}
