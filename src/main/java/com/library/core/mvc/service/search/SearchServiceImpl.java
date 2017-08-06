package com.library.core.mvc.service.search;

import com.library.dto.search.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 04.08.2017.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchFacade facade;

    @Override
    public List<SearchDTO> search(String string) {
        return facade.search(string);
    }
}
