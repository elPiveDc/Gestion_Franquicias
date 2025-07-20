USE sistema_franquicias;

-- Tabla de usuarios (dueños de franquicia)
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de franquicias registradas por cada usuario
CREATE TABLE franquicias (
    id_franquicia INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre_franquicia VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('ACTIVA', 'INACTIVA', 'ELIMINADA') DEFAULT 'ACTIVA',
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Tabla de configuraciones de bases de datos por franquicia
CREATE TABLE bases_datos_franquicia (
    id_bd INT AUTO_INCREMENT PRIMARY KEY,
    id_franquicia INT NOT NULL,
    nombre_bd VARCHAR(100) NOT NULL,
    tipo_bd ENUM('MYSQL', 'POSTGRESQL', 'ORACLE', 'MONGODB', 'CASSANDRA') NOT NULL,
    estado ENUM('CONFIGURADA', 'NO_CONFIGURADA', 'ERROR') DEFAULT 'NO_CONFIGURADA',
    url_conexion TEXT,
    usuario_conexion VARCHAR(100),
    pass_conexion_hash VARCHAR(255),
    FOREIGN KEY (id_franquicia) REFERENCES franquicias(id_franquicia)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- NUEVA TABLA: Objetos (tablas, vistas, etc.) creados en cada base de datos de franquicia
CREATE TABLE objetos_bd_franquicia (
    id_objeto INT AUTO_INCREMENT PRIMARY KEY,
    id_bd INT NOT NULL,
    nombre_tabla VARCHAR(100) NOT NULL,
    tipo_objeto ENUM('TABLA', 'VISTA', 'FUNCION') DEFAULT 'TABLA',
    es_tabla_usuarios BOOLEAN DEFAULT FALSE,
    columnas TEXT NOT NULL, -- JSON con nombre y tipo de columnas
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_bd) REFERENCES bases_datos_franquicia(id_bd)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);