CREATE TABLE IF NOT EXISTS products (
    id bigint,
    name varchar(255),
    description varchar(255),
    price double precision,
    image varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id bigint,
    email varchar(255),
    password varchar(255),
    role varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS carts (
    id bigint,
    user_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS cart_items (
    id bigint,
    cart_id bigint,
    product_id bigint,
    quantity bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (cart_id) REFERENCES carts (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);