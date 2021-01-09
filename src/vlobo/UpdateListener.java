package vlobo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vlobo.queryTG.QueryTG;
import vlobo.queryVK.Answer;

import java.io.IOException;

public class UpdateListener extends Thread {
    public TelegramV core;

    public UpdateListener(TelegramV tg,String name) {
        super(name);
        this.core = tg;
    }

    public void run()  {
        while (this.core.isOnline()) {
            String url = this.core.urlApi+this.core.token+"/getUpdates";
            String queryStr="";
            try {
                queryStr=this.core.CON(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            QueryTG query = gson.fromJson(queryStr, QueryTG.class);
            System.out.println(1);
        }
    }
}
