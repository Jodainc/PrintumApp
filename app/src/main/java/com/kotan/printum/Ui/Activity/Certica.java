package com.kotan.printum.Ui.Activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.kotan.printum.Model.CertiModel;
import com.kotan.printum.Model.DataModel;
import com.kotan.printum.Model.TrollToken;
import com.kotan.printum.Model.Users;
import com.kotan.printum.Network.VolleySingleton;
import com.kotan.printum.R;
import com.kotan.printum.Ui.Adapter.CertiAdapter;
import com.kotan.printum.Ui.Adapter.ListUserAdapter;
import com.kotan.printum.Ui.Dao.DaoTroller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
public class Certica extends Activity implements OnItemLongClickListener, OnItemClickListener, OnClickListener  {
        private EditText editText;
        private Button button;
    private ListView mListviewEmployees;
    private TextView mTxtEmptyListEmployees;
    private TextView getText;
    private ImageButton mBtnAddEmployee;
    private List<CertiModel> certiModelList;
    private CertiAdapter mAdapter;
    private String value;
    private DaoTroller dao1;
        CertiModel dataModel;
        private String C8pROTECCION;
    public static final String TAG = "Certica";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_certica);
            certiModelList = new ArrayList<CertiModel>();
            initViews();
            dao1 = new DaoTroller(this);
            getText = (TextView)findViewById(R.id.editText2);
            button  =(Button) findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    JsonParser(value  =  getText.getText().toString());
                    Log.d(TAG, "response" + value);
                }
            });
        }
    private void initViews() {
        this.mListviewEmployees = (ListView) findViewById(R.id.list_employees);
        this.mTxtEmptyListEmployees = (TextView) findViewById(R.id.txt_empty_list_employees);
        this.mBtnAddEmployee = (ImageButton) findViewById(R.id.btn_add_employee);
        this.mListviewEmployees.setOnItemClickListener(this);
        this.mListviewEmployees.setOnItemLongClickListener(this);
        this.mBtnAddEmployee.setOnClickListener(this);
    }
        @Override
        protected void onResume() {
            super.onResume();
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
        public void JsonParser(String value) {
            final String url = String.format("http://printumsaa.zapto.org:8080/api/Pro_Certificados/%s",value );
            if(certiModelList != null && !certiModelList.isEmpty()) {
                certiModelList.clear();
                for (int i=0; i>certiModelList.size();i++   ){
                    certiModelList.remove(i);
                }
                mAdapter = new CertiAdapter(this, certiModelList);
                mListviewEmployees.setAdapter(mAdapter);
            }
            RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
            JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, "response" + response);
                    parseJSONresponse(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "Error Message12" + error.getMessage());
                }
            }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    TrollToken a = dao1.getTrollerById(dao1.getProfilesCount());
                    String tocken1 = a.getPasswordType();
                    Log.d("tyype",tocken1);
                    String tocken2 = a.getTrollTok();
                    Log.d("tyype",tocken2);
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append(tocken1);
                    strBuf.append(" ");
                    strBuf.append(tocken2);
                    headers.put("Authorization", strBuf.toString());
                    return headers;
                }
            };
            Log.d(TAG, "Error Message10" + jsObjRequest);
            requestQueue.add(jsObjRequest);
        }

        private void parseJSONresponse(JSONArray response) {
            try {
                String jsonString = response.toString();
                String C8Codigo="null";
                String c8epp ="null";
                String c8mASiNFO ="null";
                String C8pROTECCION ="null";

                Log.i(TAG, "Json" + jsonString);
                JSONArray jsonArray = new JSONArray(URLDecoder.decode( jsonString, "UTF-8" ));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    if(!(jsonObject.getString("C8pROTECCION").equals("null"))){
                        C8Codigo = jsonObject.getString("C8Codigo");
                        c8epp = jsonObject.getString("c8epp");
                        c8mASiNFO = jsonObject.getString("c8mASiNFO");
                        C8pROTECCION = jsonObject.getString("C8pROTECCION");
                        Log.i("Certi", C8pROTECCION);
                    }
                    dataModel = new CertiModel(C8Codigo, c8epp, c8mASiNFO, C8pROTECCION);

                    if(!(C8Codigo.isEmpty())&&!(C8pROTECCION.equals("null"))){
                        certiModelList.add(dataModel);
                        if(certiModelList != null && !certiModelList.isEmpty()) {
                            mAdapter = new CertiAdapter(this, certiModelList);
                            mListviewEmployees.setAdapter(mAdapter);
                        }
                        else {
                            mTxtEmptyListEmployees.setVisibility(View.VISIBLE);
                            mListviewEmployees.setVisibility(View.GONE);
                        }
                    }



                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "dataModelArrayList jsonparser" );

        }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_employee:
                //Intent intent = new Intent(this, AddEmployeeActivity.class);
                //startActivityForResult(intent, REQUEST_CODE_ADD_EMPLOYEE);
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    CertiModel certiModel = mAdapter.getItem(i);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        CertiModel certiModel = mAdapter.getItem(i);
        showDeleteDialogConfirmation(certiModel);
        return false;
    }
    private void showDeleteDialogConfirmation(final CertiModel certiModel) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Abrir Certificado");
        alertDialogBuilder
                .setMessage("Desea abrir estes Certificado \""
                        + certiModel.getC8Codigo() + " "
                        + certiModel.getC8pROTECCION() + "\"");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the employee and refresh the list
                //Webview a = new Webview(certiModel.getC8Codigo(),certiModel.getC8epp(),certiModel.getC8mASiNFO(),certiModel.getC8pROTECCION());
                Intent intent = new Intent(getApplicationContext(), Webview.class);
                intent.putExtra("EXTRA_SESSION_ID", certiModel.getC8pROTECCION());
                startActivity(intent);
                dialog.dismiss();
                Toast.makeText(Certica.this, "Certificado Abierto!", Toast.LENGTH_SHORT).show();

            }
        });

        // set neutral button OK
        alertDialogBuilder.setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }
}