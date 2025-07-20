package com.MiNegocio.configuracioncentral.service.impl;

import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.repository.UsuarioRepository;
import com.MiNegocio.configuracioncentral.service.UsuarioService;
import com.MiNegocio.configuracioncentral.utils.PasswordUtils;

import java.time.LocalDateTime;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        Optional<Usuario> existente = usuarioRepository.buscarPorCorreo(usuario.getCorreo());

        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }

        String hash = PasswordUtils.hashear(usuario.getPasswordHash());
        usuario.setPasswordHash(hash);
        usuario.setFechaRegistro(LocalDateTime.now());
        usuarioRepository.guardar(usuario);
    }

    @Override
    public Optional<Usuario> login(String correo, String passwordPlano) {
        Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorCorreo(correo);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            boolean validado = PasswordUtils.verificar(passwordPlano, usuario.getPasswordHash());
            return validado ? Optional.of(usuario) : Optional.empty();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Usuario> obtenerPorId(int id) {
        return usuarioRepository.buscarPorId(id);
    }
}

