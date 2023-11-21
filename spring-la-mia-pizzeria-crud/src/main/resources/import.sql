INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Margherita', 'Pomodoro, mozzarella, basilico', 8, 'https://picsum.photos/id/1080/200/300', '2023-11-09 15:00:00');

INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Marinara', 'Pomodoro, aglio, origano', 7, 'https://picsum.photos/id/1060/200/300', '2023-11-09 15:05:00');

INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Quattro Stagioni', 'Pomodoro, mozzarella, funghi, prosciutto', 13, 'https://picsum.photos/id/1062/200/300', '2023-11-09 15:10:00');

INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Diavola', 'Pomodoro, mozzarella, salame piccante', 10, 'https://picsum.photos/id/1067/200/300', '2023-11-09 15:15:00');

INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Quattro Formaggi', 'Mozzarella, gorgonzola, parmigiano, pecorino', 11, 'https://picsum.photos/id/1070/200/300', '2023-11-09 15:20:00');

INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Ortolana', 'Pomodoro, mozzarella, verdure grigliate', 9.5, 'https://picsum.photos/id/1074/200/300', '2023-11-09 15:25:00');

INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Funghi', 'Pomodoro, mozzarella, funghi champignon', 9, 'https://picsum.photos/id/1035/200/300', '2023-11-09 15:30:00');

INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Napoli', 'Pomodoro, mozzarella, acciughe', 10, 'https://picsum.photos/id/1025/200/300', '2023-11-09 15:35:00');

INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Bufalina', 'Pomodoro, mozzarella di bufala, basilico', 12, 'https://picsum.photos/id/1084/200/300', '2023-11-09 15:40:00');

INSERT INTO pizzas (nome, descrizione, prezzo, url_image, created_at) VALUES('Prosciutto e Funghi', 'Pomodoro, mozzarella, prosciutto cotto, funghi', 11.5, 'https://picsum.photos/id/1023/200/300', '2023-11-09 15:45:00');

-- Ingredients
INSERT INTO ingredients(nome) VALUES('Pomodoro');
INSERT INTO ingredients(nome) VALUES('Mozzarella');
INSERT INTO ingredients(nome) VALUES('Basilico');
INSERT INTO ingredients(nome) VALUES('Aglio');
INSERT INTO ingredients(nome) VALUES('Origano');
INSERT INTO ingredients(nome) VALUES('Funghi');
INSERT INTO ingredients(nome) VALUES('Prosciutto');
INSERT INTO ingredients(nome) VALUES('Salame Piccante');
INSERT INTO ingredients(nome) VALUES('Gorgonzola');
INSERT INTO ingredients(nome) VALUES('Parmigiano');
INSERT INTO ingredients(nome) VALUES('Pecorino');
INSERT INTO ingredients(nome) VALUES('Verdure Grigliate');
INSERT INTO ingredients(nome) VALUES('Funghi Champignon');
INSERT INTO ingredients(nome) VALUES('Acciughe');
INSERT INTO ingredients(nome) VALUES('Mozzarella di Bufala');
INSERT INTO ingredients(nome) VALUES('Prosciutto Cotto');

-- Margherita: Pomodoro, Mozzarella, Basilico
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (1, 1); -- Pomodoro
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (1, 2); -- Mozzarella
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (1, 3); -- Basilico

-- Marinara: Pomodoro, Aglio, Origano
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (2, 1); -- Pomodoro
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (2, 4); -- Aglio
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (2, 5); -- Origano

-- Quattro Stagioni: Pomodoro, Mozzarella, Funghi, Prosciutto
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (3, 1); -- Pomodoro
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (3, 2); -- Mozzarella
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (3, 6); -- Funghi
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (3, 7); -- Prosciutto

-- Diavola: Pomodoro, Mozzarella, Salame Piccante
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (4, 1); -- Pomodoro
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (4, 2); -- Mozzarella
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (4, 8); -- Salame Piccante

-- Quattro Formaggi: Mozzarella, Gorgonzola, Parmigiano, Pecorino
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (5, 2); -- Mozzarella
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (5, 9); -- Gorgonzola
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (5, 10); -- Parmigiano
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (5, 11); -- Pecorino

-- Ortolana: Pomodoro, Mozzarella, Verdure Grigliate
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (6, 1); -- Pomodoro
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (6, 2); -- Mozzarella
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (6, 12); -- Verdure Grigliate

-- Funghi: Pomodoro, Mozzarella, Funghi Champignon
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (7, 1); -- Pomodoro
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (7, 2); -- Mozzarella
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (7, 13); -- Funghi Champignon

-- Napoli: Pomodoro, Mozzarella, Acciughe
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (8, 1); -- Pomodoro
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (8, 2); -- Mozzarella
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (8, 14); -- Acciughe

-- Bufalina: Pomodoro, Mozzarella di Bufala, Basilico
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (9, 1); -- Pomodoro
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (9, 15); -- Mozzarella di Bufala
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (9, 3); -- Basilico

-- Prosciutto e Funghi: Pomodoro, Mozzarella, Prosciutto Cotto, Funghi
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (10, 1); -- Pomodoro
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (10, 2); -- Mozzarella
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (10, 16); -- Prosciutto Cotto
INSERT INTO pizzas_ingredients (pizza_id, ingredients_id) VALUES (10, 6); -- Funghi

INSERT INTO roles (id, nome) VALUES(1, 'ADMIN');
INSERT INTO roles (id, nome) VALUES(2, 'USER');

INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('john@email.com', 'John', 'Doe', '2023-11-20 10:35', '{noop}john');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('jane@email.com', 'Jane', 'Smith', '2023-11-20 10:35','{noop}jane');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('pippo@email.com', 'Pippo', 'Pazzo', '2023-11-20 10:35', '{noop}pippo');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('mimmo@email.com', 'Mimmo', 'Modem', '2023-11-20 10:35', '{noop}mimmo');

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
--INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(3, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(4, 1);
