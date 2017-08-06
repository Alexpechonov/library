package com.library.core.mvc.service.search;

import com.library.dto.search.SearchDTO;

import java.util.List;

/**
 * Created by user on 04.08.2017.
 */
public interface SearchService {
    List<SearchDTO> search(String string);
}
