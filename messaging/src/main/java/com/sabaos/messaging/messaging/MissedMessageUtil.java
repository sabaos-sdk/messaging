package com.sabaos.messaging.messaging;

import com.sabaos.messaging.messaging.api.Api;
import com.sabaos.messaging.messaging.api.ApiException;
import com.sabaos.messaging.messaging.api.Callback;
import com.sabaos.messaging.messaging.log.Log;
import com.sabaos.messaging.client.api.MessageApi;
import com.sabaos.messaging.client.model.Message;
import com.sabaos.messaging.client.model.PagedMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.sabaos.messaging.messaging.api.Callback.call;

public class MissedMessageUtil {
    static final int NO_MESSAGES = 0;

    private final MessageApi api;

    public MissedMessageUtil(MessageApi api) {
        this.api = api;
    }

    public void lastReceivedMessage(Callback.SuccessCallback<Integer> successCallback) {
        api.getMessages(1, 0)
                .enqueue(
                        call(
                                (messages) -> {
                                    if (messages.getMessages().size() == 1) {
                                        successCallback.onSuccess(
                                                messages.getMessages().get(0).getId());
                                    } else {
                                        successCallback.onSuccess(NO_MESSAGES);
                                    }
                                },
                                (e) -> {}));
    }

    public List<Message> missingMessages(int till) {
        List<Message> result = new ArrayList<>();
        try {

            Integer since = null;
            while (true) {
                PagedMessages pagedMessages = Api.execute(api.getMessages(10, since));
                List<Message> messages = pagedMessages.getMessages();
                List<Message> filtered = filter(messages, till);
                result.addAll(filtered);
                if (messages.size() != filtered.size()
                        || messages.size() == 0
                        || pagedMessages.getPaging().getNext() == null) {
                    break;
                }
                since = pagedMessages.getPaging().getSince();
            }
        } catch (ApiException e) {
            Log.e("cannot retrieve missing messages", e);
        }
        Collections.reverse(result);
        return result;
    }

    private List<Message> filter(List<Message> messages, int till) {
        List<Message> result = new ArrayList<>();

        for (Message message : messages) {
            if (message.getId() > till) {
                result.add(message);
            } else {
                break;
            }
        }

        return result;
    }
}
