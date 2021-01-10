package vlobo.VkApiTelegram;

import vlobo.VkApiTelegram.queryTG.Update;

import java.io.IOException;

public interface TelegramVListener {
    void UpdateQuery(Update update) throws IOException;
}
