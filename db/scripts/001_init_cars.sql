create table cars(
                     id serial primary key,
                     model varchar(255),
                     created timestamp
);

create table if not exists brand(
                                    id serial primary key,
                                    name varchar(255)
);

create table if not exists model(
                                    id serial primary key,
                                    name varchar(255),
                                    brand_id int references brand(id)
);