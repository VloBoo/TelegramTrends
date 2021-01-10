package test;


import vlobo.VkApiTelegram.TelegramV;
import vlobo.VkApiTelegram.VkontakteV;

public class Laucher {
    public static void main(String[] args) {
        TelegramV tg = new TelegramV("bot1441003414:AAGi14K3wW_63OxmLqVel-Yu7K7NMstMdzY");
        VkontakteV vk = new VkontakteV("77fcb8c477fcb8c477fcb8c4b277897188777fc77fcb8c4280882dcb8b16f3379818e3c");
        Listner listner = new Listner(tg, vk);
        tg.listener(listner);
    }
}
