package com.codebait.bot.fb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ConfigurationProperties(prefix = "bot")
class BotConfigurationProperties {

  private final Map<String, String> racter = new ConcurrentHashMap<>();

  public Map<String, String> getRacter() {
    return racter;
  }
}
