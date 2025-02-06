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
('EXPENSE'),
('REVENUE'),
('REVENUE'),
('REVENUE'),
('REVENUE'),
('REVENUE'),
('REVENUE');