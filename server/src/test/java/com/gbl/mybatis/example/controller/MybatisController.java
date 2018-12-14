package com.gbl.mybatis.example.controller;

import com.gbl.mybatis.example.entity.NewString;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by guobaolin on 2018/11/5.
 */
public class MybatisController {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            NewString dataTypeName = sqlSession.selectOne("com.gbl.mybatis.example.dao.DataTypeMapper.selectById", 7);
            System.out.println(dataTypeName);
        }finally {
            sqlSession.close();
        }
    }
}
