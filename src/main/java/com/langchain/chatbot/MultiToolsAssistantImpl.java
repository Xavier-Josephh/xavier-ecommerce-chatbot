package com.langchain.chatbot;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Component;

@Component
public class MultiToolsAssistantImpl {

    private static final String PROJECT = "langchain-with-tools-chatbot";
    private static final String LOCATION = "asia-south1";
    private static final String MODEL_NAME = "gemini-1.5-pro";
    

    public void getMultiToolsAssistant() {

        MessageWindowChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

        ChatLanguageModel model = VertexAiGeminiChatModel.builder()
                .project(PROJECT)
                .location(LOCATION)
                .modelName(MODEL_NAME)
                .maxOutputTokens(100)
                .build();
        
        MultiToolsAssistant assistant = AiServices.builder(MultiToolsAssistant.class)
                .chatLanguageModel(model)
                .chatMemory(memory)
                .systemMessageProvider(id -> "You are an ecommerce chat bot, who will tell customers the status of the order, " +
                        "replace/exchange orders and refund orders which are requested by " +
                        "customer, Be polite always")
                .build();
    }
}
