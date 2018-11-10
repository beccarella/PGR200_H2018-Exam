create table if not exists days (
    id serial primary key,
    date date
);

create table if not exists timeslots (
    id serial primary key,
    time time
);

create table if not exists conference_talks (
    id serial primary key,
    title varchar not null,
    description varchar not null,
    topic varchar not null,
    day_id integer not null,
    time_id integer not null,
    foreign key(day_id) references days(id),
    foreign key(time_id) references timeslots(id)
);