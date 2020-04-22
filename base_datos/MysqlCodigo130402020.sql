SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


CREATE SCHEMA IF NOT EXISTS bd_tpc;
USE bd_tpc;

CREATE TABLE persona(
	dni int(11) primary key,
	nombre varchar(45) NOT NULL,
	apellido varchar(45) NOT NULL,
	fechaNacimiento date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE cliente(
	idCliente int(11) primary key AUTO_INCREMENT,
	dni int(11) not null,
	email varchar(45) not null,
  CONSTRAINT persona_fk FOREIGN KEY (dni) REFERENCES persona (dni) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE empleado(
	idEmpleado int(11) PRIMARY KEY AUTO_INCREMENT,
	dni int(11) NOT NULL,
	franjaHoraria varchar(45),
	tipoEmpleado bit(1) NOT NULL,
	idLocal int(11) NOT NULL,
	CONSTRAINT fk_comercio
    FOREIGN KEY (idLocal) 
        REFERENCES comercio(idLocal),
    CONSTRAINT fk_persona
    FOREIGN KEY (dni) 
        REFERENCES persona(dni)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
	estado bit(1) not null,
	CONSTRAINT fk_producto
    FOREIGN KEY (idProducto) 
        REFERENCES producto(idProducto)
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
	CONSTRAINT fk_stock
    FOREIGN KEY (idStock) 
        REFERENCES stock(idStock)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


create table pedido(
	idPedido int(11) primary key AUTO_INCREMENT,
	idProducto int(11) not null,
	cantidad int(11) not null,
	idLocal int(11) not null,
	idCliente int(11) not null,
	idEmpleado_original int(11) not null,
	idEmpleado_auxiliar int(11),
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
        REFERENCES cliente(idCliente),
	CONSTRAINT fk_empleado_original
    FOREIGN KEY (idEmpleado_original) 
        REFERENCES empleado(idEmpleado)	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


create table solicitudStock(
	idSolicitudStock int(11) primary key AUTO_INCREMENT,
	fecha date not null,
	idProducto int(11) not null,
	cantidad int(11) not null,
	idEmpleado_vendedor int(11) not null,
	idEmpleado_colaborador int(11),
	aceptado bit(1) not null,
	CONSTRAINT fk_producto_solicitud
    FOREIGN KEY (idProducto) 
        REFERENCES producto(idProducto),
	CONSTRAINT fk_empleado_vendedor
    FOREIGN KEY (idEmpleado_vendedor) 
        REFERENCES empleado(idEmpleado)	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


create table carrito (
	idCarrito int(11) primary key AUTO_INCREMENT,
	fecha date not null,
	total float	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;







