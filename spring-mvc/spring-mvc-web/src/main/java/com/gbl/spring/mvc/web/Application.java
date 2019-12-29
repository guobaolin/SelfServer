package com.gbl.spring.mvc.web;

import java.util.Arrays;

/**
 * Created by guobaolin on 2019/11/18.
 */
public class Application {

    public static void main(String[] args) {
        String positionIds = "AA-BB-CC-";
        String[] positionIdArray = positionIds.split("-");
        Arrays.stream(positionIdArray).forEach(System.out::println);
    }
}
