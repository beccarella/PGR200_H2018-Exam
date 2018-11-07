create table if not exists conference_talks (
    id serial primary key,
    title varchar not null,
    description varchar not null,
    day_date date,
    time_of_day time
);