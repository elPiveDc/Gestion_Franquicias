package com.MiNegocio.configuracioncentral.service.impl;

import com.MiNegocio.configuracioncentral.domain.Franquicia;
import com.MiNegocio.configuracioncentral.domain.EstadoFranquicia;
import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.repository.FranquiciaRepository;
import com.MiNegocio.configuracioncentral.repository.UsuarioRepository;
import com.MiNegocio.configuracioncentral.service.FranquiciaService;

import java.time.LocalDateTime;
import java.util.List;

public class FranquiciaServiceImpl implements FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;
    private final UsuarioRepository usuarioRepository;

    public FranquiciaServiceImpl(FranquiciaRepository franquiciaRepository, UsuarioRepository usuarioRepository) {
        this.franquiciaRepository = franquiciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void registrarFranquicia(int idUsuario, Franquicia franquicia) {
        Usuario usuario = usuarioRepository.buscarPorId(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        if (franquiciaRepository.existeNombreFranquicia(franquicia.getNombre())) {
            throw new IllegalArgumentException("El nombre de la franquicia ya está en uso.");
        }
        franquicia.setFechaCreacion(LocalDateTime.now());
        franquicia.setEstado(EstadoFranquicia.ACTIVA);
        franquicia.setPropietario(usuario);

        franquiciaRepository.guardar(franquicia);
    }

    @Override
    public List<Franquicia> listarFranquiciasPorUsuario(int idUsuario) {
        return franquiciaRepository.obtenerPorUsuario(idUsuario);
    }

    @Override
    public Franquicia obtenerFranquiciaPorId(int id) {
        return franquiciaRepository.buscarPorId(id);
    }
}
