package br.com.aeviagens.backend.endpoints.dto;

public class DefaultResponseDTO<T> {
    // Getters e setters
    private T data;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DefaultResponseDTO() {}

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



