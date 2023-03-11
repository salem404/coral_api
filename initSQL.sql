SET
    NAMES utf8;

SET
    time_zone = '+02:00';

SET
    sql_mode = '';

-- Database
DROP DATABASE IF EXISTS coralData;

CREATE DATABASE coralData CHARACTER
SET
    utf8mb4 COLLATE utf8mb4_unicode_ci;

USE coralData;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;

/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;

/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;

/*!40101 SET NAMES utf8 */;

/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;

/*!40103 SET TIME_ZONE='+00:00' */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Estructura de tabla usuario
DROP TABLE IF EXISTS usuario;

create table
    if not exists coralData.usuario (
        id BIGINT auto_increment primary key,
        username varchar(45) not null unique,
        email varchar(255) null,
        nombre varchar(45) null,
        apellidos varchar(255) null
    ) engine = InnoDB;

-- Estructura de tabla perfil
DROP TABLE IF EXISTS perfil;

create table
    coralData.perfil (
        id BIGINT auto_increment primary key,
        id_usuario bigint not null,
        nombre varchar(455) not null,
        nombre_granja int not null,
        constraint perfil_usuario_id_fk foreign key (id_usuario) references coralData.usuario (id) on update cascade on delete cascade
    ) engine = InnoDB;

-- Estructura de tabla perfil
DROP TABLE IF EXISTS tarea;

create table
    coralData.tarea (
        id BIGINT auto_increment primary key,
        id_perfil bigint not null,
        descripcion varchar(255) null,
        constraint tarea_perfil_id_fk foreign key (id_perfil) references coralData.perfil (id) on update cascade on delete cascade
    ) engine = InnoDB;

-- Estructura de tabla cultivo
DROP TABLE IF EXISTS cultivo;

create table
    coralData.cultivo (
        id BIGINT auto_increment primary key,
        nombre VARCHAR(25) ,
        dias_crecimiento int not null,
        estacion enum ('primavera', 'verano', 'otoño', 'invierno') not null
    ) engine = InnoDB;

-- Estructura de tabla fruta
DROP TABLE IF EXISTS fruta;

create table
    coralData.fruta (
        id BIGINT auto_increment primary key,
        nombre VARCHAR(25),
        dias_crecimiento int not null,
        estacion enum ('primavera', 'verano', 'otoño', 'invierno') not null,
        tipo varchar(255) not null
    ) engine = InnoDB;

-- Estructura de tabla personaje
DROP TABLE IF EXISTS personaje;

create table
    coralData.personaje (
        id BIGINT auto_increment primary key,
        nombre VARCHAR(25),
        dia_cumpleaños int not null,
        estacion_cumpleaños enum ('primavera', 'verano', 'otoño', 'invierno') not null
    ) engine = InnoDB;