import org.junit.Test;

import java.util.*;

/**
 * Created by guobaolin on 2019/8/30.
 */
public class AtomicReferenceFieldUpdaterTest {

    @Test
    public void test() {
        TableCache tableCache = new TableCache();
//        AtomicReferenceFieldUpdater fieldUpdater = AtomicReferenceFieldUpdater.newUpdater();


    }

    @Test
    public void test1() {
        List<String> strings = Collections.synchronizedList(new ArrayList<>());
        strings.add("guo");
        strings.add("bao");
        strings.add("lin");
        strings.add("are");
        strings.add("you");
        strings.add("ok");
        strings.add("!");
        System.out.println(strings);

        List<String> tempList = strings.subList(0, 2);
        System.out.println(tempList);
        tempList.clear();
        System.out.println(strings);
    }

    @Test
    public void test3() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("1111");
        });
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        thread.interrupted();
        System.out.println(thread.getState());

    }


    class TableCache {
        volatile int total = 0;
        volatile Map<String /* taableName */, List<String>> tables = new HashMap<>();
    }
}
