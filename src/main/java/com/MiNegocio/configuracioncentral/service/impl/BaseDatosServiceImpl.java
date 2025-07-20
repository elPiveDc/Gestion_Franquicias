package com.MiNegocio.configuracioncentral.service.impl;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.EstadoBD;
import com.MiNegocio.configuracioncentral.repository.BaseDatosRepository;
import com.MiNegocio.configuracioncentral.repository.FranquiciaRepository;
import com.MiNegocio.configuracioncentral.service.BaseDatosService;
import com.MiNegocio.configuracioncentral.integration.basedatos.*;
import java.util.List;

public class BaseDatosServiceImpl implements BaseDatosService {

    private final BaseDatosRepository bdRepository;
    private final FranquiciaRepository franquiciaRepository;

    public BaseDatosServiceImpl(BaseDatosRepository bdRepository, FranquiciaRepository franquiciaRepository) {
        this.bdRepository = bdRepository;
        this.franquiciaRepository = franquiciaRepository;
    }

    @Override
    public void registrarBaseDatos(int idFranquicia, BaseDatosFranquicia bd) {
        var franquicia = franquiciaRepository.buscarPorId(idFranquicia);
        if (franquicia == null) {
            throw new IllegalArgumentException("Franquicia no encontrada");
        }
        try {
            GestorCreacionBD gestor = GestorCreacionBDFactory.getGestor(bd.getTipo().name());
            gestor.crearBaseDatos(bd);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la base de datos en el servidor: " + e.getMessage(), e);
        }
        bd.setEstado(EstadoBD.CONFIGURADA);
        bdRepository.guardar(bd);
    }

    @Override
    public List<BaseDatosFranquicia> listarBasesDatosPorFranquicia(int idFranquicia) {
        return bdRepository.obtenerPorFranquicia(idFranquicia);
    }

    @Override
    public BaseDatosFranquicia obtenerBaseDatosPorId(int id) {
        return bdRepository.buscarPorId(id);
    }
}
