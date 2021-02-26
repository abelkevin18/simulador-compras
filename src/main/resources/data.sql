INSERT INTO cliente(dni,nombre,apellido_paterno,apellido_materno,fecha_nacimiento) VALUES ('74567656','Abel','Nuñez','Chavez','1992-11-22 00:00:00.000');
INSERT INTO cliente(dni,nombre,apellido_paterno,apellido_materno,fecha_nacimiento) VALUES ('86675367','Gabriel','Torres','Ramirez','1998-10-28 00:00:00.000');
INSERT INTO cliente(dni,nombre,apellido_paterno,apellido_materno,fecha_nacimiento) VALUES ('76872346','Sara','Llanos','Melgarejo','2001-01-30 00:00:00.000');

INSERT INTO cliente(dni,nombre,apellido_paterno,apellido_materno,fecha_nacimiento) VALUES ('45637823','Nelly','Casas','Rivera','2003-04-11 00:00:00.000');

INSERT INTO cuenta_bancaria(numero_cuenta,fecha_apertura) VALUES (20209181014041,'2021-01-14 15:31:59.000');
INSERT INTO cuenta_bancaria(numero_cuenta,fecha_apertura) VALUES (23020956101404,'2021-01-28 11:41:00.000');
INSERT INTO cuenta_bancaria(numero_cuenta,fecha_apertura) VALUES (24582091810256,'2021-01-28 04:10:00.000');

INSERT INTO tarjeta(tipo_tarjeta,numero_tarjeta,fecha_expiracion) VALUES ('CLASICA',23456567867,'2026-08-18 18:11:59.000');
INSERT INTO tarjeta(tipo_tarjeta,numero_tarjeta,fecha_expiracion) VALUES ('ORO',23456567867,'2025-02-11 15:31:59.000');
INSERT INTO tarjeta(tipo_tarjeta,numero_tarjeta,fecha_expiracion) VALUES ('BLACK',23456567867,'2025-10-30 09:22:59.000');

INSERT INTO producto(cliente_id,cuenta_bancaria_id,tarjeta_id,codigo_producto,tea,moneda) VALUES(1,1,1,0014,99.90,'soles');
INSERT INTO producto(cliente_id,cuenta_bancaria_id,tarjeta_id,codigo_producto,tea,moneda) VALUES(2,2,2,0015,95.90,'soles');
INSERT INTO producto(cliente_id,cuenta_bancaria_id,tarjeta_id,codigo_producto,tea,moneda) VALUES(3,3,3,0016,90.90,'soles');