package database;

public class Book extends ValueObject {
    //名称，简介，加个，种类W，出版社W，图片url，作者，库存
    private String book_name;
    private String book_intro;
    private String book_price;
    private String type_id_fk;
    private String pub_id_fk;
    private String image_url;
    private String author;
    private String repertory_size;
    //外键引用
    private Type type;
    private Publisher publisher;

    public Book(){};
    public Book(String book_name,String book_intro,String book_price,
                Type type,Publisher publisher,String image_url,String author){
        this.book_name = book_name;
        this.book_intro = book_intro;
        this.book_price = book_price;
        this.type = type;
        this.publisher = publisher;      //
        this.image_url = image_url;     //
        this.author = author;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_intro() {
        return book_intro;
    }

    public void setBook_intro(String book_intro) {
        this.book_intro = book_intro;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

    public String getType_id_fk() {
        return type_id_fk;
    }

    public void setType_id_fk(String type_id_fk) {
        this.type_id_fk = type_id_fk;
    }

    public String getPub_id_fk() {
        return pub_id_fk;
    }

    public void setPub_id_fk(String pub_id_fk) {
        this.pub_id_fk = pub_id_fk;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRepertory_size() {
        return repertory_size;
    }

    public void setRepertory_size(String repertory_size) {
        this.repertory_size = repertory_size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
