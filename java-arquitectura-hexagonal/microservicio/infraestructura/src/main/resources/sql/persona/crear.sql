INSERT INTO cita
(pers_id,
pers_tipo_documento,
pers_documento,
pers_nombre,
pers_apellido,
pers_fecha_nacimiento,
pers_celular,
pers_email)
VALUES
(:id,
:tipoDocumento,
:documento,
:nombre,
:apellido,
:fechaNacimiento,
:celular,
:email);