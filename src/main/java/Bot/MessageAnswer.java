package Bot;

import java.util.HashMap;
import java.util.Map;

public class MessageAnswer {
    private final Map<String, String> messageBase = new HashMap<>() {{
        put("привет", "Добрый день, я Escalada Bot, помогу чем смогу.1");
        put("дела", "Спасибо хорошо");
        put("пока", "Ждем Вас на скалодроме!1");
        put("свидания", "Ждем Вас на скалодроме!2");
        put("расписание", "Мы работаем каждый день1");
        put("режим", "Мы работаем каждый день2");
        put("стоит", "Для Вас недорого1");
        put("стоимость", "Для Вас недорого2");
        put("работаете", "Мы работаем каждый день3");
        put("прийти", "Мы работаем каждый день4");
        put("приду", "Мы работаем каждый день5");
        put("придем", "Мы работаем каждый день6");
        put("добрый", "Добрый день, я Escalada Bot, помогу чем смогу.2");
    }};
    String[] message;

    public String getAnswer(String request) {
        request = request.replaceAll("\\s*\\p{Punct}+\\s*$", "");
        message = request.toLowerCase().split(" ");

        for (String word : message) {
            if (messageBase.containsKey(word)) {
                return messageBase.get(word);
            }
        }
        return "Я Вас не понял, я всего лишь Бот, свяжитесь, пожалуйста, с Бусинкой";
    }
}