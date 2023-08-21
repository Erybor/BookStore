package model;

public class Author {

    private String info;
    private String name;

    private int id;

    public Author() { }
    public Author(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public Author(int id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Author{" +
                "info='" + info + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
