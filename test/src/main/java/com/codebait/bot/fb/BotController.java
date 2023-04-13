package com.codebait.bot.fb;

import com.clivern.racter.receivers.BaseReceiver;
import com.clivern.racter.receivers.VerifyWebhook;
import com.clivern.racter.utils.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class BotController {

  private final Logger log = LoggerFactory.getLogger(BotController.class);
  private final Config config;
  private final MessageService messageService;

  BotController(Config config, MessageService messageService) {
    this.config = config;
    this.messageService = messageService;
  }

  @GetMapping
  ResponseEntity<String> verifyTokenRoute(@RequestParam(value = "hub.mode", defaultValue = "") String mode,
      @RequestParam(value = "hub.verify_token", defaultValue = "") String verifyToken,
      @RequestParam(value = "hub.challenge", defaultValue = "") String challenge) {

    VerifyWebhook verifyWebhook = new VerifyWebhook(config);
    verifyWebhook.setHubMode(mode);
    verifyWebhook.setHubVerifyToken(verifyToken);
    verifyWebhook.setHubChallenge(challenge);
    if (verifyWebhook.challenge()) {
      log.info("Route token verified.");
      return ResponseEntity.ok(challenge);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification token mismatch");
  }

  @PostMapping
  ResponseEntity<String> handle(@RequestBody BaseReceiver baseReceiver) {
    baseReceiver.getMessages().values().forEach(messageService::handle);
    return ResponseEntity.ok("ok");
  }
}
