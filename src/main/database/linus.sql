-- -----------------------------------------------------
-- Table tb_level
-- -----------------------------------------------------

CREATE TABLE tb_level (
  id_level INT IDENTITY(1,1) NOT NULL,
  level_type VARCHAR(15) NOT NULL,
  insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_level)
 );

-- -----------------------------------------------------
-- Table tb_user
-- -----------------------------------------------------

CREATE TABLE tb_user (
  id_user INT IDENTITY(1,1) NOT NULL,
  name VARCHAR(45) NOT NULL,
  username VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(255) NOT NULL,
  born_date DATE DEFAULT NULL,
  phone_number VARCHAR(45) DEFAULT NULL,
  admin_key VARCHAR(45) DEFAULT NULL,
  fk_level INT NOT NULL,
  insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_user),
  FOREIGN KEY (fk_level)
  REFERENCES tb_level (id_level)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table tb_distro
-- -----------------------------------------------------

CREATE TABLE tb_distro (
  id_distro INT IDENTITY(1,1) NOT NULL,
  distro_name VARCHAR(45) NOT NULL,
  distro_base VARCHAR(45) NULL,
  distro_version VARCHAR(15) NOT NULL,
  fk_level INT NOT NULL,
  insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_distro),
  FOREIGN KEY (fk_level)
  REFERENCES tb_level (id_level)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table tb_content
-- -----------------------------------------------------

CREATE TABLE tb_content (
  id_content INT IDENTITY(1,1) NOT NULL,
  content_title VARCHAR(45) NULL,
  content VARCHAR(4500) NULL,
  fk_distro INT NOT NULL,
  fk_level INT NOT NULL,
  insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_content),
  FOREIGN KEY (fk_distro)
  REFERENCES tb_distro (id_distro)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (fk_level)
  REFERENCES tb_level (id_level)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table tb_user_favorite_content
-- -----------------------------------------------------

CREATE TABLE tb_user_favorite_content (
  id_favorite_content INT IDENTITY(1,1) NOT NULL,
  fk_user INT NOT NULL,
  fk_content INT NOT NULL,
  insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_favorite_content),
  FOREIGN KEY (fk_user)
  REFERENCES tb_user (id_user)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (fk_content)
  REFERENCES tb_content (id_content)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table tb_user_favorite_content
-- -----------------------------------------------------

CREATE TABLE tb_user_content_interact (
  id_content_interact INT IDENTITY(1,1) NOT NULL,
  interact_type INT NOT NULL,
  fk_user INT NOT NULL,
  fk_content INT NOT NULL,
  insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_content_interact),
  FOREIGN KEY (fk_user)
  REFERENCES tb_user (id_user)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (fk_content)
  REFERENCES tb_content (id_content)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table tb_user_commentary
-- -----------------------------------------------------

CREATE TABLE tb_user_commentary (
  id_commentary INT IDENTITY(1,1) NOT NULL,
  commentary_content VARCHAR(250) NOT NULL,
  fk_content INT NOT NULL,
  fk_user INT NOT NULL,
  insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_commentary),
  FOREIGN KEY (fk_content)
  REFERENCES tb_content (id_content)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (fk_user)
  REFERENCES tb_user (id_user)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

  -- -----------------------------------------------------
-- Table tb_user_commentary_interact
-- -------------------------------------------------------

CREATE TABLE tb_user_commentary_interact (
  id_commentary_interact INT NOT NULL,
  interact_type INT NOT NULL,
  fk_commentary INT NOT NULL,
  fk_user INT NOT NULL,
  insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_commentary_interact),
  FOREIGN KEY (fk_commentary)
  REFERENCES tb_user_commentary (id_commentary)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (fk_user)
  REFERENCES tb_user (id_user)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);
