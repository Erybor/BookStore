-- Select the database

CREATE SCHEMA IF NOT EXISTS BookStore;
USE BookStore;

-- Drop tables if they exist
DROP TABLE IF EXISTS recommendations;
DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS book_genres;
DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS follows;
DROP TABLE IF EXISTS user_posts;
DROP TABLE IF EXISTS users;

-- Create User Table
CREATE TABLE users
(
    user_id         INT PRIMARY KEY AUTO_INCREMENT,
    username        VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    name            VARCHAR(255),
    last_name       VARCHAR(255),
    gender          VARCHAR(10),
    profile_picture VARCHAR(255)
);

-- Create Authors Table
CREATE TABLE authors
(
    author_id   INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(255) NOT NULL,
    author_info TEXT
    -- Additional author information columns
);

-- Create Book Table
CREATE TABLE books
(
    book_id     INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    author      VARCHAR(255) NOT NULL,
    author_id   INT          NOT NULL,
    description TEXT,
    rating      DECIMAL(3, 2),
    year        INT,
    cover_url   VARCHAR(255),
    FOREIGN KEY (author_id) REFERENCES authors (author_id)
# bug
#     FOREIGN KEY (author) REFERENCES authors(author_name)
    -- Additional book information columns
);

-- Create Book Genres Table
CREATE TABLE book_genres
(
    book_id INT,
    genre   VARCHAR(255)
);


-- Create Favorite Table
CREATE TABLE favorites
(
    favorite_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id     INT,
    book_id     INT,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (book_id) REFERENCES books (book_id)
);

-- Create Recommendation Table
CREATE TABLE recommendations
(
    recommendation_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id           INT,
    book_id           INT,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (book_id) REFERENCES books (book_id)
);

CREATE TABLE follows
(
    follower_id  INT NOT NULL,
    following_id INT NOT NULL,
    PRIMARY KEY (follower_id, following_id),
    FOREIGN KEY (follower_id) REFERENCES users (user_id),
    FOREIGN KEY (following_id) REFERENCES users (user_id)
);


CREATE TABLE reviews
(
    review_id    INT PRIMARY KEY AUTO_INCREMENT,
    book_id      INT,
    user_id      INT,
    rating_value INT,
    review_text  TEXT,
    FOREIGN KEY (book_id) REFERENCES books (book_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE user_posts
(
    post_id      INT PRIMARY KEY AUTO_INCREMENT,
    user_id      INT,
    content      TEXT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
