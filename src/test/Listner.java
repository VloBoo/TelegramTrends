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
    private String regexUrl[] = {"^https:\\/\\/vk\\.com\\/[a-zA-z]{1,100}\\?w=wall-?[0-9]{1,100}_[0-9]{1,100}$", "^https:\\/\\/vk\\.com\\/wall-?[0-9]{1,100}_[0-9]{1,100}$"};
    private Pattern pattern;

    public Listner(TelegramV tg, VkontakteV vk) {
        this.vk = vk;
        this.tg = tg;
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();
    }

    @Override
    public void UpdateQuery(Update update) throws IOException {
        String mess = update.message.text;
        String idPost = "";
        System.out.println("[VVT][Update text]{" + update.message.chat.username + "," + update.message.chat.id + "}:" + mess);
        boolean as = this.pattern.matches(this.regexUrl[0], mess) ^ this.pattern.matches(this.regexUrl[1], mess);
        if (as) {
            for (int i = 5; i < mess.length(); i++) {
                idPost = idPost + mess.charAt(i);
                if (mess.charAt(i - 3) == 'w' & mess.charAt(i - 2) == 'a' & mess.charAt(i - 1) == 'l' & mess.charAt(i) == 'l') {
                    idPost = "";
                }
            }
            Answer ans = this.gson.fromJson(this.vk.getPost(idPost), Answer.class);
            System.out.println("[VVT][Send OK]:" + tg.sendMessage(String.valueOf(update.message.chat.id), ans.response[0].getEncoderText()));
        }
    }
}
