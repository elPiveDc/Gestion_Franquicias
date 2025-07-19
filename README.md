# Gestor de Franquicias

Aplicación de escritorio desarrollada en **Java (JDK 23)** con **Maven** y **Apache NetBeans IDE 22**, que permite gestionar franquicias (negocios) y configurarlas con distintos motores de base de datos.

---

## Formas de ejecutar el sistema

### Opción 1: Ejecución **local**

El sistema puede funcionar enteramente en tu máquina local si tienes instalados los siguientes servicios:

* **MySQL** 8.0.42  (gestión central), aqui se deve ejecutar el sql de la BD Central (https://dev.mysql.com/downloads/installer/)
* **PostgreSQL** 17.5 on x86_64-windows, compiled by msvc-19.43.34808, 64-bit 
* **Oracle** Database 21c Express Edition Release 21.0.0.0.0 - Production
* **MongoDB** 8.0.11 https://www.mongodb.com/try/download/community
* **Couchbase** Community Edition 7.6.2 build 3721 https://www.couchbase.com/downloads/

Se configuran desde el archivo `config.properties`.

### Opción 2: Ejecución con **bases de datos en la nube**

Puedes utilizar servicios en la nube como:

* **PlanetScale** (MySQL en la nube)  https://planetscale.com/
* **Amazon RDS** (MySQL, PostgreSQL, Oracle)  https://aws.amazon.com/rds/mysql/
* **MongoDB Atlas** https://www.mongodb.com/cloud/atlas
* **Couchbase Capella** https://www.couchbase.com/downloads/

Solo debes reemplazar las URLs y credenciales en `config.properties` con los valores que te da la plataforma remota, ademas de crear una cuenta y añadir la IP de su maquina.

### Opción 3: Uso de Doker

Ejecutas la aplicación Java desde tu PC normalmente, se encontraran en la *"Rama-Docker"* loa archivos correspondientes.

Los servicios de bases de datos corren como contenedores Docker en la misma máquina:

* **MySQL** → localhost:3307 (o cualquier puerto que mapees)
* **PostgreSQL** → localhost:5433
* **MongoDB** → localhost:27018
* **Oracle XE** → localhost:1522
* **Couchbase** → localhost:8091

**Debes tener:**

* Docker instalado y corriendo
* Un docker-compose.yml con los servicios definidos. (archivos realcionados en *"Rama-Docker"* )
* Puertos correctamente mapeados a localhost (diferentes a los usados por instalaciones locales)

---

## Descripción general

El sistema permite:

* Crear y registrar **usuarios (dueños de franquicias)**.
* Crear **franquicias** asociadas a cada usuario.
* Configurar motores de bases de datos por franquicia: MySQL, PostgreSQL, Oracle.
* Replicar datos en MongoDB como respaldo documental y de Audioria,a demas de guardar los reportes generados como copias.
* Subir imágenes (logos de franquicia) en Couchbase.
* Creacion de tablas en las dieferentes BD creadas por franquicia, una o mas.
* Se puede subir archivos json, csv e ingreso manuales de datos a las difernetes tablas, seleccionando las BD y sus respectivas tablas.
* Gestion de consultas estructuradas y uso de IA para hacer consultas mas complejas.
* Creacion de reportes en base a las tablas generadas en las BD, este reporte se peude bajar como pdf y una copia se realizara en Mongo.
* Gestionar conexiones mediante un archivo `config.properties`.
* Visualizar e interactuar con interfaces hechas en Swing (`JFrame`, `JPanel`).

---

## Requisitos del sistema

* Java 23 (JDK)
* Apache NetBeans IDE 22
* Maven
* Bases de datos locales o en la nube:

  * MySQL
  * PostgreSQL
  * Oracle
  * MongoDB
  * Couchbase

---

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu_usuario/gestor-franquicias.git
cd gestor-franquicias
```

### 2. Compilar el proyecto

```bash
mvn clean install
```

### 3. Abrir en NetBeans

* Importar como proyecto Maven existente.
* Ejecutar clase principal:
  `com.MiNegocio.interfazgrafica.BusquedaFranquicia`

---

## Configuración (`config.properties`)

Ubicación: `src/main/resources/config.properties`

```properties
# =======================
# Base de datos central (MySQL)
# =======================
db.url=jdbc:mysql://localhost:3306/sistema_franquicias
db.user=root (tu usuario)
db.password=tu_password

# Oracle
oracle.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
oracle.user=sys as sysdba (tu usuario)
oracle.password=tu_password

# PostgreSQL
postgresql.url=jdbc:postgresql://localhost:5432/postgres
postgresql.user=postgres (tu usuario)
postgresql.password=tu_password

# MongoDB
mongodb.uri=mongodb://localhost:27017

# Couchbase
couchbase.host=localhost
couchbase.bucket=miBucket (predeterminado)
couchbase.username=Servico_Franquicias (tu usuario)
couchbase.password=tu_password

# IA (Cohere)
ia.api_key=OBD9F5L8RMdqsxg61W6MDKQs970CPYCH1cU44VeF (defecto, si se desea usar el servicio IA para hacer consultas)
ia.api_url=https://api.cohere.ai/v1/chat
```

Puedes reemplazar las URLs locales por servicios en la nube (como PlanetScale, MongoDB Atlas o Capella) si prefieres evitar instalaciones locales o modificar los puertos. *Recuerda que siempre tendras que añadir tu ip a este tipod e servicios o crearte una cuenta con posibles cobros por el servicio*

---


---

## Script SQL de la base de datos central (MySQL)

```sql
-- Crear base de datos principal del sistema

CREATE DATABASE IF NOT EXISTS sistema_franquicias;
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
```

---

## Seguridad

Este proyecto contiene claves de ejemplo para pruebas.
Para producción, se usara archivos `.env`, variables de entorno, o gestores de secretos.

---

## Estado del Proyecto

Este proyecto se encuantra en una estapa desarrollo. Actualizaciones futuras ligadas con lo aprendido.

* **Proyeccion:** Migracion a aplicacion web e uso de otras tecnologias.

*Se encontrar un documento mas detallado en la carpeta `Docs` con una copia en PDF de la documentación*

---
