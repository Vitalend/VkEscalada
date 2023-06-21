package Bot;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;

import java.util.List;
import java.util.Random;


import static java.lang.Thread.sleep;

public class EscaladaBot {
    public static void main(String[] args) {

        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        GroupActor actor = new GroupActor(220873386, "vk1.a.lJJiMnovZF-fh6_jJ-ntfFajUL2oOPFr5ePtFvXvUmQvnkmm4nc897uOoRhBwx37an-HNVkkQ70_XHZBHRqUoBxEajHMUzlhKBVHYPE3ZrcZQdXloP0TpG_xyJ1_y2pxNeBeVU2VQM3_v_n362PbpE_Ur3orcWh4J6PFclEYMstpTs1LlEJlgkZgK7dC3MV_k1KQcSvtTiu6ZxR131MFAA");
        Integer ts = null;
        try {
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }

        var messageAnswerer = new MessageAnswer();

        while (true) {
            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages;
            try {
                messages = historyQuery.execute().getMessages().getItems();
            } catch (ApiException | ClientException e) {
                throw new RuntimeException(e);
            }
            if (!messages.isEmpty()) {
                messages.forEach(message -> {
                    System.out.println(message.toString());
                    try {
                        String answer = messageAnswerer.getAnswer(message.getText());
                        vk.messages()
                                .send(actor)
                                .message(answer)
                                .userId(message.getFromId())
                                .randomId(random.nextInt(10000))
                                .execute();

                    } catch (ApiException | ClientException e) {
                        e.printStackTrace();
                    }
                });
            }
            try {
                ts = vk.messages().getLongPollServer(actor).execute().getTs();
            } catch (ApiException | ClientException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
