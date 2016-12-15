package com.kotan.printum.Network;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

/**
 * Created by JoyDa,inc on 01/11/2016.
 */
public class Download extends AsyncTask<String,Context, File> {
    File outputFile;
    Context context10;
    public Download(File file, Context context){
        this.outputFile =file;
        this.context10 = context;
    }
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 11;
    protected File doInBackground(String... url1) {
        return onPreExecute(url1[0],context10);
    }
    protected File onPreExecute(String progress,Context context) {
        try {
            String fileName = "E4";
            String fileExtension = ".pdf";
            URL url = new URL(progress);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            String PATH;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                int permissionCheck = ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_CALENDAR);
                    PATH  = Environment.getExternalStorageDirectory() + "/mydownload/";
            }else{
                PATH  = Environment.getExternalStorageDirectory() + "/mydownload/";
            }

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
