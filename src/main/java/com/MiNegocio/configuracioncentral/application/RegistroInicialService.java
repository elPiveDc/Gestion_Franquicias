package com.MiNegocio.configuracioncentral.application;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.Franquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.factory.ConexionMultifactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosBD;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.repository.BaseDatosRepository;
import com.MiNegocio.configuracioncentral.repository.ObjetoBDRepository;
import com.MiNegocio.configuracioncentral.repository.UsuarioRepository;
import com.MiNegocio.configuracioncentral.service.BaseDatosService;
import com.MiNegocio.configuracioncentral.service.FranquiciaService;
import com.MiNegocio.configuracioncentral.service.UsuarioService;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import java.util.List;
import javax.swing.JOptionPane;

public class RegistroInicialService {

    private final UsuarioService usuarioService;
    private final FranquiciaService franquiciaService;
    private final BaseDatosService baseDatosService;

    public RegistroInicialService(UsuarioService usuarioService,
            FranquiciaService franquiciaService,
            BaseDatosService baseDatosService) {
        this.usuarioService = usuarioService;
        this.franquiciaService = franquiciaService;
        this.baseDatosService = baseDatosService;
    }

    public void registrarUsuarioYNegocio(Usuario usuario, Franquicia franquicia, List<BaseDatosFranquicia> bases) {
        usuarioService.registrarUsuario(usuario);

        franquicia.setPropietario(usuario);
        franquiciaService.registrarFranquicia(usuario.getId(), franquicia);

        for (BaseDatosFranquicia bd : bases) {
            bd.setId(franquicia.getId());
            baseDatosService.registrarBaseDatos(franquicia.getId(), bd);
        }

        System.out.println("Usuario y negocio registrados correctamente.");
    }

    public void registrarUsuarioYNegocio(Component parent, Usuario usuario, Franquicia franquicia, List<BaseDatosFranquicia> bases) {
        try {
            usuarioService.registrarUsuario(usuario);

            franquicia.setPropietario(usuario);
            franquiciaService.registrarFranquicia(usuario.getId(), franquicia);

            for (BaseDatosFranquicia bd : bases) {
                bd.setId(franquicia.getId());
                baseDatosService.registrarBaseDatos(franquicia.getId(), bd);
            }

            JOptionPane.showMessageDialog(parent,
                    "Usuario y franquicia registrados correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent,
                    "Error al registrar el usuario o la franquicia: " + e.getMessage(),
                    "Error de registro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
