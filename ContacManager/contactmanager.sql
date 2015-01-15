-- SQL Manager Lite for PostgreSQL 5.5.0.44853
-- ---------------------------------------
-- Хост         : localhost
-- База данных  : contactmanager
-- Версия       : PostgreSQL 9.3.5, compiled by Visual C++ build 1600, 64-bit



SET check_function_bodies = false;
--
-- Structure for table contacts (OID = 49153) : 
--
SET search_path = public, pg_catalog;
CREATE TABLE public.contacts (
    id_contact bigint NOT NULL,
    firstname varchar(100),
    lastname varchar(100),
    telephone varchar(100),
    email varchar(100),
    created timestamp(0) without time zone,
    id_account bigint NOT NULL
)
WITH (oids = false);
--
-- Definition for sequence contact_seq (OID = 65538) : 
--
CREATE SEQUENCE public.contact_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table account (OID = 73728) : 
--
CREATE TABLE public.account (
    id_account bigint NOT NULL,
    active boolean NOT NULL,
    created timestamp without time zone NOT NULL,
    email varchar(100) NOT NULL,
    login varchar(60) NOT NULL,
    name varchar(80),
    password varchar(255) NOT NULL,
    second_name varchar(80),
    surname varchar(80),
    updated timestamp without time zone
)
WITH (oids = false);
--
-- Structure for table role (OID = 73756) : 
--
CREATE TABLE public.role (
    id_role bigint NOT NULL,
    name varchar(15) NOT NULL
)
WITH (oids = false);
--
-- Structure for table account_role (OID = 73761) : 
--
CREATE TABLE public.account_role (
    id_account_role bigint NOT NULL,
    id_account bigint NOT NULL,
    id_role bigint NOT NULL
)
WITH (oids = false);
--
-- Definition for sequence account_seq (OID = 73788) : 
--
CREATE SEQUENCE public.account_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence account_role_seq (OID = 73790) : 
--
CREATE SEQUENCE public.account_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence role_seq (OID = 73792) : 
--
CREATE SEQUENCE public.role_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table persistent_logins (OID = 81920) : 
--
CREATE TABLE public.persistent_logins (
    username varchar(64) NOT NULL,
    series varchar(64) NOT NULL,
    token varchar(64) NOT NULL,
    last_used timestamp(0) without time zone NOT NULL
)
WITH (oids = false);
--
-- Definition for index CONTACTS_pkey (OID = 49157) : 
--
ALTER TABLE ONLY contacts
    ADD CONSTRAINT "CONTACTS_pkey"
    PRIMARY KEY (id_contact);
--
-- Definition for index account_pkey (OID = 73734) : 
--
ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey
    PRIMARY KEY (id_account);
--
-- Definition for index role_pkey (OID = 73759) : 
--
ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey
    PRIMARY KEY (id_role);
--
-- Definition for index account_role_pkey (OID = 73764) : 
--
ALTER TABLE ONLY account_role
    ADD CONSTRAINT account_role_pkey
    PRIMARY KEY (id_account_role);
--
-- Definition for index fk_5x8lvnpdjwicck02pt3g80vds (OID = 73766) : 
--
ALTER TABLE ONLY account_role
    ADD CONSTRAINT fk_5x8lvnpdjwicck02pt3g80vds
    FOREIGN KEY (id_account) REFERENCES account(id_account);
--
-- Definition for index fk_rrmqgobqv842nnkl8lerfy00k (OID = 73771) : 
--
ALTER TABLE ONLY account_role
    ADD CONSTRAINT fk_rrmqgobqv842nnkl8lerfy00k
    FOREIGN KEY (id_role) REFERENCES role(id_role);
--
-- Definition for index contacts_fk (OID = 73783) : 
--
ALTER TABLE ONLY contacts
    ADD CONSTRAINT contacts_fk
    FOREIGN KEY (id_account) REFERENCES account(id_account);
--
-- Definition for index persistent_logins_pkey (OID = 81923) : 
--
ALTER TABLE ONLY persistent_logins
    ADD CONSTRAINT persistent_logins_pkey
    PRIMARY KEY (series);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
