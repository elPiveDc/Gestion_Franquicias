package com.MiNegocio.configuracioncentral.service.impl;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.integration.cargadatos.CargadorDatos;
import com.MiNegocio.configuracioncentral.integration.cargadatos.CargadorDatosFactory;
import com.MiNegocio.configuracioncentral.repository.BaseDatosRepository;
import com.MiNegocio.configuracioncentral.repository.ObjetoBDRepository;
import com.MiNegocio.configuracioncentral.service.ServicioCargaDatos;


public class ServicioCargaDatosImpl implements ServicioCargaDatos {

    private final ObjetoBDRepository objetoRepo;
    private final BaseDatosRepository bdRepo;

    public ServicioCargaDatosImpl(ObjetoBDRepository objetoRepo, BaseDatosRepository bdRepo) {
        this.objetoRepo = objetoRepo;
        this.bdRepo = bdRepo;
    }

    @Override
    public void cargarDesdeCSV(int idObjeto) throws Exception {

        ObjetoBDFranquicia objeto = obtenerObjetoValidado(idObjeto);
        BaseDatosFranquicia bd = bdRepo.buscarPorId(objeto.getIdBD());

        CargadorDatos cargador = CargadorDatosFactory.obtenerCargador("csv");

        cargador.cargar(bd, objeto.getNombreTabla(), objeto.getColumnas());

    }

    @Override
    public void cargarDesdeJSON(int idObjeto) throws Exception {
        ObjetoBDFranquicia objeto = obtenerObjetoValidado(idObjeto);

        BaseDatosFranquicia bd = bdRepo.buscarPorId(objeto.getIdBD());

        CargadorDatos cargador = CargadorDatosFactory.obtenerCargador("json");

        cargador.cargar(bd, objeto.getNombreTabla(), objeto.getColumnas());

    }

    @Override
    public void cargarDesdeManual(int idObjeto) throws Exception {

        ObjetoBDFranquicia objeto = obtenerObjetoValidado(idObjeto);

        BaseDatosFranquicia bd = bdRepo.buscarPorId(objeto.getIdBD());

        CargadorDatos cargador = CargadorDatosFactory.obtenerCargador("manual");

        cargador.cargar(bd, objeto.getNombreTabla(), objeto.getColumnas());
    }

    private ObjetoBDFranquicia obtenerObjetoValidado(int idObjeto) {
        ObjetoBDFranquicia objeto = objetoRepo.buscarPorId(idObjeto);
        if (objeto == null) {
            throw new IllegalArgumentException("No existe objeto con ID: " + idObjeto);
        }
        if (!"TABLA".equalsIgnoreCase(objeto.getTipoObjeto())) {
            throw new IllegalArgumentException("Solo se pueden cargar datos en objetos tipo TABLA.");
        }
        return objeto;
    }
}
