package com.kotan.printum.Network;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by COEQ-IT on 01/11/2016.
 */

public class Download extends AsyncTask<String, Integer, File> {
    File outputFile;
    protected File doInBackground(String... url1) {
        return onPreExecute(url1);
    }
    protected File onPreExecute(String... progress) {
        try {
            String fileName = "E4";
            String fileExtension = ".pdf";
            URL url = new URL(progress[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            String PATH = Environment.getExternalStorageDirectory() + "/mydownload/";
            File file = new File(PATH);
            file.mkdirs();
            outputFile = new File(file, fileName + fileExtension);
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.flush();
            fos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return outputFile;
    }
}
