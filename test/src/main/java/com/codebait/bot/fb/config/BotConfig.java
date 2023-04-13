package com.codebait.bot.fb.config;

import com.clivern.racter.senders.BaseSender;
import com.clivern.racter.utils.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
class BotConfig {

  private final BotConfigurationProperties botConfigurationProperties;

  BotConfig(BotConfigurationProperties botConfigurationProperties) {
    this.botConfigurationProperties = botConfigurationProperties;
  }

  @Bean
  Config getConfig() {
    Config configs = new Config();
    botConfigurationProperties.getRacter().forEach(configs::set);
    configs.configLogger();
    return configs;
  }

  @Bean
  BaseSender getBaseSender(Config config) {
    return new BaseSender(config);
  }

}
