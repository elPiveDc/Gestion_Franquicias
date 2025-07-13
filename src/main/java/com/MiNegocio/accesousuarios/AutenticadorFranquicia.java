package com.MiNegocio.accesousuarios;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;

public class AutenticadorFranquicia {

    private final RepositorioAutenticacion repo = new RepositorioAutenticacion();

    public boolean autenticar(String nombreFranquicia, String correo, String passwordHash) {
        try {
            BaseDatosFranquicia bd = repo.obtenerBDUsuariosPorFranquicia(nombreFranquicia);
            if (bd == null) {
                System.out.println("No se encontró tabla 'usuarios' para esta franquicia.");
                return false;
            }

            UsuarioFranquicia usuario = repo.verificarCredenciales(bd, correo, passwordHash);
            if (usuario != null) {
                SesionFranquicia.iniciarSesion(usuario);
                System.out.println("Usuario autenticado correctamente. Bienvenido " + usuario.getNombreUsuario());
                return true;
            } else {
                System.out.println("Credenciales inválidas.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al autenticar usuario: " + e.getMessage());
            return false;
        }
    }
}
