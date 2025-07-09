package com.langchain.chatbot;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import org.springframework.stereotype.Component;

@Component
public class LLMModelConfiguration {
    
    private static final String PROJECT = "langchain-with-tools-chatbot";
    private static final String LOCATION = "asia-south1";
    private static final String MODEL_NAME = "gemini-1.5-pro";
    
    public ChatLanguageModel getModelConfiguration() {
        return VertexAiGeminiChatModel.builder()
                .project(PROJECT)
                .location(LOCATION)
                .modelName(MODEL_NAME)
                .maxOutputTokens(100)
                .build();
    }
}
