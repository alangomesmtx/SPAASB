package org.prth.service;

import org.prth.dao.SPAADao;
import org.prth.model.SPAA;

import java.util.List;

public class SpaaServiceImpl implements SpaaService {

    private final SPAADao SPAADao;

    public SpaaServiceImpl(SPAADao SPAADao) {
        this.SPAADao = SPAADao;
    }

    @Override
    public SPAA getSpaaById(Long id) {
        return SPAADao.getSpaaById(id);
    }

    @Override
    public List<SPAA> getAllSpaa() {
        return SPAADao.getAllSpaa();
    }

    @Override
    public SPAA addSpaa(SPAA SPAA) {
        return SPAADao.addSpaa(SPAA);
    }

    @Override
    public SPAA updateSpaa(Long id, SPAA SPAA) {
        return SPAADao.updateSpaa(id, SPAA);
    }

    @Override
    public boolean deleteSpaa(Long id) {
        return SPAADao.deleteSpaa(id);
    }

    @Override
    public  List<SPAA> getSpaaByTag(String tag){
        return SPAADao.getSpaaByTag(tag);
    }
}
