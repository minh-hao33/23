package com.example.hrms.biz.sample.service;

import com.example.hrms.biz.sample.model.Sample;
import com.example.hrms.biz.sample.model.criteria.SampleCriteria;
import com.example.hrms.biz.sample.model.dto.SampleDTO;
import com.example.hrms.biz.sample.repository.SampleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleService {

    private final SampleMapper mapper;

    public SampleService(SampleMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Count sample
     * @param criteria Input criteria
     * @return total of sample
     */
    public int count(SampleCriteria criteria) {
        return mapper.count(criteria);
    }

    /**
     * List sample
     * @param criteria Input criteria
     * @return List of sample
     */
    public List<SampleDTO.Resp> list(SampleCriteria criteria) {
        List<Sample> samples =  mapper.select(criteria);
        return samples.stream().map(SampleDTO.Resp::toResponse).toList();
    }

    /**
     * Create new sample
     * @param req Sample request
     */
    public void insert(SampleDTO.Req req) {
        Sample sample =req.toSample();

        /* Example for use exception
        Sample result = mapper.selectById(sample.getId());
        if (ObjectUtils.isNotEmpty(result)) {
            throw new InvalidArgumentException("Duplicated sample ID");
        }
         */

        mapper.insert(sample);
    }
}
