package vlobo.VkApiTelegram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VkontakteV {
    private final String urlApi = "https://api.vk.com/method/";
    private String token;
    private final String versionApi = "5.126";
    private boolean online = true;

    public VkontakteV(String token) {
        this.token = token;
    }

    public String getPost(String idPost) throws IOException {
        String method = "wall.getById";
        return connectV(this.urlApi + method + "?posts=" + idPost + "&access_token=" + this.token + "&v=" + this.versionApi);
    }

    protected String connectV(String url) throws IOException {
        return connectV(url, "GET");
    }

    protected String connectV(String url, String method) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod(method);

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
