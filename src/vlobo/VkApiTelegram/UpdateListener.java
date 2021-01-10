package vlobo.VkApiTelegram;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vlobo.VkApiTelegram.queryTG.QueryTG;
import java.io.IOException;

public class UpdateListener extends Thread {
    private TelegramV core;
    private long lastIdUpdate=0;
    private String url;
    private Gson gson;


    public UpdateListener(TelegramV tg, String name) {
        super(name);
        this.core = tg;
        this.url = this.core.urlApi + this.core.token + "/getUpdates?offset=" + this.lastIdUpdate;
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();
    }

    public void run() {
        //определяем последний запрос
        QueryTG query = this.getUpdates(this.url);
        if (!(query.result == null)) {
            for (int i = 0; i < query.result.length; i++) {
                if (this.lastIdUpdate < query.result[i].update_id) {
                    this.lastIdUpdate = query.result[i].update_id;
                }
            }
        }
        while (this.core.isOnline()) {
            query = this.getUpdates(this.url);
            if(query.result==null){continue;}
            for (int i = 0; i < query.result.length; i++) {
                if (this.lastIdUpdate < query.result[i].update_id) {
                    System.out.println(100005);
                    this.lastIdUpdate = query.result[i].update_id;
                    try {
                        this.core.listenerClass.UpdateQuery(query.result[i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private QueryTG getUpdates(String url) {
        String buf = "";
        try {
            buf = this.core.CON(this.url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.gson.fromJson(buf, QueryTG.class);
    }
}

