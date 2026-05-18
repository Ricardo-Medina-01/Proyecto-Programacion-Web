----------------------------
--TABLE CONSULTAS
-----------------------------
CREATE TABLE Consultas(
    idConsulta INT IDENTITY PRIMARY KEY,
    idCita INT UNIQUE REFERENCES Citas(idCita),
    medicamento VARCHAR(100),
    observaciones VARCHAR(200),
    fechaConsulta DATE DEFAULT GETDATE()
);

----------------------------
--PROCEDURE DATSO CITAS
----------------------------
CREATE OR ALTER PROCEDURE DatosCita
    @idCita INT
AS
BEGIN
    SELECT 
        c.idCita,
        p.nombre + ' ' + p.aPaterno + ' ' + p.aMaterno AS paciente,
        c.usuarioMedico,
        c.fecha,
        c.hora,
        c.motivo
    FROM Citas c
    INNER JOIN Pacientes p ON c.idPaciente = p.idPaciente
    WHERE c.idCita=@idCita
END
GO

-----------------------------
--PROCEDURE REGISTRAR CONSULTA
-----------------------------
CREATE OR ALTER PROCEDURE RegistrarConsulta
    @idCita INT,
    @medicamento VARCHAR(100),
    @observaciones VARCHAR(200),
    @respuesta VARCHAR(80) OUTPUT
AS
BEGIN
    IF EXISTS(SELECT 1 FROM Consultas WHERE idCita=@idCita)
    BEGIN
        SET @respuesta='Esta cita ya tiene consulta registrada'
    END
    ELSE
    BEGIN
        INSERT INTO Consultas(idCita, medicamento, observaciones)
        VALUES(@idCita, @medicamento, @observaciones)

        UPDATE Citas
        SET status='F'
        WHERE idCita=@idCita

        SET @respuesta='Consulta registrada'
    END
END
GO




