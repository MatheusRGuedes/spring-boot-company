/*
INSERT SCRIPTS FOR cargos
*/
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (1,'Analista I',9);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (2,'Recrutador',8);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (3,'Consultor Interno',8);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (6,'Programador Java I',9);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (8,'Programador Delphi',9);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (10,'Contador',12);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (11,'Programador Java II',9);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (12,'Diretor Administrativo',11);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (13,'DBA',9);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (14,'Auxiliar Financeiro',5);
INSERT INTO cargos (`id`,`nome`,`id_departamento_fk`) VALUES (15,'Gerente Financeiro',5);

/*
INSERT SCRIPTS FOR departamentos
*/
INSERT INTO departamentos (`id`,`nome`) VALUES (11,'Administrativo');
INSERT INTO departamentos (`id`,`nome`) VALUES (13,'Almoxarifado');
INSERT INTO departamentos (`id`,`nome`) VALUES (12,'Contabilidade');
INSERT INTO departamentos (`id`,`nome`) VALUES (5,'Financeiro');
INSERT INTO departamentos (`id`,`nome`) VALUES (8,'Recursos Humanos');
INSERT INTO departamentos (`id`,`nome`) VALUES (9,'Tecnologia da Informação');
INSERT INTO departamentos (`id`,`nome`) VALUES (14,'Teste');

/*
INSERT SCRIPTS FOR enderecos
*/
INSERT INTO enderecos (`id`,`bairro`,`cep`,`cidade`,`complemento`,`logradouro`,`numero`,`uf`) VALUES (5,'Benfica','20973-011','Rio de Janeiro','prédio','Avenida Dom Hélder Câmara',1501,'RJ');
INSERT INTO enderecos (`id`,`bairro`,`cep`,`cidade`,`complemento`,`logradouro`,`numero`,`uf`) VALUES (6,'Guarujá','20198-455','São Paulo','apt 206','Rua Efigênio',1259,'SP');
INSERT INTO enderecos (`id`,`bairro`,`cep`,`cidade`,`complemento`,`logradouro`,`numero`,`uf`) VALUES (7,'Blumenau','21987-656','Santa Catarina','casa 220','Vale Europeu',123,'SC');
INSERT INTO enderecos (`id`,`bairro`,`cep`,`cidade`,`complemento`,`logradouro`,`numero`,`uf`) VALUES (8,'Vila Isabel','20560-032','Rio de Janeiro','apt 402','Rua Barão São Francisco',566,'RJ');

/*
INSERT SCRIPTS FOR funcionarios
*/
INSERT INTO funcionarios (`id`,`data_entrada`,`data_saida`,`nome`,`salario`,`cargo_id_fk`,`endereco_id_fk`) VALUES (1,'2019-10-14',NULL,'João',1600.50,6,5);
INSERT INTO funcionarios (`id`,`data_entrada`,`data_saida`,`nome`,`salario`,`cargo_id_fk`,`endereco_id_fk`) VALUES (4,'2018-03-17',NULL,'Yasmin',986.00,1,6);
INSERT INTO funcionarios (`id`,`data_entrada`,`data_saida`,`nome`,`salario`,`cargo_id_fk`,`endereco_id_fk`) VALUES (5,'2017-06-05','2020-06-05','Larissa',1620.05,1,7);
INSERT INTO funcionarios (`id`,`data_entrada`,`data_saida`,`nome`,`salario`,`cargo_id_fk`,`endereco_id_fk`) VALUES (6,'2018-05-02','2019-12-19','Cleiton Rasta',2599.99,3,8);