SELECT count(1)
FROM persona
WHERE pers_documento = :documento and pers_tipo_documento = :tipoDocumento;