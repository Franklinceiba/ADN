UPDATE persona
SET
pers_tipo_documento = :tipoDocumento,
pers_documento = :documento,
pers_nombre = :nombre,
pers_apellido = :apellido,
pers_fecha_nacimiento = :fechaNacimiento,
pers_celular = :celular,
pers_email = :email
WHERE pers_id = :id;