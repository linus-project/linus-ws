-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`nivel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`nivel` (
  `idNivel` INT NOT NULL,
  `tipo` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idNivel`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idUsuario` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `datanasc` DATE NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(10) NOT NULL,
  `admin` TINYINT NULL,
  `fk_nivel` INT NOT NULL,
  PRIMARY KEY (`idUsuario`, `fk_nivel`),
  INDEX `fk_usuario_nivel1_idx` (`fk_nivel` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_nivel1`
    FOREIGN KEY (`fk_nivel`)
    REFERENCES `mydb`.`nivel` (`idNivel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`distribuicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`distribuicao` (
  `idDistribuicao` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `base` VARCHAR(45) NULL,
  `versao` VARCHAR(15) NOT NULL,
  `fk_nivel` INT NOT NULL,
  PRIMARY KEY (`idDistribuicao`, `fk_nivel`),
  INDEX `fk_distribuicao_nivel1_idx` (`fk_nivel` ASC) VISIBLE,
  CONSTRAINT `fk_distribuicao_nivel1`
    FOREIGN KEY (`fk_nivel`)
    REFERENCES `mydb`.`nivel` (`idNivel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`conteudo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`conteudo` (
  `idConteudo` INT NOT NULL,
  `titulo` VARCHAR(45) NULL,
  `favoritado` TINYINT NULL,
  `conteudo` VARCHAR(45) NULL,
  `fk_distribuicao` INT NOT NULL,
  `fk_nivel` INT NOT NULL,
  PRIMARY KEY (`idConteudo`, `fk_distribuicao`, `fk_nivel`),
  INDEX `fk_conteudo_distribuicao1_idx` (`fk_distribuicao` ASC) VISIBLE,
  INDEX `fk_conteudo_nivel1_idx` (`fk_nivel` ASC) VISIBLE,
  CONSTRAINT `fk_conteudo_distribuicao1`
    FOREIGN KEY (`fk_distribuicao`)
    REFERENCES `mydb`.`distribuicao` (`idDistribuicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_conteudo_nivel1`
    FOREIGN KEY (`fk_nivel`)
    REFERENCES `mydb`.`nivel` (`idNivel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`interacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`interacao` (
  `idInterecao` INT NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `curtida` TINYINT NULL,
  `fk_conteudo` INT NOT NULL,
  `fk_usuario` INT NOT NULL,
  PRIMARY KEY (`idInterecao`, `fk_conteudo`, `fk_usuario`),
  INDEX `fk_interacao_conteudo1_idx` (`fk_conteudo` ASC) VISIBLE,
  INDEX `fk_interacao_usuario1_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_interacao_conteudo1`
    FOREIGN KEY (`fk_conteudo`)
    REFERENCES `mydb`.`conteudo` (`idConteudo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_interacao_usuario1`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `mydb`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
