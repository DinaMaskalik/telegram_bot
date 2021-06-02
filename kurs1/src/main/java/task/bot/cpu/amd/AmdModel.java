package task.bot.cpu.amd;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class AmdModel {

    public SendMessage sendModelRyzen3Msg(Message message, String text) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Ссылка");
        inlineKeyboardButton1.setUrl("https://catalog.onliner.by/cpu?mfr%5B0%5D=amd&cpu_model%5B0%5D=ryzen3&cpu_model%5Boperation%5D=union");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setText(text);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;

    }

    public SendMessage sendModelRyzen5Msg(Message message, String text) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Ссылка");
        inlineKeyboardButton1.setUrl("https://catalog.onliner.by/cpu?mfr%5B0%5D=amd&cpu_model%5B0%5D=ryzen5&cpu_model%5Boperation%5D=union");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setText(text);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;

    }

    public SendMessage sendModelRyzen7Msg(Message message, String text) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Ссылка");
        inlineKeyboardButton1.setUrl("https://catalog.onliner.by/cpu?mfr%5B0%5D=amd&cpu_model%5B0%5D=ryzen7&cpu_model%5Boperation%5D=union");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setText(text);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;

    }

    public SendMessage sendModelRyzen9Msg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Ссылка");
        inlineKeyboardButton1.setUrl("https://catalog.onliner.by/cpu?mfr%5B0%5D=amd&cpu_model%5B0%5D=ryzen9&cpu_model%5Boperation%5D=union");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);

        sendMessage.setReplyMarkup(null);

        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setText(text);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);




        return sendMessage;

    }
}
