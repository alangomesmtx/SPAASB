package org.prth.service;

import org.prth.model.SPAA;

import java.util.List;

public interface SpaaService {
    SPAA getSpaaById(Long id);
    List<SPAA> getAllSpaa();
    SPAA addSpaa(SPAA SPAA);
    SPAA updateSpaa(Long id, SPAA SPAA);
    boolean deleteSpaa(Long id);
    List<SPAA> getSpaaByTag(String city);
}
