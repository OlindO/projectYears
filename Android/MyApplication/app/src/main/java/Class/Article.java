package Class;

/**
 * Created by yanis on 20/02/2017.
 */

public class Article {
    private int id;
    private String name;
    private String description;

    public Article() {
    }

    public Article(int id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString ()
    {
        return "id : " + id + "Description :" + description + "Name : " + name;
    }

}
