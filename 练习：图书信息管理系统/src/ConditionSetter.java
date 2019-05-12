//参数设置工具类
public class ConditionSetter {

    //该数据表的列名
    public static String[] columnNames = {
            "name",
            "price",
            "author",
            "publish",
            "cover_img",
            "quantity"
    };

    public static String getColumnName(int index){
        return columnNames[index];
    }
    /**
        //根据索引[1-5]返回多列名称（形参可变）
        public static String[] getColumnNames(int... index){
            String[] strings = new String[index.length];
            for (int i=0;i<index.length;i++){
                strings[i] = columnNames[index[i] - 1];
            }
            return strings;
        }
     */
    //根据index数组获取列名数组
    public static String[] getColumnNames(int[] index){
        String[] strings = new String[index.length];
        for (int i=0;i<index.length;i++){
            strings[i] = columnNames[index[i] - 1];
        }
        return strings;
    }
    public static String[] getAllColumnNames(){
        return columnNames;
    }

} 