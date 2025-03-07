package com.example.hrms.biz.sample.controller.rest;

import com.example.hrms.biz.sample.model.Sample;
import com.example.hrms.biz.sample.model.criteria.SampleCriteria;
import com.example.hrms.biz.sample.model.dto.SampleDTO;
import com.example.hrms.biz.sample.service.SampleService;
import com.example.hrms.common.http.model.Result;
import com.example.hrms.common.http.model.ResultPageData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collections;
import java.util.List;

@Tag(name = "Advertisement API v1")
@RestController
@RequestMapping("/api/v1/sample")
public class SampleRestController {

    private final SampleService sampleService;

    public SampleRestController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @Operation(summary="List sample")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SampleDTO.Resp.class)))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @GetMapping("")
    public ResultPageData<List<SampleDTO.Resp>> list(SampleCriteria criteria) {
        int total = sampleService.count(criteria);
        ResultPageData<List<SampleDTO.Resp>> response = new ResultPageData<>(criteria, total);
        if (total > 0) {
            response.setResultData(sampleService.list(criteria));
        } else {
            response.setResultData(Collections.emptyList());
        }
        return response;
    }

    @Operation(summary="Create sample")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Call back success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Result.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @PostMapping("")
    public Result create(SampleDTO.Req req) {
        sampleService.insert(req);

        //TODO return to 201
        return new Result();
    }

}
