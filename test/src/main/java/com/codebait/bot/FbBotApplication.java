package com.codebait.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FbBotApplication {

  public static void main(String[] args) {
    SpringApplication.run(FbBotApplication.class, args);
  }

}
