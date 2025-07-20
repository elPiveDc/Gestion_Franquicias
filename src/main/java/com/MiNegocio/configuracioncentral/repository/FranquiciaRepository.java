package com.MiNegocio.configuracioncentral.repository;

import com.MiNegocio.configuracioncentral.domain.Franquicia;
import java.util.List;

public interface FranquiciaRepository {
    void guardar(Franquicia franquicia);
    List<Franquicia> obtenerPorUsuario(int idUsuario);
    Franquicia buscarPorId(int id);
    boolean existeNombreFranquicia(String nombre);
}

