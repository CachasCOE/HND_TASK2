package com.alumnos.hnd_task2.api;

import com.alumnos.hnd_task2.beans.TipsBean;
import com.alumnos.hnd_task2.response.ResponseTip;
import com.alumnos.hnd_task2.response.ResponseTips;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ApiTips {
    private final String URL = "http://androiddiego.esy.es/APIandroid/tips/";
    private OkHttpClient client;

    public ApiTips() {
        client = new OkHttpClient();
    }

    public ArrayList<TipsBean> getTips(String token) {
        try {
            Request request = new Request.Builder()
                    .url(URL)
                    .addHeader("Token", token)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponseTips responseTips =ResponseTips.fromJson(json);
            return responseTips.getTips();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TipsBean getTips(int id, String token) {
        try {
            Request request = new Request.Builder()
                    .url(URL+id)
                    .addHeader("Token", token)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponseTip responseTip =ResponseTip.fromJson(json);
            return responseTip.getTip();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
