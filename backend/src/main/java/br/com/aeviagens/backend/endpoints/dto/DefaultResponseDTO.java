package br.com.aeviagens.backend.endpoints.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultResponseDTO<T> {

    private T data;
    private String message;

    public DefaultResponseDTO(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public static <T> DefaultResponseDTO<T> success(T data) {
        return new DefaultResponseDTO<>(data, "Operação realizada com sucesso.");
    }

    public static <T> DefaultResponseDTO<T> success(T data, String message) {
        return new DefaultResponseDTO<>(data, message);
    }

    public static <T> DefaultResponseDTO<T> error(String message) {
        return new DefaultResponseDTO<>(null, message);
    }

}



