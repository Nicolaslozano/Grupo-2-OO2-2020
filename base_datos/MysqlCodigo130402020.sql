SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


CREATE SCHEMA IF NOT EXISTS bd_tpc;
USE bd_tpc;

CREATE TABLE persona(
	idPersona int(11) primary key AUTO_INCREMENT,
	dni int(11) not null,
	nombre varchar(45) NOT NULL,
	apellido varchar(45) NOT NULL,
	fechaNacimiento date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE cliente(
	idPersona int(11) primary key,
	email varchar(45) not null,
  CONSTRAINT fk_cliente_id 
  	FOREIGN KEY (idPersona) 
  	REFERENCES persona (idPersona) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE empleado(
	idPersona int(11) primary key,
	franjaHoraria varchar(45),
	tipoEmpleado bit(1) NOT NULL,
	idLocal int(11) NOT NULL,
	CONSTRAINT fk_empleado_comercio
    FOREIGN KEY (idLocal) 
        REFERENCES comercio (idLocal) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_empleado_id
    FOREIGN KEY (idPersona) 
        REFERENCES persona (idPersona) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE producto(
	idProducto int(11) PRIMARY KEY AUTO_INCREMENT,
	nombre varchar(45) not null,
	descripcion varchar(100),
	precio double not null,
	fechaAlta date not null
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE factura(
	idFactura int(11) primary key,
	factura varchar(500) not null
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE lote(
	idLote int(11) primary key AUTO_INCREMENT,
	cantidadInicial int(11) not null,
	cantidadActual int(11),
	fechaIngreso date not null,
	idProducto int(11) not null,
	idStock int(11) not null,
	estado bit(1) not null,
	CONSTRAINT fk_producto
    FOREIGN KEY (idProducto) 
        REFERENCES producto(idProducto),
	CONSTRAINT fk_stock_lote
    FOREIGN KEY (idStock) 
        REFERENCES stock(idStock)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

create table stock(
	idStock int(11) primary key AUTO_INCREMENT,
	cantidad int(11) not null
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE comercio(
	idLocal int(11) primary key AUTO_INCREMENT,
	direccion varchar(45) not null,
	latitud double not null,
	longitud double not null,
	telefono int(11),
	idStock int(11) not null,
	CONSTRAINT fk_comercio_stock
    FOREIGN KEY (idStock) 
        REFERENCES stock (idStock)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


create table pedido(
	idPedido int(11) primary key AUTO_INCREMENT,
	idProducto int(11) not null,
	cantidad int(11) not null,
	idLocal int(11) not null,
	idCliente int(11) not null,
	subtotal float,
	aceptado bit(1) not null,
	CONSTRAINT fk_producto_pedido
    FOREIGN KEY (idProducto) 
        REFERENCES producto(idProducto),
    CONSTRAINT fk_comercio_pedido
    FOREIGN KEY (idLocal) 
        REFERENCES comercio(idLocal),
	CONSTRAINT fk_cliente
    FOREIGN KEY (idCliente) 
        REFERENCES cliente(idCliente)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


create table solicitudStock(
	idSolicitud int(11) primary key AUTO_INCREMENT,
	fecha date not null,
	idProducto int(11) not null,
	cantidad int(11) not null,
	aceptado bit(1) not null,
	CONSTRAINT fk_producto_solicitud
    FOREIGN KEY (idProducto) 
        REFERENCES producto(idProducto)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


create table carrito (
	idCarrito int(11) primary key AUTO_INCREMENT,
	fecha date not null,
	total float	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;







