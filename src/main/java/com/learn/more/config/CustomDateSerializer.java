package com.learn.more.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CustomDateSerializer extends StdSerializer<Date> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public CustomDateSerializer() {
    super(Date.class);
  }

  @Override
  public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    Instant instant = value.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
    gen.writeString(FORMATTER.format(localDateTime));
  }
}
