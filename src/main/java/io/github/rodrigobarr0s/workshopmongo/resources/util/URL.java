package io.github.rodrigobarr0s.workshopmongo.resources.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

public class URL {
    public static String decodeParam(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }

    public static Instant convertDate(String textDate, Instant defaultDate) {
        try {
            LocalDate localDate = LocalDate.parse(textDate); // yyyy-MM-dd por padrão
            return localDate.atStartOfDay(ZoneOffset.UTC).toInstant(); // Início do dia em UTC
        } catch (DateTimeParseException e) {
            return defaultDate;
        }
    }
}
