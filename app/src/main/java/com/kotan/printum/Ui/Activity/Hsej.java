package com.kotan.printum.Ui.Activity;

import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.concurrent.ExecutionException;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.kotan.printum.Network.Download;
import com.kotan.printum.R;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutionException;

/**
 * Created by COEQ-IT on 11/11/2016.
 */

public class Hsej extends AppCompatActivity implements OnPageChangeListener,OnDrawListener{
    Uri path;
    private PDFView pdfView;
    private EditText editText;
    private Button button;
    String download_file_url = "http://192.168.0.98:8080/PRO1_Productos/PDF/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        editText = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button2) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a =  editText.getText().toString();
                if(a!= null && !(a.equals("Producto"))){
                    download_file_url = download_file_url+a;
                    editText.setText(""+download_file_url);
                    ViewPDF();
                    download_file_url = "http://192.168.0.98:8080/PRO1_Productos/PDF/";
                }else{
                    editText.setText("No product");
                }
            }
        });

    }

    protected void ViewPDF(){
        try {
            if(!(isOnlineNet())){
                pdfView.fromAsset("printum.pdf")
                        .onDraw(this)
                        .enableDoubletap(true)
                        .defaultPage(1)
                        .onPageChange(this)
                        .load();

            }else{
                //
                pdfView.fromFile(new Download().execute(download_file_url).get())
                        .onDraw(this)
                        .enableDoubletap(true)
                        .defaultPage(1)
                        .onPageChange(this)
                        .load();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void onPageChanged(int page, int pageCount) {
    }
    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

    }
    public Boolean isOnlineNet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void onBackPressed()
    {
        // Your Code Here. Leave empty if you want nothing to happen on back press.
    }
}