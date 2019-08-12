CREATE TABLE IF NOT EXISTS public."user"
(
    id           bigint  NOT NULL,
    enabled      boolean NOT NULL,
    first_name   character varying(255) COLLATE pg_catalog."default",
    last_name    character varying(255) COLLATE pg_catalog."default",
    password     character varying(255) COLLATE pg_catalog."default",
    phone_number character varying(255) COLLATE pg_catalog."default",
    username     character varying(255) COLLATE pg_catalog."default",
    token     character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public."user"
    OWNER to table_booking;

create sequence if not exists user_sequence START 2 INCREMENT 1;
alter sequence user_sequence
    owner to table_booking;

CREATE TABLE IF NOT EXISTS public.role
(
    id          bigint NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    name        character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.role
    OWNER to table_booking;

create sequence if not exists role_sequence START 3 INCREMENT 1;
alter sequence role_sequence
    owner to table_booking;


CREATE TABLE IF NOT EXISTS users_roles
(
    user_id bigint not null
        constraint fkgd3iendaoyh04b95ykqise6qh
            references "user",
    role_id bigint not null
        constraint fkt4v0rrweyk393bdgt107vdx0x
            references role,
    constraint users_roles_pkey
        primary key (user_id, role_id)
);

ALTER TABLE users_roles
    owner to table_booking;

create sequence if not exists users_roles_sequence START 3 INCREMENT 1;
alter sequence users_roles_sequence
    owner to table_booking;




CREATE TABLE IF NOT EXISTS room
(
    id               bigint           not null
        constraint room_pkey primary key,
    amount_of_person integer          not null,
    booking_price    double precision not null,
    number           integer          not null,
    price            double precision not null,
    status           varchar(255),
    type             integer          not null
);

ALTER TABLE room
    OWNER to table_booking;

create sequence if not exists room_sequence START 1 INCREMENT 1;
alter sequence room_sequence
    owner to table_booking;



CREATE TABLE IF NOT EXISTS "order"
(
    id                 bigint           not null
        constraint order_pkey primary key,
    amount_of_person   integer          not null,
    booked_days        integer          not null,
    booking_date       timestamp,
    included_breakfast boolean          not null,
    price              double precision not null,
    status             varchar(255),
    room_id            bigint
        constraint fkgxnt6j632hfbkb6nncand03jj
            references room,
    user_id            bigint
        constraint fkt7abetueht6dd1gs9jyl3o4t7
            references "user"
);

ALTER TABLE "order"
    OWNER to table_booking;

create sequence if not exists order_sequence START 1 INCREMENT 1;
alter sequence order_sequence
    owner to table_booking;



