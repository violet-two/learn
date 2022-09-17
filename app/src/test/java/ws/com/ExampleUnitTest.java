package ws.com;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(78);
        list.add(12);
        list.add(90);
        list.add(1);
        //问题一类型不安全
        list.add("Tom");
        for (Object score: list) {
            int stuScore = (int) score;
            System.out.println(stuScore);
        }
    }
}