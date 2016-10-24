CREATE TABLE pizza (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    foto VARCHAR(100) NOT NULL,
    sabor VARCHAR(50) NOT NULL,
    segundoSabor VARCHAR(50) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    promocao BOOLEAN DEFAULT true,
    status BOOLEAN DEFAULT true,
	tipo VARCHAR(50) NOT NULL,
    ingredientes TEXT NOT NULL,    
    content_type VARCHAR(100),    
    codigo_tamanho BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_tamanho) REFERENCES tamanho(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
