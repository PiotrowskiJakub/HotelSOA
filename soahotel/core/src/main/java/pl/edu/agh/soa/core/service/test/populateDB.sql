-- first do 3 times /rest/test  to make accounts and so on

 insert into soahotel.hotel
                values(201,'2',3,'1',2,3);

    insert into  soahotel.room_types
                 values(301,'desc','hdsfj',2134.3);

    insert into
    soahotel.room(roo_id,roo_floor,roo_number,roo_size,roo_hot_id,roo_rty_id)
    values(1001,1,1202,4,201,301);


    insert into soahotel.reservation (res_id, res_acc_id, res_end_date,res_start_date,res_paid,res_roo_id)
    values(101,1,'2004-10-19 10:23:54','2004-09-19 10:23:54',FALSE,1001),
    (102,4,'2004-10-19 10:23:54','2004-09-19 10:23:54',FALSE,1001),
            (103,7,'2015-06-21 10:23:54','2004-09-19 10:23:54',FALSE,1001),
            (104,4,'2015-06-20 10:23:54','2015-06-13 10:23:54',FALSE,1001),
            (105,7,'2015-06-19 10:23:54','2015-06-03 10:23:54',FALSE,1001),
            (106,4,'2015-06-21 10:23:54','2015-06-06 10:23:54',FALSE,1001),
            (107,7,'2015-06-21 10:23:54','2015-06-17 10:23:54',FALSE,1001);


    insert into soahotel.payment (pay_due_date,
    pay_gross_cost,
    pay_status,
    res_id,
    pay_id)
    values('2004-10-19 10:23:54', 122.12, 2, 101, 401),
    ('2004-12-19 10:23:54', 1232.12, 2, 102, 402),
            ('2004-12-19 10:23:54', 1292.12, 2, 103, 403);

            insert into soahotel.additional_services_types(ast_id,ast_description, ast_name,dst_price)
values(501,'fdafd' ,'fdjewoifkswd',21.00),
	(502,'fdsarewfrdfs','duewindce',43.0),
	(503,'fdsarewfrdfs','duewindce',3.0);

insert into soahotel.additional_services(ase_id,ase_ast_id,ase_res_id,ase_date,ase_count)
values(601,501,101,'1970-01-01 00:00:01' ,1),
(602,502,102,'1970-01-01 00:00:01',2),
(603,503,103,'1970-01-01 00:00:01',3),
(604,503,104,'1970-01-01 00:00:01',24),
(605,502,105,'1970-01-01 00:00:01',2),
(606,501,106,'1970-01-01 00:00:01',2),
(607,502,107,'1970-01-01 00:00:01',4);


select * from soahotel.reservation