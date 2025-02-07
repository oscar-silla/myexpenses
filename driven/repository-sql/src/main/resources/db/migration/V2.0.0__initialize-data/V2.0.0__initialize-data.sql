/************************************
* D_TRANSACTION_TYPE
************************************/
INSERT INTO D_TRANSACTION_TYPE (TRANSACTION_TYPE_ID) VALUES ('EXPENSE'), ('REVENUE');

/************************************
* O_CATEGORIES
************************************/
INSERT INTO O_CATEGORIES (TRANSACTION_TYPE_ID)
VALUES
('EXPENSE'),
('EXPENSE'),
('EXPENSE'),
('EXPENSE'),
('EXPENSE'),
('REVENUE'),
('REVENUE'),
('REVENUE'),
('REVENUE'),
('REVENUE');
COMMIT;

/************************************
* D_CATEGORY_NAMES
************************************/
INSERT INTO D_CATEGORY_NAMES (CATEGORY_ID, "name", LOCALE)
VALUES
(1, 'FOOD', 'en_EN'),
(1, 'COMIDA', 'es_ES'),
(2, 'WORK', 'en_EN'),
(2, 'TRABAJO', 'es_ES'),
(3, 'FINANCES', 'en_EN'),
(3, 'FINANZAS', 'es_ES'),
(4, 'HOME', 'en_EN'),
(4, 'CASA', 'es_ES'),
(5, 'ENTERTAINMENT', 'en_EN'),
(5, 'ENTRETENIMIENTO', 'es_ES');