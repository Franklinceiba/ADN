create table persona(
   pers_id INT NOT NULL AUTO_INCREMENT,
   pers_tipo_documento VARCHAR(40) NOT NULL,
   pers_documento VARCHAR(40) NOT NULL,
   pers_nombre VARCHAR(100) NOT NULL,
   pers_apellido VARCHAR(100) NOT NULL,
   pers_fecha_nacimiento DATE NOT NULL,
   pers_celular VARCHAR(20) NOT NULL,
   pers_email VARCHAR(200) NOT NULL,
   PRIMARY KEY ( pers_id)
);

create table cita (
   cita_id INT NOT NULL AUTO_INCREMENT,
   cita_descripcion VARCHAR(500) NOT NULL,
   cita_fecha DATE NOT NULL,
   cita_hora TIME NOT NULL,
   cita_valor INT NOT NULL,
   pers_id INT NOT NULL,
   PRIMARY KEY ( cita_id),
   FOREIGN KEY (pers_id) REFERENCES persona(pers_id)
);
insert into persona(pers_tipo_documento,
	pers_documento,
    pers_nombre,
    pers_apellido,
    pers_fecha_nacimiento,
    pers_celular,
    pers_email) values( 'cc', '1090482947', 'franklin', 'vasquez', now(), '3177093283', 'franklin@gmail.com');
