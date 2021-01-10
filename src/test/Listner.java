package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vlobo.VkApiTelegram.TelegramV;
import vlobo.VkApiTelegram.TelegramVListener;
import vlobo.VkApiTelegram.VkontakteV;
import vlobo.VkApiTelegram.queryTG.Update;
import vlobo.VkApiTelegram.queryVK.Answer;

import java.io.IOException;
import java.util.regex.Pattern;

public class Listner implements TelegramVListener {
    private TelegramV tg;
    private VkontakteV vk;
    private Gson gson;
    private String regexUrl="^https:\\/\\/vk\\.com\\/[a-zA-z]{1,100}\\?w=wall-?[0-9]{1,100}_[0-9]{1,100}$";
    private Pattern pattern;
    public Listner(TelegramV tg, VkontakteV vk) {
        this.vk = vk;
        this.tg = tg;
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();
    }

    @Override
    public void UpdateQuery(Update update) throws IOException {
        System.out.println(746564456);
        String mess = update.message.text;
        String idPost="";
        System.out.println(mess);
        boolean as = this.pattern.matches(this.regexUrl,mess);
        if(this.pattern.matches(this.regexUrl,mess)){
            for(int i = 0;i < mess.length();i++){
                idPost = idPost+mess.charAt(i);
                if(mess.charAt(i)=='='){
                    idPost="";
                    i+=4;
                }
            }
            System.out.println(746564456);
            Answer ans = this.gson.fromJson(this.vk.getPost(idPost), Answer.class);
            System.out.println(tg.sendMessage(String.valueOf(update.message.chat.id), ans.response[0].getEncoderText()));
        }
    }
}
