package com.kotan.printum.Ui.Fragments;


import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.kotan.printum.Model.DataModel;
import com.kotan.printum.Model.LocationModel;
import com.kotan.printum.Network.Download;
import com.kotan.printum.Network.VolleySingleton;
import com.kotan.printum.R;
import com.kotan.printum.Ui.Adapter.SearchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
public class BookMarkFragment extends Fragment implements OnPageChangeListener,OnDrawListener,View.OnClickListener {

    private String LOG_TAG = SearchFragment.class.getSimpleName();
    private PDFView pdfView;
    private EditText editText;
    public Button button;
    private View viste;
    String download_file_url = "http://192.168.0.98:8080/PRO1_Productos/PDF/";
    ArrayList<DataModel> dataModelArrayList = new ArrayList<>();
    SearchAdapter mSearchAdapter;

    @Bind(R.id.recyclerViewMovie)
    RecyclerView mSearchRecyclerView;

    public BookMarkFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         viste = inflater.inflate(R.layout.fragment_book_mark, container, false);
        button = (Button) viste.findViewById(R.id.button2) ;
        button.setOnClickListener(this);
        return viste;
    }
    @Override
    public void onClick(View v) {
        pdfView = (PDFView) viste.findViewById(R.id.pdfView);
        editText = (EditText) viste.findViewById(R.id.editText2);
                String a =  editText.getText().toString();
                if(a!= null && !(a.equals("Producto"))){
                    download_file_url = download_file_url+a;

                }else{
                    editText.setText("No product");
                }
        /*
        try {
            if(!(isOnlineNet())){
                pdfView.fromAsset("printum.pdf")
                        .onDraw(this)
                        .enableDoubletap(true)
                        .defaultPage(1)
                        .onPageChange(this)
                        .load();
                editText.setText("Productos");

            }else{
                //
                /*
                pdfView.fromFile(new Download().execute(download_file_url).get())
                        .onDraw(this)
                        .enableDoubletap(true)
                        .defaultPage(1)
                        .onPageChange(this)
                        .load();
                download_file_url = "http://192.168.0.98:8080/PRO1_Productos/PDF/";

                editText.setText("Productos");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        */
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
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }
}
