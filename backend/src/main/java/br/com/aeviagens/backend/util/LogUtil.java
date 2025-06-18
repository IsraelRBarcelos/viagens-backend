package br.com.aeviagens.backend.util;

import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LogUtil {
    public static void logEntrada(Logger logger, Class<?> clazz, String metodo) {
        logger.info("Entrou na classe {}, no método {}", clazz.getSimpleName(), metodo);
    }

    public static void serviceInfo(Logger logger, Class<?> clazz, String metodo, String mensagemAdicional, Object object) {
        logger.info("Na classe {}, no método {}, {}, {}", clazz.getSimpleName(), metodo, mensagemAdicional, object);
    }

    public static void mostrarObjetoEmqualquerPonto(Logger logger, Class<?> clazz, String metodo, Object object) {
        logger.info("Na classe {}, no método {}, objeto para analise: {}", clazz.getSimpleName(), metodo, stringify(object));
    }







    public static String stringify(Object obj) {
        return stringify(obj, new HashSet<>(), 0);
    }

    private static String stringify(Object obj, Set<Object> visited, int depth) {
        if (obj == null) return "null";

        if (obj.getClass().isPrimitive()
                || obj instanceof String
                || obj instanceof Number
                || obj instanceof Boolean
                || obj.getClass().isEnum()) {
            return obj.toString();
        }

        // Evita loop infinito em objetos circulares
        if (visited.contains(obj)) return "[CICLO DETECTADO]";
        visited.add(obj);

        StringBuilder sb = new StringBuilder();
        String indent = "  ".repeat(depth);

        Class<?> clazz = obj.getClass();
        sb.append(clazz.getSimpleName()).append(" {");

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                sb.append("\n").append(indent).append("  ")
                        .append(field.getName()).append(": ");

                if (value == null) {
                    sb.append("null");
                } else if (isJavaLang(value)) {
                    sb.append(value.toString());
                } else if (value instanceof Collection<?>) {
                    sb.append("[");
                    for (Object item : (Collection<?>) value) {
                        sb.append("\n").append(indent).append("    ")
                                .append(stringify(item, visited, depth + 2));
                    }
                    sb.append("\n").append(indent).append("  ]");
                } else if (value instanceof Map<?, ?>) {
                    sb.append("{");
                    for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                        sb.append("\n").append(indent).append("    ")
                                .append(entry.getKey()).append(": ")
                                .append(stringify(entry.getValue(), visited, depth + 2));
                    }
                    sb.append("\n").append(indent).append("  }");
                } else {
                    sb.append(stringify(value, visited, depth + 1));
                }
            } catch (IllegalAccessException e) {
                sb.append("[Erro ao acessar]");
            }
        }

        sb.append("\n").append(indent).append("}");
        return sb.toString();
    }

    private static boolean isJavaLang(Object obj) {
        return obj instanceof String ||
                obj instanceof Number ||
                obj instanceof Boolean ||
                obj.getClass().isEnum();
    }
}
