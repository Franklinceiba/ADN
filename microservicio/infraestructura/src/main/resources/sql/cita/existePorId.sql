SELECT count(1)
FROM cita
WHERE cita_fecha = :fecha AND cita_hora = :hora AND cita_id <> :id;