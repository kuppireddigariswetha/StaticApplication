package com.example.roja_pc.todoproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Table Name
	public static final String TABLE_NAME = "Mednote";

	// Table columns
	public static final String _ID = "_id";
	public static final String TODO_UID = "uid";
	public static final String TODO_NAME = "name";
	public static final String TODO_FATHERNAME = "fathername";
	public static final String TODO_SCHOOLNAME= "Schoolname";
	public static final String TODO_DISTRICT = "district";
	public static final String TODO_DATEOFBIRTH = "dateofbirth";
	public static final String TODO_MOBILE="mobile";
	public static final String TODO_ADNO = "adno";
	public static final String TODO_SECTION= "section";
	public static final String TODO_CLASS = "class";
	public static final String TODO_DATEOFEXAM= "dateofexam";
	public static final String TODO_HEIGHT = "height";
	public static final String TODO_WEIGHT = "weight";
	public static final String TODO_BMI="bmi";
	public static final String TODO_IMAGEPATH="imagepath";
	public static final String TODO_PULSE="pulse";
	public static final String TODO_BP = "bp";
	public static final String TODO_HB = "hb";
	public static final String TODO_BLOODGROUP = "bloodgroup";
	public static final String TODO_ABNORMALITIES= "abnormalities";
	public static final String TODO_ORTHO= "ortho";
	public static final String TODO_POSTURAL= "postural";
	public static final String TODO_DEFECTSATBIRTH= "defectsatbirth";
	public static final String TODO_DEFICIENCIES= "defiencies";
	public static final String TODO_CHILDHOODDISEASES= "childwooddiseases";
	public static final String TODO_DDDISABILITY= "dddisability";
	public static final String TODO_SPEECHSCREENING= "speechscreening";
	public static final String TODO_NAD= "NAD";
	public static final String TODO_VISIONSCREEN= "VISONSCREEN";
	public static final String TODO_AUDITORYSCREEN= "AUDITORYSCREEN";
	public static final String TODO_DENTALRCHECK= "DENTALCHECK";
	public static final String TODO_DESCRIPTION1 = "description1";
	public static final String TODO_ADVICE = "advice";
	public static final String TODO_WITHOUTR="withoutright";
	public static final String TODO_WITHOUTL="withoutleft";
	public static final String TODO_WITHR="withright";
	public static final String TODO_WITHL="withleft";
	public static final String TODO_RG1 ="rg1";
	public static final String TODO_RG2="rg2";
	public static final String TODO_DESCRIPTION2="description2";
	public static final String TODO_RG3="rg3";
	public static final String TODO_RG4="rg4";
	public static final String TODO_DESCRIPTION3="description3";
	public static final String TODO_RG5 = "rg5";
	public static final String TODO_RG6="rg6";
	public static final String TODO_RG7="rg7";
	public static final String TODO_RG8="rg8";
	public static final String TODO_RG9="rg9";
	public static final String TODO_DESCRIPTION4 = "discription4";
	public static final String TODO_DC11="dc11";
	public static final String TODO_DC12="dc12";
	public static final String TODO_DC13="dc13";
	public static final String TODO_DC14="dc14";
	public static final String TODO_GENERAL="general";
	public static final String TODO_DENTAL = "dental";
	public static final String TODO_OPTHO="opthomologist";
	public static final String TODO_AUDIO="audio";
	public static final String TODO_HISTORY="history";
	public static final String TODO_DATEANDTIME="dateandtime";
	public static final String TODO_ADVISE="advise";

	// Database Information
	static final String DB_NAME = "ANDROID.DB";

	// database version
	static final int DB_VERSION = 8;


	// Creating table query
	/*private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO_UID + " TEXT NOT NULL, `" + TODO_NAME + " TEXT, " + TODO_FATHERNAME + " TEXT, " + TODO_SCHOOLNAME + " TEXT, " + TODO_DATEOFBIRTH + " TEXT, " + TODO_DISTRICT + " TEXT);";
*/
	private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +  _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO_UID + " TEXT NOT NULL, " + TODO_NAME + " TEXT, " + TODO_FATHERNAME + " TEXT, " + TODO_SCHOOLNAME + " TEXT, " +
			TODO_DATEOFBIRTH + " TEXT, " + TODO_DISTRICT + " TEXT, " + TODO_MOBILE + " TEXT, " + TODO_ADNO + " TEXT, " + TODO_SECTION + " TEXT, " +
			TODO_CLASS + " TEXT, " + TODO_DATEOFEXAM + " TEXT, " + TODO_HEIGHT + " TEXT, "+ TODO_WEIGHT + " TEXT, " + TODO_BMI + " TEXT, " + TODO_PULSE + " TEXT, " +
			TODO_BP + " TEXT, " + TODO_HB + " TEXT, " + TODO_BLOODGROUP + " TEXT, "+ TODO_DESCRIPTION1 + " TEXT, " + TODO_ADVICE + " TEXT, " +
			TODO_WITHOUTR + " TEXT, " + TODO_WITHOUTL + " TEXT, " + TODO_WITHR + " TEXT, "+ TODO_WITHL + " TEXT, " + TODO_DESCRIPTION2 + " TEXT, " +
			TODO_RG1 + " TEXT, " + TODO_RG2 + " TEXT, " + TODO_DESCRIPTION3 + " TEXT, " + TODO_RG3 + " TEXT, " + TODO_RG4 + " TEXT, "+
			TODO_RG5 + " TEXT, " + TODO_RG6 + " TEXT, " + TODO_RG7 + " TEXT, " + TODO_RG8 + " TEXT, " + TODO_RG9 + " TEXT, " + TODO_DESCRIPTION4 + " TEXT, " +
			TODO_ABNORMALITIES + " TEXT, " + TODO_ORTHO + " TEXT, " + TODO_POSTURAL + " TEXT, " + TODO_DEFECTSATBIRTH + " TEXT, " + TODO_DEFICIENCIES + " TEXT, " + TODO_CHILDHOODDISEASES + " TEXT, " +
			TODO_DDDISABILITY + " TEXT, " + TODO_SPEECHSCREENING + " TEXT, " + TODO_NAD + " TEXT, " + TODO_VISIONSCREEN + " TEXT, " + TODO_AUDITORYSCREEN + " TEXT, " +
			TODO_DENTALRCHECK + " TEXT, " + TODO_DC11 + " TEXT," +  TODO_DC12 + " TEXT," + TODO_DC13 + " TEXT," + TODO_DC14 + " TEXT," +
			TODO_IMAGEPATH + " TEXT," +  TODO_GENERAL + " TEXT," + TODO_DENTAL + " TEXT," + TODO_OPTHO + " TEXT," + TODO_AUDIO + " TEXT," + TODO_HISTORY + " TEXT," + TODO_DATEANDTIME + " TEXT," +  TODO_ADVISE + " TEXT);";


	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}



}