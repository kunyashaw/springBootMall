package com.zzl.demo01.service.impl;

import com.zzl.demo01.mbg.mapper.PmsBrandMapper;
import com.zzl.demo01.mbg.model.PmsBrand;
import com.zzl.demo01.mbg.model.PmsBrandExample;
import com.zzl.demo01.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private PmsBrandMapper mapper;

    @Override
    public PmsBrand getBrand(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> getAllList() {
        return mapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int deleteBrand(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
