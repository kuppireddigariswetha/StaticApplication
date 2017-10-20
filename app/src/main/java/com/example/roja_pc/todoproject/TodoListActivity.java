package com.example.roja_pc.todoproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import net.lingala.zip4j.io.CipherOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import de.idyl.crypto.zip.AesZipFileEncrypter;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TodoListActivity extends AppCompatActivity implements View.OnClickListener{
	byte[] filesBytes;
	private DBManager dbManager;
	DBController controller;
    public String Flagg;
	boolean menu_flag=true;
	String passedArg,signPath="";
	String Str;
	Button logouut;
	SharedPreferences pref;
	String result,range;
	int l;

	private static int RnCntr=0;
	SharedPreferences.Editor editor1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_main);
		SharedPreferences   pref1=getApplicationContext().getSharedPreferences("options",MODE_PRIVATE);
		editor1=pref1.edit();
		controller = new DBController(this);
		LinearLayout layone1= (LinearLayout) findViewById(R.id.layout1);
		LinearLayout layone2= (LinearLayout) findViewById(R.id.layout2);
		LinearLayout layone3= (LinearLayout) findViewById(R.id.layout3);
		LinearLayout layone4= (LinearLayout) findViewById(R.id.layout4);
		LinearLayout layone8= (LinearLayout) findViewById (R.id.layout8);
		LinearLayout layone0= (LinearLayout) findViewById (R.id.layout0);
		LinearLayout layone7= (LinearLayout) findViewById (R.id.layout7);
        LinearLayout layone10=(LinearLayout)findViewById(R.id.layout10);
		logouut=(Button)findViewById(R.id.logout);
		logouut.setOnClickListener(this);

		pref=getApplicationContext().getSharedPreferences("options",MODE_PRIVATE);
		passedArg=	pref.getString("one","");
		Str=pref.getString("two","");
		range=pref.getString("length","");
		Log.d("str", "" + Str);
		Log.d("passedag",passedArg);
		Log.d("range--118",range);
		String[] separated = range.split("-");
		String index=separated[0];
		Log.d("index1",index);
		if(RnCntr==0)
			RnCntr=Integer.parseInt(index);
		if(Str.equals("tswreis"))
		{
			layone3.setVisibility(View.VISIBLE);
		}
		else if(Str.equals("tmreis"))
		{
			layone2.setVisibility(View.VISIBLE);
		}else if(Str.equals("ttwreis")) {
			layone1.setVisibility(View.VISIBLE);
		}
		if(Str.equals("ameya schools"))
		{
			layone0.setVisibility(View.VISIBLE);
		}

		if(passedArg.equals("medusersw1@gmail.com"))
		{	layone4.setVisibility(View.VISIBLE);
		}else if(passedArg.equals("medusermw1@gmail.com"))
		{	layone8.setVisibility(View.VISIBLE);
		}
		else if(passedArg.equals("medusertw1@gmail.com"))
		{
			layone7.setVisibility(View.VISIBLE);
		}else if(passedArg.equals("schoolhealth.ameya@gmail.com"))
        {
            layone10.setVisibility(View.VISIBLE);
        }

		dbManager = new DBManager(this);
		dbManager.open();

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		invalidateOptionsMenu();
		getMenuInflater().inflate(R.menu.menu_main, menu);
		MenuItem item = menu.findItem(R.id.add_rar);

		return true;
	}
	public void onBackPressed()
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(TodoListActivity.this);
		// Setting Dialog Title
		alertDialog.setTitle(" Exit");
		// Setting Dialog Message
		alertDialog.setMessage("Do you want to exit.");
		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {
				RnCntr=0;
				Intent i=new Intent(getApplicationContext(),MedNoteMain.class);
				startActivity(i);
			}
		});
		// Setting Negative "NO" Button
		alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		// Showing Alert Message
		alertDialog.show();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.add_record) {
				RnCntr++;
				String str1=Integer.toString(RnCntr);
				Intent add_mem = new Intent(this, AddTodoActivity.class);
				editor1.putString("three",Str);
				editor1.putString("four",passedArg);
			    editor1.putString("length",str1);
				editor1.commit();
				startActivity(add_mem);

		} else if (id == R.id.add_sign) {

			Intent add1=new Intent(this,CaptureSignatureAcivity.class);
			startActivity(add1);

		}else if (id == R.id.add_list) {

			Intent add1=new Intent(this,ListClass.class);
			startActivity(add1);

		}/*else if (id == R.id.export_records) {

                Cursor cursor = dbManager.fetch();
                try {
                    exportToExcel(cursor);

                } catch (IOException e) {
                    e.printStackTrace();
                }

		}*/ else if (id == R.id.import_records) {
			Intent add=new Intent(this,MainActivity1.class);
			startActivity(add);

		} else if (id == R.id.add_rar) {

            Cursor cursor = dbManager.fetch();
            try {
                exportToExcel(cursor);

            } catch (IOException e) {
                e.printStackTrace();
            }

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(TodoListActivity.this);
            // Setting Dialog Title
            alertDialog.setTitle(" Zip File");
            // Setting Dialog Message
            alertDialog.setMessage("Do you want to Zip a file ");
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    File folder = new File(Environment.getExternalStorageDirectory() + "/Health");

                    SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                    String format = s.format(new Date());

                    boolean success = true;
                    if (!folder.exists()) {
                        success = folder.mkdir();
                    }
                    if (success) {
                    } else {
                    }
                    folder.setExecutable(true);
                    folder.setReadable(true);
                    folder.setWritable(true);
                    MediaScannerConnection.scanFile(getApplicationContext(), new String[] {folder.toString()}, null, null);
                    String str="/storage/emulated/0/Health/"+format+".zip";

                    if(zipFileAtPath("/storage/emulated/0/MedNote",str))
                    {
                        MediaScannerConnection.scanFile(getApplicationContext(), new String[] {str}, null, null);
                        dbManager.delete();
                        Flagg="true";
                        deleteFiles("/storage/emulated/0/MedNote");
						deleteFiles("/storage/emulated/0/Pictures");
					//	dbManager.deleteRecord();
                        Toast.makeText(getApplicationContext(),"sucess",Toast.LENGTH_LONG).show();
                    }

                }
            });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            // Showing Alert Message
            alertDialog.show();



		}
		return super.onOptionsItemSelected(item);
	}
	public static void deleteFiles(String path) {

		File file = new File(path);

		if (file.exists()) {
			String deleteCmd = "rm -r " + path;
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec(deleteCmd);
			} catch (IOException e) { }
		}
	}
	public boolean zipFileAtPath(String sourcePath, String toLocation) {

		final int BUFFER = 8000;
		File sourceFile = new File(sourcePath);
		try {
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(toLocation);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
			if (sourceFile.isDirectory()) {
				zipSubFolder(out, sourceFile, sourceFile.getParent().length());
			} else {
				byte data[] = new byte[BUFFER];
				FileInputStream fi = new FileInputStream(sourcePath);
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry(getLastPathComponent(sourcePath));
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

/*
 *
 * Zips a subfolder
 *
 */

	private void zipSubFolder(ZipOutputStream out, File folder,
							  int basePathLength) throws IOException {

		final int BUFFER = 8000;
		File[] fileList = folder.listFiles();
		BufferedInputStream origin = null;
		for (File file : fileList) {
			if (file.isDirectory()) {
				zipSubFolder(out, file, basePathLength);
			} else {
				byte data[] = new byte[BUFFER];

				String unmodifiedFilePath = file.getPath();
				String relativePath = unmodifiedFilePath.substring(basePathLength);
				FileInputStream fi = new FileInputStream(unmodifiedFilePath);
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry(relativePath);
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
			}
		}
	}

	/*
     * gets the last path component
     *
     * Example: getLastPathComponent("downloads/example/fileToZip");
     * Result: "fileToZip"
     */
	public String getLastPathComponent(String filePath) {
		String[] segments = filePath.split("/");
		if (segments.length == 0)
			return "";
		String lastPathComponent = segments[segments.length - 1];
		return lastPathComponent;
	}



	/**
	 * Exports the cursor value to an excel sheet.
	 * Recommended to call this method in a separate thread,
	 * especially if you have more number of threads.
	 *
	 * @param cursor
	 */

	private void exportToExcel(Cursor cursor) throws IOException {
		final String fileName = "Screening.xls";

		//Saving file in external storage
		File sdCard = Environment.getExternalStorageDirectory();
		File directory = new File(sdCard.getAbsolutePath() + "/MedNote");

		//create directory if not exist
		if (!directory.isDirectory()) {
			directory.mkdirs();
		}

		//file path
		File file = new File(directory, fileName);

		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("en", "EN"));
		WritableWorkbook workbook;

		try {
			workbook = Workbook.createWorkbook(file, wbSettings);
			//Excel sheet name. 0 represents first sheet
			WritableSheet sheet = workbook.createSheet("Documentation", 0);
			//sheet.setColumnWidth(0, (15 * 500));
			try {
				sheet.addCell(new Label(0, 0, "HOSPITAL UNIQUE ID")); // column and row
				sheet.addCell(new Label(1, 0, "STUDENT NAME"));
				sheet.addCell(new Label(2, 0, "FATHER NAME"));
				sheet.addCell(new Label(3, 0, "SCHOOL NAME"));
				sheet.addCell(new Label(4, 0, "DISTRICT"));
				sheet.addCell(new Label(5, 0, "DATE OF BIRTH"));
				sheet.addCell(new Label(6, 0, "MOBILE NUMBER"));
				sheet.addCell(new Label(7, 0, "AD NO"));
				sheet.addCell(new Label(8, 0, "SECTION"));
				sheet.addCell(new Label(9, 0, "CLASS"));
				sheet.addCell(new Label(10, 0, "DATE OF EXAM"));
				sheet.addCell(new Label(11, 0, "HEIGHT"));
				sheet.addCell(new Label(12, 0, "WEIGHT"));
				sheet.addCell(new Label(13, 0, "BMI"));
				sheet.addCell(new Label(14, 0, "PULSE"));
				sheet.addCell(new Label(15, 0, "B P"));
				sheet.addCell(new Label(16, 0, "H B"));
				sheet.addCell(new Label(17, 0, "BLOOD GROUP"));
				sheet.addCell(new Label(18, 0, "DESCRIPTION1"));
				sheet.addCell(new Label(19, 0, "SKIN CONDITIONS"));
				sheet.addCell(new Label(20, 0, "WITHOUT RIGHT"));
				sheet.addCell(new Label(21, 0, "WITHOUT LEFT"));
				sheet.addCell(new Label(22, 0, "WITH RIGHT"));
				sheet.addCell(new Label(23, 0, "WITH LEFT"));
				sheet.addCell(new Label(24, 0, "COLOUR BLINDNESS RIGHT"));
				sheet.addCell(new Label(25, 0, "COLOUR BLINDNESS LEFT"));
				sheet.addCell(new Label(26, 0, "DESCRIPTION2"));
				sheet.addCell(new Label(27, 0, "AUDITORY SCREENING RIGHT"));
				sheet.addCell(new Label(28, 0, "AUDITORY SCREENING LEFT"));
				sheet.addCell(new Label(29, 0, "DESCRIPTION3"));
				sheet.addCell(new Label(30, 0, "ORAL HYGIENE"));
				sheet.addCell(new Label(31, 0, "CARIOUS TEETH"));
				sheet.addCell(new Label(32, 0, "FLOUROSIS"));
				sheet.addCell(new Label(33, 0, "ORTHODONIC TREATMENT"));
				sheet.addCell(new Label(34, 0, "INDICATION TREATMENT"));
				sheet.addCell(new Label(35, 0, "DESCRIPTION4"));
				sheet.addCell(new Label(36, 0, "ABNORMALITIES"));
				sheet.addCell(new Label(37, 0, "ORTHO"));
				sheet.addCell(new Label(38, 0, "POSTURAL"));
				sheet.addCell(new Label(39, 0, "DEFECTS AT BIRTH"));
				sheet.addCell(new Label(40, 0, "DEFICIENCIES"));
				sheet.addCell(new Label(41, 0, "CHILDHOOD DISEASES"));
				sheet.addCell(new Label(42, 0, "DD&DISABILITY"));
				sheet.addCell(new Label(43, 0, "SPEECH SCREENING"));
				sheet.addCell(new Label(44, 0, "N A D"));
				sheet.addCell(new Label(45, 0, "VISION SCREEN REFERRAL"));
				sheet.addCell(new Label(46, 0, "AUDITORY SCREEN REFERRAL"));
				sheet.addCell(new Label(47, 0, "DENTAL CHECK REFERRAL"));
				sheet.addCell(new Label(48, 0, "DC 11"));
				sheet.addCell(new Label(49, 0, "DC 12"));
				sheet.addCell(new Label(50, 0, "DC 13"));
				sheet.addCell(new Label(51, 0, "DC 14"));
				sheet.addCell(new Label(52, 0, "IMAGEPATH"));
				sheet.addCell(new Label(53, 0, "GENERAL PHYSIAN SIGN"));
				sheet.addCell(new Label(54, 0, "DENTIST SIGN"));
				sheet.addCell(new Label(55, 0, "OPTHOMOLOGIST SIGN"));
				sheet.addCell(new Label(56, 0, "AUDIOLOGIST SIGN"));
				sheet.addCell(new Label(57, 0, "HISTORY"));
				sheet.addCell(new Label(58, 0, "ATTACHMENTS"));
				sheet.addCell(new Label(59, 0, "ADVICE"));

				sheet.setColumnView(0, (25));
				sheet.setColumnView(1, (25));
				sheet.setColumnView(2, (25));
				sheet.setColumnView(3, (25));
				sheet.setColumnView(4, (25));
				sheet.setColumnView(5, (25));
				sheet.setColumnView(6, (25));
				sheet.setColumnView(7, (25));
				sheet.setColumnView(8, (25));
				sheet.setColumnView(9, (25));
				sheet.setColumnView(10, (25));
				sheet.setColumnView(11, (25));
				sheet.setColumnView(12, (25));
				sheet.setColumnView(13, (25));
				sheet.setColumnView(14, (25));
				sheet.setColumnView(15, (25));
				sheet.setColumnView(16, (25));
				sheet.setColumnView(17, (25));
				sheet.setColumnView(18, (25));
				sheet.setColumnView(19, (25));
				sheet.setColumnView(20, (25));
				sheet.setColumnView(21, (25));
				sheet.setColumnView(22, (25));
				sheet.setColumnView(23, (25));
				sheet.setColumnView(24, (25));
				sheet.setColumnView(25, (25));
				sheet.setColumnView(26, (25));
				sheet.setColumnView(27, (25));
				sheet.setColumnView(28, (25));
				sheet.setColumnView(29, (25));
				sheet.setColumnView(30, (25));
				sheet.setColumnView(31, (25));
				sheet.setColumnView(32, (25));
				sheet.setColumnView(33, (25));
				sheet.setColumnView(34, (25));
				sheet.setColumnView(35, (25));
				sheet.setColumnView(36, (25));
				sheet.setColumnView(37, (25));
				sheet.setColumnView(38, (25));
				sheet.setColumnView(39, (25));
				sheet.setColumnView(40, (25));
				sheet.setColumnView(41, (25));
				sheet.setColumnView(42, (25));
				sheet.setColumnView(43, (25));
				sheet.setColumnView(44, (25));
				sheet.setColumnView(45, (25));
				sheet.setColumnView(46, (25));
				sheet.setColumnView(47, (25));
				sheet.setColumnView(48, (25));
				sheet.setColumnView(49, (25));
				sheet.setColumnView(50, (25));
				sheet.setColumnView(51, (25));
				sheet.setColumnView(52, (25));
				sheet.setColumnView(53, (25));
				sheet.setColumnView(54, (25));
				sheet.setColumnView(55, (25));
				sheet.setColumnView(56, (25));
				sheet.setColumnView(57, (25));
				sheet.setColumnView(58, (25));
				sheet.setColumnView(59, (25));


				if (cursor.moveToFirst()) {
					do {
						String UID1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_UID));
						String NAME1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_NAME));
						String FATHERNAME1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_FATHERNAME));
						String SCHOOLNAME1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_SCHOOLNAME));
						String DISTRICT1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DISTRICT));
						String DATEOFBIRTH1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DATEOFBIRTH));
						String MOBILE1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_MOBILE));
						String ADNO1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_ADNO));
						String SECTION1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_SECTION));
						String CLASS1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_CLASS));
						String  DATEOFEXAM1= cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DATEOFEXAM));
						String HEIGHT1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_HEIGHT));
						String WEIGHT1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_WEIGHT));
						String BMI1=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_BMI));
						String PULSE1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_PULSE));
						String BP1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_BP));
						String HB1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_HB));
						String BLOODGROUP1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_BLOODGROUP));
						String DESCRIPTION1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DESCRIPTION1));
						String ADVICE1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_ADVICE));
						String WITHOUTR1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_WITHOUTR));
						String WITHOUTL1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_WITHOUTL));
						String WITHR = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_WITHR));
						String WITHL = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_WITHL));
						String RG1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_RG1));
						String RG2 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_RG2));
						String DESCRIPTION2 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DESCRIPTION2));
						String RG3 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_RG3));
						String RG4 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_RG4));
						String DESCRIPTION3 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DESCRIPTION3));
						String RG5 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_RG5));
						String RG6 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_RG6));
						String RG7 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_RG7));
						String RG8 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_RG8));
						String RG9 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_RG9));
						String DESCRIPTION4 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DESCRIPTION4));
						String ABNORMALITIES=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_ABNORMALITIES));
						String ORTHO = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_ORTHO));
						String POSTURAL = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_POSTURAL));
						String DEFECTSATBIRTH = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DEFECTSATBIRTH));
						String DEFICIENCIES = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DEFICIENCIES));
						String CHILDHOODDISEASES = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_CHILDHOODDISEASES));
						String DDDISABILITY = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DDDISABILITY));
						String SPEECHSCREEN = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_SPEECHSCREENING));
						String NAD = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_NAD));
						String VISIONSCREEN = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_VISIONSCREEN));
						String AUDITORYSCREEN = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_AUDITORYSCREEN));
						String DENTALREF=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DENTALRCHECK));
						String DC1=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DC11));
						String DC2=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DC12));
						String DC3=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DC13));
						String DC4=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DC14));
						String IMAGEPATH=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_IMAGEPATH));
						String GENERAL=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_GENERAL));
						String DENTAL=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DENTAL));
						String OPTHO=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_OPTHO));
						String AUDIO=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_AUDIO));
						String HISTORY=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_HISTORY));
						String ATTACHMENTS=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DATEANDTIME));
						String ADVICE=cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_ADVISE));
						Log.d("exported", "bbbbbbbbbbbbbb");


						int i = cursor.getPosition() + 1;
						sheet.addCell(new Label(0, i, UID1));
						sheet.addCell(new Label(1, i, NAME1));
						sheet.addCell(new Label(2, i, FATHERNAME1));
						sheet.addCell(new Label(3, i, SCHOOLNAME1));
						sheet.addCell(new Label(4, i, DISTRICT1));
						sheet.addCell(new Label(5, i, DATEOFBIRTH1));
						sheet.addCell(new Label(6, i, MOBILE1));
						sheet.addCell(new Label(7, i, ADNO1));
						sheet.addCell(new Label(8, i, SECTION1));
						sheet.addCell(new Label(9, i, CLASS1));
						sheet.addCell(new Label(10, i, DATEOFEXAM1));
						sheet.addCell(new Label(11, i, HEIGHT1));
						sheet.addCell(new Label(12, i, WEIGHT1));
						sheet.addCell(new Label(13, i, BMI1));
						sheet.addCell(new Label(14, i, PULSE1));
						sheet.addCell(new Label(15, i, BP1));
						sheet.addCell(new Label(16, i, HB1));
						sheet.addCell(new Label(17, i, BLOODGROUP1));
						sheet.addCell(new Label(18, i, DESCRIPTION1));
						sheet.addCell(new Label(19, i, ADVICE1));
						sheet.addCell(new Label(20, i, WITHOUTR1));
						sheet.addCell(new Label(21, i, WITHOUTL1));
						sheet.addCell(new Label(22, i, WITHR));
						sheet.addCell(new Label(23, i, WITHL));
						sheet.addCell(new Label(24, i, RG1));
						sheet.addCell(new Label(25, i, RG2));
						sheet.addCell(new Label(26, i, DESCRIPTION2));
						sheet.addCell(new Label(27, i, RG3));
						sheet.addCell(new Label(28, i, RG4));
						sheet.addCell(new Label(29, i, DESCRIPTION3));
						sheet.addCell(new Label(30, i, RG5));
						sheet.addCell(new Label(31, i, RG6));
						sheet.addCell(new Label(32, i, RG7));
						sheet.addCell(new Label(33, i, RG8));
						sheet.addCell(new Label(34, i, RG9));
						sheet.addCell(new Label(35, i, DESCRIPTION4));
						sheet.addCell(new Label(36, i, ABNORMALITIES));
						sheet.addCell(new Label(37, i, ORTHO));
						sheet.addCell(new Label(38, i, POSTURAL));
						sheet.addCell(new Label(39, i, DEFECTSATBIRTH));
						sheet.addCell(new Label(40, i, DEFICIENCIES));
						sheet.addCell(new Label(41, i, CHILDHOODDISEASES));
						sheet.addCell(new Label(42, i, DDDISABILITY));
						sheet.addCell(new Label(43, i, SPEECHSCREEN));
						sheet.addCell(new Label(44, i, NAD));
						sheet.addCell(new Label(45, i, VISIONSCREEN));
						sheet.addCell(new Label(46, i, AUDITORYSCREEN));
						sheet.addCell(new Label(47, i, DENTALREF));
						sheet.addCell(new Label(48, i, DC1));
						sheet.addCell(new Label(49, i, DC2));
						sheet.addCell(new Label(50, i, DC3));
						sheet.addCell(new Label(51, i, DC4));
						sheet.addCell(new Label(52, i, IMAGEPATH));
						sheet.addCell(new Label(53, i, GENERAL));
						sheet.addCell(new Label(54, i, DENTAL));
						sheet.addCell(new Label(55, i, OPTHO));
						sheet.addCell(new Label(56, i, AUDIO));
						sheet.addCell(new Label(57, i, HISTORY));
						sheet.addCell(new Label(58, i, ATTACHMENTS));
						sheet.addCell(new Label(59, i, ADVICE));
					} while (cursor.moveToNext());
				}
				//closing cursor
				cursor.close();
			}  catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
			workbook.write();
			try {
				workbook.close();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	/*	AlertDialog.Builder builder =
				new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
		builder.setTitle(" Exit");
		builder.setMessage("Data Exported Successfully ....");
		builder.setPositiveButton("OK", null);
		builder.show();*/
	}


	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.logout:
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(TodoListActivity.this);
				// Setting Dialog Title
				alertDialog.setTitle(" Logout");
				// Setting Dialog Message
				alertDialog.setMessage("Do you want to logout the application please make sure that you clicked on Zip button or not ");
				// Setting Positive "Yes" Button
				alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int which) {
						RnCntr=0;
						Intent i=new Intent(getApplicationContext(),MedNoteMain.class);
						startActivity(i);
						Toast.makeText(getApplicationContext(),"logout successfully ",Toast.LENGTH_SHORT).show();
					}
				});
				// Setting Negative "NO" Button
				alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				// Showing Alert Message
				alertDialog.show();
		}
	}
}












