
package com.langchain.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ChatApplication {

  @Value("${NAME:Sharon!!!}")
  String name;

  @RestController
  class HelloworldController {

    List<String> emailAddress = new ArrayList<>();

    @Autowired
    ToolsConfiguration MultiToolsAssistant;
    
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
      
       return  MultiToolsAssistant.getMultiToolsAssistant().chat(prompt);
    } 
  }
  public static void main(String[] args) {
    SpringApplication.run(ChatApplication.class, args);
  }
}
