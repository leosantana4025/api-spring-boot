INSERT INTO USUARIO(nome, email, senha) VALUES('Leonardo Santana', 'leonardo', '$2a$10$/9zhPVj66kVMSx5wVK7CAOtoTjcZKhLpx.wzjQunX2x6u/ty9y2we');
INSERT INTO USUARIO(nome, email, senha) VALUES('Marcelo Barros', 'marcelo', '$2a$10$/9zhPVj66kVMSx5wVK7CAOtoTjcZKhLpx.wzjQunX2x6u/ty9y2we');

INSERT INTO PERFIL(id, nome) VALUES(1, 'ROLE_MODERADOR');
INSERT INTO PERFIL(id, nome) VALUES(2, 'ROLE_ALUNO');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(1, 1);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(2, 2);

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Back-End');
INSERT INTO CURSO(nome, categoria) VALUES('React', 'Front-End');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, usuario_id, curso_id) VALUES('Dúvida 1', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, usuario_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, usuario_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);