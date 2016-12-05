package com.kotan.printum.Ui.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.kotan.printum.Model.CertiModel;
import com.kotan.printum.Network.VolleySingleton;
import com.kotan.printum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Certica extends Activity  {
        private EditText editText;
        private Button button;
        CertiModel dataModel;
        private String C8pROTECCION;
    public static final String TAG = "Certica";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pdfview);
            button  =(Button) findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    JsonParser();
                }
            });
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
        @Override
        public void onBackPressed()
        {
        }
        public void JsonParser() {
            final String url = "http://192.168.0.98:8080/api/Pro_Certificados/212-1-3480/2016-10-04";
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
                    headers.put("Authorization", "Bearer HwlTHUt-Fn3Em_rXgf6HCgX19ItiQioOWAUcIkEILraHi6O5bDHD57siFhPVWv7ofD_UzmA5pvF4Rwn6WKOV1gDSwSB5ERG-d5D6gty2WG1dT7J7t4h2IzJ4m5a6V_6Q7QnHmJbqzjoKSrTgS4UR0ddTv5xrxpQOxAlSPlT_CnDdTFo-4w1pPfTF7ubLe_HRowCbHsMYJ5hRwI-9PjYKk6jGTo-HaMJkMB8SK7zV_6rJG6pe4Sc-2XXWLgucxO5WdMZt7uQnagP1fmtsgYT3oqcmf4AJoq4BgzrAa8YQa0Muh_9x7uz8JJ1iz5SpPhK2pgiGvEzXbYiaaS18aO08Ds1lbOAdFbAGjVRcpsbfpH5fSm0lyd07037NR0vvulZ9ALoAuGT1Wo5EPjeFIsBoiRkffLr268_uH2IdJWslwZIZ7ZVr1S_4lJujMH0TdmIquvHTcbEmO70S4s8LxKvhfFZQ2j4nmn0Z7ZCmvC2p1oc8R5b-Bnmov133i3Hwu-P8zBxGTlWmwPylTt6N6iEK6NRcv5RCW4X_oe5ufMYyjZ8");
                    return headers;
                }
            };
            Log.d(TAG, "Error Message10" + jsObjRequest);
            requestQueue.add(jsObjRequest);
        }

        private void parseJSONresponse(JSONArray response) {
            try {
                String jsonString = response.toString();
                Log.i(TAG, "Json" + jsonString);
                JSONArray jsonArray = new JSONArray(URLDecoder.decode( jsonString, "UTF-8" ));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String C8Codigo = jsonObject.getString("C8Codigo");
                    Log.i(TAG, "C8Codigo" + C8Codigo);
                    String c8epp = jsonObject.getString("c8epp");
                    Log.i(TAG, "C8Codigo" + c8epp);
                    String c8mASiNFO = jsonObject.getString("c8mASiNFO");
                    Log.i(TAG, "C8Codigo" + c8mASiNFO);
                    C8pROTECCION = jsonObject.getString("C8pROTECCION");
                    Log.i(TAG, "C8Codigo" + C8pROTECCION);
                    dataModel = new CertiModel(C8Codigo,c8epp,c8mASiNFO,C8pROTECCION);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "dataModelArrayList jsonparser" );

        }
}