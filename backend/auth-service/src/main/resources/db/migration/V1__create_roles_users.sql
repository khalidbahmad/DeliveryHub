CREATE TABLE roles (
                       id    NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name  VARCHAR2(30) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id            NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       email         VARCHAR2(150) NOT NULL UNIQUE,
                       password_hash VARCHAR2(100) NOT NULL,
                       full_name     VARCHAR2(100),
                       role_id       NUMBER NOT NULL,
                       enabled       BOOLEAN DEFAULT TRUE NOT NULL,
                       created_at    TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
                       CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE INDEX idx_users_role ON users(role_id);