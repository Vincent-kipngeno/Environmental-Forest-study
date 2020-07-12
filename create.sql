CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;
CREATE TABLE animals(id serial PRIMARY KEY, name VARCHAR, type VARCHAR, health VARCHAR, age VARCHAR);
CREATE TABLE sightings (id serial PRIMARY KEY, location VARCHAR, ranger_name VARCHAR, animal_id INTEGER, created_at TIMESTAMP);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;