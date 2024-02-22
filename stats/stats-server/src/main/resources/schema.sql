DROP TABLE IF EXISTS stats CASCADE;
CREATE TABLE IF NOT EXISTS stats (
id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
app VARCHAR(125) NOT NULL,
uri VARCHAR(125) NOT NULL,
ip VARCHAR(125) NOT NULL,
timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL
);