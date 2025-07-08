package com.MiNegocio.configuracioncentral.application;

import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.service.UsuarioService;

import java.util.Optional;

public class LoginApplicationService {

    private final UsuarioService usuarioService;

    public LoginApplicationService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario login(String correo, String passwordPlano) {
        Optional<Usuario> usuario = usuarioService.login(correo, passwordPlano);
        return usuario.orElseThrow(() -> new RuntimeException("❌ Usuario o contraseña incorrectos"));
    }
}
