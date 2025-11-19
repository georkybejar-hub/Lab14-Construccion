package com.tecsup.labs;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de registrar usuarios aplicando reglas
 * de validación simples. Incluye manejo de errores y registro
 * en memoria.
 */
public final class UserRegistrationService {

    /**
     * Último mensaje de error generado por el servicio.
     */
    private String lastErrorMessage = "";

    /**
     * Obtiene el último mensaje de error.
     *
     * @return mensaje de error
     */
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    /**
     * Lista de usuarios registrados.
     */
    private final List<String> users = new ArrayList<>();

    /**
     * Longitud mínima permitida para las contraseñas.
     */
    private static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * Constructor del servicio.
     */
    public UserRegistrationService() {
        System.out.println("Constructor ejecutado");
    }

    /**
     * Registra un usuario verificando reglas básicas.
     *
     * @param username nombre del usuario
     * @param password contraseña del usuario
     * @param email    correo electrónico
     * @return true si se registra correctamente
     */
    public boolean registerUser(
            final String username,
            final String password,
            final String email) {

        if (username == null || username.trim().isEmpty()) {
            lastErrorMessage = "El nombre de usuario está vacío.";
            return false;
        }

        if (password == null) {
            lastErrorMessage = "La contraseña es null.";
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            lastErrorMessage = "La contraseña es muy corta.";
            return false;
        }

        if (email == null || !email.contains("@")
                || !email.contains(".")) {
            lastErrorMessage = "El correo electrónico no es válido.";
            return false;
        }

        try {
            saveUser(username, password, email);
        } catch (IllegalArgumentException e) {
            lastErrorMessage = e.getMessage();
            return false;
        } catch (Exception e) {
            lastErrorMessage = "Error al guardar el usuario.";
            return false;
        }

        System.out.println("Usuario registrado: " + username);
        return true;
    }

    /**
     * Guarda el usuario en memoria simulando una base de datos.
     *
     * @param username nombre
     * @param password contraseña
     * @param email    correo
     */
    private void saveUser(
            final String username,
            final String password,
            final String email) {

        if ("error".equals(username)) {
            throw new IllegalArgumentException(
                    "Nombre de usuario no permitido.");
        }

        users.add(username);
    }

    /**
     * Cuenta los caracteres de una cadena.
     *
     * @param text texto a evaluar
     * @return cantidad de caracteres o -1 si es null
     */
    public int contarCaracteres(final String text) {
        if (text == null) {
            return -1;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(c);
        }
        return sb.length();
    }
}
