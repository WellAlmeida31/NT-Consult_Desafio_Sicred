create table assembleia (
    id bigserial not null,
    nome varchar(255) not null,
    status varchar(255) check (status in ('ABERTA','ENCERRADA')),
    primary key (id)
);

create table associado (
    id bigserial not null,
    cpf varchar(255) not null,
    nome varchar(255) not null,
    status varchar(255) check (status in ('ABLE_TO_VOTE','UNABLE_TO_VOTE')),
    primary key (id)
);

create table associado_voto (
    associado_id bigint not null,
    voto_id bigint not null
);

create table pauta (
    id bigserial not null,
    descricao varchar(255) not null,
    fim timestamp(6),
    inicio timestamp(6),
    status varchar(255) check (status in ('INICIADA','FINALIZADA')),
    assembleia_id bigint not null,
    primary key (id)
);

create table pauta_voto (
    pauta_id bigint not null,
    voto_id bigint not null
);

create table voto (
    id bigserial not null,
    voto_valor varchar(255) not null check (voto_valor in ('SIM','NAO')),
    primary key (id)
);

alter table if exists associado
drop constraint if exists UK_associado;

alter table if exists associado
add constraint UK_associado unique (cpf);

alter table if exists associado_voto
    add constraint FKassociado_voto_voto_id
    foreign key (voto_id)
    references voto;

alter table if exists associado_voto
    add constraint FKassociado_voto_associado_id
    foreign key (associado_id)
    references associado;

alter table if exists pauta
    add constraint FKpauta_assembleia_id
    foreign key (assembleia_id)
    references assembleia;

alter table if exists pauta_voto
    add constraint FKpauta_voto_voto_id
    foreign key (voto_id)
    references voto;

alter table if exists pauta_voto
    add constraint FKpauta_voto_pauta_id
    foreign key (pauta_id)
    references pauta;