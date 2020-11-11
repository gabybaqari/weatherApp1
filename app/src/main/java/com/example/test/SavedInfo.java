/*
package com.example.test;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;
import static com.example.test.MainActivity.saveToFileList;

public class SavedInfo {

     public static void writeFile() {


        try {
            FileOutputStream fileOutputStream = openFileOutput("Test File.txt", MODE_PRIVATE);


            for (int i = 0; i < saveToFileList.size(); i++) {
                //Log.d("___________________", s);
                fileOutputStream.write(saveToFileList.get(i).getBytes());

                fileOutputStream.write("\n".getBytes());
            }


            fileOutputStream.close();

            Toast.makeText(getApplicationContext(), "Text Saved", Toast.LENGTH_SHORT).show();

            Log.d("file is: ", "written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Context getApplicationContext() {return getApplicationContext();
    }

    private static FileOutputStream openFileOutput(String s, int modePrivate) {
         return openFileOutput("Test File.txt", MODE_PRIVATE);
    }

    */
/*public void readFile() {
        try {
            FileInputStream fileInputStream = openFileInput("Test File.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileInputStream openFileInput(String s) {
    }*//*

}
*/
