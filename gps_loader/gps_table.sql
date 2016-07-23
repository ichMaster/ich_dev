create table gps (
    id serial not NULL primary key,
    user_id int,
    gps_date date,
    gps_time time,
    lat float,
    lon float
);
