-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.20-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para pizzaria
DROP DATABASE IF EXISTS `pizzaria`;
CREATE DATABASE IF NOT EXISTS `pizzaria` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pizzaria`;

-- Copiando estrutura para tabela pizzaria.auditoria
DROP TABLE IF EXISTS `auditoria`;
CREATE TABLE IF NOT EXISTS `auditoria` (
  `codAuditoria` int(11) NOT NULL AUTO_INCREMENT,
  `acao` varchar(500) CHARACTER SET utf8 NOT NULL,
  `tabela` varchar(50) CHARACTER SET utf8 NOT NULL,
  `dataHora` datetime NOT NULL,
  `usuario` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`codAuditoria`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1 COMMENT='Registra todas as informações das principais ações do sistema. Esta técnica também é chamada LOG.';

-- Copiando dados para a tabela pizzaria.auditoria: ~50 rows (aproximadamente)
INSERT INTO `auditoria` (`codAuditoria`, `acao`, `tabela`, `dataHora`, `usuario`) VALUES
	(1, 'Sabor de pizza = calabresa inserido na linha = 1', 'pizza', '2023-11-19 15:14:20', 'erik@localhost'),
	(2, 'Ingrediente = calabresa inserido na linha = 1', 'ingrediente', '2023-11-19 15:14:33', 'erik@localhost'),
	(3, 'Ingrediente =  inserido na linha = 2', 'ingrediente', '2023-11-24 07:24:28', 'root@localhost'),
	(4, 'Sabor de pizza = portuguessa inserido na linha = 2', 'pizza', '2023-11-24 07:25:31', 'root@localhost'),
	(5, 'Sabor de pizza = quatro queijos inserido na linha = 3', 'pizza', '2023-11-24 07:31:46', 'root@localhost'),
	(6, 'Pizza sabor de = quatro queijos teve o seu preço alterado para = 45.00', 'pizza', '2023-11-24 07:31:58', 'root@localhost'),
	(7, 'Sabor de pizza = frango com requeijão inserido na linha = 4', 'pizza', '2023-11-24 07:33:32', 'root@localhost'),
	(8, 'Pizza sabor de = frango com requeijão teve o seu preço alterado para = 28.00', 'pizza', '2023-11-24 07:33:44', 'root@localhost'),
	(9, 'Sabor de pizza = vegetariana inserido na linha = 5', 'pizza', '2023-11-24 07:34:21', 'root@localhost'),
	(10, 'Sabor de pizza = vegana inserido na linha = 6', 'pizza', '2023-11-24 07:34:52', 'root@localhost'),
	(11, 'Sabor de pizza = muçarela inserido na linha = 7', 'pizza', '2023-11-24 07:36:00', 'root@localhost'),
	(12, 'Sabor de pizza = marguerita inserido na linha = 8', 'pizza', '2023-11-24 07:36:32', 'root@localhost'),
	(13, 'Sabor de pizza = napolitana inserido na linha = 9', 'pizza', '2023-11-24 07:37:02', 'root@localhost'),
	(14, 'Sabor de pizza = frango com catupiry inserido na linha = 10', 'pizza', '2023-11-24 07:37:38', 'root@localhost'),
	(15, 'Sabor de pizza = 1 inserido na linha = 11', 'pizza', '2023-11-24 08:09:38', 'root@localhost'),
	(16, 'Pizza sabor de = 111111111111111 teve o seu preço alterado para = 1.00', 'pizza', '2023-11-24 08:09:42', 'root@localhost'),
	(17, 'Pizza sabor de = 111111111111111 teve o seu preço alterado para = 11.00', 'pizza', '2023-11-24 08:09:51', 'root@localhost'),
	(18, 'Bebida = Fanta uva teve o seu preço alterado para = 9.00', 'bebida', '2023-11-24 08:11:42', 'root@localhost'),
	(19, 'Ingrediente = portu inserido na linha = 3', 'ingrediente', '2023-11-24 08:12:10', 'root@localhost'),
	(20, 'Pizza sabor de = calabresa teve o seu preço alterado para = 2.00', 'pizza', '2023-11-28 12:20:27', 'root@localhost'),
	(21, 'Pizza sabor de = portuguessa teve o seu preço alterado para = 30.00', 'pizza', '2024-02-05 15:12:21', 'root@localhost'),
	(22, 'Pizza sabor de = calabresa teve o seu preço alterado para = 2.00', 'pizza', '2024-02-05 15:12:22', 'root@localhost'),
	(23, 'Pizza sabor de = frango com requeijão teve o seu preço alterado para = 28.00', 'pizza', '2024-02-05 15:12:25', 'root@localhost'),
	(24, 'Pizza sabor de = quatro queijos teve o seu preço alterado para = 45.00', 'pizza', '2024-02-05 15:12:26', 'root@localhost'),
	(25, 'Pizza sabor de = vegetariana teve o seu preço alterado para = 30.00', 'pizza', '2024-02-05 15:12:26', 'root@localhost'),
	(26, 'Pizza sabor de = vegana teve o seu preço alterado para = 45.00', 'pizza', '2024-02-05 15:12:27', 'root@localhost'),
	(27, 'Pizza sabor de = muçarela teve o seu preço alterado para = 29.00', 'pizza', '2024-02-05 15:12:28', 'root@localhost'),
	(28, 'Pizza sabor de = marguerita teve o seu preço alterado para = 35.00', 'pizza', '2024-02-05 15:12:29', 'root@localhost'),
	(29, 'Pizza sabor de = napolitana teve o seu preço alterado para = 50.00', 'pizza', '2024-02-05 15:12:30', 'root@localhost'),
	(30, 'Pizza sabor de = frango com catupiry teve o seu preço alterado para = 40.00', 'pizza', '2024-02-05 15:12:31', 'root@localhost'),
	(31, 'Pizza sabor de = calabresa teve o seu preço alterado para = 2.00', 'pizza', '2024-02-05 15:14:10', 'root@localhost'),
	(32, 'Pizza sabor de = portuguessa teve o seu preço alterado para = 30.00', 'pizza', '2024-02-05 15:14:21', 'root@localhost'),
	(33, 'Bebida = q1 teve o seu preço alterado para = 1.00', 'bebida', '2024-04-05 09:50:40', 'root@localhost'),
	(34, 'Bebida = q1 teve o seu preço alterado para = 1.00', 'bebida', '2024-04-05 09:51:38', 'root@localhost'),
	(35, 'Bebida = q1 teve o seu preço alterado para = 1.00', 'bebida', '2024-04-05 09:51:38', 'root@localhost'),
	(36, 'Ingrediente = 1 inserido na linha = 3', 'ingrediente', '2024-04-07 20:56:15', 'root@localhost'),
	(37, 'Ingrediente = 1 inserido na linha = 4', 'ingrediente', '2024-04-07 20:57:24', 'root@localhost'),
	(38, 'Bebida = 11 teve o seu preço alterado para = 1.00', 'bebida', '2024-04-08 13:06:03', 'root@localhost'),
	(39, 'Bebida = 11 teve o seu preço alterado para = 1.00', 'bebida', '2024-04-08 14:16:29', 'root@localhost'),
	(40, 'Ingrediente = 1 inserido na linha = 3', 'ingrediente', '2024-04-08 14:17:48', 'root@localhost'),
	(41, 'Bebida = Coca-cola1 teve o seu preço alterado para = 10.00', 'bebida', '2024-07-01 13:08:53', 'root@localhost'),
	(42, 'Bebida = Coca-cola teve o seu preço alterado para = 10.00', 'bebida', '2024-07-01 13:08:59', 'root@localhost'),
	(43, 'Bebida = Coca-cola teve o seu preço alterado para = 10.00', 'bebida', '2024-08-12 13:16:24', 'root@localhost'),
	(44, 'Bebida = Pepsis teve o seu preço alterado para = 8.00', 'bebida', '2024-08-12 13:18:33', 'root@localhost'),
	(45, 'Ingrediente = Presunto inserido na linha = 2', 'ingrediente', '2024-08-19 10:41:16', 'root@localhost'),
	(46, 'Ingrediente = Presunto inserido na linha = 3', 'ingrediente', '2024-08-19 13:16:04', 'root@localhost'),
	(47, 'Bebida = Coca-cola teve o seu preço alterado para = 10.00', 'bebida', '2024-09-02 13:50:21', 'root@localhost'),
	(48, 'Ingrediente = Presunto inserido na linha = 4', 'ingrediente', '2024-09-02 13:50:55', 'root@localhost'),
	(49, 'Pizza sabor de = frango teve o seu preço alterado para = 50.00', 'pizza', '2024-10-14 11:42:58', 'root@localhost'),
	(50, 'Sabor de pizza = cebola inserido na linha = 10', 'pizza', '2024-10-14 11:43:24', 'root@localhost'),
	(51, 'Ingrediente = Q inserido na linha = 3', 'ingrediente', '2024-10-14 14:10:01', 'root@localhost'),
	(52, 'Bebida = Fanta Uva teve o seu preço alterado para = 8.00', 'bebida', '2024-10-14 14:27:00', 'root@localhost'),
	(53, 'Ingrediente = Frango inserido na linha = 2', 'ingrediente', '2024-10-14 14:31:01', 'root@localhost'),
	(54, 'Ingrediente = Queijo inserido na linha = 3', 'ingrediente', '2024-10-14 14:31:20', 'root@localhost'),
	(55, 'Sabor de pizza = l inserido na linha = 11', 'pizza', '2024-10-14 14:41:53', 'root@localhost'),
	(56, 'Pizza sabor de = l teve o seu preço alterado para = 10.00', 'pizza', '2024-10-14 14:41:58', 'root@localhost');

-- Copiando estrutura para tabela pizzaria.bebida
DROP TABLE IF EXISTS `bebida`;
CREATE TABLE IF NOT EXISTS `bebida` (
  `codBebida` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET utf8 NOT NULL,
  `preco` double(10,2) NOT NULL DEFAULT 0.00,
  `quantidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`codBebida`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.bebida: ~3 rows (aproximadamente)
INSERT INTO `bebida` (`codBebida`, `nome`, `preco`, `quantidade`) VALUES
	(1, 'Coca-cola', 10.00, 100),
	(2, 'Fanta Laranja', 8.00, 1),
	(3, 'Fanta Uva', 8.00, 1);

-- Copiando estrutura para tabela pizzaria.bebida_por_venda
DROP TABLE IF EXISTS `bebida_por_venda`;
CREATE TABLE IF NOT EXISTS `bebida_por_venda` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Venda_codPedido` int(11) NOT NULL,
  `Bebida_codBebida` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `precoVenda` double(10,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_Venda_has_Bebida_Bebida1_idx` (`Bebida_codBebida`),
  KEY `fk_Venda_has_Bebida_Venda1_idx` (`Venda_codPedido`),
  CONSTRAINT `fk_Venda_has_Bebida_Bebida1` FOREIGN KEY (`Bebida_codBebida`) REFERENCES `bebida` (`codBebida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_has_Bebida_Venda1` FOREIGN KEY (`Venda_codPedido`) REFERENCES `venda` (`codPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.bebida_por_venda: ~3 rows (aproximadamente)
INSERT INTO `bebida_por_venda` (`codigo`, `Venda_codPedido`, `Bebida_codBebida`, `quantidade`, `precoVenda`) VALUES
	(1, 4, 2, 2, 1.50),
	(2, 3, 3, 2, 2.00),
	(3, 5, 1, 3, 5.00);

-- Copiando estrutura para tabela pizzaria.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `codCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8 NOT NULL,
  `telefone` varchar(100) CHARACTER SET utf8 NOT NULL,
  `cpf` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `bairro` varchar(50) CHARACTER SET utf8 NOT NULL,
  `rua` varchar(50) CHARACTER SET utf8 NOT NULL,
  `numero` varchar(50) CHARACTER SET utf8 NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.cliente: ~3 rows (aproximadamente)
INSERT INTO `cliente` (`codCliente`, `nome`, `telefone`, `cpf`, `bairro`, `rua`, `numero`, `email`) VALUES
	(1, 'Eduardo', '(35) 9999-9999 ', '111.111.111-11', 'Das oliveiras', '5 de maio', '105', 'eduardo@gmail.com'),
	(2, 'Sabrina', '(35) 2222-2222', '222.222.222-22', 'Do limoeiro', '2 de abril', '108', 'sabrina@gmail.com'),
	(3, 'Julia', '(35) 1111-1111', '666.666.666-62', 'Da jaca', '35 de dezembro', '0', 'julia@gmail.com');

-- Copiando estrutura para tabela pizzaria.fornecedor
DROP TABLE IF EXISTS `fornecedor`;
CREATE TABLE IF NOT EXISTS `fornecedor` (
  `codFornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `telefone` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cpf` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codFornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela pizzaria.fornecedor: ~0 rows (aproximadamente)
INSERT INTO `fornecedor` (`codFornecedor`, `nome`, `telefone`, `email`, `cpf`) VALUES
	(1, 'Rogerio', '(35) 222-2222', 'rogerio@gmail.com', '555.555.555-22');

-- Copiando estrutura para tabela pizzaria.funcionario
DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `codFuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8 NOT NULL,
  `cpf` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `salario` double(10,2) NOT NULL DEFAULT 0.00,
  `cargo` varchar(100) CHARACTER SET utf8 NOT NULL,
  `bairro` varchar(100) CHARACTER SET utf8 NOT NULL,
  `rua` varchar(100) CHARACTER SET utf8 NOT NULL,
  `numero` varchar(100) CHARACTER SET utf8 NOT NULL,
  `telefone` varchar(100) CHARACTER SET utf8 NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 NOT NULL,
  `nascimento` date DEFAULT NULL,
  PRIMARY KEY (`codFuncionario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.funcionario: ~3 rows (aproximadamente)
INSERT INTO `funcionario` (`codFuncionario`, `nome`, `cpf`, `salario`, `cargo`, `bairro`, `rua`, `numero`, `telefone`, `email`, `nascimento`) VALUES
	(1, 'Thiago', '333.333.333-33', 1200.00, 'Balconista', 'do amendoim', '3 de julho', '204', '(35) 2222-2222', 'thiago@gamil.com', '2000-11-11'),
	(2, 'Maria', '888.888.888-88', 2600.00, 'Cozinheira', 'da manga', '9 de setembro', '67', '(35) 1212-1212', 'maria@outlook.com', '1980-02-03'),
	(3, 'Eduarda', '777.777.777-77', 1400.00, 'Garçonete', 'dos nobres', 'espanha', '138', '(35)3451-6389', 'sabrina@hotmail.com', '2003-07-12');

-- Copiando estrutura para tabela pizzaria.ingrediente
DROP TABLE IF EXISTS `ingrediente`;
CREATE TABLE IF NOT EXISTS `ingrediente` (
  `codIngrediente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8 NOT NULL,
  `preco` double(10,2) NOT NULL DEFAULT 0.00,
  `quantEstoque` float(10,3) NOT NULL DEFAULT 0.000,
  PRIMARY KEY (`codIngrediente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.ingrediente: ~3 rows (aproximadamente)
INSERT INTO `ingrediente` (`codIngrediente`, `nome`, `preco`, `quantEstoque`) VALUES
	(1, 'calabresa', 1.00, 200.000),
	(2, 'Frango', 2.00, 100.000),
	(3, 'Queijo', 3.00, 60.000);

-- Copiando estrutura para tabela pizzaria.ingrediente_por_pizza
DROP TABLE IF EXISTS `ingrediente_por_pizza`;
CREATE TABLE IF NOT EXISTS `ingrediente_por_pizza` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Pizza_codPizza` int(11) NOT NULL,
  `Ingrediente_codIngrediente` int(11) NOT NULL,
  `quantIngrediente` float(10,3) DEFAULT 0.000,
  PRIMARY KEY (`codigo`),
  KEY `fk_Pizza_has_Ingrediente_Ingrediente1_idx` (`Ingrediente_codIngrediente`),
  KEY `fk_Pizza_has_Ingrediente_Pizza1_idx` (`Pizza_codPizza`),
  CONSTRAINT `fk_Pizza_has_Ingrediente_Ingrediente1` FOREIGN KEY (`Ingrediente_codIngrediente`) REFERENCES `ingrediente` (`codIngrediente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pizza_has_Ingrediente_Pizza1` FOREIGN KEY (`Pizza_codPizza`) REFERENCES `pizza` (`codPizza`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.ingrediente_por_pizza: ~4 rows (aproximadamente)
INSERT INTO `ingrediente_por_pizza` (`codigo`, `Pizza_codPizza`, `Ingrediente_codIngrediente`, `quantIngrediente`) VALUES
	(1, 1, 1, 200.000),
	(2, 4, 3, 500.000),
	(3, 2, 2, 300.000),
	(4, 1, 2, 400.000);

-- Copiando estrutura para tabela pizzaria.itensvenda
DROP TABLE IF EXISTS `itensvenda`;
CREATE TABLE IF NOT EXISTS `itensvenda` (
  `codItensVenda` int(11) NOT NULL AUTO_INCREMENT,
  `Itens_codVenda` int(11) DEFAULT NULL,
  `Itens_codPizza` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `valor` double DEFAULT 0,
  PRIMARY KEY (`codItensVenda`),
  KEY `Itens_codVenda` (`Itens_codVenda`,`Itens_codPizza`) USING BTREE,
  KEY `fk_Itens_codPizza` (`Itens_codPizza`),
  CONSTRAINT `fk_Itens_codPizza` FOREIGN KEY (`Itens_codPizza`) REFERENCES `pizza` (`codPizza`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Itens_codVenda` FOREIGN KEY (`Itens_codVenda`) REFERENCES `venda` (`codPedido`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.itensvenda: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela pizzaria.pizza
DROP TABLE IF EXISTS `pizza`;
CREATE TABLE IF NOT EXISTS `pizza` (
  `codPizza` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8 NOT NULL,
  `precoBase` double(10,2) NOT NULL DEFAULT 0.00,
  `Pizza_borda` int(11) NOT NULL,
  PRIMARY KEY (`codPizza`),
  KEY `Coluna 4` (`Pizza_borda`),
  CONSTRAINT `FK_pizza_recheio_borda` FOREIGN KEY (`Pizza_borda`) REFERENCES `recheio_borda` (`codBorda`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.pizza: ~10 rows (aproximadamente)
INSERT INTO `pizza` (`codPizza`, `nome`, `precoBase`, `Pizza_borda`) VALUES
	(1, 'calabresa', 2.00, 2),
	(2, 'portuguessa', 30.00, 2),
	(3, 'quatro queijos', 45.00, 1),
	(4, 'frango com requeijão', 28.00, 1),
	(5, 'vegetariana', 30.00, 1),
	(6, 'vegana', 45.00, 1),
	(7, 'muçarela', 29.00, 1),
	(8, 'marguerita', 35.00, 1),
	(9, 'napolitana', 50.00, 1),
	(10, 'cebola', 30.70, 1);

-- Copiando estrutura para tabela pizzaria.pizza_por_venda
DROP TABLE IF EXISTS `pizza_por_venda`;
CREATE TABLE IF NOT EXISTS `pizza_por_venda` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Venda_codPedido` int(11) NOT NULL,
  `Pizza_codPizza` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `tamanho` enum('Pequena','Média','Grande') CHARACTER SET utf8 NOT NULL,
  `precoVenda` double(10,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_Venda_has_Pizza_Pizza1_idx` (`Pizza_codPizza`),
  KEY `fk_Venda_has_Pizza_Venda1_idx` (`Venda_codPedido`),
  CONSTRAINT `fk_Venda_has_Pizza_Pizza1` FOREIGN KEY (`Pizza_codPizza`) REFERENCES `pizza` (`codPizza`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Venda_has_Pizza_Venda1` FOREIGN KEY (`Venda_codPedido`) REFERENCES `venda` (`codPedido`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.pizza_por_venda: ~3 rows (aproximadamente)
INSERT INTO `pizza_por_venda` (`codigo`, `Venda_codPedido`, `Pizza_codPizza`, `quantidade`, `tamanho`, `precoVenda`) VALUES
	(1, 3, 1, 2, 'Grande', 35.00),
	(2, 2, 8, 1, 'Média', 20.00),
	(3, 4, 10, 1, 'Pequena', 40.00),
	(4, 5, 3, 10, 'Média', 100.00);

-- Copiando estrutura para tabela pizzaria.recheio_borda
DROP TABLE IF EXISTS `recheio_borda`;
CREATE TABLE IF NOT EXISTS `recheio_borda` (
  `codBorda` int(11) NOT NULL AUTO_INCREMENT,
  `sabor` varchar(50) NOT NULL DEFAULT '',
  `preco` double(10,2) NOT NULL,
  PRIMARY KEY (`codBorda`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela pizzaria.recheio_borda: ~2 rows (aproximadamente)
INSERT INTO `recheio_borda` (`codBorda`, `sabor`, `preco`) VALUES
	(1, 'Catupiry', 4.00),
	(2, 'Cheddar', 5.00),
	(3, 'Salsicha', 2.00);

-- Copiando estrutura para procedure pizzaria.sp_alteraliente
DROP PROCEDURE IF EXISTS `sp_alteraliente`;
DELIMITER //
CREATE PROCEDURE `sp_alteraliente`(
	IN `codAlterar` INT,
	IN `nomeNovo` VARCHAR(100),
	IN `telefoneNovo` VARCHAR(100),
	IN `cpfNovo` VARCHAR(100),
	IN `bairroNovo` VARCHAR(50),
	IN `ruaNovo` VARCHAR(50),
	IN `numeroNovo` VARCHAR(50)
)
BEGIN

SELECT COUNT(*) INTO @contador FROM cliente WHERE codCliente = codAlterar;
if (@contador = 0)
then SELECT "Cliente não cadastrado" AS erro;
ELSE UPDATE cliente SET nome = nomeNovo, telefone = telefoneNovo, cpf = cpfNovo, bairro = bairroNovo,
rua = ruaNovo, numero = numeroNovo WHERE codCliente = codAlterar;
END IF;

END//
DELIMITER ;

-- Copiando estrutura para procedure pizzaria.sp_excluiClente
DROP PROCEDURE IF EXISTS `sp_excluiClente`;
DELIMITER //
CREATE PROCEDURE `sp_excluiClente`(
	IN `cod` INT
)
BEGIN
	DELETE FROM cliente WHERE codCliente = cod;
END//
DELIMITER ;

-- Copiando estrutura para procedure pizzaria.sp_insereCliente
DROP PROCEDURE IF EXISTS `sp_insereCliente`;
DELIMITER //
CREATE PROCEDURE `sp_insereCliente`(
	IN `codCliente` INT,
	IN `nome` VARCHAR(100),
	IN `telefone` VARCHAR(100),
	IN `cpf` VARCHAR(100),
	IN `bairro` VARCHAR(50),
	IN `rua` VARCHAR(50),
	IN `numero` VARCHAR(50)
)
BEGIN

   INSERT INTO
    cliente(codCliente, nome, telefone, cpf, bairro, rua, numero ) values
     (NULL, nome, telefone, cpf, bairro, rua, numero );

END//
DELIMITER ;

-- Copiando estrutura para tabela pizzaria.venda
DROP TABLE IF EXISTS `venda`;
CREATE TABLE IF NOT EXISTS `venda` (
  `codPedido` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `tipoPagamento` enum('Dinheiro','Cartão de crédito','Cartão de débito','Pix','Cheque') CHARACTER SET utf8 DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  `comissao` double DEFAULT NULL,
  `Cliente_codCliente` int(11) NOT NULL,
  `Funcionario_codFuncionario` int(11) NOT NULL,
  PRIMARY KEY (`codPedido`),
  KEY `fk_Venda_Cliente_idx` (`Cliente_codCliente`),
  KEY `fk_Venda_Funcionario1_idx` (`Funcionario_codFuncionario`),
  CONSTRAINT `fk_Venda_Cliente` FOREIGN KEY (`Cliente_codCliente`) REFERENCES `cliente` (`codCliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Venda_Funcionario1` FOREIGN KEY (`Funcionario_codFuncionario`) REFERENCES `funcionario` (`codFuncionario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pizzaria.venda: ~6 rows (aproximadamente)
INSERT INTO `venda` (`codPedido`, `data`, `tipoPagamento`, `desconto`, `comissao`, `Cliente_codCliente`, `Funcionario_codFuncionario`) VALUES
	(1, '2024-06-05', 'Dinheiro', 2, 2, 1, 1),
	(2, '2024-07-24', 'Cartão de crédito', 2.53, 2.05, 2, 1),
	(3, '2024-10-12', 'Cartão de débito', 6, 2, 2, 3),
	(4, '2024-10-11', 'Pix', 6, 2, 2, 2),
	(5, '2024-10-03', 'Cheque', 6, 2, 2, 2),
	(6, '2024-10-03', 'Pix', 10, 10, 2, 3),
	(7, '2024-11-13', 'Dinheiro', 10, 10, 1, 1),
	(8, '2024-11-13', 'Dinheiro', 10, 10, 2, 2);

-- Copiando estrutura para trigger pizzaria.tri_alteraPrecoBebida
DROP TRIGGER IF EXISTS `tri_alteraPrecoBebida`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_alteraPrecoBebida` AFTER UPDATE ON `bebida` FOR EACH ROW BEGIN

SET @msg = CONCAT("Bebida = ", NEW.nome, " teve o seu preço alterado para = ", NEW.preco);
INSERT INTO auditoria VALUES(NULL, @msg, "bebida", NOW(), USER());

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger pizzaria.tri_alteraPrecoPizza
DROP TRIGGER IF EXISTS `tri_alteraPrecoPizza`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_alteraPrecoPizza` AFTER UPDATE ON `pizza` FOR EACH ROW BEGIN

SET @msg = CONCAT("Pizza sabor de = ", NEW.nome, " teve o seu preço alterado para = ", NEW.precoBase);
INSERT INTO auditoria VALUES(NULL, @msg, "pizza", NOW(), USER());

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger pizzaria.tri_insereIngrediente
DROP TRIGGER IF EXISTS `tri_insereIngrediente`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_insereIngrediente` AFTER INSERT ON `ingrediente` FOR EACH ROW BEGIN

SET @mensagem = CONCAT("Ingrediente = ", NEW.nome, " inserido na linha = ", NEW.codIngrediente);
INSERT INTO auditoria VALUES(NULL, @mensagem, "ingrediente", NOW(), USER());

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger pizzaria.tri_insereSaborPizza
DROP TRIGGER IF EXISTS `tri_insereSaborPizza`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_insereSaborPizza` AFTER INSERT ON `pizza` FOR EACH ROW BEGIN

SET @mensagem = CONCAT("Sabor de pizza = ", NEW.nome, " inserido na linha = ", NEW.codPizza);
INSERT INTO auditoria VALUES(NULL, @mensagem, "pizza", NOW(), USER());

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
