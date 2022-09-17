package ws.com.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GenericTest {

    /**
     * 泛型的使用
     * 1.jdk 5.0新增的特性
     * 2.在集合中使用泛型
     *   总结：
     *   ① 集合接口或者集合类在jdk5.0时都修改带泛型的特性
     *   ②在实例化集合时，可以指明具体的泛型类型
     *   ③指明完以后，在集合类或者接口中凡是定义类或者接口时，内部结构使用到类的泛型的位置都可以指定实例化泛型类型
     *      比如：add(E e) --->实例化以后：add(Integer e)
     *   ④注意点:泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置,那包装类替换
     *   ⑤如果实例化时，没有指明泛型的类型。默认使用Object类型
     */

    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(78);
        list.add(12);
        list.add(90);
        list.add(1);
        //问题一类型不安全
//        list.add("Tom");
        for (Object score: list) {
            int stuScore = (int) score;
            System.out.println(stuScore);
        }
    }
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList();
        list.add(78);
        list.add(12);
        list.add(90);
        list.add(1);
        //编译时，直接进行类型转换
        for (Integer score: list) {
            //问题二:强转时出现问题
            int stuScore = score;
            System.out.println(stuScore);
        }
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            int stuScore = iterator.next();
            System.out.println(stuScore);
        }
    }

    @Test
    public void test3(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("001",1);
        map.put("002",2);
        map.put("003",3);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key+""+value);
        }
    }
}
