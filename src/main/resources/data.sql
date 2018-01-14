/* insert */
insert into client (name,password,user_group) values ('lisa','1234','user');
insert into client (name,password,user_group) values ('masha','1234','user');
insert into client (name,password,user_group) values ('tasha','1234','user');
insert into client (name,password,user_group) values ('rome','1234','user');

insert into pharmacist (name,password,user_group) values ('default','default','employee');
insert into pharmacist (name,password,user_group) values ('seller1','1234','employee');
insert into pharmacist (name,password,user_group) values ('seller2','1234','employee');

insert into drug (drug_name) values('Allegra');
insert into drug (drug_name) values('Concerta');
insert into drug (drug_name) values('Latisse');
insert into drug (drug_name) values('Levitra');
insert into drug (drug_name) values('Lexapro');

insert into drug_store (id_drug,price,amount) values ((SELECT id_drug from drug where drug_name ='Allegra'),15, 500);
insert into drug_store (id_drug,price,amount) values ((SELECT id_drug from drug where drug_name ='Concerta'),10, 45);
insert into drug_store (id_drug,price,amount) values ((SELECT id_drug from drug where drug_name ='Latisse'),100, 181);
insert into drug_store (id_drug,price,amount) values ((SELECT id_drug from drug where drug_name ='Levitra'),28, 15);
insert into drug_store (id_drug,price,amount) values ((SELECT id_drug from drug where drug_name ='Lexapro'),112, 100);

/*
это первый заказ. создаем заказ а потом инсертим позиции из заказа. это заказ с фармацевтом default т.е. заказ сделанный самостоятельно. после формирования позиции в заказе - надо уменьшить кол-во на складе.
для этого используется update
*/
insert into order_t (id_client,id_pharmacist) values ((SELECT id_client from client where name ='lisa'),(SELECT id_pharmacist from pharmacist where name = 'default'));
insert into
  order_items (id_order,id_drug,price,amount)
values (
  (
    SELECT id_order from order_t WHERE id_client  = (SELECT id_client from client where name ='lisa') AND id_pharmacist = (SELECT id_pharmacist from pharmacist where name = 'default') ORDER BY date DESC NULLS LAST LIMIT 1
  ),
  (
    SELECT id_drug from drug where drug_name = 'Allegra'
  ),
  (
    SELECT price from drug_store where id_drug =(SELECT id_drug from drug where drug_name = 'Allegra')
  ),
  10
);
UPDATE drug_store SET amount = amount - 10 WHERE id_drug = (SELECT id_drug FROM drug where drug_name = 'Allegra');

insert into
  order_items (id_order,id_drug,price,amount)
values (
  (
    SELECT id_order from order_t WHERE id_client  = (SELECT id_client from client where name ='lisa') AND id_pharmacist = (SELECT id_pharmacist from pharmacist where name = 'default') ORDER BY date DESC NULLS LAST LIMIT 1
  ),
  (
    SELECT id_drug from drug where drug_name = 'Latisse'
  ),
  (
    SELECT price from drug_store where id_drug =(SELECT id_drug from drug where drug_name = 'Latisse')
  ),
  15
);
UPDATE drug_store SET amount = amount - 15 WHERE id_drug = (SELECT id_drug FROM drug where drug_name = 'Latisse');



/*
второй заказ клиента лиза сделанный уже в аптеке у фармацевта seller1
*/

insert into order_t (id_client,id_pharmacist) values ((SELECT id_client from client where name ='lisa'),(Select id_pharmacist from pharmacist where name = 'seller1'));
insert into
  order_items (id_order,id_drug,price,amount)
values (
  (
    SELECT id_order from order_t WHERE id_client  = (SELECT id_client from client where name ='lisa') AND id_pharmacist = (SELECT id_pharmacist from pharmacist where name = 'seller1') ORDER BY date DESC NULLS LAST LIMIT 1
  ),
  (
    SELECT id_drug from drug where drug_name = 'Lexapro'
  ),
  (
    SELECT price from drug_store where id_drug =(SELECT id_drug from drug where drug_name = 'Lexapro')
  ),
  10
);
UPDATE drug_store SET amount = amount - 10 WHERE id_drug = (SELECT id_drug FROM drug where drug_name = 'Lexapro');

/*
третий заказ
*/
insert into order_t (id_client,id_pharmacist) values ((SELECT id_client from client where name ='masha'),(Select id_pharmacist from pharmacist where name = 'seller2'));
insert into
  order_items (id_order,id_drug,price,amount)
values (
  (
    SELECT id_order from order_t WHERE id_client  = (SELECT id_client from client where name ='masha') AND id_pharmacist = (SELECT id_pharmacist from pharmacist where name = 'seller2') ORDER BY date DESC NULLS LAST LIMIT 1
  ),
  (
    SELECT id_drug from drug where drug_name = 'Concerta'
  ),
  (
    SELECT price from drug_store where id_drug =(SELECT id_drug from drug where drug_name = 'Concerta')
  ),
  2
);
UPDATE drug_store SET amount = amount - 2 WHERE id_drug = (SELECT id_drug FROM drug where drug_name = 'Concerta');

insert into
  order_items (id_order,id_drug,price,amount)
values (
  (
    SELECT id_order from order_t WHERE id_client  = (SELECT id_client from client where name ='masha') AND id_pharmacist = (SELECT id_pharmacist from pharmacist where name = 'seller2') ORDER BY date DESC NULLS LAST LIMIT 1
  ),
  (
    SELECT id_drug from drug where drug_name = 'Levitra'
  ),
  (
    SELECT price from drug_store where id_drug =(SELECT id_drug from drug where drug_name = 'Levitra')
  ),
  1
);
UPDATE drug_store SET amount = amount - 1 WHERE id_drug = (SELECT id_drug FROM drug where drug_name = 'Levitra');