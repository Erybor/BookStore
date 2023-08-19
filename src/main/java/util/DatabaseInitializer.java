package util;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.ConnectionManager;
import model.Author;
import model.Book;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInitializer {
    public static void initializeDatabaseFromJson(String filePath) {
        Connection conn = ConnectionManager.getConnection();
        BookDAO bookDAO = new BookDAO(conn);
        AuthorDAO authorDAO = new AuthorDAO(conn);

        // Parse the JSON array
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Parse the entire JSON data as a JSONArray
        JSONArray jsonArray = new JSONArray(new JSONTokener(bufferedReader));

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // Get book details from JSON
            String title = jsonObject.getString("title");
            String authorName = jsonObject.getString("author");
            String description = jsonObject.getString("description");
            double rating = jsonObject.getDouble("rating");
            int year = jsonObject.getInt("year");
            JSONArray genresJsonArray = jsonObject.getJSONArray("genres");
            String coverUrl = "book_covers/" + jsonObject.getString("cover_url");

            System.out.println("TITLE:" + title);
            System.out.println("Author:" + authorName);
            System.out.println("description:" + description);
            System.out.println("rating:" + rating);
            System.out.println("year:" + year);
            System.out.println("GENRESS:" + genresJsonArray.toString());
            System.out.println("coverURL:" + coverUrl.toString());

            List<String> genres = new ArrayList<>();
            for (int j = 0; j < genresJsonArray.length(); j++) {
                genres.add(genresJsonArray.getString(j));
            }

            Author author = authorDAO.getAuthor(authorName);
//            Author author = new Author(authorName, "None");
            int authorId;

            // TODO think of a better solution?

            if (author == null) {
                // If author doesn't exist, add the author to the database
                author = new Author(authorName, "");
                authorDAO.addAuthor(author);
                authorId = authorDAO.getAuthor(authorName).getId();
            } else {
                // If author exists, get their authorId
                authorId = author.getId();
            }

            Book book = new Book(title, authorName, authorId, description, rating, genres, year, coverUrl);
            bookDAO.addBook(book);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

