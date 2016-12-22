package com.kotan.printum.Ui.Activity;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.kotan.printum.Network.Download;
import com.kotan.printum.R;
import java.util.concurrent.ExecutionException;
/**
 * Created by Kotan@JoyDainc on 05/12/2016.
 */
public class FichaTec extends AppCompatActivity implements OnPageChangeListener,OnDrawListener {
    Uri path;
    private PDFView pdfView;
    private EditText editText;
    private Button button;
    private Context context;
    String download_file_url = "http://printumsaa.zapto.org:8080/PRO1_Productos/PDF/";
    final private int REQUEST_WRITE_STORAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        editText = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button2) ;
        context = FichaTec.this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a =  editText.getText().toString();
                if(a!= null && !(a.equals("Producto"))){
                    download_file_url = String.format("http://printumsaa.zapto.org:8080/PRO1_Productos/PDF/%s", a);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        } else {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQUEST_WRITE_STORAGE);
                        }
                    }else{ ViewPDF(download_file_url);}
                }else{
                    editText.setText("No product");
                }
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ViewPDF(download_file_url);
                }
                return;
            }
        }
    }
    protected void ViewPDF(String Value){
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
                pdfView.fromFile(new Download(null,context).execute(Value).get())
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
            e.printStackTrace();
        }
        return false;
    }

}