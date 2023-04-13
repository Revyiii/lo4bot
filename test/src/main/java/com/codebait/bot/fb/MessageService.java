package com.codebait.bot.fb;

import com.clivern.racter.receivers.webhook.MessageReceivedWebhook;
import com.clivern.racter.senders.BaseSender;
import com.clivern.racter.senders.templates.MessageTemplate;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class MessageService {

  private static final String HI_MESSAGE = "hej";
  private final Logger log = LoggerFactory.getLogger(MessageService.class);
  private final BaseSender baseSender;
  private final RandomMessageGenerator randomMessageGenerator;

  MessageService(BaseSender baseSender, RandomMessageGenerator randomMessageGenerator) {
    this.baseSender = baseSender;
    this.randomMessageGenerator = randomMessageGenerator;
  }

  void handle(MessageReceivedWebhook message) {
    MessageTemplate messageTemplate = baseSender.getMessageTemplate();
    messageTemplate.setRecipientId(message.getUserId());
    messageTemplate.setNotificationType(BaseSender.NOTIFICATION_TYPE_REGULAR);
    if (HI_MESSAGE.equalsIgnoreCase(message.getMessageText())) {
      messageTemplate.setMessageText("Hej, co tam?");
    } else {
      messageTemplate.setMessageText(randomMessageGenerator.getMessage());
    }
    try {
      baseSender.send(messageTemplate);
    } catch (UnirestException e) {
      log.error(e.getMessage(), e);
    }
  }
}
