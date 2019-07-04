package main;

import dao.*;
import service.*;

/**
 * 实体类构造器签名
 * public Publisher(String pub_name,String pub_tel,
 *                      String pub_link_man,String pub_intro)
 * public Type(String type_name,String type_intro)
 * public Book(String book_name,String book_intro,String book_price,
 *                 String type_name,String pub_name,String image_url,String author)
 * public SaleRecord(String recordDate)
 * public BookSaleRecord(Book book,SaleRecord saleRecord,String trade_sum)
 */
//表现层测试
public class Main {

    //表现层有业务层的接口
    private PublisherService publisherService;
    private BookService bookService;
    private SaleRecordService saleRecordService;
    public Main(PublisherService publisherService,
                BookService bookService,SaleRecordService saleRecordService
                ) throws Exception
    {
        this.publisherService = publisherService;
        this.bookService = bookService;
        this.saleRecordService = saleRecordService;
    }

    //表现层
    public static void main(String[] args) throws Exception {

        //传入实现类
        Main m = new Main(new PublisherServiceImpl(new PublisherDaoImpl()),
                new BookServiceImpl(new BookDaoImpl(),new TypeDaoImpl(),new PublisherDaoImpl()),
                new SaleRecordServiceImpl(new SaleRecordDaoImpl(),new BookSaleRecordDaoImpl()));


        /**
        //出版社测试
        System.out.println("====================出版社测试====================");
        for (Publisher v : m.publisherService.getAll()){
            System.out.println(v.getPub_name());
            System.out.println(v.getPub_tel());
        }
        System.out.println(m.publisherService.findById("2").getPub_name());
        for (Publisher v : m.publisherService.findByName("电子工业出版社")){
            System.out.println(v.getPub_name());
            System.out.println(v.getPub_tel());
        }
        /**
            m.service.add(new Publisher("中北出版社","152",
                    "蔡帅","中北大学专属出版社"));
         */
        /**
        m.publisherService.addOrUpdate(new Publisher("中北大学出版社","152",
                "学生","中北大学专属出版社"));

        //书测试
        System.out.println("====================书测试=====================");
        for (Book b : m.bookService.getAll()){
            System.out.println(b.getBook_name());
            System.out.println(b.getType().getType_name());
        }
        for (Book b : m.bookService.findByName("悲惨世界")){
            System.out.println(b.getBook_name());
            System.out.println(b.getType().getType_name());
        }
        Book b = m.bookService.findById("1");
        System.out.println(b.getBook_name());
        System.out.println(b.getType().getType_name());
         */
/**
            Book insert = new Book("新增书2","新增的书本","20",
                    new Type("新种类","新的种类"),
                    new Publisher("新出版社","155","联系人","简介"),
                    ".\\out\\网易云看板娘.jpg","蔡帅");
            m.bookService.addOrUpdate(insert);
            System.out.println(insert.getType_id_fk() + " " + insert.getPub_id_fk());
 */

        /**
        //种类测试
        System.out.println("======================种类测试======================");
        for (Type t : m.bookService.getAllType()){
            System.out.println(t.getType_name());
            System.out.println(t.getType_intro());
        }
        for (Book bb : m.bookService.findByType("科学")){
            System.out.println(bb.getBook_name());
            System.out.println(bb.getType().getType_name());
        }
         */

        /**
         * 销售记录测试
         * 先得到销售记录时间，再到具体的销售记录
         */
        /**
        System.out.println("======================销售记录测试======================");
        for (SaleRecord s : m.saleRecordService.getAll()){
            System.out.println(s.getRecord_date());
            System.out.println(s.getBookNames()[0]);
            System.out.println(s.getTotalPrice());
            System.out.println(s.getAmount());
            for (BookSaleRecord b : m.saleRecordService.findBySaleRecordId(s.getId())){
                System.out.println(b.getTrade_sum());
                break;
            }
            break;
        }
        for (SaleRecord s : m.saleRecordService.findByDate("2019-04-02")){
            System.out.println(s.getRecord_date());
            System.out.println(s.getBookNames()[0]);
            System.out.println(s.getTotalPrice());
            System.out.println(s.getAmount());
        }
         */
/**
        BookSaleRecord bsr = m.saleRecordService.addOrUpdate(new BookSaleRecord(
                new Book("新增书100","新增的书本","20",
                        "新增种类100","新增出版社100",
                        ".\\out\\网易云看板娘.jpg","蔡帅"),
                new SaleRecord("2019-04-02 21:00:57"),
                "24"));
        System.out.println(bsr.getBook_id_fk());
 */
    }
}
