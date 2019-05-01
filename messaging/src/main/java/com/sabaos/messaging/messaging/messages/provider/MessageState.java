package com.sabaos.messaging.messaging.messages.provider;

import com.sabaos.messaging.client.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageState {
    public static final int ALL_MESSAGES = -1;

    int appId;
    boolean loaded;
    boolean hasNext;
    int nextSince = 0;
    List<Message> messages = new ArrayList<>();
}
