package com.MiNegocio.configuracioncentral.repository;

import com.MiNegocio.configuracioncentral.domain.Usuario;
import java.util.Optional;

public interface UsuarioRepository {
    void guardar(Usuario usuario);
    Optional<Usuario> buscarPorCorreo(String correo);
    Optional<Usuario> buscarPorId(int id);
}
