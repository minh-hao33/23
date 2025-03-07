package com.example.hrms.biz.sample.repository;

import com.example.hrms.biz.sample.model.Sample;
import com.example.hrms.biz.sample.model.criteria.SampleCriteria;

import java.util.List;

//@Mapper
public interface SampleMapper {

    void insert(Sample sample);

    Sample selectById(long id);

    int count(SampleCriteria criteria);

    List<Sample> select(SampleCriteria criteria);

}