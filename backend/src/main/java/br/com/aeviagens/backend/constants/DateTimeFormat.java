package br.com.aeviagens.backend.constants;

import lombok.Getter;

@Getter
public enum DateTimeFormat {
    DATE_FORMAT("ddMMyyyy"),
    TIME_FORMAT("HHmm");

    private final String format;

    DateTimeFormat(String format) {
        this.format = format;
    }
}
