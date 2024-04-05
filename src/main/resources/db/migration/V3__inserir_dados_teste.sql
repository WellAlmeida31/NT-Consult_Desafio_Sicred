INSERT INTO public.assembleia
(id, nome, status)
VALUES(1, 'Assembleia Geral Jedi', 'ABERTA');

INSERT INTO public.assembleia
(id, nome, status)
VALUES(2, 'Assembleia Geral Sith', 'ABERTA');

INSERT INTO public.associado
(id, cpf, nome, status)
VALUES(1, '21249450098', 'Anakin skywalker', 'ABLE_TO_VOTE');

INSERT INTO public.associado
(id, cpf, nome, status)
VALUES(2, '54864769060', 'Mestre Yoda', 'ABLE_TO_VOTE');

INSERT INTO public.associado
(id, cpf, nome, status)
VALUES(3, '61512700029', 'Luke Skywalker', 'ABLE_TO_VOTE');

INSERT INTO public.associado
(id, cpf, nome, status)
VALUES(4, '80950183091', 'Obi-Wan Kenobi', 'UNABLE_TO_VOTE');

INSERT INTO public.associado
(id, cpf, nome, status)
VALUES(5, '83096982082', 'Darth Sidious Palpatine', 'ABLE_TO_VOTE');

INSERT INTO public.pauta
(id, descricao, fim, inicio, status, assembleia_id)
VALUES(1, 'Devemos expulsar Padmé do Senado por ser muito chata?', '2025-04-01T05:21', '2024-04-01T05:21', 'INICIADA', 1);

INSERT INTO public.pauta
(id, descricao, fim, inicio, status, assembleia_id)
VALUES(2, 'Devemos fazer clones para executar a ordem 66?', '2025-04-01T05:21', '2024-04-01T05:21', 'INICIADA', 2);

INSERT INTO public.voto
(id, voto_valor)
VALUES(1, 'SIM');

INSERT INTO public.pauta_voto
(pauta_id, voto_id)
VALUES(1, 1);

INSERT INTO public.associado_voto
(associado_id, voto_id)
VALUES(2, 1);

INSERT INTO public.voto
(id, voto_valor)
VALUES(2, 'SIM');

INSERT INTO public.pauta_voto
(pauta_id, voto_id)
VALUES(2, 2);

INSERT INTO public.associado_voto
(associado_id, voto_id)
VALUES(5, 2);

INSERT INTO public.voto
(id, voto_valor)
VALUES(3, 'SIM');

INSERT INTO public.pauta_voto
(pauta_id, voto_id)
VALUES(1, 3);

INSERT INTO public.associado_voto
(associado_id, voto_id)
VALUES(3, 3);