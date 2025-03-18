
INSERT INTO user_entity (id, username, password, first_name, last_name, email) VALUES
                                                                                   (1, 'user1', 'password1', 'John', 'Doe', 'john.doe@example.com'),
                                                                                   (2, 'user2', 'password2', 'Jane', 'Doe', 'jane.doe@example.com'),
                                                                                   (3, 'user3', 'password3', 'Alice', 'Smith', 'alice.smith@example.com'),
                                                                                   (4, 'user4', 'password4', 'Bob', 'Johnson', 'bob.johnson@example.com'),
                                                                                   (5, 'user5', 'password5', 'Charlie', 'Brown', 'charlie.brown@example.com'),
                                                                                   (6, 'user6', 'password6', 'Dave', 'Williams', 'dave.williams@example.com'),
                                                                                   (7, 'user7', 'password7', 'Eve', 'Davis', 'eve.davis@example.com'),
                                                                                   (8, 'user8', 'password8', 'Frank', 'Miller', 'frank.miller@example.com'),
                                                                                   (9, 'user9', 'password9', 'Grace', 'Wilson', 'grace.wilson@example.com'),
                                                                                   (10, 'user10', 'password10', 'Hank', 'Moore', 'hank.moore@example.com');



INSERT INTO user_entity_children_id (user_entity_id, children_id) VALUES
                                                                      (1, 1), (1, 2),  -- user1 has children 1,2
                                                                      (2, 1), (2, 2),  -- user2 has children 1,2
                                                                      (3, 3),          -- user3 has child 3
                                                                      (4, 4),          -- user4 has child 4
                                                                      (5, 5),          -- user5 has child 5
                                                                      (6, 6),          -- user6 has child 6
                                                                      (7, 7),          -- user7 has child 7
                                                                      (8, 8),          -- user8 has child 8
                                                                      (9, 9),          -- user9 has child 9
                                                                      (10, 10);        -- user10 has child 10

