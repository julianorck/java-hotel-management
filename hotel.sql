--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.10
-- Dumped by pg_dump version 9.3.10
-- Started on 2016-01-05 17:35:36

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2034 (class 1262 OID 16518)
-- Name: hotel; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE hotel WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE hotel OWNER TO postgres;

\connect hotel

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 186 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 186
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 16569)
-- Name: alert; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE alert (
    id integer NOT NULL,
    type character varying(255),
    "desc" character varying(255)
);


ALTER TABLE public.alert OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16567)
-- Name: alert_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE alert_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alert_id_seq OWNER TO postgres;

--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 181
-- Name: alert_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE alert_id_seq OWNED BY alert.id;


--
-- TOC entry 185 (class 1259 OID 16589)
-- Name: alert_reservation_room; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE alert_reservation_room (
    alertid integer NOT NULL,
    reservation_roomreservationid integer NOT NULL,
    reservation_roomroomid integer NOT NULL
);


ALTER TABLE public.alert_reservation_room OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16561)
-- Name: bill; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE bill (
    id integer NOT NULL,
    total real,
    reservationid integer NOT NULL
);


ALTER TABLE public.bill OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16559)
-- Name: bill_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE bill_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bill_id_seq OWNER TO postgres;

--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 179
-- Name: bill_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE bill_id_seq OWNED BY bill.id;


--
-- TOC entry 184 (class 1259 OID 16580)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employee (
    id integer NOT NULL,
    password character varying(255),
    type character varying(255),
    user_name character varying(20)
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16578)
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_id_seq OWNER TO postgres;

--
-- TOC entry 2040 (class 0 OID 0)
-- Dependencies: 183
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE employee_id_seq OWNED BY employee.id;


--
-- TOC entry 171 (class 1259 OID 16521)
-- Name: guest; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE guest (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    street character varying(255),
    street_number character varying(255),
    phone character varying(255),
    celphone character varying(255),
    city character varying(255),
    state character varying(255),
    country character varying(255),
    email character varying(255),
    facebook character varying(255)
);


ALTER TABLE public.guest OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16519)
-- Name: guest_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE guest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.guest_id_seq OWNER TO postgres;

--
-- TOC entry 2041 (class 0 OID 0)
-- Dependencies: 170
-- Name: guest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE guest_id_seq OWNED BY guest.id;


--
-- TOC entry 173 (class 1259 OID 16532)
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reservation (
    id integer NOT NULL,
    guestid integer NOT NULL,
    num_nights integer,
    checkin_date timestamp without time zone,
    checkout_date timestamp without time zone,
    num_guests integer,
    room_total real,
    employeeid integer NOT NULL,
    status character varying(255),
    requirement character varying(600)
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16530)
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservation_id_seq OWNER TO postgres;

--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 172
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE reservation_id_seq OWNED BY reservation.id;


--
-- TOC entry 178 (class 1259 OID 16554)
-- Name: reservation_room; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reservation_room (
    reservationid integer NOT NULL,
    roomid integer NOT NULL,
    alertid integer
);


ALTER TABLE public.reservation_room OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16540)
-- Name: room; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE room (
    id integer NOT NULL,
    occupied character(1),
    room_typeid integer NOT NULL,
    room_number integer
);


ALTER TABLE public.room OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16538)
-- Name: room_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_id_seq OWNER TO postgres;

--
-- TOC entry 2043 (class 0 OID 0)
-- Dependencies: 174
-- Name: room_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE room_id_seq OWNED BY room.id;


--
-- TOC entry 177 (class 1259 OID 16548)
-- Name: room_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE room_type (
    id integer NOT NULL,
    occupancy integer,
    rate real,
    description character varying(600),
    type character varying(255)
);


ALTER TABLE public.room_type OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16546)
-- Name: room_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE room_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_type_id_seq OWNER TO postgres;

--
-- TOC entry 2044 (class 0 OID 0)
-- Dependencies: 176
-- Name: room_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE room_type_id_seq OWNED BY room_type.id;


--
-- TOC entry 1878 (class 2604 OID 16572)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alert ALTER COLUMN id SET DEFAULT nextval('alert_id_seq'::regclass);


--
-- TOC entry 1877 (class 2604 OID 16564)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bill ALTER COLUMN id SET DEFAULT nextval('bill_id_seq'::regclass);


--
-- TOC entry 1879 (class 2604 OID 16583)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employee ALTER COLUMN id SET DEFAULT nextval('employee_id_seq'::regclass);


--
-- TOC entry 1873 (class 2604 OID 16524)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY guest ALTER COLUMN id SET DEFAULT nextval('guest_id_seq'::regclass);


--
-- TOC entry 1874 (class 2604 OID 16535)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation ALTER COLUMN id SET DEFAULT nextval('reservation_id_seq'::regclass);


--
-- TOC entry 1875 (class 2604 OID 16543)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY room ALTER COLUMN id SET DEFAULT nextval('room_id_seq'::regclass);


--
-- TOC entry 1876 (class 2604 OID 16551)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY room_type ALTER COLUMN id SET DEFAULT nextval('room_type_id_seq'::regclass);



--
-- TOC entry 1894 (class 2606 OID 16577)
-- Name: alert_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY alert
    ADD CONSTRAINT alert_pkey PRIMARY KEY (id);


--
-- TOC entry 1898 (class 2606 OID 16593)
-- Name: alert_reservation_room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY alert_reservation_room
    ADD CONSTRAINT alert_reservation_room_pkey PRIMARY KEY (alertid, reservation_roomreservationid, reservation_roomroomid);


--
-- TOC entry 1892 (class 2606 OID 16566)
-- Name: bill_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (id);


--
-- TOC entry 1896 (class 2606 OID 16588)
-- Name: employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- TOC entry 1881 (class 2606 OID 16529)
-- Name: guest_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY guest
    ADD CONSTRAINT guest_pkey PRIMARY KEY (id);


--
-- TOC entry 1884 (class 2606 OID 16537)
-- Name: reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- TOC entry 1890 (class 2606 OID 16558)
-- Name: reservation_room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reservation_room
    ADD CONSTRAINT reservation_room_pkey PRIMARY KEY (reservationid, roomid);


--
-- TOC entry 1886 (class 2606 OID 16545)
-- Name: room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- TOC entry 1888 (class 2606 OID 16553)
-- Name: room_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY room_type
    ADD CONSTRAINT room_type_pkey PRIMARY KEY (id);


--
-- TOC entry 1882 (class 1259 OID 16656)
-- Name: fki_fkreservatio574636; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_fkreservatio574636 ON reservation USING btree (guestid);


--
-- TOC entry 1906 (class 2606 OID 16629)
-- Name: fkalert_rese195977; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alert_reservation_room
    ADD CONSTRAINT fkalert_rese195977 FOREIGN KEY (reservation_roomreservationid, reservation_roomroomid) REFERENCES reservation_room(reservationid, roomid);


--
-- TOC entry 1905 (class 2606 OID 16624)
-- Name: fkalert_rese721922; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alert_reservation_room
    ADD CONSTRAINT fkalert_rese721922 FOREIGN KEY (alertid) REFERENCES alert(id);


--
-- TOC entry 1904 (class 2606 OID 16619)
-- Name: fkbill88614; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bill
    ADD CONSTRAINT fkbill88614 FOREIGN KEY (reservationid) REFERENCES reservation(id);


--
-- TOC entry 1903 (class 2606 OID 16609)
-- Name: fkreservatio120505; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation_room
    ADD CONSTRAINT fkreservatio120505 FOREIGN KEY (roomid) REFERENCES room(id);


--
-- TOC entry 1899 (class 2606 OID 16614)
-- Name: fkreservatio574635; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation
    ADD CONSTRAINT fkreservatio574635 FOREIGN KEY (employeeid) REFERENCES employee(id);


--
-- TOC entry 1900 (class 2606 OID 16651)
-- Name: fkreservatio574636; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation
    ADD CONSTRAINT fkreservatio574636 FOREIGN KEY (guestid) REFERENCES guest(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1902 (class 2606 OID 16604)
-- Name: fkreservatio817526; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation_room
    ADD CONSTRAINT fkreservatio817526 FOREIGN KEY (reservationid) REFERENCES reservation(id);


--
-- TOC entry 1901 (class 2606 OID 16599)
-- Name: fkroom572464; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY room
    ADD CONSTRAINT fkroom572464 FOREIGN KEY (room_typeid) REFERENCES room_type(id);


--
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-01-05 17:35:37

--
-- PostgreSQL database dump complete
--

