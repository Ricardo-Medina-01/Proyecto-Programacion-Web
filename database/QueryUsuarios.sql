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