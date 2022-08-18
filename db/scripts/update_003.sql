create table if not exists vacancies(
                                        id serial primary key,
                                        title varchar(255),
                                        description text
);

create table if not exists bases(
                                        id serial primary key,
                                        name varchar(255)
);

create table if not exists candidates(
                                    id serial primary key,
                                    name varchar(255),
                                    experience int,
                                    salary double precision,
                                    vacancybase_id int not null unique references bases(id)
);

create table if not exists bases_vacancies(
                                                  vacancybase_id int not null references bases(id),
                                                  vacancies_id int references vacancies(id)

);
insert into vacancies (title, description) values ('Java Developer', 'Middle Java Developer');
insert into vacancies (title, description) values ('JS Developer', 'Junior JS Developer');

insert into bases (name) values ('base1');

insert into candidates (name, experience, salary, vacancybase_id)
    values ('Фёдор Сумкин','5', '100.0','1');

insert into bases_vacancies (vacancybase_id, vacancies_id) values ('1','1');
insert into bases_vacancies (vacancybase_id, vacancies_id) values ('1','2');
