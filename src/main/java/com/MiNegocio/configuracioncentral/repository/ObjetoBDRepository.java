package com.MiNegocio.configuracioncentral.repository;

import java.util.List;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;

public interface ObjetoBDRepository {

    void guardarObjetoBD(ObjetoBDFranquicia objeto);
    
    void guardarObjetoEnMongo(ObjetoBDFranquicia objeto);

    List<ObjetoBDFranquicia> listarObjetosPorBD(int idBD);

    ObjetoBDFranquicia buscarObjetoPorNombre(String nombreTabla, int idBD);
    
    ObjetoBDFranquicia buscarPorId(int idObjeto);

    public boolean existeTablaUsuarios(int idBD);

    public boolean existeTablaUsuariosParaFranquicia(int idFranquicia);
    
    
}
