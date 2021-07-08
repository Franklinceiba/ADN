SELECT count(1)
FROM cita
WHERE cita_fecha = :fecha AND cita_hora = :hora;