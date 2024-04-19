INSERT INTO  Medico
    (nome,crm)
VALUES ('Dr HANSCHSCROU', '1234'),
       ('Dr ERIQUI', '4321');

INSERT INTO Consulta
    (nome, data_agendamento, local,medico_id)
values
    ('Pediatra', '2024-04-17', 'Hospital Albert Einstein',1),
    ('Cardiologista', '2024-04-17', 'Hospital Albert Einstein',2);
