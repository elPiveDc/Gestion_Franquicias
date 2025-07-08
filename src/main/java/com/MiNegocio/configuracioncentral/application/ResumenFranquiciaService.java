package com.MiNegocio.configuracioncentral.application;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.Franquicia;
import com.MiNegocio.configuracioncentral.service.BaseDatosService;
import com.MiNegocio.configuracioncentral.service.FranquiciaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumenFranquiciaService {

    private final FranquiciaService franquiciaService;
    private final BaseDatosService baseDatosService;

    public ResumenFranquiciaService(FranquiciaService franquiciaService,
                                    BaseDatosService baseDatosService) {
        this.franquiciaService = franquiciaService;
        this.baseDatosService = baseDatosService;
    }

    public Map<Franquicia, List<BaseDatosFranquicia>> obtenerResumenPorUsuario(int idUsuario) {
        List<Franquicia> franquicias = franquiciaService.listarFranquiciasPorUsuario(idUsuario);
        Map<Franquicia, List<BaseDatosFranquicia>> resumen = new HashMap<>();

        for (Franquicia f : franquicias) {
            List<BaseDatosFranquicia> bases = baseDatosService.listarBasesDatosPorFranquicia(f.getId());
            resumen.put(f, bases);
        }

        return resumen;
    }
}
