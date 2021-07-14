insert into persona(pers_tipo_documento,
	pers_documento,
    pers_nombre,
    pers_apellido,
    pers_fecha_nacimiento,
    pers_celular,
    pers_email) values( 'cc', '10904829478', 'franklin', 'vasquez', now(), '3177093283', 'franklin@gmail.com');
INSERT INTO cita
(cita_descripcion,
cita_fecha,
cita_hora,
cita_valor,
pers_id)
VALUES
('revicion de examenes',
'2021-07-05',
'10:30:00',
30000,
1);