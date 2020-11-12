package com.zzl.demo01.service;

import com.zzl.demo01.mbg.model.PmsBrand;

import java.util.List;

public interface BrandService {
    List<PmsBrand> getAllList();
    PmsBrand getBrand(Long id);
    int deleteBrand(Long id);
}
