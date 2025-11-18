package com.tecsup.labs;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio corregido con mejores prácticas.
 */
public class UserRegistrationService {

    // Campo privado con getter
    private String lastErrorMessage = "";

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    // Lista usando genéricos
    private final List<String> users = new ArrayList<>();

    private static final int MIN_PASSWORD_LENGTH = 8;

    public UserRegistrationService() {
        System.out.println("Constructor ejecutado");
    }

    public boolean registerUser(String username, String password, String email) {

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

        // Validación mejorada de email
        if (email == null || !email.contains("@") || !email.contains(".")) {
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

    private void saveUser(String username, String password, String email) {
        if ("error".equals(username)) {
            throw new IllegalArgumentException("Nombre de usuario no permitido.");
        }
        users.add(username);
    }

    // Método renombrado y optimizado
    public int contarCaracteres(String s) {
        if (s == null) {
            return -1;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c);
        }
        return sb.length();
    }
}
