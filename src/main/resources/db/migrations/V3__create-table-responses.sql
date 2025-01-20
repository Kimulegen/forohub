CREATE TABLE responses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    creationDate DATETIME NOT NULL,
    solution TEXT NOT NULL,
    author BIGINT,
    topic BIGINT,
    active BOOLEAN NOT NULL,
    FOREIGN KEY (author) REFERENCES users(id),
    FOREIGN KEY (topic) REFERENCES topics(id)
);