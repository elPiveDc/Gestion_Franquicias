package com.MiNegocio.configuracioncentral.application;

import com.MiNegocio.configuracioncentral.repository.UsuarioRepository;
import com.MiNegocio.configuracioncentral.repository.FranquiciaRepository;

public class VerificacionPreviaService {

    private final UsuarioRepository usuarioRepository;
    private final FranquiciaRepository franquiciaRepository;

    public VerificacionPreviaService(UsuarioRepository usuarioRepository,
                                     FranquiciaRepository franquiciaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.franquiciaRepository = franquiciaRepository;
    }

    public boolean correoDisponible(String correo) {
        return usuarioRepository.buscarPorCorreo(correo).isEmpty();
    }

    public boolean nombreFranquiciaDisponible(String nombre) {
        return franquiciaRepository.obtenerPorUsuario(-1).stream()
            .noneMatch(f -> f.getNombre().equalsIgnoreCase(nombre));
        // Nota: deberías hacer una búsqueda real por nombre en la BD
    }
}