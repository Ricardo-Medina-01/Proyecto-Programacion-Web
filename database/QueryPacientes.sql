Use dentista
Go


------------------
--TABLA PACIENTES
-----------------
CREATE TABLE Pacientes(
    idPaciente INT IDENTITY PRIMARY KEY,
    nombre VARCHAR(30),
    aPaterno VARCHAR(30),
    aMaterno VARCHAR(30),
    telefono VARCHAR(15),
    email VARCHAR(50),
    fechaNacimiento DATE,
    direccion VARCHAR(100),
    status CHAR(1) CHECK(status IN('A','I')) DEFAULT 'A'
);

---------------------------
--PROCEDURE MODIFICAR PACIENTE
-----------------------------
CREATE OR ALTER PROCEDURE ModificarPaciente
    @idPaciente INT,
    @nombre VARCHAR(30),
    @aPaterno VARCHAR(30),
    @aMaterno VARCHAR(30),
    @telefono VARCHAR(15),
    @respuesta VARCHAR(80) OUTPUT
AS
BEGIN

    UPDATE Pacientes
    SET nombre=@nombre,
        aPaterno=@aPaterno,
        aMaterno=@aMaterno,
        telefono=@telefono
    WHERE idPaciente=@idPaciente

    SET @respuesta='Paciente modificado'

END
GO

----------------------------
--PROCEDURE DESACTIVAR PACIENTE
--------------------------------
CREATE OR ALTER PROCEDURE DesactivarPaciente
    @idPaciente INT,
    @respuesta VARCHAR(80) OUTPUT
AS
BEGIN

    UPDATE Pacientes
    SET status='I'
    WHERE idPaciente=@idPaciente

    SET @respuesta='Paciente dado de baja'

END
GO

---------------------------
--PROCEDURE ACTIVAR PACIENTE
----------------------------
CREATE OR ALTER PROCEDURE ActivarPaciente
    @idPaciente INT,
    @respuesta VARCHAR(80) OUTPUT
AS
BEGIN
    UPDATE Pacientes
    SET status='A'
    WHERE idPaciente=@idPaciente

    SET @respuesta='Paciente activado'
END
GO

----------------------------
--PROCEDURE SELECTOR PACIENTES
----------------------------
CREATE OR ALTER PROCEDURE SelectorPacientes
AS
BEGIN

    SELECT idPaciente,
           nombre,
           aPaterno,
           aMaterno
    FROM Pacientes

END
GO
--MODIFICABLE
CREATE OR ALTER PROCEDURE SelectorPacientes
AS
BEGIN
    SELECT idPaciente, nombre, aPaterno, aMaterno
    FROM Pacientes
    WHERE status='A'
END
GO

CREATE OR ALTER PROCEDURE SelectorPacientesTodos
AS
BEGIN
    SELECT idPaciente, nombre, aPaterno, aMaterno, status
    FROM Pacientes
END
GO

----------------------
--PROCEDIMIENTO ALMACENADO ALTA PACIENTE
----------------------
CREATE PROCEDURE AltaPaciente
    @nombre VARCHAR(30),
    @aPaterno VARCHAR(30),
    @aMaterno VARCHAR(30),
    @telefono VARCHAR(15),
    @email VARCHAR(50),
    @fechaNacimiento DATE,
    @direccion VARCHAR(100),
    @respuesta VARCHAR(80) OUTPUT
AS
BEGIN
    IF EXISTS (
        SELECT 1 FROM Pacientes
        WHERE nombre=@nombre
        AND aPaterno=@aPaterno
        AND aMaterno=@aMaterno
    )
    BEGIN
        SET @respuesta = 'Paciente ya existe'
    END
    ELSE
    BEGIN
        INSERT INTO Pacientes(
            nombre, aPaterno, aMaterno, telefono, email, fechaNacimiento, direccion
        )
        VALUES(
            @nombre, @aPaterno, @aMaterno, @telefono, @email, @fechaNacimiento, @direccion
        )

        SET @respuesta = 'Paciente registrado'
    END
END

--------------------
--PROCEDIMIENTO ALMACENADO CONSULTAR PACIENTES
---------------------------------
CREATE PROCEDURE ConsultarPacientes
AS
BEGIN
    SELECT idPaciente, nombre, aPaterno, aMaterno, telefono, email, fechaNacimiento, direccion, status
    FROM Pacientes
END


select * from Pacientes