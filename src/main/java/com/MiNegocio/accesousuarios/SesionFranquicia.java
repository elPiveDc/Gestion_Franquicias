package com.MiNegocio.accesousuarios;

public class SesionFranquicia {

    private static UsuarioFranquicia usuarioActual;

    private SesionFranquicia() {}

    public static void iniciarSesion(UsuarioFranquicia usuario) {
        usuarioActual = usuario;
    }

    public static UsuarioFranquicia obtenerUsuarioActual() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static boolean sesionActiva() {
        return usuarioActual != null;
    }
}
