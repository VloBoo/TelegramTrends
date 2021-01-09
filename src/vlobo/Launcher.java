package vlobo;

import vlobo.queryVK.Answer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        VkontakteV vk = new VkontakteV("77fcb8c477fcb8c477fcb8c4b277897188777fc77fcb8c4280882dcb8b16f3379818e3c");
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Answer ans = gson.fromJson(vk.getPost("-155462018_216816"), Answer.class);
        TelegramV tg = new TelegramV("bot1441003414:AAGi14K3wW_63OxmLqVel-Yu7K7NMstMdzY");
        System.out.println(tg.sendMessage("988376761", ans.response[0].getEncoderText()));
         tg.listener(null);
    }
}
