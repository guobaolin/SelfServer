package com.gbl.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gbl.api.service.PersonService;
import com.gbl.api.service.TraceService;
import com.gbl.stereotype.Service;

@Service(value = "traceService")
public class TraceServiceImpl implements TraceService {

    @Reference
    private PersonService personService;

    @Override
    public String getTrace() {

        String author = personService.getAuthor();

        return "hello weChat, can we chat! author ==> " + author;
    }
}

