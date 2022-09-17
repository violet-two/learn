package ws.com.generic;

import org.junit.Test;

import java.util.ArrayList;

public class GenericTest {
    public static void main(String[] args) {

    }

    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(78);
        list.add(12);
        list.add(90);
        list.add(1);
        //问题一:类型不安全
        list.add("Tom");
        for (Object score: list) {
            //问题二:强转时出现问题
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
        //问题一:类型不安全
        for (Object score: list) {
            //问题二:强转时出现问题
            int stuScore = (int) score;
            System.out.println(stuScore);
        }
    }
}
