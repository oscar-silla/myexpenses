/************************************
* D_TRANSACTION_TYPE
************************************/
INSERT INTO D_TRANSACTION_TYPE (TRANSACTION_TYPE_ID) VALUES ('EXPENSE'), ('REVENUE');

/************************************
* O_CATEGORIES
************************************/
INSERT INTO O_CATEGORIES (CATEGORY_NAME, TRANSACTION_TYPE_ID) 
VALUES 
('FOOD',         'EXPENSE'), 
('HOME',         'EXPENSE'),
('FINANCES',     'EXPENSE'),
('WORK',         'EXPENSE'),
('ENTERTAINMENT','EXPENSE'),
('OTHER',        'EXPENSE'),
('FOOD',         'REVENUE'),
('HOME',         'REVENUE'),
('FINANCES',     'REVENUE'),
('WORK',         'REVENUE'),
('ENTERTAINMENT','REVENUE'),
('OTHER',        'REVENUE');