package com.codebait.bot.fb;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
class RandomMessageGenerator {
  private final List<String> messages = List.of("aha", "ok", "ciekawe 🤔", "mów dalej", "🙂");

  String getMessage(){
    int index = ThreadLocalRandom.current().nextInt(messages.size());
    return messages.get(index);
  }
}
