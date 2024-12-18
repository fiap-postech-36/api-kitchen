CREATE TABLE IF NOT EXISTS orderStatusControl(

    id SERIAL PRIMARY KEY,
    order_id SERIAL not null,
    client varchar(20),
    status varchar(20) not null,
    amount decimal not null,
    payment_at timestamp(6)
);

ALTER TABLE orderStatusControl ADD CONSTRAINT fk_payment_order_id FOREIGN KEY (order_id) REFERENCES ordered(id);