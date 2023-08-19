import util.DatabaseInitializer;


public class WebAppMain {
    public static void main(String[] args) {
        String jsonPath = "src/main/resources/books.json";
        DatabaseInitializer.initializeDatabaseFromJson(jsonPath);

    }
}

