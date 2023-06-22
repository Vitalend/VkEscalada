package Bot;

import java.util.*;

public class MessageAnswer {
    private final Map<String, String> messageBase = new HashMap<>() {{
        put("привет", "Добрый день, я Escalada Bot, помогу чем смогу");
        put("дела", "Спасибо хорошо");
        put("пока", "Ждем Вас на скалодроме!");
        put("свидания", "Ждем Вас на скалодроме!");
        put("расписание", "Мы работаем каждый день");
        put("режим", "Мы работаем каждый день");
        put("стоит", "Для Вас недорого");
        put("стоимость", "Для Вас недорого");
        put("работаете", "Мы работаем каждый день");
        put("прийти", "Мы работаем каждый день");
        put("приду", "Мы работаем каждый день");
        put("придем", "Мы работаем каждый день");
        put("добрый", "Добрый день, я Escalada Bot, помогу чем смогу");
        put("цена", "Для Вас недорого");
    }};
    String[] message;
    String answer;
    // List<String> list = new ArrayList<>();
    StringBuilder constructor = new StringBuilder();
    Set<String> linkedHashSet = new LinkedHashSet<>();

    public String getAnswer(String request) {
        message = request.toLowerCase().split(" ");
        for (String word : message) {
            if (messageBase.containsKey(word.replaceAll("\\s*\\p{Punct}+\\s*$", ""))) {
                linkedHashSet.add(messageBase.get(word.replaceAll("\\s*\\p{Punct}+\\s*$", "")));
            }
        }
        if (linkedHashSet.isEmpty()) {
            return "Я Вас не понял, я всего лишь Бот, свяжитесь, пожалуйста, с Бусинкой";
        }
       // for (int i = 0; i < list.size(); i++) {
      //     for (int j = i + 1; j < list.size(); j++) {
       //         if (messageBase.get(list.get(i)).equals(messageBase.get(list.get(j)))) {
        //            list.remove(j);
          //      }
         //   }
      //  }
        for (String line : linkedHashSet) {
            constructor.append(line);
            constructor.append(". ");
        }
        answer = constructor.toString();
        constructor.delete(0, constructor.length());
        linkedHashSet.clear();
        return answer;
    }
}
