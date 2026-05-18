CREATE TABLE Citas(
    idCita INT IDENTITY PRIMARY KEY,

    idPaciente INT
    REFERENCES Pacientes(idPaciente),

    usuarioMedico CHAR(15)
    REFERENCES Usuarios(usuario),

    fecha DATE,

    hora TIME,

    motivo VARCHAR(100),

    status CHAR(1)
    CHECK(status IN('A','C','F'))
    DEFAULT 'A'

    -- A = Activa
    -- C = Cancelada
    -- F = Finalizada
);


-----------------------------
--PROCEDIMIENTO ALMACENADO ALTA CITA
----------------------------------
CREATE OR ALTER PROCEDURE AltaCita
    @idPaciente INT,
    @usuarioMedico CHAR(15),
    @fecha DATE,
    @hora TIME,
    @motivo VARCHAR(100),
    @respuesta VARCHAR(80) OUTPUT
AS
BEGIN
    DECLARE @statusPaciente CHAR(1)

    SELECT @statusPaciente = status
    FROM Pacientes
    WHERE idPaciente = @idPaciente

    IF @statusPaciente IS NULL
    BEGIN
        SET @respuesta = 'Paciente no existe'
        RETURN
    END

    IF @statusPaciente <> 'A'
    BEGIN
        SET @respuesta = 'No se puede agendar cita: paciente inactivo'
        RETURN
    END

    INSERT INTO Citas(idPaciente, usuarioMedico, fecha, hora, motivo)
    VALUES(@idPaciente, @usuarioMedico, @fecha, @hora, @motivo)

    SET @respuesta = 'Cita registrada'
END
GO

SELECT idPaciente, nombre, status
FROM Pacientes
WHERE idPaciente = 2

DECLARE @r VARCHAR(80)

EXEC AltaCita
    @idPaciente = 2,
    @usuarioMedico = 'TU_MEDICO',
    @fecha = '2026-05-20',
    @hora = '10:00',
    @motivo = 'Prueba',
    @respuesta = @r OUTPUT

SELECT @r AS respuesta


---------------------------------
--PROCEDURE CONSULTAR CITAS
---------------------------------
CREATE OR ALTER PROCEDURE ConsultarCitas
AS
BEGIN
    SELECT 
        c.idCita,
        p.nombre + ' ' + p.aPaterno + ' ' + p.aMaterno AS paciente,
        c.usuarioMedico,
        c.fecha,
        c.hora,
        c.motivo,
        c.status
    FROM Citas c
    INNER JOIN Pacientes p ON c.idPaciente = p.idPaciente
END
GO

------------------------------
--PROCEDURE CANCELAR CITA
-------------------------------
CREATE OR ALTER PROCEDURE CancelarCita
    @idCita INT,
    @respuesta VARCHAR(80) OUTPUT
AS
BEGIN
    UPDATE Citas
    SET status='C'
    WHERE idCita=@idCita

    SET @respuesta='Cita cancelada'
END
GO


----------------------------------
--PROCEDURE MODIFICAR CITA
-----------------------------------
CREATE OR ALTER PROCEDURE ModificarCita
    @idCita INT,
    @fecha DATE,
    @hora TIME,
    @motivo VARCHAR(100),
    @respuesta VARCHAR(80) OUTPUT
AS
BEGIN
    UPDATE Citas
    SET fecha=@fecha,
        hora=@hora,
        motivo=@motivo
    WHERE idCita=@idCita

    SET @respuesta='Cita modificada'
END
GO

----------------------------------------
-- PROCEDURE SELECTOR CITAS ACTIVAS
-----------------------------------------
CREATE OR ALTER PROCEDURE SelectorCitasActivas
AS
BEGIN
    SELECT idCita
    FROM Citas
    WHERE status='A'
END
GO