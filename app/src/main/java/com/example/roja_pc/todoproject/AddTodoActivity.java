package com.example.roja_pc.todoproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class AddTodoActivity extends Activity implements AdapterView.OnItemSelectedListener , OnClickListener  {
   private  String selected1,selected2,selected3,selected4,selected5,selected6,selected7,selected8,selected9;
    String result;
    byte[] BYTE;
    ByteArrayOutputStream bytearrayoutputstream;
    private String indication1,ortho1,flouro1,cal1,orl1,asr1,asl1,cbr1,cbl1;
    ArrayList<String> limits1= new ArrayList<String>();
    ArrayList<String> limits2= new ArrayList<String>();
    ArrayList<String> limits3= new ArrayList<String>();
    ArrayList<String> limits4= new ArrayList<String>();
    ArrayList<String> limits5= new ArrayList<String>();
    ArrayList<String> limits6= new ArrayList<String>();
    ArrayList<String> limits7= new ArrayList<String>();
    ArrayList<String> limits8= new ArrayList<String>();
    ArrayList<String> limits9= new ArrayList<String>();
    ArrayList<String> limits10=new ArrayList<>();
    public static String Flag_New,Flag_old;
    boolean imageError=true;
    List<String> list = new ArrayList<String>();
    String nad1,vsref1,asref1,dcref1;
    static String Uid;
    List<String> imagesEncodedList;
    String ImagePathh;
    String ImagePathh1;
    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    boolean braked=false;
   // static EditText Uid;
   public static EditText ed1,ed2,ed3;
   public static TextView tx1,tx2;
    ImageView img1,img2,img3,img4;
    ArrayList<String> listtt=new ArrayList<String>();
    ArrayList<String> listt11=new ArrayList<>();
    SharedPreferences pref1;
    String Attachments;
    String Attachments1;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    private Uri fileUri; // file url to store image/video
    private ImageView imgPreview,seach;
    EditText Name,Fathername,Schoolname,District,Dateofbirth,Mobile,Adno,Section,Cls,Doe,Height,Bmi,Weight,Pulse,Bp,Hb;
    Spinner s1,sp2,sp3,sp4,sp5,sp6,sp7,sp8,sp9;
    EditText dcdesc,Withoutr,Withoutl,Withr,Withl,cbdesc,asdesc,dentaldesc,descc;
    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9;
    RadioButton lvl_mon1;
    String signature1,signature2,signature3,signature4;
    Calendar myCalendar = Calendar.getInstance();
	private Button addTodoBtn;
    String s2,s3,s4,s5,s6,s7,s8,s9,s10,ar,br,cr;
    static String Path;
    SharedPreferences.Editor editor2;
    String bmii = null;
    private DBManager dbManager;
    DBController controller;
    String iname;
    String Image_path;
    int numberOfImages;
    String passedArg;
    public int z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_record);
        controller = new DBController(this);
        SharedPreferences pref3=getApplicationContext().getSharedPreferences("options",MODE_PRIVATE);
        editor2=pref3.edit();
        LinearLayout melay1= (LinearLayout) findViewById(R.id.melayout1);
        LinearLayout melay2= (LinearLayout) findViewById(R.id.melayout2);
        LinearLayout melay3= (LinearLayout) findViewById(R.id.melayout3);
        LinearLayout melay4= (LinearLayout) findViewById(R.id.melayout4);
        bytearrayoutputstream = new ByteArrayOutputStream();
        pref1=getApplicationContext().getSharedPreferences("options",MODE_PRIVATE);
        ar =pref1.getString("three","");
        br= pref1.getString("four","");
       cr=pref1.getString("length","");
       Log.d("crrr",cr);

        if(ar.equals("tswreis"))
        {
           melay1.setVisibility(View.VISIBLE);
        }else if(ar.equals("tmreis")){
            melay2.setVisibility(View.VISIBLE);
        }else if(ar.equals("ttwreis"))
        {
            melay3.setVisibility(View.VISIBLE);
        }
        else if(ar.equals("ameya schools"))
        {
            melay4.setVisibility(View.VISIBLE);
        }

        seach=(ImageView)findViewById(R.id.search);
        imgPreview=(ImageView)findViewById(R.id.eimage);

        ed1=(EditText)findViewById(R.id.ename1);
        //ed2=(EditText)findViewById(R.id.ename2);
        ed3=(EditText)findViewById(R.id.ename3);
        tx1=(TextView)findViewById(R.id.etext2) ;
       // tx2=(TextView)findViewById(R.id.etext2);



        Name=(EditText)findViewById(R.id.enamee);

        img1=(ImageView)findViewById(R.id.imageii1);
        img2=(ImageView)findViewById(R.id.imageii2);
        img3=(ImageView)findViewById(R.id.imageii3);
        img4=(ImageView)findViewById(R.id.imageii4);

        String str1 = CaptureSignatureAcivity.signpath1;
        signature1=str1;
        Log.d("sign1--148",""+str1);
        String str2 = CaptureSignatureAcivity.signpath3;
        signature2=str2;
        Log.d("sign1--150",""+str2);
        String str3 = CaptureSignatureAcivity.signpath4;
        signature3=str3;
        Log.d("sign1--152",""+str3);
        String str4 = CaptureSignatureAcivity.signpath2;
        signature4=str4;
        Log.d("sign1--154",""+str4);
       if(str1!=null) {
           File imgFile = new File(str1);
           if (imgFile.exists()) {
               Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
               img1.setImageBitmap(myBitmap);
               Log.d("imageeeeeeeeeeeee",img1.toString());

           }
       }

        if(str2!=null) {
            File imgFile = new File(str2);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                img2.setImageBitmap(myBitmap);
            }
        }
        if(str3!=null) {
            File imgFile = new File(str3);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                img3.setImageBitmap(myBitmap);
            }
        }
        if(str4!=null) {
            File imgFile = new File(str4);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                img4.setImageBitmap(myBitmap);
            }
        }
       /* if(str4==null&&str1==null&&str3==null&&str4==null)
       {
           AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddTodoActivity.this);
           // Setting Dialog Title
           alertDialog.setTitle("Confirm ...");
           // Setting Dialog Message
           alertDialog.setMessage("Do you want to submit your form with out doctors signature.");
           // Setting Positive "Yes" Button
           alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog,int which) {
               }
           });
           // Setting Negative "NO" Button
           alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                  Intent i=new Intent(getApplicationContext(),TodoListActivity.class);
                   startActivity(i);
               }
           });
           // Showing Alert Message
           alertDialog.show();
       }*/
        String str10=ed1.getText().toString();
        String str13=tx1.getText().toString();
        String str14=ed3.getText().toString();
        Uid=str10+str13+str14;

        Fathername=(EditText)findViewById(R.id.efathername);
        Schoolname=(EditText)findViewById(R.id.eschoolname);
        District=(EditText)findViewById(R.id.edistrict);
        Dateofbirth=(EditText)findViewById(R.id.edob);
        Mobile=(EditText)findViewById(R.id.emobile);
        Adno=(EditText)findViewById(R.id.eAdno);
        Section=(EditText)findViewById(R.id.eexamdate);
        Cls=(EditText)findViewById(R.id.eclas);

        Doe=(EditText)findViewById(R.id.esection);

        SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yy" );
        Doe.setText( sdf.format( new Date() ));
        Height=(EditText)findViewById(R.id.eheight);
        Weight=(EditText)findViewById(R.id.eweight);

        Pulse=(EditText)findViewById(R.id.epulse);
        Bmi=(EditText)findViewById(R.id.ebmi);

        Bp=(EditText)findViewById(R.id.ebp);
        Hb=(EditText)findViewById(R.id.ehb);
        dcdesc=(EditText)findViewById(R.id.edesc);
        descc=(EditText) findViewById(R.id.edesc11);
        cbdesc=(EditText)findViewById(R.id.cbdesc);
        asdesc=(EditText)findViewById(R.id.asdesc);
        dentaldesc=(EditText)findViewById(R.id.dentaldesc);

        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        rg3 = (RadioGroup) findViewById(R.id.rg3);
        rg4 = (RadioGroup) findViewById(R.id.rg4);
        rg5 = (RadioGroup) findViewById(R.id.rg5);
        rg6 = (RadioGroup) findViewById(R.id.rg6);
        rg7 = (RadioGroup) findViewById(R.id.rg7);
        rg8 = (RadioGroup) findViewById(R.id.rg8);
        rg9=(RadioGroup)findViewById(R.id.rg9);

        s1=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.blood_array,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(this);

        sp2=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.color_blind,android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter1);
        sp2.setOnItemSelectedListener(this);

        sp3=(Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.color_blind,android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter2);
        sp3.setOnItemSelectedListener(this);

        sp4=(Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(this,R.array.color_blind,android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp4.setAdapter(adapter3);
        sp4.setOnItemSelectedListener(this);

        sp5=(Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4=ArrayAdapter.createFromResource(this,R.array.color_blind,android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp5.setAdapter(adapter4);
        sp5.setOnItemSelectedListener(this);

        sp6=(Spinner) findViewById(R.id.spinner11);
        ArrayAdapter<CharSequence> adapter5=ArrayAdapter.createFromResource(this,R.array.dental_cavities1,android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp6.setAdapter(adapter5);
        sp6.setOnItemSelectedListener(this);

        sp7=(Spinner) findViewById(R.id.spinner12);
        ArrayAdapter<CharSequence> adapter6=ArrayAdapter.createFromResource(this,R.array.
                dental_cavities2,android.R.layout.simple_spinner_dropdown_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp7.setAdapter(adapter6);
        sp7.setOnItemSelectedListener(this);

        sp8=(Spinner) findViewById(R.id.spinner21);
        ArrayAdapter<CharSequence> adapter7=ArrayAdapter.createFromResource(this,R.array.dental_cavities3,android.R.layout.simple_spinner_dropdown_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp8.setAdapter(adapter7);
        sp8.setOnItemSelectedListener(this);

        sp9=(Spinner) findViewById(R.id.spinner22);
        ArrayAdapter<CharSequence> adapter8=ArrayAdapter.createFromResource(this,R.array.dental_cavities4,android.R.layout.simple_spinner_dropdown_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp9.setAdapter(adapter8);
        sp9.setOnItemSelectedListener(this);

        seach.setOnClickListener(this);
        imgPreview.setOnClickListener(this);
        Dateofbirth.setOnClickListener(this);
        Doe.setOnClickListener(this);
        Pulse.setOnClickListener(this);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                lvl_mon1= (RadioButton)findViewById(checkedId);
                switch (checkedId) {
                    case R.id.cbrleft:
                        Log.d("yes",lvl_mon1.getText().toString());
                        asr1="Yes";
                        break;
                    case R.id.cbrright:
                        Log.d("no",lvl_mon1.getText().toString());
                        asr1="No";
                        break;
                }
            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                lvl_mon1= (RadioButton)findViewById(checkedId);
                switch (checkedId) {
                    case R.id.cblleft:
                        Log.d("yes",lvl_mon1.getText().toString());
                        asl1="Yes";
                        break;
                    case R.id.cblright:
                        Log.d("no",lvl_mon1.getText().toString());
                        asl1="No";
                        break;
                }
            }
        });
        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                lvl_mon1= (RadioButton)findViewById(checkedId);
                switch (checkedId) {
                    case R.id.asleft1:
                        Log.d("Pass",lvl_mon1.getText().toString());
                        cbr1="Pass";
                        break;
                    case R.id.asright1:
                        Log.d("no",lvl_mon1.getText().toString());
                        cbr1="Fail";
                        break;
                }
            }
        });

        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                lvl_mon1= (RadioButton)findViewById(checkedId);
                switch (checkedId) {
                    case R.id.asleft2:
                        Log.d("yes",lvl_mon1.getText().toString());
                        cbl1="Pass";
                        break;
                    case R.id.asright2:
                        Log.d("no",lvl_mon1.getText().toString());
                        cbl1="Fail";
                        break;
                }
            }
        });
        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                lvl_mon1= (RadioButton)findViewById(checkedId);
                switch (checkedId) {
                    case R.id.oralgood:
                        Log.d("good",lvl_mon1.getText().toString());
                        orl1="Good";
                        break;
                    case R.id.oralfair:
                        Log.d("fair",lvl_mon1.getText().toString());
                        orl1="Fair";
                        break;
                    case R.id.oralpoor:
                        Log.d("poor",lvl_mon1.getText().toString());
                        orl1="Poor";
                        break;
                }
            }
        });

        rg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                lvl_mon1= (RadioButton)findViewById(checkedId);
                switch (checkedId) {
                    case R.id.calyes:
                        Log.d("yes",lvl_mon1.getText().toString());
                        cal1="Yes";
                        break;
                    case R.id.calno:
                        Log.d("no",lvl_mon1.getText().toString());
                        cal1="No";
                        break;
                }
            }
        });
        rg7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                lvl_mon1= (RadioButton)findViewById(checkedId);
                switch (checkedId) {
                    case R.id.floyes:
                        Log.d("yes",lvl_mon1.getText().toString());
                        flouro1="Yes";
                        break;
                    case R.id.flono:
                        Log.d("no",lvl_mon1.getText().toString());
                        flouro1="No";
                        break;
                }
            }
        });
        rg8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                lvl_mon1= (RadioButton)findViewById(checkedId);
                switch (checkedId) {
                    case R.id.othoyes:
                        Log.d("yes",lvl_mon1.getText().toString());
                        ortho1="Yes";
                        break;
                    case R.id.orthono:
                        Log.d("no",lvl_mon1.getText().toString());
                        ortho1="No";
                        break;
                }
            }
        });
        rg9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                lvl_mon1= (RadioButton)findViewById(checkedId);
                switch (checkedId) {
                    case R.id.ieyes:
                        Log.d("yes",lvl_mon1.getText().toString());
                        indication1="Yes" ;
                        break;
                    case R.id.ieno:
                        Log.d("no",lvl_mon1.getText().toString());
                        indication1="No";
                        break;
                }
            }
        });

        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
        addTodoBtn = (Button) findViewById(R.id.assubmit);
        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    public void settings(View view) {
        selectImage();

    }

        private void selectImage() {

            final CharSequence[] items = { "Take Photo", "Choose from Library",
                    "Cancel" };

            AlertDialog.Builder builder = new AlertDialog.Builder(
                    AddTodoActivity.this);
            builder.setTitle("Add Photo!");
            // builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].equals("Take Photo")) {
                        captureImage1();

                    } else if (items[item].equals("Choose from Library")) {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);


                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        }

        /*Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image*//*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);*/

    private void captureImage1() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri1(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // start the image capture Intent
        startActivityForResult(intent, 1);
    }

    public Uri getOutputMediaFileUri1(int type) {
        return Uri.fromFile(getOutputMediaFile1(type));
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile1(int type) {

        // External sdcard location
        File mediaStorageDir = new  File(Environment.getExternalStorageDirectory() + "/ShelterPhotos");
        Log.d("mediaStorageDir==",mediaStorageDir.toString());
        Path=mediaStorageDir.toString();
        Log.d("path",Path);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "HomelessPersonPic ");
        } else {
            return null;
        }
        return mediaFile;
    }
    private void previewCapturedImage1() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            Bitmap thumbnail = (BitmapFactory.decodeFile(fileUri.getPath()));
            saveImage(thumbnail);
            Log.v("BITMAP---1224",String.valueOf(bitmap));

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public void SelectItem(View view)
    {
        boolean checked=((CheckBox)view).isChecked();
        switch (view.getId())
        {
            case R.id.neauo:if(checked) limits1.add("Neuologic");
            else limits1.remove("Neuologic");
                break;
            case R.id.hn:if(checked) limits1.add("H and N");
            else limits1.remove("H and N");
                break;
            case R.id.ent:if(checked) limits1.add("ENT");
            else limits1.remove("ENT");
                break;
            case R.id.lymph:if(checked) limits1.add("Lyphamatic");
            else limits1.remove("Lyphamatic");
                break;
            case R.id.heart:if(checked) limits1.add("Heart");
            else limits1.remove("Heart");
                break;
            case R.id.longg:if(checked) limits1.add("Lungs");
            else limits1.remove("Lungs");
                break;
            case R.id.abd:
                if(checked) limits1.add("Abdomen");
                else limits1.remove("Abdomen");
                break;
            case R.id.gen:
                if(checked) limits1.add("Genetalia");
                else limits1.remove("Genetalia");
                break;
            case R.id.skin:
                if(checked) limits1.add("Skin");
                else limits1.remove("Skin");
                break;
            case R.id.py:
                if(checked) limits1.add("Pyrexia");
                else limits1.remove("Pyrexia");
                break;
            case R.id.ur:
                if(checked)
                {
                    limits1.add("URTI");
                }

                else limits1.remove("URTI");
                break;
            case R.id.in:
                if(checked) limits1.add("Injuries");
                else limits1.remove("Injuries");
                break;
            case R.id.ut:
                if(checked) limits1.add("UTI");
                else limits1.remove("UTI");
                break;
            case R.id.angular:
                if(checked) limits1.add("Angular Stomatities");
                else limits1.remove("Angular Stomatities");
                break;
            case R.id.aphthous:
                if(checked) limits1.add("Aphthous Ulcers");
                else limits1.remove("Aphthous Ulcers");
                break;
            case R.id.glossit:
                if(checked) limits1.add("Glossities");
                else limits1.remove("Glossities");
                break;
            case R.id.phary:
                if(checked) limits1.add("Pharyngitis");
                else limits1.remove("Pharyngitis");
                break;
            case R.id.neck:
                if(checked) limits2.add("Neck");
                else limits2.remove("Neck");
                break;
            case R.id.shoulder:
                if(checked) limits2.add("Shoulders");
                else limits2.remove("Shoulders");
                break;
            case R.id.arms:
                if(checked) limits2.add("Arms");
                else limits2.remove("Arms");
                break;
            case R.id.hips:
                if(checked) limits2.add("Hips");
                else limits2.remove("Hips");
                break;
            case R.id.knees:
                if(checked) limits2.add("Knees");
                else limits2.remove("Knees");
                break;
            case R.id.feet:
                if(checked) limits2.add("Feet");
                else limits2.remove("Feet");
                break;
            case R.id.spinalno:
                if(checked) limits3.add("No spinal Abnormality");
                else limits3.remove("No spinal Abnormality");
                break;
            case R.id.spinal:
                if(checked) limits3.add("Spinal Abnomality");
                else limits3.remove("Spinal Abnormality");
                break;
            case R.id.mild:
                if(checked) limits3.add("Mild");
                else limits3.remove("Mild");
                break;
            case R.id.marked:
                if(checked) limits3.add("Marked");
                else limits3.remove("Marked");
                break;
            case R.id.moderate:
                if(checked) limits3.add("Moderate");
                else limits3.remove("Moderate");
                break;
            case R.id.rm:
                if(checked) limits3.add("Referal Made");
                else limits3.remove("Referal Made");
                break;
            case R.id.ntd:
                if(checked) limits4.add("Neural Tube Defect");
                else limits4.remove("Neural Tube Defect");
                break;
            case R.id.ds:
                if(checked) limits4.add("Down Syndome");
                else limits4.remove("Down Syndrome");
                break;
            case R.id.clp:
                if(checked) limits4.add("Cleft Lip and Palate");
                else limits4.remove("Cleft Lip and Palate");
                break;
            case R.id.tcf:
                if(checked) limits4.add("Talipes Club foot");
                else limits4.remove("Talipes Club foot");
                break;
            case R.id.ddh:
                if(checked) limits4.add("Developmental Dyslpasia of Hip");
                else limits4.remove("Developmental Dyslpasia of Hip");
                break;
            case R.id.cc:
                if(checked) limits4.add("Congential Cataract");
                else limits4.remove("congential Catarct");
                break;
            case R.id.cdef:
                if(checked) limits4.add("Congential Deafness");
                else limits4.remove("congential Deafness");
                break;
            case R.id.chd:
                if(checked) limits4.add("Congential Heart Disease");
                else limits4.remove("Congential Heart Disease");
                break;
            case R.id.rop:
                if(checked) limits4.add("Retinopathy of Prematurity");
                else limits4.remove("Retinopathy of Prematurity");
                break;
            case R.id.an:
                if(checked) limits5.add("Anemia-Mild");
                else limits5.remove("Anemia-Mild");
                break;
            case R.id.ane:
                if(checked) limits5.add("Anemia-Moderate");
                else limits5.remove("Anemia-Moderate");
                break;
            case R.id.anem:
                if(checked) limits5.add("Anemia-Severe");
                else limits5.remove("Anemia-Severe");
                break;
            case R.id.vdbcom:
                if(checked) limits5.add("Vitamin Deficiency - BComplex");
                else limits5.remove("Vitamin Deficiency - BComplex");
                break;
            case R.id.vda:
                if(checked) limits5.add("Vitamin A Deficiency");
                else limits5.remove("Vitamin A Deficiency");
                break;
            case R.id.vdd:
                if(checked) limits5.add("Vitamin D Deficiency");
                else limits5.remove("Vitamin D Deficiency");
                break;
            case R.id.vdc:
                if(checked) limits5.add("Vitamin C Deficiency");
                else limits5.remove("Vitamin C Deficiency");
                break;

            case R.id.sam:
                if(checked) limits5.add("SAM/Stunting");
                else limits5.remove("SAM/Stunting");
                break;
            case R.id.goiter:
                if(checked) limits5.add("Goiter");
                else limits5.remove("Goiter");
                break;
            case R.id.unwt:
                if(checked) limits5.add("Under Weight");
                else limits5.remove("Under Weight");
                break;
            case R.id.norwt:
                if(checked) limits5.add("Normal Weight");
                else limits5.remove("Normal Weight");
                break;
            case R.id.ovwt:
                if(checked) limits5.add("Over Weight");
                else limits5.remove("Over Weight");
                break;
            case R.id.obese:
                if(checked) limits5.add("Obese");
                else limits5.remove("Obese");
                break;
            case R.id.cer:
                if(checked) limits6.add("Cervical Lymph Adenitis");
                else limits6.remove("Cervical Lymph Adenitis");
                break;
            case R.id.asom:
                if(checked) limits6.add("ASOM");
                else limits6.remove("ASOM");
                break;
            case R.id.csom:
                if(checked) limits6.add("CSOM");
                else limits6.remove("CSOM");
                break;
            case R.id.rhe:
                if(checked) limits6.add("Rheumatic Heart Disease");
                else limits6.remove("Rheumatic Heart Disease");
                break;
            case R.id.acute:
                if(checked) limits6.add("Acute Br.Asthama");
                else limits6.remove("Acute Br.Asthama");
                break;
            case R.id.chronic:
                if(checked) limits6.add("Chronic Br.Asthma");
                else limits6.remove("Chronic Br.Asthma");
                break;
            case R.id.ict:
                if(checked) limits6.add("Icterus");
                else limits6.remove("Icterus");
                break;
            case R.id.hypo:
                if(checked) limits6.add("Hypothyrodism");
                else limits6.remove("Hypothyrodism");
                break;
            case R.id.hyper:
                if(checked) limits6.add("Hyperthyroidism");
                else limits6.remove("Hyperthyroidism");
                break;
            case R.id.typeI:
                if(checked) limits6.add("Type-I Diabeties");
                else limits6.remove("Type-I Diabeties");
                break;

            case R.id.typeII:
                if(checked) limits6.add("Type-II Diabeties");
                else limits6.remove("Type-II Diabeties");
                break;
            case R.id.epilepsy1:
                if(checked) limits6.add("Epilepsy");
                else limits6.remove("Epilepsy");
                break;
            case R.id.scabies:
                if(checked) limits9.add("Scabies");
                else limits9.remove("Scabies");
                break;
            case R.id.TCor:
                if(checked) limits9.add("Taenia Corporis");
                else limits9.remove("Taenia Corporis");
                break;
            case R.id.TCur:
                if(checked) limits9.add("Taenia Facialis");
                else limits9.remove("Taenia Facialis");
                break;
            case R.id.TCru:
                if(checked) limits9.add("Taenia Cruris");
                else limits9.remove("Taenia Cruris");
                break;
            case R.id.ecc:
                if(checked) limits9.add("ECCEMA");
                else limits9.remove("ECCEMA");
                break;
            case R.id.psor:
                if(checked) limits9.add("Psoriasis");
                else limits9.remove("Psoriasis");
                break;
            case R.id.allergy:
                if(checked) limits9.add("Allergic Rash");
                else limits9.remove("Allergic Rash");
                break;
            case R.id.white:
                if(checked) limits9.add("White Patches on Face");
                else limits9.remove("White Patches on Face");
                break;
            case R.id.acne:
                if(checked) limits9.add("Acne on Face");
                else limits9.remove("Acne on Face");
                break;
            case R.id.hyperpig:
                if(checked) limits9.add("Hyper Pigmentation");
                else limits9.remove("Hyper Pigmatation");
                break;
            case R.id.hypopig:
                if(checked) limits9.add("Hypo Pigmentation");
                else limits9.remove("Hypo Pigmentation");
                break;
            case R.id.hansens:
                if(checked) limits9.add("Hansens Disease");
                else limits9.remove("Hansens Disease");
                break;
            case R.id.mollu:
                if(checked) limits9.add("Molluscum");
                else limits9.remove("Molluscum");
                break;
            case R.id.nail:
                if(checked) limits9.add("Nail Bed Disease");
                else limits9.remove("Nail Bed Disease");
                break;
            case R.id.grey:
                if(checked) limits9.add("Greying Hair");
                else limits9.remove("Greying Hair");
                break;
            case R.id.dand:
                if(checked) limits9.add("Danddruff");
                else limits9.remove("Danddruff");
                break;
            case R.id.crack:
                if(checked) limits9.add("Cracked Feet");
                else limits9.remove("Cracked Feet");
                break;
            case R.id.Hyperhidrosis:
                if(checked) limits9.add("Hyperhidrosis");
                else limits9.remove("Hyperhidrosis");
                break;


            case R.id.nad:
                if(checked)
                    nad1="Yes";
                else
                   nad1="No";
                break;
            case R.id.cbref:
                if(checked)
                    vsref1="Yes";
                else
                    vsref1="No";
            case R.id.normal:
                if(checked) limits7.add("Normal ");
                else limits7.remove("Normal");
                break;

            case R.id.delay:
                if(checked) limits7.add("Delay");
                else limits7.remove("Delay");
                break;
            case R.id.misarti:
                if(checked) limits7.add("Misarticulaion");
                else limits7.remove("Misarticulaion");
                break;
            case R.id.voice:
                if(checked) limits7.add("Voice");
                else limits7.remove("Voice");
                break;
            case R.id.fluency:
                if(checked) limits7.add("Fluency ");
                else limits7.remove("Fluency");
                break;
            case R.id.flue:
                if(checked) limits7.add("Tongue Tie ");
                else limits7.remove("Tongue Tie");
                break;
            case R.id.stam:
                if(checked) limits7.add("Stammering");
                else limits7.remove("Stammering");
                break;
            case R.id.ld:
                if(checked) limits8.add("Language Delay ");
                else limits8.remove("Language Delay");
                break;
            case R.id.bd:
                if(checked) limits8.add("Behaviour Disorder ");
                else limits8.remove("Behaviour Disorder");
                break;
            case R.id.asref:
                if(checked)
                    asref1="Yes";
                else
                    asref1="No";
                break;
            case R.id.dentalref:
                if(checked)
                   dcref1="Yes";
                else
                    dcref1="No";
                break;
        }


        for (String i : limits1)
        {
            //====

            Object[] mStringArray = limits1.toArray();

            String elements = "";
            for(int j = 0; j < mStringArray.length ; j++){
                Log.d("string is",(String)mStringArray[j]);
                String str = (String)mStringArray[j];
                if(j == mStringArray.length-1){
                    elements = elements + str;
                }else{
                    elements = elements + str + ",";
                }
            }Log.d("string is final==1004 ",elements);
            s2=elements;
            //=====


        }

        for (String j : limits2)
        {

            //====

            Object[] mStringArray = limits2.toArray();

            String elements = "";
            for(int i = 0; i < mStringArray.length ; i++){
                Log.d("string is",(String)mStringArray[i]);
                String str = (String)mStringArray[i];
                if(i == mStringArray.length-1){
                    elements = elements + str;
                }else{
                    elements = elements + str + ",";
                }
            }Log.d("string is final==1027 ",elements);
            s3=elements;
            //=====


        }
        for(String k: limits3){

            //====

            Object[] mStringArray = limits3.toArray();

            String elements = "";
            for(int i = 0; i < mStringArray.length ; i++){
                Log.d("string is",(String)mStringArray[i]);
                String str = (String)mStringArray[i];
                if(i == mStringArray.length-1){
                    elements = elements + str;
                }else{
                    elements = elements + str + ",";
                }
            }Log.d("string is final ==1048",elements);
            s4=elements;
            //=====


        }
        for(String l: limits4){
            //====

            Object[] mStringArray = limits4.toArray();

            String elements = "";
            for(int i = 0; i < mStringArray.length ; i++){
                Log.d("string is",(String)mStringArray[i]);
                String str = (String)mStringArray[i];
                if(i == mStringArray.length-1){
                    elements = elements + str;
                }else{
                    elements = elements + str + ",";
                }
            }Log.d("string is final ==1068",elements);
            s5=elements;
            //=====
        }
        for(String m: limits5){
            //====

            Object[] mStringArray = limits5.toArray();

            String elements = "";
            for(int i = 0; i < mStringArray.length ; i++){
                Log.d("string is",(String)mStringArray[i]);
                String str = (String)mStringArray[i];
                if(i == mStringArray.length-1){
                    elements = elements + str;
                }else{
                    elements = elements + str + ",";
                }
            }Log.d("string is final ==1086",elements);
            s6=elements;
            //=====
        }
        for(String n: limits6){
            //====

            Object[] mStringArray = limits6.toArray();

            String elements = "";
            for(int i = 0; i < mStringArray.length ; i++){
                Log.d("string is",(String)mStringArray[i]);
                String str = (String)mStringArray[i];
                if(i == mStringArray.length-1){
                    elements = elements + str;
                }else{
                    elements = elements + str + ",";
                }
            }Log.d("string is final==1104 ",elements);
            s7=elements;
            //=====
        }
        for(String o: limits7){

            //====

            Object[] mStringArray = limits7.toArray();

            String elements = "";
            for(int i = 0; i < mStringArray.length ; i++){
                Log.d("string is",(String)mStringArray[i]);
                String str = (String)mStringArray[i];
                if(i == mStringArray.length-1){
                    elements = elements + str;
                }else{
                    elements = elements + str + ",";
                }
            }Log.d("string is final ==1123",elements);
            s8=elements;
            //=====
        }
        for(String p: limits8){
            //====

            Object[] mStringArray = limits8.toArray();

            String elements = "";
            for(int i = 0; i < mStringArray.length ; i++){
                Log.d("string is",(String)mStringArray[i]);
                String str = (String)mStringArray[i];
                if(i == mStringArray.length-1){
                    elements = elements + str;
                }else{
                    elements = elements + str + ",";
                }
            }Log.d("string is final ",elements);
            s9=elements;
            //=====
        }
        for(String q: limits9){
            //====

            Object[] mStringArray = limits9.toArray();

            String elements = "";
            for(int i = 0; i < mStringArray.length ; i++){
                Log.d("string is",(String)mStringArray[i]);
                String str = (String)mStringArray[i];
                if(i == mStringArray.length-1){
                    elements = elements + str;
                }else{
                    elements = elements + str + ",";
                }
            }Log.d("string is final ",elements);
            s10=elements;
            //=====
        }
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
         }
    /*
         * Here we store the file url as it will be null after returning from camera
         * app
         */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }
    public void onBackPressed()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddTodoActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle(" Exit");
        // Setting Dialog Message
        alertDialog.setMessage("Do you want to exit.");
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent i=new Intent(getApplicationContext(),TodoListActivity.class);
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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage1();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        String path="";
        try {
            if (resultCode == RESULT_OK) {
                imagesEncodedList = new ArrayList<String>();
                Log.d("ImgesNumber--1081",String.valueOf(imagesEncodedList.size()));
                if (data != null) {
                    ClipData mClipData = data.getClipData();
                    if (mClipData != null) {

                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            if (i > 19) {
                                braked = true;
                                break;
                            }

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            // Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            // bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
                            mArrayUri.add(uri);

                            //In case you need image's absolute path
                            path = GetPathToImage (uri);
                            imagesEncodedList.add(path);
                        }

                    } else {
                        Uri uri = data.getData();
                        path = GetPathToImage(uri);
                        imagesEncodedList.add (path);
                    }

                    System.out.println("=====> Array path => " +imagesEncodedList);
                    String str=String.valueOf(imagesEncodedList.size());
                    String Str1=str+" Photos attached Sucessfully";
                    Toast.makeText(getApplicationContext(),Str1,Toast.LENGTH_SHORT).show();

                    if (braked == true) {
                        // Toast.MakeText (Xamarin.Forms.Forms.Context, "Only the top 20 images will be uploaded", ToastLength.Long).Show ();
                    }

                }
            }
            //Send the paths to forms
        } catch (Exception ex) {

            //Toast.MakeText (Xamarin.Forms.Forms.Context, "Unable to open, error:" + ex.ToString(), ToastLength.Long).Show ();
        }
    }

    private String GetPathToImage( Uri uri)
    {
        String doc_id = "";
        Cursor cursor = getContentResolver().query (uri, null, null, null, null); {
        cursor.moveToFirst();

        String document_id = cursor.getString (0);
        doc_id = document_id.substring (document_id.lastIndexOf (":") + 1);
    }

        String path = null;

        // The projection contains the columns we want to return in our query.
        String selection = MediaStore.Images.Media._ID + " =? ";
        cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, selection, new String[] {doc_id}, null);
        {
            if (cursor == null) return path;
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(columnIndex);
            String q=  String.valueOf(mArrayUri.size());
            if( (mArrayUri.size())==(0)) {
                q = (q + 1);
                Log.d("image is", q);
                Bitmap thumbnail = (BitmapFactory.decodeFile(path));
                saveImage(thumbnail);
            }else{
                Log.d("Path is", q+"photos again ");
                Log.d("filesare", q+"photos");
                Log.d("image is", q);
                Bitmap thumbnail = (BitmapFactory.decodeFile(path));
                saveImage(thumbnail);

            }
        }

        return path;
    }

    private void saveImage1(Bitmap finalBitmap) {

        String str111=ed1.getText().toString();
    //    String str112=ed2.getText().toString();
        String str113=tx1.getText().toString();
       // String str114=tx2.getText().toString();
        String str115=ed3.getText().toString();
        String str=str111+str113+str115;
        File myDir = new File(Environment.getExternalStorageDirectory() + "/MedNote"+"/"+str);
        if (!myDir.exists()) {
            myDir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());




       /* File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".png");
        } else {
            return null;
        }*/
        Random generator = new Random();
        int n = 10000;
       // n = generator.nextInt(n);
        iname = "IMG_" + timeStamp + ".png";
        result=iname;
        Log.d("result==1388",result);
        File file = new File(myDir, iname);
        Log.d("Pathh====1392",file.toString());
       ImagePathh1=file.toString();
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            Log.d("file_photo_path == 1386",fileUri.getPath());
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void saveImage(Bitmap finalBitmap) {
        File myDir = new File(Environment.getExternalStorageDirectory() + "/MedNote"+"/"+"Attachments");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        String str111=ed1.getText().toString();
       // String str112=ed1.getText().toString();
        String str113=tx1.getText().toString();
      //  String str114=tx2.getText().toString();
        String str115=ed3.getText().toString();
        String str=str111+str113+str115;
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        iname = str+"_Attachment_" + n +".png" +
                "";

        listtt.add(iname);
        Log.d("listttt-1423",listtt.toString().replace("[", "").replace("]", ""));
        Attachments=listtt.toString().replace("[", "").replace("]", "");
        File file = new File(myDir, iname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[] { file.toString() }, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });

        Image_path = Environment.getExternalStorageDirectory()+ "/Pictures/folder_name/"+iname;
        File[] files = myDir.listFiles();
        numberOfImages=files.length;
        System.out.println("Total images in Folder "+numberOfImages);
    }
    private void previewCapturedImage() {
        try {
            imageError=false;
            BitmapFactory.Options options = new BitmapFactory.Options();
           options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            try
            {
                FileOutputStream filee=new FileOutputStream(fileUri.getPath());
                bitmap.compress(Bitmap.CompressFormat.PNG,70,filee);
                filee.flush();
                filee.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveImage1(bitmap);

          /*  bitmap.compress(Bitmap.CompressFormat.JPEG,40,bytearrayoutputstream);
            BYTE=bytearrayoutputstream.toByteArray();
            final Bitmap bitmap1=BitmapFactory.decodeByteArray(BYTE,0,BYTE.length);*/
            ImagePathh=fileUri.getPath();

            if(ImagePathh==null)
            {
                Log.v("image is not taken","Add ToDo List");
            }
            Log.d("image--1028",fileUri.getPath());
            Log.v("BITMAP---111",String.valueOf(bitmap));

            if(imgPreview != null) {

                imgPreview.setImageBitmap(bitmap);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    // Creating file uri to store image/video

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }


    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        String str111=ed1.getText().toString();
      //  String str112=ed2.getText().toString();
        String str113=tx1.getText().toString();
      //  String str114=tx2.getText().toString();
        String str115=ed3.getText().toString();
        String str=str111+str113+str115;
        Log.d("Unique photo--1517",str);
        // External sdcard location
        File mediaStorageDir = new  File(Environment.getExternalStorageDirectory() + "/Pictures"+"/"+str);
        Log.d("mediaStorageDir==",mediaStorageDir.toString());



        Path=mediaStorageDir.toString();
        Log.d("path",Path);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
       /* iname = "IMG_" + timeStamp + ".png";
        File mediaFile = new File(myDir, iname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/

        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG2_" + timeStamp + ".png");
        } else {
            return null;
        }
        return mediaFile;
    }

    public boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

      switch (parent.getId())
      {
          case R.id.spinner:
             String selected11=parent.getItemAtPosition(position).toString();
              selected1=selected11;
              break;
          case R.id.spinner1:
             String selected12=parent.getItemAtPosition(position).toString();
              selected2=selected12;
              break;
          case R.id.spinner2:
            String  selected13=parent.getItemAtPosition(position).toString();
              selected3=selected13;
              break;
          case R.id.spinner3:
              String selected14=parent.getItemAtPosition(position).toString();
              selected4=selected14;
              break;
          case R.id.spinner4:
             String selected15=parent.getItemAtPosition(position).toString();
              selected5=selected15;
              break;
          case R.id.spinner11:
              String selected16=parent.getItemAtPosition(position).toString();
              selected6=selected16;
              break;
          case R.id.spinner12:
            String selected17=parent.getItemAtPosition(position).toString();
              selected7=selected17;
              break;
          case R.id.spinner21:
             String selected18=parent.getItemAtPosition(position).toString();
              selected8=selected18;
              break;
          case R.id.spinner22:
             String selected19=parent.getItemAtPosition(position).toString();
              selected9=selected19;
              break;
      }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Dateofbirth.setText(sdf.format(myCalendar.getTime()));
    }

    private float calculateBMI(float weight,float height)
    {
        return (float)(weight/(height*height));

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edob:
                new DatePickerDialog(AddTodoActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;

            case R.id.eimage:

                captureImage();
                break;

            case R.id.search:
                String str10=ed1.getText().toString();
            //    String str11=ed2.getText().toString();
                String str12=tx1.getText().toString();
               // String str13=tx2.getText().toString();
                String str14=ed3.getText().toString();
                Uid=str10+str12+str14;
                String uuid=Uid;

                if(Uid!=null) {
                    Log.d("uuid---43", uuid.toString());
                    if(str14.isEmpty())
                    {
                        ed3.setText(cr);
                    }
                    Cursor in1 = controller.onUpdate1(uuid);
                    if (in1.moveToFirst()) {

                        Name.setText(in1.getString(1));
                        Fathername.setText(in1.getString(3));
                        Schoolname.setText(in1.getString(4));
                        District.setText(in1.getString(5));
                        Dateofbirth.setText(in1.getString(6));
                        Cls.setText(in1.getString(7));
                        Section.setText(in1.getString(8));
                        Mobile.setText(in1.getString(9));
                        Flag_old="yes";
                        Log.d("Flag____",Flag_old);
                    }
                }
                break;
            case R.id.ebp:
                final String height3=Height.getText().toString();
                final String weight3=Weight.getText().toString();
                float weight2=Float.parseFloat(weight3);
                float height2=Float.parseFloat(height3)/100;
                float bmiValue2=calculateBMI(weight2,height2);
                final String bmi2=String.valueOf(bmiValue2);
                Bmi.setText(bmi2);

                Log.d("bmiii",bmi2);
                break;
        case R.id.assubmit:

            str10=ed1.getText().toString();
          //  str11=ed2.getText().toString();
            str12=tx1.getText().toString();
         //   str13=tx2.getText().toString();
            str14=ed3.getText().toString();
            if(str10!=null||str14!=null)
            {
                Uid=str10+str12+str14;
            }

        	final String uid=Uid;
            Calendar c = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
            String strDate = sdf.format(c.getTime());

            list.add(ar);
            list.add(br);
            list.add(strDate);
            Log.d("histoy--1239",list.toString().replace("[","").replace("]",""));

            Log.d("strdate",strDate);
            final String name = Name.getText().toString();
            final String fathername=Fathername.getText().toString();
            final String schoolname=Schoolname.getText().toString();
            final String district=District.getText().toString();
            final String dateofbirth=Dateofbirth.getText().toString();
            final String mobile=Mobile.getText().toString();
            final String adno = Adno.getText().toString();
            final String section=Section.getText().toString();
            final String classs=Cls.getText().toString();
            final String dateofexam=Doe.getText().toString();
             String height=Height.getText().toString();
            final String weight=Weight.getText().toString();

            if(!height.isEmpty() && !weight.isEmpty()) {
                float weight1 = Float.parseFloat(weight);
                float height1 = Float.parseFloat(height) / 100;
                float bmiValue = calculateBMI(weight1, height1);
                float m=(float)Math.round(bmiValue*100)/100;
                Log.d("Bmi===1733",String.valueOf(m));
                String bmi= String.valueOf(m);
                Bmi.setText(bmi);
               bmii =bmi;
            }
            if(Flag_old=="yes")
            {
            Log.d("Flag---1089",Flag_old);
            }
            if(Flag_old!="yes")
            {
                Flag_old="no";
                Log.d("Flag---",Flag_old);
            }

           // bmii =bmi;
            final String pulse = Pulse.getText().toString();
            final String bp=Bp.getText().toString();
            final String hb=Hb.getText().toString();
            final String bloodgroup=selected1;
            final String rg1=asr1;
            final String rg2=asl1;
            final String rg3=cbr1;
            final String rg4=cbl1;
            final String rg5=orl1;
            final String rg6=cal1;
            final String rg7=flouro1;
            final String rg8=ortho1;
            final String rg9=indication1;
            final String description1=dcdesc.getText().toString();
            final String advise=descc.getText().toString();
            final String description2=cbdesc.getText().toString();
            final String description3=asdesc.getText().toString();
            final String withoutr=selected2;
            final String withoutl = selected3;
            final String withr=selected4;
            final String withl=selected5;
            final String description4=dentaldesc.getText().toString();
            final String advice=s10;
            final String Abnormalities=s2;
            final String Ortho=s3;
            final String Postural=s4;
            final String DefectsatBirth=s5;
            final String Deficiencies=s6;
            final String ChildwoodDiaeases=s7;
            final String DDDisability=s9;
            final String SpeechScreening=s8;
            final String nad2=nad1;
            final String vsref2=vsref1;
            final String asref2=asref1;
            final String dcref2=dcref1;
            final String dc11=selected6;
            final String dc12=selected7;
            final String dc13=selected8;
            final String dc14=selected9;
            final String imagepath=ImagePathh1;

            final String genaral=signature1;
            final String dental=signature4;
            final String optho=signature2;
            final String audio=signature3;
            final String history1=list.toString();
            final String dateandtime=Attachments;

//mandatory fields checking

            if(imageError){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddTodoActivity.this);
                alertDialog.setTitle("Registration Error ...");
                alertDialog.setMessage("Please Enter Mandatory Fields i.e, Star Marked fields Other wise Your form will not be submitted  ");
                alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                    }
                });

                alertDialog.show();
            }else
            {
                if( uid.trim().equals("")||str10.trim().equals("")||str14.trim().equals("")||
                        name.trim().equals("")||mobile.trim().equals("")||classs.trim().equals("")||section.trim().equals("")) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddTodoActivity.this);
                    alertDialog.setTitle("Registration Error ...");
                    alertDialog.setMessage("Please Enter Mandatory Fields i.e, Star Marked fields Other wise Your form will not be submitted  ");
                    alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                        }
                    });

                    alertDialog.show();
                }
                else {
                    //inserting values into database
                    dbManager.insert(uid, name, fathername, schoolname,district,dateofbirth,mobile,adno,section,classs,
                            dateofexam,height,weight,bmii,pulse,bp,hb,bloodgroup,description1,advice,withoutr, withoutl,
                            withr,withl,rg1,rg2,description2,rg3,rg4,description3,rg5,rg6,rg7,rg8,rg9,description4,Abnormalities,Ortho,Postural,DefectsatBirth,Deficiencies,
                            ChildwoodDiaeases,DDDisability,SpeechScreening,nad2,vsref2,asref2,dcref2,dc11,dc12,dc13,dc14,imagepath,genaral,dental,optho,audio,history1,dateandtime,advise);
                    Log.d("inseterd","aaaaaaaaaaaaa");
                    Toast.makeText(this,"sucessfully submitted  Medicalevaluation Form",Toast.LENGTH_SHORT).show();
                    Intent ik = new Intent(this, TodoListActivity.class);
                    editor2.putString("one", br);
                    editor2.putString("two", ar);
                    editor2.commit();
                    startActivity(ik);
                }
            }


            break;
        }
    }


}