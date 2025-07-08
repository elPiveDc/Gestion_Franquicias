package com.MiNegocio.configuracioncentral.domain;

public class BaseDatosFranquicia {
    private int id;
    private String nombreBD;
    private TipoBD tipo;
    private EstadoBD estado;
    private String urlConexion;
    private String usuarioBD;
    private String passwordHash;

    public BaseDatosFranquicia() {}

    public BaseDatosFranquicia(int id, String nombreBD, TipoBD tipo, EstadoBD estado, String urlConexion, String usuarioBD, String passwordHash) {
        this.id = id;
        this.nombreBD = nombreBD;
        this.tipo = tipo;
        this.estado = estado;
        this.urlConexion = urlConexion;
        this.usuarioBD = usuarioBD;
        this.passwordHash = passwordHash;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreBD() { return nombreBD; }
    public void setNombreBD(String nombreBD) { this.nombreBD = nombreBD; }

    public TipoBD getTipo() { return tipo; }
    public void setTipo(TipoBD tipo) { this.tipo = tipo; }

    public EstadoBD getEstado() { return estado; }
    public void setEstado(EstadoBD estado) { this.estado = estado; }

    public String getUrlConexion() { return urlConexion; }
    public void setUrlConexion(String urlConexion) { this.urlConexion = urlConexion; }

    public String getUsuarioBD() { return usuarioBD; }
    public void setUsuarioBD(String usuarioBD) { this.usuarioBD = usuarioBD; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
