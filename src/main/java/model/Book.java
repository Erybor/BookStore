package model;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private String author;
    private int authorId;
    private String description;
    private double rating;
    private List<String> genres;
    private int year;


    // Constructor without AuthorId

//    public Book(String title, String author, String description, double rating, List<String> genres, int year) {
//        this.title = title;
//        this.author = author;
//        this.description = description;
//        this.rating = rating;
//        this.genres = genres;
//        this.year = year;
//    }

    // Constructor with AuthorId
    public Book(String title, String author, int authorId, String description, double rating, List<String> genres, int year) {
        this.title = title;
        this.author = author;
        this.authorId = authorId;
        this.description = description;
        this.rating = rating;
        this.genres = genres;
        this.year = year;
    }

    public Book(int id, String title, String author, int authorId, String description, double rating, List<String> genres, int year) {
        this(title, author, authorId, description, rating, genres, year);
        this.id = id;
    }

//    public Book(int id, String title, String author, String description, double rating, List<String> genres, int year) {
//        this(title, author, description, rating, genres, year);
//        this.id = id;
//    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Book) obj).getId();
    }
}
