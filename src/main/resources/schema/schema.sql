-------------- CREATE TABLE USERS

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    date_of_birth DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_users_timestamp
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

-------------- CREATE TABLE ACCOUNTS END

-------------- CREATE TABLE ACCOUNTS
CREATE TABLE accounts (
    account_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    account_num VARCHAR(255) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (account_name, account_num),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_users_timestamp
BEFORE UPDATE ON accounts
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

ALTER TABLE accounts
ADD COLUMN current_balance DECIMAL(15, 2) DEFAULT 0.00,
ADD COLUMN previous_balance DECIMAL(15, 2) DEFAULT 0.00;

-------------- CREATE TABLE ACCOUNTS END

-------------- CREATE TABLE OPERATIONS

CREATE TABLE operations (
    operation_id SERIAL PRIMARY KEY,
    source_account_id INT NOT NULL,
	target_account_id INT,
    create_by_user_id INT NOT NULL,
    transaction_type VARCHAR(50) NOT NULL CHECK (transaction_type IN ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transaction_amount DECIMAL(15, 2) NOT NULL,
	previous_balance DECIMAL(15, 2) NOT NULL,
	final_balance DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (source_account_id) REFERENCES accounts(account_id),
	FOREIGN KEY (target_account_id) REFERENCES accounts(account_id),
    FOREIGN KEY (create_by_user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

--CREATE OR REPLACE FUNCTION update_final_balance()
--RETURNS TRIGGER AS $$
--BEGIN
--   NEW.final_balance = NEW.previous_balance + NEW.transaction_amount;
--   RETURN NEW;
--END;
--$$ LANGUAGE plpgsql;


--CREATE TRIGGER before_insert_operations
--BEFORE INSERT ON operations
--FOR EACH ROW
--EXECUTE FUNCTION update_final_balance();

