CREATE TABLE tb_missoes (
    id          BIGSERIAL PRIMARY KEY,
    nome        VARCHAR(255),
    dificuldade VARCHAR(255)
);

CREATE TABLE tb_ninjas (
    id        BIGSERIAL PRIMARY KEY,
    nome      VARCHAR(255),
    idade     INTEGER,
    email     VARCHAR(255) UNIQUE,
    missao_id BIGINT REFERENCES tb_missoes(id)
);
