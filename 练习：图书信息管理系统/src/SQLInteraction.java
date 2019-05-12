import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * SQLOperating方法签名
 * 插入 void insert(Book book)
 * 删除 int delete(String... condition)
 * 查询 ResultSet select(String[] paramColumnNames,String... condition)
 *      ResultSet selects（）
 *
 * ConditionSetter静态方法签名
 * 获得列名 String getColumnName(int index)
 *      String[] getColumnNames(int...index)
 *      String[] getAllColumnNames()
 */
//有关数组，用sql列索引
public class SQLInteraction {

    //用来执行SQL语句
    public SQLOperating sqlOperating = new SQLOperating();
    //用来保存修改时，列索引与内容的映射关系（按顺序遍历）
    private Map<Integer,String> content = new LinkedHashMap<>();
    private Scanner sc = null;
    private BufferedReader br = null;
    private String brLine = "";

    public SQLInteraction(Scanner sc, BufferedReader br) {
        this.sc = sc;
        this.br = br;
    }

    public void init() throws Exception {
        System.out.println("====输入要进行的操作：\n" +
                "==insert：插入；\n" +
                "==delete：删除；\n" +
                "==select：查询；\n" +
                "==update：修改；\n" +
                "==exit：退出");
        String scLine = "";
        while ((scLine = sc.nextLine()) != null) {
            switch (scLine.trim()) {
                case "insert":
                    insert();
                    break;
                case "delete":
                    delete();
                    break;
                case "select":
                    select();
                    break;
                case "update":
                    update();
                    break;
                case "exit":
                    exit();
                    break;
            }
        }
    }

    //插入
    private void insert() throws Exception {
        System.out.println("====请按照如下格式顺序输入构造参数（英文）：\n" +
                "==书名,价格,作者,出版社,封面图片路径");
        //设置Book参数的数组
        String[] parmas = new String[5];
        while ((brLine = br.readLine()) != null) {
            //分隔参数之后的数组
            String[] brParams = brLine.split(",");
            System.arraycopy(brParams, 0, parmas, 0, brParams.length);
            try {
                //得到主键列，查看有无重复，重复的话更新图书信息，并且库存 + 1
                ResultSet priAndQuaSet = sqlOperating.select(ConditionSetter
                        .getColumnNames(new int[]{1,6}));
                while (priAndQuaSet.next()){
                    if (parmas[0].equals(priAndQuaSet.getString(1))){
                        //加入Map，i为列索引
                        for (int i=2;i<brParams.length + 1;i++){
                            content.put(i,parmas[i-1]);
                        }
                        Integer quantity = priAndQuaSet.getInt(2) + 1;
                        content.put(6,quantity.toString());
                        //更新图书信息
                        sqlOperating.update(content,
                                "name = " + "'" + parmas[0] + "'");
                        //抛出异常，阻止插入
                        throw new SQLIntegrityConstraintViolationException("主键列重复！");
                    };
                }
                if (parmas[1] != null) {
                    int price = Integer.parseInt(parmas[1]);
                    Book book = new Book(parmas[0], price, parmas[2], parmas[3], parmas[4]);
                    sqlOperating.insert(book);
                    System.out.println("==插入成功！");
                } else {
                    Book book = new Book(parmas[0]);
                    sqlOperating.insert(book);
                    System.out.println("==插入成功！");
                }
                //这个break代表成功就会跳出，异常就继续循环
                break ;
            }
            //主键列重复异常
            catch (SQLIntegrityConstraintViolationException sqlicve){
                System.out.println("====已有该书存在，插入成功！");
            }
            catch (NumberFormatException nfe) {
                System.out.println("====价格参数异常，请重新输入！");
            }
            catch (SQLSyntaxErrorException ssee){
                System.out.println("====参数异常，请重新输入！");
            }
            break ;
        }
    }

    //删除
    private void delete() throws Exception {
        System.out.println("====请输入限制条件（多个条件以,分隔）：\n" +
                "==无条件删除：no condition");
        while ((brLine = br.readLine()) != null) {
            try {
                if (brLine.trim().equals("no condition")) {
                    System.out.println("==删除成功！影响记录条数：" + sqlOperating.delete());
                } else {
                    //将,替换为and
                    System.out.println(brLine.replace(",", " and "));
                    System.out.println("==删除成功！影响记录条数：" +
                            sqlOperating.delete(brLine.replace(",", " and ")));
                }
                break;
            } catch (SQLSyntaxErrorException SQLsee) {
                System.out.println("==条件参数异常，请重新输入！");
            }
        }
    }

    //查询
    private void select() throws Exception {
        System.out.println("====请输入列索引【1-5】（多个索引以,分隔）\n" +
                "==全表查询：selects");
        //标签
        sign:
        while ((brLine = br.readLine()) != null) {
            try {
                //内层循环读取
                String brLine1 = "";
                //全表查询
                if (brLine.trim().equals("selects")) {
                    System.out.println("==请输入查询条件（多个条件以,分隔）\n" +
                            "==无条件查询：no condition");
                    while ((brLine1 = br.readLine()) != null) {
                        try {
                            //无条件查询
                            if (brLine1.trim().equals("no condition")) {
                                sqlOperating.printResultSet(sqlOperating.selects());
                            }
                            //条件查询
                            else if (brLine1.split(",").length >= 0) {
                                sqlOperating.printResultSet(sqlOperating.selects(
                                        brLine1.replace(",", " and ")));
                            }
                            break sign;
                        } catch (SQLSyntaxErrorException sqlE) {
                            System.out.println("==条件参数异常，请重新输入！");
                        }
                    }
                }
                //索引查询
                else {
                    try {
                        System.out.println("==请输入查询条件（多个条件以,分隔）\n" +
                                "==无条件查询：no condition");
                        int[] index = new int[brLine.split(",").length];
                        for (int i = 0; i < brLine.split(",").length; i++) {
                            index[i] = Integer.parseInt(brLine.split(",")[i]);
                        }
                        while ((brLine1 = br.readLine()) != null) {
                            //无条件查询
                            if (brLine1.trim().equals("no condition")) {
                                sqlOperating.printResultSet(sqlOperating.select(
                                        ConditionSetter.getColumnNames(index)));
                            }
                            //条件查询
                            else {
                                sqlOperating.printResultSet(sqlOperating.select(
                                        ConditionSetter.getColumnNames(index),
                                        brLine1.replace(",", " and ")));
                            }
                            break sign;
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("==索引参数异常，请重新输入！");
                    }
                }
            } catch (SQLSyntaxErrorException sqlE) {
                System.out.println("====条件参数异常，请重新输入索引！");
            }
        }
    }

    //修改
    private void update() throws Exception {
        System.out.println("====请输入要修改的列索引和内容（多对之间以,隔开）\n" +
                "==格式：key1=value1,key2=value2 ……");
        //标签
        sign:
        while ((brLine = br.readLine()) != null) {
            try {
                //清空原来的格式
                content.clear();
                //加入Map
                for (String s : brLine.split(",")) {
                    content.put(Integer.parseInt(s.split("=")[0]),
                            s.split("=")[1]);
                }
                System.out.println("==请输入修改列条件（多个条件以,分隔）\n" +
                        "==无条件修改：no condition");
                String brLine1 = "";
                while ((brLine1 = br.readLine()) != null) {
                    try {
                        if (brLine1.trim().equals("no condition")) {
                            int affectedNum = sqlOperating.update(content);
                            System.out.println(affectedNum > 0 ? "==修改成功！影响记录条数：" + affectedNum:
                                    "==修改失败，数据表为空！");

                        } else {
                            int affectedNum = sqlOperating.update(content,
                                    brLine1.replace(",", " and "));
                            System.out.println(affectedNum > 0 ? "==修改成功！影响记录条数：" + affectedNum:
                                    "==修改失败，没有记录满足条件！");
                        }
                        break sign;
                    } catch (SQLSyntaxErrorException sqlE) {
                        System.out.println("==条件参数异常，请重新输入！");
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("====格式参数异常，请重新输入！");
            }
        }
    }

    //退出
    private void exit() throws IOException {
        sc.close();
        br.close();
        System.exit(0);
    }

}