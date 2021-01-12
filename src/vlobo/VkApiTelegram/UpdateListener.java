package vlobo.VkApiTelegram;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vlobo.VkApiTelegram.queryTG.QueryTG;

import java.io.IOException;

public class UpdateListener extends Thread {
    private TelegramV core;
    private long lastIdUpdate = 1   ;
    private String url;
    private Gson gson;
    private long tick=0;


    public UpdateListener(TelegramV tg, String name) {
        super(name);
        this.core = tg;
        this.url = this.core.urlApi + this.core.token + "/getUpdates?offset=";
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();
    }

    public void run() {
        System.err.println("[VVT][Listener]:Start...");
        QueryTG query = this.getUpdates(this.url + this.lastIdUpdate);
        if (!(query == null)) {
            if (!(query.result == null)) {
                for (int i = 0; i < query.result.length; i++) {
                    if (this.lastIdUpdate < query.result[i].update_id) {
                        this.lastIdUpdate = query.result[i].update_id;
                    }
                }
            }
        }
        while (this.core.isOnline()) {
            query = this.getUpdates(this.url + this.lastIdUpdate);
            System.out.print("\r");
            System.out.print(this.tick);
            this.tick++;
            if (query == null) {
                continue;
            }
            if (query.result == null) {
                continue;
            }
            for (int i = 0; i < query.result.length; i++) {
                if (this.lastIdUpdate < query.result[i].update_id) {
                    this.lastIdUpdate = query.result[i].update_id;
                    try {
                        //тут нужен будет потом отдельный поток включать
                        this.core.listenerClass.UpdateQuery(query.result[i]);
                        System.out.print("0");
                        this.tick=0;
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
            buf = this.core.connectV(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.gson.fromJson(buf, QueryTG.class);
    }
}

