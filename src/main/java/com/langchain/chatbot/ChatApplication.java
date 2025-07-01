
package com.langchain.chatbot;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class ChatApplication {

  @Value("${NAME:Sharon!!}")
  String name;

  @Autowired
  MultiTools multiTools;

    private static final String PROJECT = "langchain-with-tools-chatbot";
    private static final String LOCATION = "asia-south1";
    private static final String MODEL_NAME = "gemini-1.5-pro";

  @RestController
  class HelloworldController {

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
            .tools(multiTools)
            .build();
    
    List<String> emailAddress = new ArrayList<>();
    
    @GetMapping("/")
    String getResponse(@RequestParam(required = false,
            defaultValue = "What is the status of my order?") String prompt) { 
/*      ChatModel model = VertexAiGeminiChatModel.builder()
              .project(PROJECT)
              .location(LOCATION)
              .modelName(MODEL_NAME)
              .build();
      
      String response = model.chat("Tell me a joke");
      String response_Two = model.chat("What do you think about universe?");
      //System.out.println(response);
      return "Hello " + response_Two + "!";*/
      
       return  assistant.chat(prompt);
    } 
  }
  public static void main(String[] args) {
    SpringApplication.run(ChatApplication.class, args);
  }
}
