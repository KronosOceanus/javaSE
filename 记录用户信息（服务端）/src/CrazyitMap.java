import java.util.*;

/**
 * 保存用户名和对应输出流之间的关系
 * 集合类应该有删除，获取，查询，插入等操作
 * 要求value不可重复(K,V分别为String,PrintStream)
 */

public class CrazyitMap<K,V> {

    public Map<K,V> map = Collections.synchronizedMap(new HashMap<>());
    //根据value删除指定项目
    public synchronized void removeByValue(Object value){
        for(Object key : map.keySet()){
            if(map.get(key) == value){
                map.remove(key);
                break;
            }
        }
    }
    //获取所有value
    public synchronized Set<V> valueSet(){
        Set<V> result = new HashSet<>();
        map.forEach(((k, v) -> result.add(v)));
        return result;
    }
    //根据value查找key
    public synchronized K getKeyByValue(V val){
        for(K key : map.keySet()){
            if(map.get(key) == val || map.get(key).equals(val)){
                return key;
            }
        }
        return null;
    }
    //put不允许value重复
    public synchronized V put(K key,V value){
        for(V val : valueSet())
        {
            if(val.equals(value) && val.hashCode() == value.hashCode()){
                throw new RuntimeException("MyMap实例中不允许有重复value！");
            };
        }
        return map.put(key,value);
    }
}
