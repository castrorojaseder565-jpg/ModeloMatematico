-- Datos de ejemplo (esquema creado por Hibernate con ddl-auto=update)
USE eduoptima_db;
INSERT INTO tutor (name, specialty, max_capacity) VALUES
 ('Carlos Ruiz','Matemáticas',2),
 ('Sandra Mora','Física',1),
 ('Julián Torres','Programación',2);
INSERT INTO student (name, topic, academic_risk, priority) VALUES
 ('Ana García','Matemáticas',4,9),
 ('Luis Pérez','Física',3,7),
 ('María López','Matemáticas',5,10),
 ('Pedro Gómez','Matemáticas',2,5),
 ('Laura Díaz','Programación',4,8),
 ('Andrés Cano','Programación',5,6),
 ('Sofía Rojas','Programación',3,4),
 ('Camila Vega','Química',5,9);
