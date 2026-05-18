CREATE DATABASE Dentista
GO
USE Dentista

----------------------------------------
--CREACION DE TABLA USUARIO PARA SISTEMA
----------------------------------------
CREATE TABLE Usuarios(
	usuario char(15) primary key,									--usuario para ingreso al sistema
	pass char(50),													--contraseña para ingreso al sistema
	nombre varchar(20),												--nombre real del usuario
	aPaterno varchar(20),											--apellido paterno
	aMaterno varchar(20),											--apellido paterno
	email varchar(30),												--correo (información adicional)
	tipo char(1) check(tipo in ('A', 'M', 'E', 'I')) default 'I',   --tipo de usuario que ingrese al sistema (Administrador / Medico / Empleado / Invitado)
	status char(1) check(status in('A', 'I')) default 'A'			--status del usuario (ACTIVO ó INACTIVO)
)
GO
SELECT * FROM Usuarios

INSERT INTO Usuarios(usuario, pass, nombre, aPaterno, aMaterno, email, tipo) VALUES('RicMedSor001', 'adminsistema001', 'Ricardo', 'Medina', 'Soriano', 'ricardomedsor@gmail.com', 'A')

----------------------------------------
--PROCEDIMIENTO ALMACENADO ALTA USUARIO
----------------------------------------
CREATE PROCEDURE AltaUsuario
	@nombre varchar(20), 
	@aPaterno varchar(20),
	@aMaterno varchar(20),
	@email varchar(20),
	@usuario char(12),
	@pass char(32),
	@respuesta varchar(50) output
	AS
	DECLARE @con int
	SELECT @con=COUNT(*) FROM Usuarios WHERE nombre=@nombre and aPaterno=@aPaterno and aMaterno=@aMaterno
	IF (@con<1)
		BEGIN
		  INSERT INTO usuarios(nombre,aPaterno,aMaterno,email,usuario,pass) values(@nombre,@aPaterno,@aMaterno,@email,@usuario,@pass)
		  PRINT 'Usuario registrado'
		  SET @respuesta='Usuario registrado'
		END
	ELSE
		BEGIN
		PRINT 'Usuario ya existe, no se registro'
		SET @respuesta='Usuario ya existe, no se registro'  
		END

-------------------------------------------------------
--PROCEDIMIENTO ALMACENADO VERIFICAR USUARIO PARA LOGIN
-------------------------------------------------------
CREATE PROCEDURE VerificaUsuario
    @usuario char(12),
    @pass char(32)
AS
BEGIN
    SELECT tipo,status
    FROM Usuarios
    WHERE usuario=@usuario
    AND pass=@pass
END

-------------------------------------------------------
--PROCEDIMIENTO ALMACENADO MODIFICAR TIPO USUARIO
-------------------------------------------------------
CREATE PROCEDURE ModificarTipoUsuario
    @usuario char(12),
    @tipo char(1),
    @respuesta varchar(50) OUTPUT
AS
BEGIN

    UPDATE Usuarios
    SET tipo=@tipo
    WHERE usuario=@usuario

    SET @respuesta='Tipo modificado'

END

-------------------------------------------------------
--PROCEDIMIENTO ALMACENADO ACTIVAR USUARIO
-------------------------------------------------------
CREATE PROCEDURE ActivarUsuario
    @usuario char(12),
    @respuesta varchar(50) OUTPUT
AS
BEGIN

    UPDATE Usuarios
    SET status='A'
    WHERE usuario=@usuario

    SET @respuesta='Usuario activado'

END

-------------------------------------------------------
--PROCEDIMIENTO ALMACENADO DESACTIVAR USUARIO
-------------------------------------------------------
CREATE PROCEDURE DesactivarUsuario
    @usuario char(12),
    @respuesta varchar(50) OUTPUT
AS
BEGIN

    UPDATE Usuarios
    SET status='I'
    WHERE usuario=@usuario

    SET @respuesta='Usuario desactivado'

END


----------------------------------------
--PROCEDIMIENTO ALMACENADO CONSULTA DE USUARIOS
----------------------------------------
CREATE PROCEDURE ConsultarUsuarios
AS
BEGIN

    SELECT usuario,
           nombre,
           aPaterno,
           aMaterno,
           email,
           tipo,
           status
    FROM Usuarios

END

--------------------------------
--PROCEDIMIENTO ALMACENADO LLENAR SELECTOR
---------------------------------
CREATE OR ALTER PROCEDURE SelectorUsuarios
AS
BEGIN
    SELECT usuario
    FROM Usuarios
    WHERE tipo <> 'A'
END
GO
----------------------------------------
--VISTA DE USUARIOS ACTIVOS
----------------------------------------
CREATE VIEW UsuariosActivos
	AS
	SELECT usuario
	FROM Usuarios
	WHERE status='A'

-----------------------------------
--VISTA SELECTOR DE MEDICOS
----------------------------------
CREATE PROCEDURE SelectorMedicos
AS
BEGIN
    SELECT usuario
    FROM Usuarios
    WHERE tipo='M'
    AND status='A'
END