-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TpOO2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TpOO2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TpOO2` DEFAULT CHARACTER SET utf8 ;
USE `TpOO2` ;

-- -----------------------------------------------------
-- Table `TpOO2`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Persona` (
  `Dni` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Apellido` VARCHAR(45) NULL,
  `FechaNacimiento` DATE NULL,
  PRIMARY KEY (`Dni`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Carrito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Carrito` (
  `Cliente_Dni` INT NOT NULL,
  `Carrito_Has_Producto` INT NULL,
  PRIMARY KEY (`Cliente_Dni`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Cliente` (
  `email` VARCHAR(45) NOT NULL,
  `Persona_Dni` INT NOT NULL,
  `Carrito_Cliente_Dni` INT NOT NULL,
  INDEX `fk_Cliente_Persona1_idx` (`Persona_Dni` ASC) VISIBLE,
  INDEX `fk_Cliente_Carrito1_idx` (`Carrito_Cliente_Dni` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Persona1`
    FOREIGN KEY (`Persona_Dni`)
    REFERENCES `TpOO2`.`Persona` (`Dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_Carrito1`
    FOREIGN KEY (`Carrito_Cliente_Dni`)
    REFERENCES `TpOO2`.`Carrito` (`Cliente_Dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Local`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Local` (
  `idLocal` INT NOT NULL AUTO_INCREMENT,
  `telefono` INT NULL,
  `direccion` VARCHAR(45) NULL,
  `latitud` FLOAT NULL,
  `longitud` FLOAT NULL,
  `Local_idLocal` INT NOT NULL,
  `Persona_Dni` INT NOT NULL,
  PRIMARY KEY (`idLocal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Empleado` (
  `horaEntrada` TIME NOT NULL,
  `horaSalida` TIME NOT NULL,
  `sueldo` FLOAT NOT NULL,
  `esGerente` TINYINT NULL,
  `Persona_Dni` INT NOT NULL,
  `Local_idLocal` INT NOT NULL,
  INDEX `fk_Empleado_Persona_idx` (`Persona_Dni` ASC) VISIBLE,
  INDEX `fk_Empleado_Local1_idx` (`Local_idLocal` ASC) VISIBLE,
  CONSTRAINT `fk_Empleado_Persona`
    FOREIGN KEY (`Persona_Dni`)
    REFERENCES `TpOO2`.`Persona` (`Dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empleado_Local1`
    FOREIGN KEY (`Local_idLocal`)
    REFERENCES `TpOO2`.`Local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Lote` (
  `idLote` INT NOT NULL AUTO_INCREMENT,
  `fechaIngreso` DATE NULL,
  `cantidadInicial` INT NULL,
  `cantidadActual` INT NULL,
  PRIMARY KEY (`idLote`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Producto` (
  `idproducto` INT NOT NULL AUTO_INCREMENT,
  `PrecioUnidad` FLOAT NULL,
  `fechaAlta` DATE NULL,
  `Lote_idLote` INT NOT NULL,
  PRIMARY KEY (`idproducto`),
  INDEX `fk_Producto_Lote1_idx` (`Lote_idLote` ASC) VISIBLE,
  CONSTRAINT `fk_Producto_Lote1`
    FOREIGN KEY (`Lote_idLote`)
    REFERENCES `TpOO2`.`Lote` (`idLote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Comprobante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Comprobante` (
  `idComprobante` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `cantProducto` INT NULL,
  PRIMARY KEY (`idComprobante`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Local_has_Comprobante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Local_has_Comprobante` (
  `Local_idLocal` INT NOT NULL,
  `Comprobante_idComprobante` INT NOT NULL,
  PRIMARY KEY (`Local_idLocal`, `Comprobante_idComprobante`),
  INDEX `fk_Local_has_Comprobante_Comprobante1_idx` (`Comprobante_idComprobante` ASC) VISIBLE,
  INDEX `fk_Local_has_Comprobante_Local1_idx` (`Local_idLocal` ASC) VISIBLE,
  CONSTRAINT `fk_Local_has_Comprobante_Local1`
    FOREIGN KEY (`Local_idLocal`)
    REFERENCES `TpOO2`.`Local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Local_has_Comprobante_Comprobante1`
    FOREIGN KEY (`Comprobante_idComprobante`)
    REFERENCES `TpOO2`.`Comprobante` (`idComprobante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Local_has_Lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Local_has_Lote` (
  `Local_idLocal` INT NOT NULL,
  `Lote_idLote` INT NOT NULL,
  PRIMARY KEY (`Local_idLocal`, `Lote_idLote`),
  INDEX `fk_Local_has_Lote_Lote1_idx` (`Lote_idLote` ASC) VISIBLE,
  INDEX `fk_Local_has_Lote_Local1_idx` (`Local_idLocal` ASC) VISIBLE,
  CONSTRAINT `fk_Local_has_Lote_Local1`
    FOREIGN KEY (`Local_idLocal`)
    REFERENCES `TpOO2`.`Local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Local_has_Lote_Lote1`
    FOREIGN KEY (`Lote_idLote`)
    REFERENCES `TpOO2`.`Lote` (`idLote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TpOO2`.`Carrito_has_Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TpOO2`.`Carrito_has_Producto` (
  `Carrito_Cliente_Dni` INT NOT NULL,
  `Producto_idproducto` INT NOT NULL,
  PRIMARY KEY (`Carrito_Cliente_Dni`, `Producto_idproducto`),
  INDEX `fk_Carrito_has_Producto_Producto1_idx` (`Producto_idproducto` ASC) VISIBLE,
  INDEX `fk_Carrito_has_Producto_Carrito1_idx` (`Carrito_Cliente_Dni` ASC) VISIBLE,
  CONSTRAINT `fk_Carrito_has_Producto_Carrito1`
    FOREIGN KEY (`Carrito_Cliente_Dni`)
    REFERENCES `TpOO2`.`Carrito` (`Cliente_Dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Carrito_has_Producto_Producto1`
    FOREIGN KEY (`Producto_idproducto`)
    REFERENCES `TpOO2`.`Producto` (`idproducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
