package vlobo.VkApiTelegram.queryVK;

import java.io.IOException;
import java.net.URLEncoder;

public class Post {
    public long id;
    public long from_id;
    public long owner_id;
    public long date;
    public int marked_as_ads;
    public String post_type;
    public String text;

    public String getEncoderText()  throws IOException {
        return URLEncoder.encode(this.text, "UTF-8");
    };
}
