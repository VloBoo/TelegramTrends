package VloBo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Launcher {
    private static String url="https://vk.com/wall-155462018_215624";
    private final static String token="";
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements element = doc.select(".wall_post_text");
        System.err.println(element.text());
        System.err.println("tetstefd");
        TelegramV telegramV = new TelegramV(token,"name");
    }
}
