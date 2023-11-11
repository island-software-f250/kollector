create table product_category (product_id uuid not null, category_id uuid not null, primary key (product_id, category_id));
create table tb_categories (category_id uuid not null, created_at timestamp(6) not null, name varchar(255) not null, updated_at timestamp(6) not null, primary key (category_id));
create table tb_products (product_id uuid not null, active boolean not null, created_at timestamp(6) not null, description varchar(255) not null, name varchar(255) not null, price numeric(38,2) not null, quantity integer not null, specification varchar(255), updated_at timestamp(6) not null, primary key (product_id));
alter table if exists product_category add constraint fk_category_id foreign key (category_id) references tb_categories;
alter table if exists product_category add constraint fk_product_id foreign key (product_id) references tb_products;

-- Inserir categorias
INSERT INTO tb_categories (category_id, name, created_at, updated_at) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Eletrônicos', '2023-01-01T00:00:00', '2023-01-01T00:00:00');

INSERT INTO tb_categories (category_id, name, created_at, updated_at) VALUES
    ('22222222-2222-2222-2222-222222222222', 'Roupas', '2023-01-01T00:00:00', '2023-01-01T00:00:00');

INSERT INTO tb_categories (category_id, name, created_at, updated_at) VALUES
    ('33333333-3333-3333-3333-333333333333', 'Acessórios', '2023-01-01T00:00:00', '2023-01-01T00:00:00');

INSERT INTO tb_categories (category_id, name, created_at, updated_at) VALUES
    ('44444444-4444-4444-4444-444444444444', 'Livros', '2023-01-01T00:00:00', '2023-01-01T00:00:00');

INSERT INTO tb_categories (category_id, name, created_at, updated_at) VALUES
    ('55555555-5555-5555-5555-555555555555', 'Jogos', '2023-01-01T00:00:00', '2023-01-01T00:00:00');


-- Inserir produtos
-- INSERT INTO tb_products (product_id, name, description, specification, quantity, price, active, created_at, updated_at) VALUES
--     ('aaa11111-1111-1111-1111-111111111111', 'Smartphone', 'Um ótimo smartphone', 'Especificações do smartphone', 100, 999.99, true, '2023-01-01T00:00:00', '2023-01-01T00:00:00');
--
-- INSERT INTO tb_products (product_id, name, description, specification, quantity, price, active, created_at, updated_at) VALUES
--     ('bbb22222-2222-2222-2222-222222222222', 'Camiseta', 'Uma camiseta confortável', 'Especificações da camiseta', 200, 19.99, true, '2023-01-01T00:00:00', '2023-01-01T00:00:00');
--
-- INSERT INTO tb_products (product_id, name, description, specification, quantity, price, active, created_at, updated_at) VALUES
--     ('ccc33333-3333-3333-3333-333333333333', 'Fone de Ouvido', 'Um excelente fone de ouvido', 'Especificações do fone de ouvido', 50, 129.99, true, '2023-01-01T00:00:00', '2023-01-01T00:00:00');
--
-- INSERT INTO tb_products (product_id, name, description, specification, quantity, price, active, created_at, updated_at) VALUES
--     ('ddd44444-4444-4444-4444-444444444444', 'Livro "Aventuras"', 'Uma emocionante aventura', 'Especificações do livro', 30, 29.99, true, '2023-01-01T00:00:00', '2023-01-01T00:00:00');
--
-- INSERT INTO tb_products (product_id, name, description, specification, quantity, price, active, created_at, updated_at) VALUES
--     ('eee55555-5555-5555-5555-555555555555', 'Jogo de Tabuleiro', 'Um emocionante jogo de tabuleiro', 'Especificações do jogo', 40, 39.99, true, '2023-01-01T00:00:00', '2023-01-01T00:00:00');
--
-- INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES ('aaa11111-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111111');
-- INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES ('aaa11111-1111-1111-1111-111111111111', '22222222-2222-2222-2222-222222222222');
-- INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES ('aaa11111-1111-1111-1111-111111111111', '44444444-4444-4444-4444-444444444444');
--
--
