package vlobo.VkApiTelegram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VkontakteV {
    private final String urlApi = "https://api.vk.com/method/";
    private String token;
    private String versionApi = "5.126";
    private boolean online = true;

    public VkontakteV(String token) {
        this.token = token;
    }

    public String getPost(String idPost) throws IOException {
        String method = "wall.getById";
        return CON(this.urlApi + method + "?posts=" + idPost + "&access_token=" + this.token + "&v=" + this.versionApi);
    }

    private String CON(String url) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void stop() {
        this.online = false;
    }
}
