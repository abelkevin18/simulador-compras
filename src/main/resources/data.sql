INSERT INTO cliente(dni,nombre,apellido_paterno,apellido_materno,fecha_nacimiento) VALUES ('74567656','Abel','N','Ch','1992-11-22 00:00:00.000');

INSERT INTO cuenta_bancaria(numero_cuenta,fecha_apertura) VALUES (7844535990444,'2021-01-14 15:31:59.370');

INSERT INTO tarjeta(tipo_tarjeta,numero_tarjeta,fecha_expiracion) VALUES ('ORO',23456567867,'2019-05-14 15:31:59.370');

INSERT INTO producto(cliente_id,cuenta_bancaria_id,tarjeta_id,codigo_producto,tea,moneda) VALUES(1,1,1,2345,99.9,'soles');