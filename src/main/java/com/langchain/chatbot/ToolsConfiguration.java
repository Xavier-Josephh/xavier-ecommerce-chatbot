package com.langchain.chatbot;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToolsConfiguration {

    MessageWindowChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

    @Autowired
    LLMModelConfiguration model;

    @Autowired
    MultiTools multiTools;
    
    public MultiToolsAssistant getMultiToolsAssistant () {
        return AiServices.builder(MultiToolsAssistant.class)
                .chatLanguageModel(model.getModelConfiguration())
                .chatMemory(memory)
                .systemMessageProvider(id -> "You are an ecommerce chat bot, who will tell customers the status of the order, " +
                        "replace/exchange orders and refund orders which are requested by " +
                        "customer, Be polite always")
                .tools(multiTools)
                .build();
    }
}
