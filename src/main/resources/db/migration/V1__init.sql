create table if not exists admin
(
    created_date date,
    gender       smallint
        constraint admin_gender_check
            check ((gender >= 0) AND (gender <= 1)),
    dob          timestamp(6),
    id           uuid         not null
        primary key,
    fname        varchar(20),
    lname        varchar(20),
    employee_id  varchar(255),
    password     varchar(255) not null,
    phone_number varchar(255),
    username     varchar(255) not null
        unique
);

create table if not exists category
(
    id              bigint not null
        primary key,
    super_category  bigint
        constraint fk4jrmxrcaowyw2bmm8s3inwvik
            references category,
    category_schema text,
    name            varchar(255)
);

create table if not exists customer
(
    created_date date,
    gender       smallint
        constraint customer_gender_check
            check ((gender >= 0) AND (gender <= 1)),
    dob          timestamp(6),
    id           uuid         not null
        primary key,
    fname        varchar(20),
    lname        varchar(20),
    password     varchar(255) not null,
    phone_number varchar(255),
    username     varchar(255) not null
        unique
);

create table if not exists orders
(
    id       bigserial
        primary key,
    owner_id uuid
        constraint fk_owner
            references customer
);

create table if not exists shopping_session
(
    total_cost  numeric(38, 2) not null,
    created_at  timestamp(6),
    id          bigint         not null
        primary key,
    modified_at timestamp(6),
    owner_id    uuid
        unique
        constraint fk_owner
            references customer
);

create table if not exists supplier
(
    id   bigserial
        primary key,
    name varchar(255) not null
        unique
);

create table if not exists product
(
    price        numeric(38, 2),
    qty_in_stock numeric(38, 2) not null,
    category     bigint
        constraint fk_category
            references category,
    id           bigint         not null
        primary key,
    supplier     bigint
        constraint fk_supplier
            references supplier,
    sku          varchar(12)
        unique,
    description  text,
    name         varchar(255)   not null
        unique,
    warranty     varchar(255)   not null,
    attributes   jsonb,
    images       character varying[]
);

create table if not exists customer_wish_list
(
    product_id  bigint not null
        constraint customer_wishlist_product
            references product,
    customer_id uuid   not null
        constraint customer_wishlist_customer
            references customer
);

create table if not exists orders_products
(
    order_id    bigint not null
        constraint fke4y1sseio787e4o5hrml7omt5
            references orders,
    products_id bigint not null
        unique
        constraint fkrm329y1qei2vbtf3we4oh1gyx
            references product
);

create table if not exists review
(
    rating        integer not null
        constraint rating_constraint
            check ((rating >= 0) AND (rating <= 5)),
    created_at    timestamp(6),
    last_modified timestamp(6),
    product_id    bigint  not null
        constraint fk_review_product
            references product,
    customer_id   uuid    not null
        constraint fk_review_customer
            references customer,
    comment       text,
    images        character varying[],
    primary key (product_id, customer_id)
);

create table if not exists session_item
(
    product_id bigint not null
        constraint fk_product_id
            references product,
    qty        bigint not null,
    session_id bigint not null
        constraint fk_session_id
            references shopping_session,
    primary key (product_id, session_id)
);