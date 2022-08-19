insert into vacancies (title, description) values ('Java Developer', 'Middle Java Developer');
insert into vacancies (title, description) values ('JS Developer', 'Junior JS Developer');

insert into bases (name) values ('base1');

insert into candidates (name, experience, salary, vacancybase_id)
    values ('Фёдор Сумкин','5', '100.0','1');

insert into bases_vacancies (vacancybase_id, vacancies_id) values ('1','1');
insert into bases_vacancies (vacancybase_id, vacancies_id) values ('1','2');
