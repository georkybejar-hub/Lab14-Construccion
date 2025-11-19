package com.tecsup.labs;

/**
 * Clase principal para probar el servicio de registro.
 */
public final class Main {
    /**
     * Private constructor to hide the implicit public one.
     */
    private Main() {
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(final String[] args) {

        UserRegistrationService service = new UserRegistrationService();

        service.registerUser("juan", "123", "juan@correo.com");
        System.out.println(service.getLastErrorMessage());

        service.registerUser(null, "12345678", "correo-sin-arroba");
        System.out.println(service.getLastErrorMessage());

        service.registerUser("error", "12345678",
                "error@correo.com");
        System.out.println(service.getLastErrorMessage());
    }
}
