UPDATE `consultoriofv`.`cita`
SET
`cita_descripcion` = :descripcion,
`cita_fecha` = :fecha,
`cita_hora` = :hora,
`cita_valor` = :valor,
`pers_id` = :idPersona>
WHERE `cita_id` = :id;