
package com.MiNegocio.configuracioncentral.integration.cargadatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;

public interface CargadorDatos {
    
    public void cargar(BaseDatosFranquicia bd, String nombreTabla, Object columnasData) throws Exception;
    
    
}
