-----------------------------
--PROCEDURE REPORTE CONSULTAS X PERIODO
--------------------------------
CREATE OR ALTER PROCEDURE ReporteConsultasPeriodo
    @periodo VARCHAR(10)
AS
BEGIN
    DECLARE @fechaInicio DATE

    IF @periodo='MES'
        SET @fechaInicio = DATEADD(MONTH,-1,GETDATE())

    IF @periodo='SEMESTRE'
        SET @fechaInicio = DATEADD(MONTH,-6,GETDATE())

    IF @periodo='ANIO'
        SET @fechaInicio = DATEADD(YEAR,-1,GETDATE())

    SELECT 
        ci.idCita,
        p.nombre + ' ' + p.aPaterno + ' ' + p.aMaterno AS paciente,
        ci.usuarioMedico AS medico,
        ci.motivo,
        co.medicamento,
        co.fechaConsulta
    FROM Consultas co
    INNER JOIN Citas ci ON co.idCita = ci.idCita
    INNER JOIN Pacientes p ON ci.idPaciente = p.idPaciente
    WHERE co.fechaConsulta >= @fechaInicio
END
GO

-----------------------------
--PROCEDURE REPORTE MEDICOS MAS SOLICITADOS
--------------------------------
CREATE OR ALTER PROCEDURE MedicosSolicitadosPeriodo
    @periodo VARCHAR(10)
AS
BEGIN
    DECLARE @fechaInicio DATE

    IF @periodo='MES'
        SET @fechaInicio = DATEADD(MONTH,-1,GETDATE())

    IF @periodo='SEMESTRE'
        SET @fechaInicio = DATEADD(MONTH,-6,GETDATE())

    IF @periodo='ANIO'
        SET @fechaInicio = DATEADD(YEAR,-1,GETDATE())

    SELECT 
        ci.usuarioMedico AS medico,
        COUNT(*) AS total
    FROM Consultas co
    INNER JOIN Citas ci ON co.idCita = ci.idCita
    WHERE co.fechaConsulta >= @fechaInicio

    GROUP BY ci.usuarioMedico
END
GO