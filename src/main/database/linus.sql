-- -----------------------------------------------------
-- Table `tb_nivel`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS tb_nivel (
  `id_nivel` INT IDENTITY(1,1) NOT NULL,
  `tipo` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_nivel`));

-- -----------------------------------------------------
-- Table `tb_user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS tb_user (
  `id_user` INT IDENTITY(1,1) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `datanasc` DATE DEFAULT NULL,
  `phone_number` VARCHAR(45) DEFAULT NULL,
  `admin_key` VARCHAR(45) DEFAULT NULL,
  `fk_nivel` INT NOT NULL,
  PRIMARY KEY (`id_user`),
  FOREIGN KEY (`fk_nivel`)
  REFERENCES `tb_nivel` (`id_nivel`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `distribuicao`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS distribuicao (
  `id_distro` INT IDENTITY(1,1) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `base` VARCHAR(45) NULL,
  `versao` VARCHAR(15) NOT NULL,
  `fk_nivel` INT NOT NULL,
  PRIMARY KEY (`id_distro`),
  FOREIGN KEY (`fk_nivel`)
  REFERENCES `tb_nivel` (`id_nivel`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `tb_content`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS tb_content (
  `id_content` INT IDENTITY(1,1) NOT NULL,
  `titulo` VARCHAR(45) NULL,
  `favoritado` TINYINT NULL,
  `tb_content` VARCHAR(45) NULL,
  `fk_distribuicao` INT NULL,
  `fk_nivel` INT NOT NULL,
  PRIMARY KEY (`id_content`),
  FOREIGN KEY (`fk_distribuicao`)
  REFERENCES `distribuicao` (`id_distro`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (`fk_nivel`)
  REFERENCES `tb_nivel` (`id_nivel`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `tb_interact`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS tb_interact (
  `id_interact` INT IDENTITY(1,1) NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `curtida` TINYINT NULL,
  `fk_conteudo` INT NOT NULL,
  `fk_user` INT NOT NULL,
  PRIMARY KEY (`id_interact`),
  FOREIGN KEY (`fk_conteudo`)
  REFERENCES `tb_content` (`id_content`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (`fk_user`)
  REFERENCES `tb_user` (`id_user`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);
