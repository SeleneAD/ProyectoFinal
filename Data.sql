CREATE TABLE Usuarios (
	id int not null auto_increment,
    Nombre varchar(20),
    Direccion varchar(100) null,
    Correo varchar (100) null,
    roles_id int not null,
    primary key (id),
    foreign key (roles_id) references roles(id)
);

CREATE TABLE Productos (
	id int not null auto_increment,
    nombre varchar(20) null,
    precio decimal null,
    descripcion varchar(250),
    stock int,
	primary key (id)
);

CREATE TABLE pedidos (
	id int not null auto_increment,
    usuario_id int not null,
    producto_id int not null,
	primary key (id),
	foreign key (usuario_id) references usuarios(id),
    foreign key (producto_id) references productos(id)
);

CREATE TABLE pagos (
	id int not null auto_increment,
    precio_total decimal null,
    codigo_compra varchar(4),
    usuario_id int not null,
    primary key (id),
    foreign key (usuario_id) references usuarios(id)
);

CREATE TABLE tarjeta(
	id int not null auto_increment,
    numero_tarjeta varchar(10) null,
    usuario_id int not null,
    primary key (id),
    foreign key (usuario_id) references usuarios(id)
);

CREATE TABLE roles (
	id int not null auto_increment,
    nombre varchar(50) null,
    primary key (id)
);

CREATE TABLE tarjetas(
	id int not null auto_increment,
    numero_tarjeta int null,
    dinero_disponible int null,
    usuario_id int not null,
    primary key (id),
    foreign key (usuario_id) references usuarios(id)
);