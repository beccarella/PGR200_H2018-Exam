create table if not exists conference_talks (
    id serial primary key,
    title varchar not null,
    description varchar not null,
    topic varchar not null,
    day_id integer,
    time_id integer,
    foreign key(day_id) references days(id),
    foreign key(time_id) references timeslots(id)
);