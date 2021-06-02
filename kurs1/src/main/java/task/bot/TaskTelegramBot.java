package task.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import task.bot.cpu.CpuManufacturer;
import task.bot.cpu.amd.AmdModel;
import task.bot.cpu.intel.IntelModel;
import task.bot.memory.MemoryManufacturer;
import task.bot.memory.crucial.CrucialModel;
import task.bot.memory.hyper.HyperXModel;
import task.bot.powers_supply.PowerSupplyManufacturer;
import task.bot.powers_supply.chieftec.ChieftecModel;
import task.bot.powers_supply.fsp.FspModel;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskTelegramBot extends TelegramLongPollingBot {

    final String start = "Здравствуйте! Рады видеть Вас здесь!\nМы может предложить вам следующие комплектующие!\n" +
            "Если Вам нужна помощь, то вызовите /help";

    @Autowired
    CpuManufacturer cpuManufacturer;

    @Autowired
    IntelModel intelModel;

    @Autowired
    AmdModel amdModel;

    @Autowired
    MemoryManufacturer memoryManufacturer;

    @Autowired
    HyperXModel hyperXModel;

    @Autowired
    CrucialModel crucialModel;

    @Autowired
    PowerSupplyManufacturer powerSupplyManufacturer;

    @Autowired
    ChieftecModel chieftecModel;

    @Autowired
    FspModel fspModel;

    @Value("${bot.name}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if("/start".equals(update.getMessage().getText().trim())) {
            sendStartMsg(update.getMessage(), start);

            return;
        }

        if("/help".equals(update.getMessage().getText().trim())) {
            try {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Рады Вам помочь:\n" +
                        "/help - вызов списка с функционалом\n" +
                        "/start - начать поиск товара\n" +
                        "/admin - связаться с администратором");
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        if("/admin".equals(update.getMessage().getText().trim())) {
            sendAdminMsg(update.getMessage(), "Чтобы перейти а тг админа нажмите на кнопку!");

            return;
        }

        if(update.getMessage().getText().equals("Процессор")) {
            sendProMsg(update.getMessage(), "Вы выбрали Процессоры!\nТеперь выберите производителя!");
            return;
        }

        if(update.getMessage().getText().equals("Оперативная карта")) {

            sendMemoryMsg(update.getMessage(), "Вы выбрали оперативная карта!\nВыберите производителя!");

            return;
        }

        if(update.getMessage().getText().equals("Блок питания")) {

            sendPowerSupplyMsg(update.getMessage(), "Вы выбрали блок питания!\nВыберите производителя!");

            return;
        }

        if(update.getMessage().getText().equals("Intel")) {
            final SendMessage sendMessage = cpuManufacturer.sendProModelIntelMsg(update.getMessage(), "Вы выбрали Процессоры Intel!\nТеперь выберите модель!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            sendMessage.setReplyMarkup(null);

            return;
        }

        if(update.getMessage().getText().equals("AMD")) {
            final SendMessage sendMessage = cpuManufacturer.sendProModelAmdMsg(update.getMessage(), "Вы выбрали Процессоры AMD!\nТеперь выберите модель!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Core i3")) {

            final SendMessage sendMessage = intelModel.sendModelCore3Msg
                    (update.getMessage(), "Вы выбрали Процессоры Intel Core i3!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Core i5")) {

            final SendMessage sendMessage = intelModel.sendModelCore5Msg
                    (update.getMessage(), "Вы выбрали Процессоры Intel Core i5!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Core i7")) {

            final SendMessage sendMessage = intelModel.sendModelCore7Msg
                    (update.getMessage(), "Вы выбрали Процессоры Intel Core i7!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Core i9")) {

            SendMessage sendMessage = intelModel.sendModelCore9Msg
                    (update.getMessage(), "Вы выбрали Процессоры Intel Core i9!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Ryzen 3")) {

            final SendMessage sendMessage = amdModel.sendModelRyzen3Msg
                    (update.getMessage(), "Вы выбрали Процессоры AMD Ryzen 3!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Ryzen 5")) {

            final SendMessage sendMessage = amdModel.sendModelRyzen5Msg
                    (update.getMessage(), "Вы выбрали Процессоры AMD Ryzen 5!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Ryzen 7")) {

            final SendMessage sendMessage = amdModel.sendModelRyzen7Msg
                    (update.getMessage(), "Вы выбрали Процессоры AMD Ryzen 7!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Ryzen 9")) {

            SendMessage sendMessage = amdModel.sendModelRyzen9Msg
                    (update.getMessage(), "Вы выбрали Процессоры AMD Ryzen 9!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("HyperX")) {

            SendMessage sendMessage = memoryManufacturer.sendMemoryModelHyperMsg
                    (update.getMessage(), "Вы выбрали Оперативная карта HyperX!\nВыберите модель!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Crucial")) {

            SendMessage sendMessage = memoryManufacturer.sendMemoryModelCrucialMsg
                    (update.getMessage(), "Вы выбрали Оперативная карта Crucial!\nВыберите модель!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("DDR3 DIMM")) {

            SendMessage sendMessage = hyperXModel.sendModelDdr3DimmMsg
                    (update.getMessage(), "Вы выбрали Оперативная карта HyperX DDR3 DIMM!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("DDR3 SO-DIMM")) {

            SendMessage sendMessage = hyperXModel.sendModelDdr3SoDimmMsg
                    (update.getMessage(), "Вы выбрали Оперативная карта HyperX DDR3 SO-DIMM!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("DDR4 DIMM")) {

            SendMessage sendMessage = crucialModel.sendModelDdr4DimmMsg
                    (update.getMessage(), "Вы выбрали Оперативная карта Crucial DDR4 DIMM!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("DDR4 SO-DIMM")) {

            SendMessage sendMessage = crucialModel.sendModelDdr4SoDimmMsg
                    (update.getMessage(), "Вы выбрали Оперативная карта Crucial DDR4 SO-DIMM!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("Chieftec")) {

            SendMessage sendMessage = powerSupplyManufacturer.sendPowerSupplyChieftecMsg
                    (update.getMessage(), "Вы выбрали Блок питания Chieftec!\nВыберите назначение!");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        if(update.getMessage().getText().equals("FSP")) {
            SendMessage sendMessage = powerSupplyManufacturer.sendPowerFSPMsg
                    (update.getMessage(), "Вы выбрали Блок питания FSP!\nВыберите назначение!");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        if(update.getMessage().getText().equals("стандартный")) {

            SendMessage sendMessage = chieftecModel.sendStandardMsg
                    (update.getMessage(), "Вы выбрали Блок питания Chieftec\n с назначением стандартный!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("геймерский")) {

            SendMessage sendMessage = chieftecModel.sendGameMsg
                    (update.getMessage(), "Вы выбрали Блок питания Chieftec\n с назначением геймерский!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("серверный")) {

            SendMessage sendMessage = fspModel.sendServerMsg
                    (update.getMessage(), "Вы выбрали Блок питания FSP\n с назначением серверный!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

        if(update.getMessage().getText().equals("для майнинга")) {

            SendMessage sendMessage = fspModel.sendServerMsg
                    (update.getMessage(), "Вы выбрали Блок питания FSP\n с назначением для майнинга!\nВот Вам ссылка на сайт!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return;
        }

    }
    public void sendStartMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Процессор");
        keyboardFirstRow.add("Оперативная карта");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Блок питания");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void sendAdminMsg(Message message, String text) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Admin");
        inlineKeyboardButton1.setUrl("https://t.me/Bleachnarutou");

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

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void sendProMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("AMD");
        keyboardFirstRow.add("Intel");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void sendMemoryMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("HyperX");
        keyboardFirstRow.add("Crucial");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void sendPowerSupplyMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Chieftec");
        keyboardFirstRow.add("FSP");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

}
