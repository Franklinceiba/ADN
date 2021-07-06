INSERT INTO `consultoriofv`.`cita`
(`cita_id`,
`cita_descripcion`,
`cita_fecha`,
`cita_hora`,
`cita_valor`,
`pers_id`)
VALUES
(:id,
:descripcion,
:fecha,
:hora,
:valor,
:idPersona);