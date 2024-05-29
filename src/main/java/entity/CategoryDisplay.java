package entity;

public class CategoryDisplay {

    private long id;
    private String name;
    private String description;

    public CategoryDisplay(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

//    public CategoryDisplay(long id) {
//        this.id = id;
//    }

    @Override
    public String toString() {
        return "CategoryDisplay{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
