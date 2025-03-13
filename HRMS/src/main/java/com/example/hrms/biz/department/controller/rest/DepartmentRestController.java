package com.example.hrms.biz.department.controller.rest;

import com.example.hrms.biz.department.model.dto.DepartmentDTO;
import com.example.hrms.biz.department.model.criteria.DepartmentCriteria;
import com.example.hrms.biz.department.service.DepartmentService;
import com.example.hrms.common.http.model.ResultPageData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Tag(name = "Department API v1")
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Operation(summary="List departments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DepartmentDTO.Resp.class)))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @GetMapping("")
    public ResultPageData<List<DepartmentDTO.Resp>> list(DepartmentCriteria criteria) {
        int total = departmentService.count(criteria);
        ResultPageData<List<DepartmentDTO.Resp>> response = new ResultPageData<>(criteria, total);
        if (total > 0) {
            response.setResultData(departmentService.list(criteria));
        } else {
            response.setResultData(Collections.emptyList());
        }
        return response;
    }
}