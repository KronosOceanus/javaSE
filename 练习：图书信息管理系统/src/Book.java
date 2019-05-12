//包含图书信息
public class Book {

    private String name;
    private int price;
    private String author;
    private String publish;
    private String filePath;

    //构造器重载
    private Book() {};

    public Book(String name) {
        this();
        this.name = name;
    }

    public Book(String name, int price) {
        this(name);
        this.price = price;
    }

    public Book(String name, int price, String author) {
        this(name, price);
        this.author = author;
    }

    public Book(String name, int price, String author, String publish) {
        this(name, price, author);
        this.publish = publish;
    }

    public Book(String name, int price, String author, String publish, String filePath) {
        this(name, price, author, publish);
        this.filePath = filePath;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublish() {
        return publish;
    }

    public String getFilePath() {
        return filePath;
    }

}
