package com.codebait.bot.fb.config;

import com.clivern.racter.receivers.BaseReceiver;
import com.clivern.racter.utils.Config;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
class BaseReceiverMessageConverter extends AbstractHttpMessageConverter<BaseReceiver> {

  private final Config config;

  BaseReceiverMessageConverter(Config config) {
    super(MediaType.ALL);
    this.config = config;
  }

  @Override
  protected boolean supports(Class<?> aClass) {
    return aClass.isAssignableFrom(BaseReceiver.class);
  }

  @Override
  protected BaseReceiver readInternal(Class<? extends BaseReceiver> aClass, HttpInputMessage httpInputMessage)
      throws IOException {
    String message = getString(httpInputMessage.getBody());
    return new BaseReceiver(config).set(message).parse();
  }

  private String getString(InputStream body) throws IOException {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int length;
    while ((length = body.read(buffer)) != -1) {
      result.write(buffer, 0, length);
    }
    return result.toString(StandardCharsets.UTF_8);
  }

  @Override
  protected void writeInternal(BaseReceiver baseReceiver, HttpOutputMessage httpOutputMessage) {
    throw new HttpMessageNotWritableException("Write for BaseReceiver unsupported.");
  }
}
