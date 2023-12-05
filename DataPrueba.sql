INSERT INTO roles VALUES (1,'vendedor');
INSERT INTO roles VALUES (2, 'comprador');

INSERT INTO usuarios (Nombre,Direccion,Correo,roles_id) VALUES('sele','huerfanos','sele@gmail.com',1);
SELECT * FROM usuarios WHERE correo = 'alfajor@gmail.com';

SELECT pr.precio FROM pedidos pe INNER JOIN productos pr 
ON pe.producto_id = pr.id;

ALTER TABLE pedidos
ADD codigo_compra varchar(20);

SELECT SUM(pr.precio) as suma FROM pedidos pe INNER JOIN productos pr ON pe.producto_id = pr.id WHERE pe.codigo_compra = '1331';

INSERT INTO tarjetas (numero_tarjeta,dinero_disponible,usuario_id) VALUES (123456,200000,5);

SELECT tarjetas.dinero_disponible FROM pagos INNER JOIN tarjetas
ON pagos.usuario_id = tarjetas.usuario_id;

select * from pagos;
 
 SELECT tarjetas.dinero_disponible, pagos.precio_total 
 FROM pagos INNER JOIN tarjetas ON pagos.usuario_id = tarjetas.usuario_id 
 WHERE pagos.codigo_compra = '1352';
 
 ALTER TABLE tarjetas 
 ADD estado varchar(10);
 
  ALTER TABLE pagos 
 ADD estado varchar(10);