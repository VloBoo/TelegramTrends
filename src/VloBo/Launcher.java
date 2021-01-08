package VloBo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Launcher {
    private static String url = "https://vk.com/wall-155462018_215916";
    private final static String token = "";

    public static void main(String[] args) throws IOException {
       /* URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println();
        System.out.println(response.toString());*/

        VkontakteV vk = new VkontakteV("77fcb8c477fcb8c477fcb8c4b277897188777fc77fcb8c4280882dcb8b16f3379818e3c");
        System.out.println(vk.getPost("-201214490_7"));



      /* Document doc = Jsoup.connect(url).get();
        Elements element = doc.select(".wall_post_text");
        System.err.println(element.text());
        System.err.println("tetstefd");
        TelegramV telegramV = new TelegramV(token,"name");*/
    }
}
