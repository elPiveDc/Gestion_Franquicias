package com.MiNegocio.accesousuarios;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import java.awt.Component;
import javax.swing.JOptionPane;

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

    public boolean autenticar(Component parent, String nombreFranquicia, String correo, String password) {
        try {
            BaseDatosFranquicia bd = repo.obtenerBDUsuariosPorFranquicia(nombreFranquicia);
            
            if (bd == null) {
                JOptionPane.showMessageDialog(parent,
                        "No se encontró la tabla 'usuarios' para esta franquicia.",
                        "Franquicia no válida",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }

            UsuarioFranquicia usuario = repo.verificarCredenciales(bd, correo, password);
            if (usuario != null) {
                
                SesionFranquicia.iniciarSesion(usuario);
                JOptionPane.showMessageDialog(parent,
                        "Usuario autenticado correctamente. ¡Bienvenido " + usuario.getNombreUsuario() + "!",
                        "Acceso permitido",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(parent,
                        "Credenciales inválidas. Verifica tu correo y contraseña.",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent,
                    "Error al autenticar usuario: " + e.getMessage(),
                    "Error interno",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
}
