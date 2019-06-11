package com.gbl.service;

import com.gbl.api.service.PersonService;
import com.gbl.stereotype.Service;

@Service(value = "traceService")
public class PersonServiceImpl implements PersonService {
    @Override
    public String getAuthor() {
        return "guobaolin";
    }
}

