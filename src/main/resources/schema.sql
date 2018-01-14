CREATE TABLE IF NOT EXISTS pharmacist (
  id_pharmacist integer not null auto_increment,
  name VARCHAR(45)  NOT NULL UNIQUE,
  password VARCHAR(45)  NOT NULL,
  user_group VARCHAR(45) NOT NULL,
  CHECK (user_group IN ('employee','admin'))
);

CREATE TABLE IF NOT EXISTS drug (
  id_drug integer not null auto_increment,
  drug_name VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS drug_store (
  id_drug_store integer not null auto_increment,
  id_drug INT NOT NULL, FOREIGN KEY (id_drug) REFERENCES drug(id_drug),
  price numeric (18,2) NOT NULL,
  amount int  NOT NULL,
  CHECK (price >= 0),
  CHECK (amount >= 0)
);

CREATE TABLE IF NOT EXISTS client (
  id_client integer not null auto_increment,
  name VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(45) NOT NULL,
  user_group VARCHAR(45) NOT NULL,
  CHECK (user_group IN ('user'))
);

CREATE TABLE IF NOT EXISTS order_t (
  id_order integer not null auto_increment,
  id_client INT NOT NULL, FOREIGN KEY (id_client) REFERENCES client(id_client),
  id_pharmacist INT NOT NULL, FOREIGN KEY (id_pharmacist) REFERENCES pharmacist(id_pharmacist),
  date TIMESTAMP NOT NULL DEFAULT (NOW())
);

CREATE TABLE  IF NOT EXISTS order_items (
  id_items integer not null auto_increment,
  id_order INT NOT NULL, FOREIGN KEY (id_order) REFERENCES order_t (id_order),
  id_drug INT NOT NULL, FOREIGN KEY (id_drug) REFERENCES drug (id_drug),
  price numeric (18,2) NOT NULL,
  amount int NOT NULL
);