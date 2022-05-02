-- Table: public.livro

-- DROP TABLE public.livro;

CREATE TABLE IF NOT EXISTS public.livro
(
    descricao text COLLATE pg_catalog."default" NOT NULL,
    edicao integer NOT NULL,
    id_livro integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    isbn text COLLATE pg_catalog."default" NOT NULL,
    titulo text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT livro_pkey PRIMARY KEY (id_livro)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.livro
    OWNER to postgres;
-- Table: public.autor

-- DROP TABLE public.autor;

CREATE TABLE IF NOT EXISTS public.autor
(
    id_autor integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    nome text COLLATE pg_catalog."default" NOT NULL,
    naturalidade text COLLATE pg_catalog."default" NOT NULL,
    ano_nasc integer NOT NULL,
    CONSTRAINT autor_pkey PRIMARY KEY (id_autor)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.autor
    OWNER to postgres;
	
-- Table: public.livro_autor

-- DROP TABLE public.livro_autor;

CREATE TABLE IF NOT EXISTS public.livro_autor
(
    id_livro integer NOT NULL,
    id_autor integer NOT NULL,
    CONSTRAINT pk_livro_autor PRIMARY KEY (id_livro, id_autor),
    CONSTRAINT fk_livro_autor_id_autor FOREIGN KEY (id_autor)
        REFERENCES public.autor (id_autor) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_livro_autor_id_livro FOREIGN KEY (id_livro)
        REFERENCES public.livro (id_livro) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.livro_autor
    OWNER to postgres;