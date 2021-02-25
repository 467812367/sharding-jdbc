package com.cxytiandi.sharding.test;

/**
 * @author jiangwz
 * @create 2021/2/25.
 */
public class Test {

    public static void main(String[] args){
        // Actual SQL: ds1 ::: INSERT INTO user_1  (city, name, id) VALUES (?, ?, ?) ::: [深圳, 李四, 571703543312941057]
        // Actual SQL: ds0 ::: INSERT INTO user_1  (city, name, id) VALUES (?, ?, ?) ::: [深圳, 李四, 571703543191306240]
        // ds$->{id % 2}

    }
}
