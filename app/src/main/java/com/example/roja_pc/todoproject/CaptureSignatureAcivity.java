package com.example.roja_pc.todoproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;

/**
 * Created by pc on 4/26/2017.
 */

public class CaptureSignatureAcivity extends Activity {

    public static final int SIGNATURE_ACTIVITY1 = 1;
    public static final int SIGNATURE_ACTIVITY2 = 2;
    public static final int SIGNATURE_ACTIVITY3 = 3;
    public static final int SIGNATURE_ACTIVITY4 = 4;
    public static String signpath1,signpath2,signpath3,signpath4;
    ImageView img1,img2,img3,img4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        img1=(ImageView)findViewById(R.id.imagesign1);
        img2=(ImageView)findViewById(R.id.imagesign2);
        img3=(ImageView)findViewById(R.id.imagesign3);
        img4=(ImageView)findViewById(R.id.imagesign4);

        Button getSignature1 = (Button) findViewById(R.id.signature1);
        Button getSignature2 = (Button) findViewById(R.id.signature2);
        Button getSignature3 = (Button) findViewById(R.id.signature3);
        Button getSignature4 = (Button) findViewById(R.id.signature4);

        getSignature1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CaptureSignatureAcivity.this, CaptureSignature.class);
                startActivityForResult(intent,SIGNATURE_ACTIVITY1);
            }
        });
        getSignature2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CaptureSignatureAcivity.this, CaptureSignature.class);
                startActivityForResult(intent,SIGNATURE_ACTIVITY2);
            }
        });
        getSignature3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CaptureSignatureAcivity.this, CaptureSignature.class);
                startActivityForResult(intent,SIGNATURE_ACTIVITY3);
            }
        });
        getSignature4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CaptureSignatureAcivity.this, CaptureSignature.class);
                startActivityForResult(intent,SIGNATURE_ACTIVITY4);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode) {
            case SIGNATURE_ACTIVITY1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String status  = bundle.getString("status");
                   try {
                       String str = bundle.getString("mypahhh");
                       signpath1=str;
                       Log.d("signpath--82",signpath1);

                       File imgFile = new File(str);
                       if (imgFile.exists()) {
                           Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                           img1.setImageBitmap(myBitmap);
                       }
                   }catch (Exception e)
                   {
                       e.printStackTrace();
                   }
                    if(status.equalsIgnoreCase("done")){

                        Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 105, 50);
                        toast.show();
                    }
                }
                break;
            case SIGNATURE_ACTIVITY2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String status  = bundle.getString("status");
                    try {
                        String str = bundle.getString("mypahhh");
                        signpath2=str;
                        Log.d("signpath",signpath2);
                        File imgFile = new File(str);
                        if (imgFile.exists()) {
                            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                            img2.setImageBitmap(myBitmap);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    if(status.equalsIgnoreCase("done")){

                        Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 105, 50);
                        toast.show();
                    }
                }
                break;
            case SIGNATURE_ACTIVITY3:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String status  = bundle.getString("status");
                    try {
                        String str = bundle.getString("mypahhh");
                        signpath3=str;
                        Log.d("signpath",signpath3);
                        File imgFile = new File(str);
                        if (imgFile.exists()) {
                            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                            img3.setImageBitmap(myBitmap);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    if(status.equalsIgnoreCase("done")){

                        Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 105, 50);
                        toast.show();
                    }
                }
                break;
            case SIGNATURE_ACTIVITY4:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String status  = bundle.getString("status");
                    try {
                        String str = bundle.getString("mypahhh");
                        signpath4=str;
                        Log.d("signpath",signpath4);
                        File imgFile = new File(str);
                        if (imgFile.exists()) {
                            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                            img4.setImageBitmap(myBitmap);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    if(status.equalsIgnoreCase("done")){

                        Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 105, 50);
                        toast.show();
                    }
                }
                break;
        }


    }

    public void onBackPressed() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(" Exit");
        builder.setMessage("Do You Want To Exit .......");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Intent i=new Intent(getApplicationContext(),TodoListActivity.class);
                i.putExtra("SignPath",signpath1);
                startActivity(i);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

}
