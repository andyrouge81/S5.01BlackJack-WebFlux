CREATE TABLE IF NOT EXISTS players (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(255) NOT NULL,
    role VARCHAR(50),
    status VARCHAR(50),
    games_played INT DEFAULT 0,
    games_won INT DEFAULT 0
    );