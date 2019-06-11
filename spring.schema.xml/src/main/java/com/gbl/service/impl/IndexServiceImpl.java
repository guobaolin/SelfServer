package com.gbl.service.impl;

import com.gbl.entity.Student;
import com.gbl.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guobaolin on 2019/2/23.
 */
@Service("indexService")
public class IndexServiceImpl implements IndexService {

    @Autowired
    private Student student;

    @Override
    public String getIndex(String name) {
        return "hello " + name + ", " + student.toString();
    }
}
