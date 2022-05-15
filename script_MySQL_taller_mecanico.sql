
SET GLOBAL log_bin_trust_function_creators = 1;


DROP DATABASE IF EXISTS `taller_mecanico`;

CREATE DATABASE IF NOT EXISTS `taller_mecanico` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `taller_mecanico`;

DROP TABLE IF EXISTS vehiculos;
DROP TABLE IF EXISTS categorias;
DROP TABLE IF EXISTS actividad_usuarios;
DROP TABLE IF EXISTS codificacion_actividades;
DROP TABLE IF EXISTS usuarios_taller_mecanico;

CREATE TABLE usuarios_taller_mecanico
( 
  id_usuario               CHAR(15),
  password                 VARCHAR(12), 
  CONSTRAINT pk_usuariostallermecanico PRIMARY KEY (id_usuario)
);

CREATE TABLE codificacion_actividades
(  
  codigo                   INT,
  descripcion              VARCHAR(50),
  CONSTRAINT pk_codificacionactividades PRIMARY KEY (codigo)
);

CREATE TABLE actividad_usuarios
( 
  fecha_hora               DATETIME,
  id_usuario               CHAR(15),
  ip_cliente               VARCHAR(18), 
  codigo                   INT,
  CONSTRAINT fk_actividad_codificacion FOREIGN KEY (codigo) REFERENCES codificacion_actividades (codigo)  
);

CREATE TABLE categorias
(  
  codigo                   CHAR(1),
  descripcion              VARCHAR(40),
  CONSTRAINT pk_categorias PRIMARY KEY (codigo)
);

CREATE TABLE vehiculos
(  
  id_vehiculo              CHAR(7),
  modelo                   VARCHAR(60),
  categoria                CHAR(1),
  fecha_alta               DATETIME,
  kilometraje_alta         INT,
  itv                      INT,
  CONSTRAINT pk_vehiculos PRIMARY KEY (id_vehiculo),
  CONSTRAINT fk_vehiculos_categorias FOREIGN KEY (categoria) REFERENCES categorias (codigo)  
);


-- -----------------------------------------------------------------------------------------------------------------------------
--     MEDIANTE LA SIGUIENTE TABLA, PROCEDIMIENTOS Y FUNCION SIMULAMOS EN MySQL LAS SECUENCIAS DE ORACLE
-- -----------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS secuencias;
DROP PROCEDURE IF EXISTS crea_secuencia;
DROP PROCEDURE IF EXISTS elimina_secuencia;
DROP PROCEDURE IF EXISTS secuencia_set_valor;
DROP PROCEDURE IF EXISTS secuencia_set_incremento;
DROP FUNCTION IF EXISTS secuencia_next_valor;


CREATE TABLE secuencias
(
  secuencia_nombre VARCHAR(35) NOT NULL PRIMARY KEY,
  secuencia_valor INT UNSIGNED NOT NULL,
  secuencia_incremento INT UNSIGNED NOT NULL
);


DELIMITER //

CREATE PROCEDURE crea_secuencia(secuenciaNombre VARCHAR(35), valorIncialSecuencia INT UNSIGNED, incremento INT UNSIGNED)
BEGIN
    IF (SELECT COUNT(*) FROM secuencias WHERE secuencia_nombre = secuenciaNombre) = 0 THEN
        INSERT INTO secuencias (secuencia_nombre, secuencia_valor, secuencia_incremento) VALUES (secuenciaNombre, valorIncialSecuencia, incremento);
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "No puede crear la secuencia especificada porque ya existe";
    END IF;
END//


CREATE PROCEDURE elimina_secuencia(secuenciaNombre VARCHAR(35))
BEGIN
    IF (SELECT COUNT(*) FROM secuencias WHERE secuencia_nombre = secuenciaNombre) > 0 THEN
        DELETE FROM secuencias WHERE secuencia_nombre = secuenciaNombre;     
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "La secuencia especificada no existe";
    END IF;
END//


CREATE PROCEDURE secuencia_set_valor(secuenciaNombre VARCHAR(35), secuenciaValor INT UNSIGNED)
BEGIN
    IF (SELECT COUNT(*) FROM secuencias WHERE secuencia_nombre = secuenciaNombre) > 0 THEN
        UPDATE secuencias SET secuencia_valor = secuenciaValor WHERE secuencia_nombre = secuenciaNombre;      
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "No existe la secuencia";
    END IF;
END//


CREATE PROCEDURE secuencia_set_incremento(secuenciaNombre VARCHAR(35), incremento INT UNSIGNED)
BEGIN
    IF (SELECT COUNT(*) FROM secuencias WHERE secuencia_nombre = secuenciaNombre) > 0 THEN
        UPDATE secuencias SET secuencia_incremento = incremento WHERE secuencia_nombre = secuenciaNombre;      
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "No existe la secuencia";
    END IF;
END//


CREATE FUNCTION secuencia_next_valor(secuenciaNombre VARCHAR(35)) RETURNS INT UNSIGNED
BEGIN
    DECLARE valorActual INT;

    SET valorActual = (SELECT secuencia_valor FROM secuencias WHERE secuencia_nombre = secuenciaNombre);
    IF valorActual IS NOT NULL THEN
        UPDATE secuencias SET secuencia_valor = valorActual + secuencia_incremento  WHERE secuencia_nombre = secuenciaNombre;        
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "No existe la secuencia";
    END IF;

    RETURN valorActual;
END//

DELIMITER ;

-- -----------------------------------------------------------------------------------------------------------------------------

-- CALL elimina_secuencia("secuencia_libros");

CALL crea_secuencia("secuencia_reparaciones", 1, 1);

-- CALL secuencia_set_valor("secuencia_libros", 100);

-- CALL secuencia_set_incremento("secuencia_libros", 5);

-- SELECT secuencia_next_valor("secuencia_libros");

-- -----------------------------------------------------------------------------------------------------------------------------

INSERT INTO usuarios_taller_mecanico VALUES ('usuario1','password1');
INSERT INTO usuarios_taller_mecanico VALUES ('usuario2','password2');
INSERT INTO usuarios_taller_mecanico VALUES ('usuario3','password3');

INSERT INTO codificacion_actividades VALUES (1,'op_menu - Conexion');
INSERT INTO codificacion_actividades VALUES (2,'op_menu - Desconexion');
INSERT INTO codificacion_actividades VALUES (3,'autenticacion');
INSERT INTO codificacion_actividades VALUES (4,'desconectar');
INSERT INTO codificacion_actividades VALUES (5,'op_menu - ConexionEfectuada');
INSERT INTO codificacion_actividades VALUES (6,'op_menu - Volcado a BD');
INSERT INTO codificacion_actividades VALUES (7,'volcarIncidencias');
INSERT INTO codificacion_actividades VALUES (8,'op_menu - Vista Formulario');
INSERT INTO codificacion_actividades VALUES (9,'comboVehiculos');
INSERT INTO codificacion_actividades VALUES (10,'nuevoVehiculo');
INSERT INTO codificacion_actividades VALUES (11,'aplicarCambios');
INSERT INTO codificacion_actividades VALUES (12,'eliminarVehiculo');
INSERT INTO codificacion_actividades VALUES (13,'actualizadoModelo');
INSERT INTO codificacion_actividades VALUES (14,'actualizadoCategoria');
INSERT INTO codificacion_actividades VALUES (15,'actualizadoFechaAlta');
INSERT INTO codificacion_actividades VALUES (16,'actualizadoKilometrajeAlta');
INSERT INTO codificacion_actividades VALUES (17,'actualizadoItv');
INSERT INTO codificacion_actividades VALUES (18,'op_menu - Estadisticas Actividad');
INSERT INTO codificacion_actividades VALUES (19,'consultarEstadistica');
INSERT INTO codificacion_actividades VALUES (20,'op_menu - Vista Unica Tabla');
INSERT INTO codificacion_actividades VALUES (21,'reordenar');
INSERT INTO codificacion_actividades VALUES (22,'insertarFila');
INSERT INTO codificacion_actividades VALUES (23,'cancelarInsercionFila');
INSERT INTO codificacion_actividades VALUES (24,'guardarFilaInsertada');
INSERT INTO codificacion_actividades VALUES (25,'eliminarFilaSeleccionada');
INSERT INTO codificacion_actividades VALUES (26,'actualizadaColumnaJTable');
INSERT INTO codificacion_actividades VALUES (27,'op_menu - Vista Paginada Tabla');
INSERT INTO codificacion_actividades VALUES (28,'botonPaginacionAnterior');
INSERT INTO codificacion_actividades VALUES (29,'botonPaginacionSiguiente');
INSERT INTO codificacion_actividades VALUES (30,'botonPaginacionNumerica');
INSERT INTO codificacion_actividades VALUES (31,'op_menu - CierreVentana');
INSERT INTO codificacion_actividades VALUES (32,'op_menu - Vista Arbol');
INSERT INTO codificacion_actividades VALUES (33,'Nuevo nodo');
INSERT INTO codificacion_actividades VALUES (34,'Editar nodo');
INSERT INTO codificacion_actividades VALUES (35,'Eliminar nodo');
INSERT INTO codificacion_actividades VALUES (36,'ratonClicked');
INSERT INTO codificacion_actividades VALUES (37,'cancelarEdicionNodo');
INSERT INTO codificacion_actividades VALUES (38,'op_menu - Configurar Documento');
INSERT INTO codificacion_actividades VALUES (39,'generarPDF');
INSERT INTO codificacion_actividades VALUES (40,'imprimir');
INSERT INTO codificacion_actividades VALUES (41,'copiarAlPortapapeles');

INSERT INTO categorias VALUES ('0','No clasificado');
INSERT INTO categorias VALUES ('1','Micro (minicoche)');
INSERT INTO categorias VALUES ('2','Standard - up to 90 CV (gasolina)');
INSERT INTO categorias VALUES ('3','Standard - more than 90 CV (gasolina)');
INSERT INTO categorias VALUES ('4','Hatchback (familiar)');
INSERT INTO categorias VALUES ('5','Minivan (monovolumen)');
INSERT INTO categorias VALUES ('6','Sedan (alta gama)');
INSERT INTO categorias VALUES ('7','Sport car (deportivo o coupé)');
INSERT INTO categorias VALUES ('8','Eléctrico o híbrido');
INSERT INTO categorias VALUES ('9','4 X 4 y/o diesel');

INSERT INTO vehiculos VALUES ('0122AHJ','Suzuki Celerio 1.0 68CV GL','2','1987-04-12 00:00:00',18900,1);
INSERT INTO vehiculos VALUES ('1892ATM','LIGIER JS50 SPORT ULTIMATE PROGRESS','1','2019-12-11 00:00:00',5880,0);
INSERT INTO vehiculos VALUES ('3021VGT','BMW Serie 1 Sporthatch 116i 109 CV','3','2006-06-18 00:00:00',5400,0);
INSERT INTO vehiculos VALUES ('8004AJA','Ford Ka + 2016 1.2 Ti-VCT Essential','2','2006-06-18 00:00:00',12890,1);
INSERT INTO vehiculos VALUES ('0310YRA','Dacia Logan MCV 1.0 SCe 75CV','4','1987-09-01 00:00:00',25452,1);
INSERT INTO vehiculos VALUES ('0430UGV','BMW Serie 2 ActiveTourer 225xe iPerformance 136 CV','6','1973-11-12 00:00:00',6250,0);
INSERT INTO vehiculos VALUES ('8888ASX','McLaren P1 3.8 V8 737CV P1','8','1990-04-22 00:00:00',3057,0);
INSERT INTO vehiculos VALUES ('3459WNA','KIA Niro 1.6 HEV Concept 141cv hibrido','8','1984-08-05 00:00:00',259,0);
INSERT INTO vehiculos VALUES ('0220AHP','Fiat Panda 1.2 69CV Pop','2','1968-09-12 00:00:00',5036,0);
INSERT INTO vehiculos VALUES ('0800AKL','Opel Corsa 5P 1.4 75CV Expression','2','2001-11-22 00:00:00',380,0);
INSERT INTO vehiculos VALUES ('0050NSX','AIXAM COUPE GTI','1','2018-04-22 00:00:00',5040,0);
INSERT INTO vehiculos VALUES ('1300AHH','Lexus RC 300h Executive 223 CV','7','2005-09-30 00:00:00',13009,1);
INSERT INTO vehiculos VALUES ('7870RTA','VOLVO S90 Recharge','8','2007-12-02 00:00:00',5179,0);
INSERT INTO vehiculos VALUES ('0320GIA','Dacia Duster SCe 115CV Acces 115CV','9','2001-11-12 00:00:00',6448,0);
INSERT INTO vehiculos VALUES ('3320PUA','Opel Karl 1.0 XE 75CV Selective','2','2005-04-15 00:00:00',7619,0);
INSERT INTO vehiculos VALUES ('0180JAL','BMW Z4 sDrive20i Steptronic 197 CV','7','1998-08-12 00:00:00',18517,1);
INSERT INTO vehiculos VALUES ('9780AAA','Suzuki Celerio 1.0 68CV GLX','2','2014-08-29 00:00:00',7950,0);
INSERT INTO vehiculos VALUES ('6200RRK','Citroën C-Elysée PureTech 82CV Shine','6','2010-12-19 00:00:00',5831,0);
INSERT INTO vehiculos VALUES ('0188XZV','Porsche 718 Cayman H4 2.0 300CV T','7','2009-10-23 00:00:00',2401,0);
INSERT INTO vehiculos VALUES ('1243LKA','Toyota Yaris Hybrid 100H e-CVT Advance Hybrid','8','1989-12-09 00:00:00',5902,0);
INSERT INTO vehiculos VALUES ('1110XAA','Lada 4X4 1.7i Serie M 81CV','9','1985-04-10 00:00:00',4576,0);
INSERT INTO vehiculos VALUES ('0033AVA','Smart ForTwo 52 Passion 71 CV','2','1981-04-03 00:00:00',6158,0);
INSERT INTO vehiculos VALUES ('2220AAA','Renault Twizy Life 45 5CV','8','2000-04-30 00:00:00',41037,1);
INSERT INTO vehiculos VALUES ('0938ZXS','BMW Serie 2 Cabrio 218i','7','1980-11-12 00:00:00',3690,0);
INSERT INTO vehiculos VALUES ('0323ABN','SsangYong Actyon Sports 200Xdi AWD Line 155 CV','9','1987-04-12 00:00:00',245,'0');
INSERT INTO vehiculos VALUES ('0660GHU','Opel Corsa 5P 1.4 Turbo 100CV Selective','3','2010-04-25 00:00:00',4801,0);
INSERT INTO vehiculos VALUES ('0010AUM','Peugeot 308 SW PureTech 110 Access','4','1987-08-10 00:00:00',3470,0);
INSERT INTO vehiculos VALUES ('9981ZAY','Lexus LC 500h Luxury 359 CV Hibrido','8','1986-08-03 00:00:00',2940,0);
INSERT INTO vehiculos VALUES ('6610XAL','Dacia Logan MCV 0.9 TCe 90CV Laureate 90 CV','4','1987-11-12 00:00:00',8120,0);
INSERT INTO vehiculos VALUES ('0710ANU','Suzuki Celerio 1.0 68CV GLX AGS','2','1983-04-22 00:00:00',3598,0);


COMMIT;
