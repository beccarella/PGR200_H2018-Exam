create table if not exists conference_talks (
    id serial primary key,
    title varchar not null,
    description varchar not null,
    day_date date,
    time_of_day time
);

create table if not exists days (
    id serial primary key,
    date date
);

create table if not exists timeslots (
    id serial primary key,
    time time
);

create table if not exists topics (
    id serial primary key,
    topic varchar
);