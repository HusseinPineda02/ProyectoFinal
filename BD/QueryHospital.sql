-- hospital_schema.sql
CREATE DATABASE HOSPITAL;
USE HOSPITAL

-- Tabla paciente
CREATE TABLE paciente (
  dni CHAR(8) PRIMARY KEY,
  nombre VARCHAR(30) NOT NULL,
  apellidoP VARCHAR(30),
  apellidoM VARCHAR(30),
  fechaNacimiento DATE
);

-- Tabla horario
CREATE TABLE horario (
  horarioId VARCHAR(10) PRIMARY KEY,
  dia VARCHAR(20),
  horaInicio TIME,
  horaFin TIME
);

-- Tabla trabajador (base para doctor/enfermero/administrativo)
CREATE TABLE trabajador (
  idTrabajador VARCHAR(10) PRIMARY KEY,
  nombre VARCHAR(20) NOT NULL,
  apellidoP VARCHAR(20),
  apellidoM VARCHAR(20),
  dni CHAR(8),
  salario DECIMAL(10,2),
  horarioId VARCHAR(10),
  area VARCHAR(20),     -- para administrativos
  turno VARCHAR(20),     -- para enfermeros
  constraint fk_horarioId FOREIGN KEY (horarioId) REFERENCES horario(horarioId) ON DELETE SET NULL
);

-- Tabla doctor vinculada a trabajador (opcional / para campos doctor espec�ficos)
CREATE TABLE doctor (
  idTrabajador VARCHAR(10) PRIMARY KEY,
  especialidad VARCHAR(20),
  FOREIGN KEY (idTrabajador) REFERENCES trabajador(idTrabajador) ON DELETE CASCADE
);


-- Tabla ambiente (consultorios, salas gen�ricas)
CREATE TABLE ambiente (
  idAmbiente VARCHAR(50) PRIMARY KEY,
  piso VARCHAR(50),
  numero VARCHAR(50),
);

-- Tabla sala (si quieres campos extra)
CREATE TABLE sala (
  idAmbiente VARCHAR(50) PRIMARY KEY,
  aforo INT,
  camillas INT,
  FOREIGN KEY (idAmbiente) REFERENCES ambiente(idAmbiente) ON DELETE CASCADE
);

-- Tabla consultorio (si aplicable)
CREATE TABLE consultorio (
  idAmbiente VARCHAR(50) PRIMARY KEY,
  especialidad VARCHAR(100),
  FOREIGN KEY (idAmbiente) REFERENCES ambiente(idAmbiente) ON DELETE CASCADE
);

-- Tabla cita
CREATE TABLE cita (
  idCita VARCHAR(50) PRIMARY KEY,
  pacienteDni CHAR(8),
  doctorId VARCHAR(10),
  fecha DATE,
  hora TIME,
  ambienteId VARCHAR(50),
  FOREIGN KEY (pacienteDni) REFERENCES paciente(dni) ON DELETE SET NULL,
  FOREIGN KEY (doctorId) REFERENCES doctor(idTrabajador) ON DELETE SET NULL,
  FOREIGN KEY (ambienteId) REFERENCES ambiente(idAmbiente) ON DELETE SET NULL
);

-- Tabla historia
CREATE TABLE historia (
  idHistoria VARCHAR(10) PRIMARY KEY,
  pacienteDni CHAR(8),
  CONSTRAINT FK_Historia_Paciente FOREIGN KEY (pacienteDni) REFERENCES paciente(dni) ON DELETE CASCADE
);

-- Tabla registro
CREATE TABLE registro (
  idRegistro VARCHAR(10) PRIMARY KEY,
  idHistoria VARCHAR(10),
  fecha DATE,
  descripcion TEXT,
  doctorId VARCHAR(10),
  CONSTRAINT FK_Registro_Historia FOREIGN KEY (idHistoria) REFERENCES historia(idHistoria) ON DELETE CASCADE,
  CONSTRAINT FK_Registro_Doctor FOREIGN KEY (doctorId) REFERENCES doctor(idTrabajador) ON DELETE SET NULL
);

-- Tabla medicamento
CREATE TABLE medicamento (
  idMed VARCHAR(10) PRIMARY KEY,
  nombre VARCHAR(150),
  stock INT DEFAULT 0,
  descripcion TEXT,
  tipo VARCHAR(100)
);

-- Tabla receta
CREATE TABLE receta (
  idReceta VARCHAR(10) PRIMARY KEY,
  pacienteDni CHAR(8),
  doctorId VARCHAR(10),
  fecha DATE,
  indicaciones TEXT,
  FOREIGN KEY (pacienteDni) REFERENCES paciente(dni) ON DELETE SET NULL,
  FOREIGN KEY (doctorId) REFERENCES doctor(idTrabajador) ON DELETE SET NULL
);

-- Tabla med_prescrito (relaci�n receta <-> medicamento)
CREATE TABLE med_prescrito (
  id VARCHAR(10) PRIMARY KEY,
  idReceta VARCHAR(10),
  idMed VARCHAR(10),
  dosis VARCHAR(100),
  duracion VARCHAR(100),
  FOREIGN KEY (idReceta) REFERENCES receta(idReceta) ON DELETE CASCADE,
  FOREIGN KEY (idMed) REFERENCES medicamento(idMed) ON DELETE SET NULL
);

-- Tabla inventario
CREATE TABLE inventario (
  idItem VARCHAR(50) PRIMARY KEY,
  nombre VARCHAR(150),
  tipo VARCHAR(100),
  cantidad INT DEFAULT 0,
  descripcion TEXT
);

-- Tabla factura
CREATE TABLE factura (
  idFactura VARCHAR(50) PRIMARY KEY,
  pacienteDni CHAR(8),
  tipoPago VARCHAR(50),
  monto DECIMAL(10,2),
  fecha DATE,
  FOREIGN KEY (pacienteDni) REFERENCES paciente(dni) ON DELETE SET NULL
);

-- Tabla hospital (registro b�sico)
CREATE TABLE hospital (
  idHospital VARCHAR(10) PRIMARY KEY,
  nombre VARCHAR(30),
  direccion VARCHAR(50)
);

-- Tabla relacion hospital <-> trabajador (opcional: asignar personal a un hospital)
CREATE TABLE hospital_trabajador (
  idHospital VARCHAR(10),
  idTrabajador VARCHAR(10),
  PRIMARY KEY (idHospital, idTrabajador),
  FOREIGN KEY (idHospital) REFERENCES hospital(idHospital) ON DELETE CASCADE,
  FOREIGN KEY (idTrabajador) REFERENCES trabajador(idTrabajador) ON DELETE CASCADE
);