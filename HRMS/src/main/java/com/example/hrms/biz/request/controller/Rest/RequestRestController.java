package com.example.hrms.biz.request.controller.Rest;

import com.example.hrms.biz.request.model.Request;
import com.example.hrms.biz.request.model.criteria.RequestCriteria;
import com.example.hrms.biz.request.model.dto.RequestDto;
import com.example.hrms.biz.request.service.RequestService;
import com.example.hrms.common.http.criteria.Page;
import com.example.hrms.common.http.model.ResultPageData;
import com.example.hrms.enumation.RequestStatusEnum;
import com.example.hrms.enumation.RequestTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Tag(name = "API requests")
@RestController
@RequestMapping("/api/v1/requests")
public class RequestRestController {
    private final RequestService requestService;

    public RequestRestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @Operation(summary = "List requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RequestDto.Resp.class)))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @GetMapping("")
    public ResultPageData<List<RequestDto.Resp>> list(Page page, RequestCriteria criteria) {
        int total = requestService.count(criteria);
        ResultPageData<List<RequestDto.Resp>> response = new ResultPageData<>(criteria, total);
        if (total > 0) {
            response.setResultData(requestService.list(page, criteria));
        } else {
            response.setResultData(Collections.emptyList());
        }
        return response;
    }
}