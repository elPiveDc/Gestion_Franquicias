package com.MiNegocio.configuracioncentral.service;

import com.MiNegocio.configuracioncentral.domain.Usuario;
import java.util.Optional;

public interface UsuarioService {
    void registrarUsuario(Usuario usuario);
    Optional<Usuario> login(String correo, String passwordPlano);
    Optional<Usuario> obtenerPorId(int id);
}
