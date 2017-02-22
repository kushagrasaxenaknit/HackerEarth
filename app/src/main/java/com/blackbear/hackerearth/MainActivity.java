package com.blackbear.hackerearth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    StringBuilder sb ;
    String outputname = "output";
    int PICK_IMAGE_REQUEST = 1;
    Button buttonopen;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonopen = (Button) findViewById(R.id.open);
       tv = (TextView) findViewById(R.id.text);
        buttonopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("text/plain");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            sb = new StringBuilder();
            try {
                //BufferedReader br1 = new BufferedReader(new FileReader(filePath));
                BufferedReader br = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(data.getData())));
                String line = null;
                while((line = br.readLine())!= null)
                {
                    sb.append("\n");
                    //Solve Query.
                    //Append Result in output.txt
                    sb.append(line).append("\n");
                    //this function returns the actual query to each problem
                    sb.append(new GetOutputData().extractAttachmentSearchContextFromString(line));
                }
                tv.setText(sb);
                saveinOutPutFile();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext()," File Error", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void saveinOutPutFile()
    {
        FileWriter fWriter;
        File sdCardFile = new File(Environment.getExternalStorageDirectory() + "/"+outputname+".txt");
        Log.d("TAG_1234", sdCardFile.getPath()); //<-- check the log to make sure the path is correct.
        try{
            fWriter = new FileWriter(sdCardFile, false);
            fWriter.write(sb.toString());
            fWriter.flush();
            fWriter.close();
            Toast.makeText(getApplicationContext(),"Saving File...", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error Occurred in Saving File.", Toast.LENGTH_LONG).show();
        }
    }
}
