package com.gbl;

import com.gbl.pool.PooledObjectState;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.AntPathMatcher;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        String positionIds = "a-b-c-d-";
        String[] elementIds = positionIds.split("-");
        System.out.println();
    }

    @Test
    public void test() {
//        String str = "aaa,bbb,ccc,";
//
//        str = str.replaceAll(",$", "");
//
//        System.out.println(str);

//        Date date = new Date(1575619200000L);
//        System.out.println(date);

//        Integer way = null;
//        boolean b = Integer.valueOf(1).equals(way);
//        System.out.println(b);
    }

    @Test
    public void test2() {
        String rul = "/a/b/c,/aa/bb/cc,/111/333/[0-9]*$";
        String url = "/111/333/222/22";

//        for (String u : rul.split(",")) {
//            boolean bb = Pattern.compile(u).matcher(url).find();
//            System.out.println(bb);
//        }

        boolean b = Arrays.stream(rul.split(",")).anyMatch(u -> Pattern.compile(u).matcher(url).find());

        System.out.println(b);

        @NotNull String name = "";

        Map.@NotNull Entry entry = null;
    }

    @Test
    public void test3() {
        PooledObjectState pooledObjectState = PooledObjectState.ABANDONED;
        getState(pooledObjectState);
        System.out.println(pooledObjectState);
    }

    public void getState(PooledObjectState pooledObjectState) {
        pooledObjectState = PooledObjectState.IDLE;
        System.out.println("IDLE");
    }

    @Test
    public void test4() throws IOException {
        String location = "classpath*:mapper/*.xml";
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(location);
        Arrays.stream(resources).map(Resource::getFilename).forEach(System.out::println);
    }

    @Test
    public void test5() {
        String pattern = "erie/sdfsdf/s/dfsdf/sdf/sdf,/sdf/sdf/sd/fsd/d,/aaa/**/bb/*";
        String path = "/aaa/bbb/cc/bb/confId";

        boolean b = Arrays.stream(pattern.split(",")).noneMatch(p -> new AntPathMatcher().match(p, path));
        System.out.println(b);

    }

    @Test
    public void test6() {
        String url = "/clife-openapi-business/data/push/getList";

        url = url.substring("/clife-openapi-business".length());

        System.out.println(url);

    }
}
