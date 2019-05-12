/**
 * 图片的插入与修改………………………………
 * 异常处理的责任链模式………………………………
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        SQLInteraction sqlInteraction = new SQLInteraction(new Scanner(System.in),
                new BufferedReader(new InputStreamReader(System.in)));
        sqlInteraction.sqlOperating.initParam("mysql.ini");
        sqlInteraction.init();
    }

}


/**
 * 测试样例
 *
    //插入图片IO错误 "D:\\project\\练习：图书信息管理系统\\网易云看板娘.jpg"………………
    Book book1 = new Book("疯狂Java讲义",40,
            "李刚","大学出版社");
    Book book2 = new Book("疯狂！",30,"李刚");

    System.out.println(sqlOperating.delete());
    sqlOperating.insert(book1);
    sqlOperating.insert(book2);

    content.put(ConditionSetter.getColumnName(2),25);
    sqlOperating.update(m.content);
    //查询1,2,4列
    sqlOperating.printResultSet(sqlOperating.select(ConditionSetter.
            getColumnNames(1,2,4)));



 public void select() throws Exception {
 System.out.println("====请输入查询条件（多个条件以,分隔）\n" +
 "==无条件查询：no condition");
 while ((brLine = br.readLine()) != null) {
 try {
 //无条件查询
 if (brLine.trim().equals("no condition")) {
 System.out.println("==请输入列索引【1-5】（多个索引以,分隔）\n" +
 "==全表查询：selects");
 while ((brLine = br.readLine()) != null) {
 try {
 //全表查询
 if (brLine.trim().equals("selects")) {
 sqlOperating.printResultSet(sqlOperating.selects());
 }
 //索引查询
 else if (brLine.split(",").length >= 0) {
 int[] index = new int[brLine.split(",").length];
 for (int i = 0; i < brLine.split(",").length; i++) {
 index[i] = Integer.parseInt(brLine.split(",")[i]);
 }
 sqlOperating.printResultSet(sqlOperating.select(
 ConditionSetter.getColumnNames(index)));
 break;
 }
 } catch (IndexOutOfBoundsException iobe) {
 System.out.println("==索引异常，请重新输入！");
 }
 }
 }
 //条件查询………………………………
 else{

 }
 } catch (SQLSyntaxErrorException SQLsee) {
 System.out.println("==参数异常，请重新输入！");
 }
 break;
 }
 }
 */


