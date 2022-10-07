-- create table resume
-- (
--     uuid      char(36) primary key not null,
--     full_name text                 not null
-- );
--
--
-- create table contact
-- (
--     id          serial,
--     resume_uuid char(36) not null references resume (uuid) on delete cascade,
--     type        text     not null,
--     value       text     not null
-- );
-- create unique index contact_uuid_type_index on contact (resume_uuid, type);
-- ALTER USER postgres PASSWORD 'postgres';

CREATE TABLE section (
                         id          SERIAL PRIMARY KEY,
                         resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
                         type        TEXT     NOT NULL,
                         content     TEXT     NOT NULL
);
CREATE UNIQUE INDEX section_idx
    ON section (resume_uuid, type);