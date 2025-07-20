package com.MiNegocio.configuracioncentral.service.impl;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.factory.ConexionMultifactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosBD;
import com.MiNegocio.configuracioncentral.repository.BaseDatosRepository;
import com.MiNegocio.configuracioncentral.repository.ObjetoBDRepository;
import com.MiNegocio.configuracioncentral.repository.UsuarioRepository;
import com.MiNegocio.configuracioncentral.service.IAService;
import com.MiNegocio.configuracioncentral.service.ObjetoBDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ObjetoBDServiceImpl implements ObjetoBDService {

    private final ObjetoBDRepository objetoBDRepository;
    private final BaseDatosRepository baseDatosRepository;
    private final UsuarioRepository usuarioRepository;
    private final GestorObjetosFactory gestorFactory;

    public ObjetoBDServiceImpl(
            ObjetoBDRepository repo,
            BaseDatosRepository bdRepo,
            UsuarioRepository usuarioRepo,
            ConexionMultifactory factory,
            GestorObjetosFactory gestorFactory) {
        this.objetoBDRepository = repo;
        this.baseDatosRepository = bdRepo;
        this.usuarioRepository = usuarioRepo;
        this.gestorFactory = gestorFactory;
    }

    @Override
    public void crearObjeto(int idBD, ObjetoBDFranquicia objeto) throws Exception {

        BaseDatosFranquicia bd = baseDatosRepository.buscarPorId(idBD);

        objeto.setFechaCreacion(new Date());
        objeto.setIdBD(idBD);
        // Crear el objeto solicitado
        GestorObjetosBD gestor = gestorFactory.obtenerGestor(bd.getTipo());

        gestor.crearObjeto(bd, objeto);
        objetoBDRepository.guardarObjetoBD(objeto);
        objetoBDRepository.guardarObjetoEnMongo(objeto);
    }

    public void crearObjetoConIA(int idBD, String consulta) throws Exception {

        ObjetoBDFranquicia objeto = new ObjetoBDFranquicia();
        BaseDatosFranquicia bd = baseDatosRepository.buscarPorId(idBD);

        objeto.setFechaCreacion(new Date());
        objeto.setIdBD(idBD);
        String json = IAService.generarSQLDesdePregunta(consulta
                + "usa la confiiguracion de lengujae SQL para el tipo de bd: " + bd.getTipo());
        objeto.setColumnas(json);

        // Crear el objeto solicitado
        GestorObjetosBD gestor = gestorFactory.obtenerGestor(bd.getTipo());
        gestor.crearObjeto(bd, objeto);
        objetoBDRepository.guardarObjetoBD(objeto);
        objetoBDRepository.guardarObjetoEnMongo(objeto);
    }


}
