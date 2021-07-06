SELECT `cita`.`cita_id`,
    `cita`.`cita_descripcion`,
    `cita`.`cita_fecha`,
    `cita`.`cita_hora`,
    `cita`.`cita_valor`,
    `cita`.`pers_id`
FROM `consultoriofv`.`cita`
WHERE `cita_fecha` = :fecha AND `cita_hora` = :hora;