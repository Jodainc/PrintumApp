package com.kotan.printum.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Kotan@JoyDainc on 22/11/2016.
 */

public class RestserviceTroll {
        private static final String URL = "http://192.168.0.98:8080";
        private retrofit.RestAdapter restAdapterTrole;
        private TrollerTokenService apiTroller;
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Retrofit retrofit ;

        public RestserviceTroll()
        {
            restAdapterTrole = new RestAdapter.Builder()
                    .setEndpoint(URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                     .setConverter(new GsonConverter(gson))
                    .build();
              //  addConverterFactory(GsonConverterFactory.create()).build();
        apiTroller = restAdapterTrole.create(TrollerTokenService.class);
        }
        public TrollerTokenService getApiTroller(){return  apiTroller;}
        private static class ItemTypeAdapterFactory implements TypeAdapterFactory {

                @Override
                public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

                        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
                        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

                        return new TypeAdapter<T>() {
                                @Override
                                public void write(JsonWriter out, T value) throws IOException {
                                        delegate.write(out, value);
                                }

                                @Override
                                public T read(JsonReader in) throws IOException {
                                        JsonElement jsonElement = elementAdapter.read(in);
                                        if (jsonElement.isJsonObject()) {
                                                JsonObject jsonObject = jsonElement.getAsJsonObject();
                                                if (jsonObject.entrySet().size() == 2) {
                                                        jsonObject.remove("\"");
                                                        jsonElement = jsonObject.entrySet().iterator().next().getValue();
                                                }
                                        }
                                        return delegate.fromJsonTree(jsonElement);
                                }
                        }.nullSafe();
                }
        }

}