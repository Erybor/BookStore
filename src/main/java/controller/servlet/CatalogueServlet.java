package controller.servlet;

import dao.BookDAO;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;


@WebServlet(name = "CatalogueServlet", urlPatterns = "/catalogue")
public class CatalogueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");
        List<String> categories = bookDAO.getAllCategories();
        HashMap<String, List<Book>> booksByCategories = new HashMap<>();


        System.out.println("CATEGORIES:" + categories);
        // Iterate through the categories and retrieve corresponding books for each category
        for (String category : categories) {
            List<Book> books = bookDAO.getBooksByCategory(category);
            if (books.size() > 5) {
                booksByCategories.put(category, books.subList(0, 5));
            } else {
                booksByCategories.put(category, books);
            }
        }

        LinkedHashMap<String, List<Book>> descByValues = new LinkedHashMap<>();

        booksByCategories.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size())
                .forEachOrdered(x -> descByValues.put(x.getKey(), x.getValue()));

//        descByValues
//                .forEach((k, v) -> System.out.println(k + ", " + v));

        LinkedHashMap<String, List<Book>> filteredBooks = new LinkedHashMap<>();

        int i = 0;

        for (String cat : descByValues.keySet()) {
//            System.out.println("CATEGORYYY:" + cat);
            if (i++ == 4) {
                break;
            }
            filteredBooks.put(cat, descByValues.get(cat));
        }

        List<String> genres = bookDAO.getAllCategories();

        String str = String.join(",", genres);

        req.setAttribute("categories", filteredBooks);
        req.setAttribute("genreListInput", str);

        // Forward the request to the JSP for rendering
        req.getRequestDispatcher("catalogue.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
