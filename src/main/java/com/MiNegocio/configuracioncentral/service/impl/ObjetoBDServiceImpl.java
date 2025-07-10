package com.MiNegocio.configuracioncentral.service.impl;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.factory.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosBD;
import com.MiNegocio.configuracioncentral.repository.BaseDatosRepository;
import com.MiNegocio.configuracioncentral.repository.ObjetoBDRepository;
import com.MiNegocio.configuracioncentral.service.ObjetoBDService;

public class ObjetoBDServiceImpl implements ObjetoBDService {

    private final ObjetoBDRepository objetoBDRepository;
    private final BaseDatosRepository baseDatosRepository;
    private final GestorObjetosFactory gestorFactory;

    public ObjetoBDServiceImpl(
            ObjetoBDRepository repo,
            BaseDatosRepository bdRepo,
            ConexionMultiBDFactory factory,
            GestorObjetosFactory gestorFactory) {
        this.objetoBDRepository = repo;
        this.baseDatosRepository = bdRepo;
        this.gestorFactory = gestorFactory;
    }

    @Override
    public void crearObjeto(int idBD, ObjetoBDFranquicia objeto) throws Exception {
        BaseDatosFranquicia bd = baseDatosRepository.buscarPorId(idBD);
        objeto.setFechaCreacion(new java.util.Date());
        objeto.setIdBD(idBD);

        GestorObjetosBD gestor = gestorFactory.obtenerGestor(bd.getTipo());
        
        gestor.crearObjeto(bd, objeto);

        objetoBDRepository.guardarObjetoBD(objeto);
    }
}
