CREATE TABLE refresh_tokens (
                                id          NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                token       VARCHAR2(255) NOT NULL UNIQUE,
                                user_id     NUMBER NOT NULL,
                                expires_at  TIMESTAMP NOT NULL,
                                revoked     BOOLEAN DEFAULT FALSE NOT NULL,
                                created_at  TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
                                CONSTRAINT fk_refresh_user FOREIGN KEY (user_id)
                                    REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_refresh_user ON refresh_tokens(user_id);