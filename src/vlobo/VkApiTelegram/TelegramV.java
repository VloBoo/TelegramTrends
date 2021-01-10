package vlobo.VkApiTelegram;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TelegramV {
    protected String token;
    protected final String urlApi = "https://api.telegram.org/";
    protected TelegramVListener listenerClass;
    private boolean online = true;

    public TelegramV(String token) {
        this.token = token;
    }

    public String sendMessage(String idChat, String text) throws IOException {
        String method = "/sendMessage";
        return CON(this.urlApi + token + method + "?chat_id=" + idChat + "&text=" + text);
    }

    public void listener(TelegramVListener newClass) {
        this.listenerClass=newClass;
        new UpdateListener(this,"UpdateCheakTelegram").start();

    }

    protected String CON(String url) throws IOException {
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
    public boolean isOnline(){
        return this.online;
    }
    public void stop() {
        this.online = false;
    }
}
