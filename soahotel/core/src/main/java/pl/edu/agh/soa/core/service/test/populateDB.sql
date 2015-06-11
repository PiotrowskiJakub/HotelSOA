-- first do 3 times /rest/test  to make accounts and so on

 insert into soahotel.hotel
                values(201,'2',3,'1');

    insert into  soahotel.room_types
                 values(301,'desc','hdsfj',2134.3);

    insert into
    soahotel.room(roo_id,roo_floor,roo_number,roo_size,roo_hot_id,roo_rty_id)
    values(1001,1,1202,4,201,301);


    insert into soahotel.reservation (res_id,res_acc_id, res_end_date,res_start_date,res_paid,res_roo_id)
    values(101,1,'2004-10-19 10:23:54','2004-09-19 10:23:54',FALSE,1001),
    (102,4,'2004-10-19 10:23:54','2004-09-19 10:23:54',FALSE,1001),
            (103,7,'2004-10-19 10:23:54','2004-09-19 10:23:54',FALSE,1001);


    insert into soahotel.payment (pay_due_date,
    pay_gross_cost,
    pay_status,
    pay_res_id,
    pay_id)
    values('2004-10-19 10:23:54', 122.12, 2, 101, 401),
    ('2004-12-19 10:23:54', 1232.12, 2, 102, 402),
            ('2004-12-19 10:23:54', 1292.12, 2, 103, 403);