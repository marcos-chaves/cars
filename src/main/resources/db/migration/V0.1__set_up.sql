-- create user only if not exist
DO
$body$
BEGIN
   IF NOT EXISTS (
      SELECT *
      FROM   pg_catalog.pg_user
      WHERE  usename = 'garage_admin') THEN

      CREATE USER garage_admin WITH password 'cars';
   END IF;
END
$body$;

CREATE SCHEMA IF NOT EXISTS cars_garage;

GRANT USAGE ON SCHEMA cars_garage TO garage_admin;
ALTER USER garage_admin SET search_path = 'cars_garage, public';

-- ensure that user cars will have the needed privileges on new tables
ALTER DEFAULT PRIVILEGES
   IN SCHEMA cars_garage
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLES
   TO garage_admin;

-- ensure that user cars will have the needed privileges on new sequences
ALTER DEFAULT PRIVILEGES
   IN SCHEMA cars_garage
GRANT USAGE ON SEQUENCES
   TO garage_admin;

-- ensure that new functions will not have default privilege in public schema
ALTER DEFAULT PRIVILEGES
REVOKE EXECUTE ON FUNCTIONS
 FROM PUBLIC;

-- revoke default function privilege
REVOKE EXECUTE
   ON ALL FUNCTIONS IN SCHEMA PUBLIC
 FROM PUBLIC;
