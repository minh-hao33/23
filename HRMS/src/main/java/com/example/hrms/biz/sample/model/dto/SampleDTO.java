package com.example.hrms.biz.sample.model.dto;

import com.example.hrms.biz.sample.model.Sample;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

public class SampleDTO {

    @Getter
    @Setter
    public static class Req {

        private Long id;

        private String name;

        private String sensitiveData;

        public Sample toSample() {
            Sample sample = new Sample();
            BeanUtils.copyProperties(this, sample);
            return sample;
        }

    }

    @Getter
    @Setter
    public static class Resp {

        private Long id;

        private String name;

        public static Resp toResponse(Sample sample) {
            Resp resp = new Resp();
            BeanUtils.copyProperties(sample, resp);
            return resp;
        }

    }


}
