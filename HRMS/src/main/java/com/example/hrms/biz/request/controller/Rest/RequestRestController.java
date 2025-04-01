package com.example.hrms.biz.request.controller.Rest;

import com.example.hrms.biz.request.model.Request;
import com.example.hrms.biz.request.model.criteria.RequestCriteria;
import com.example.hrms.biz.request.model.dto.RequestDto;
import com.example.hrms.biz.request.service.RequestService;
import com.example.hrms.biz.user.model.User;
import com.example.hrms.biz.user.service.UserService;
import com.example.hrms.common.http.criteria.Page;
import com.example.hrms.common.http.model.Result;
import com.example.hrms.common.http.model.ResultData;
import com.example.hrms.common.http.model.ResultPageData;
import com.example.hrms.security.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tag(name = "API requests")
@RestController
@RequestMapping("/api/v1/requests")
public class RequestRestController {
    private final RequestService requestService;
    private UserService userService;

    public RequestRestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
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
    @Operation(summary = "Get requests of staff in the same department")
    @PreAuthorize("hasRole('SUPERVISOR')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RequestDto.Resp.class)))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User is not logged in",
                    content = @Content)
    })
    @GetMapping("/staff-requests")
    public ResultPageData<List<RequestDto.Resp>> getRequestsForSupervisor(Page page) {
        String supervisorUsername = SecurityUtils.getCurrentUsername();
        User supervisor = userService.getUserByUsername(supervisorUsername);

        if (supervisor == null || !supervisor.isSupervisor()) {
            throw new RuntimeException("User is not a supervisor");
        }

        int total = requestService.countRequestsByDepartment(supervisor.getDepartmentId());
        ResultPageData<List<RequestDto.Resp>> response = new ResultPageData<>(new RequestCriteria(), total);
        response.setResultData(total > 0 ? requestService.getRequestsForSupervisor(page, supervisor.getDepartmentId()) : Collections.emptyList());

        return response;
    }
    @Operation(summary = "Approve or Reject Request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request status updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User is not logged in",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "User is not authorized",
                    content = @Content)
    })
    @PutMapping("/{requestId}/approve-reject")
    public Result approveOrRejectRequest(@PathVariable Long requestId, @RequestParam String action) {
        String supervisorUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            requestService.approveOrRejectRequest(requestId, action, supervisorUsername);
            return new Result("Success", "Request " + action.toLowerCase() + "d successfully");
        } catch (IllegalArgumentException e) {
            return new Result("Error", "Invalid action: " + action);
        } catch (RuntimeException e) {
            return new Result("Error", e.getMessage());
        } catch (Exception e) {
            return new Result("Error", "An unexpected error occurred. Please try again later.");
        }
    }
    @Operation(summary = "Get total leave days of a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved total leave days",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Result.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content)})
    @GetMapping("/days-off")
    public ResultData<Long> getTotalLeaveDays(@RequestParam String username) {
        long totalLeaveDays = requestService.getTotalLeaveDays(username);
        return new ResultData<>(Result.SUCCESS, "Total leave days retrieved successfully", totalLeaveDays);
    }

    @Operation(summary = "Create a new request")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Request created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/create")
    public Result createRequest(@RequestBody RequestDto.Req requestDto,
        @RequestParam String username) {
        boolean success = requestService.createRequest(username, requestDto);

        if (!success) {
            return new Result("Failed", "Failed to create request");
        }

        return new Result("Success", "Request created successfully.");
    }
    @PutMapping("/{id}")
    public Result updateRequest(@PathVariable Long id, @RequestBody Request request) {
        String supervisorUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        request.setRequestId(id);
        try {
            boolean updated = requestService.updateRequest(request);
            if (updated) {
                return new Result("Success", "Request updated successfully by " + supervisorUsername);
            } else {
                return new Result("Error", "Request not found.");
            }
        } catch (IllegalArgumentException e) {
            return new Result("Error", "Invalid request: " + e.getMessage());
        } catch (RuntimeException e) {
            return new Result("Error", e.getMessage());
        } catch (Exception e) {
            return new Result("Error", "An unexpected error occurred. Please try again later.");
        }
    }
    @Operation(summary = "Delete a request")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Request not found"),
        @ApiResponse(responseCode = "400", description = "Cannot delete request with status REJECTED or APPROVED")
    })
    @DeleteMapping("/{id}")
    public Result deleteRequest(@PathVariable Long id) {
        try {
            requestService.deleteRequest(id);
            return new Result("Success", "Request deleted successfully");
        } catch (ResourceNotFoundException e) {
            return new Result("Error", "Request not found");
        } catch (IllegalStateException e) {
            return new Result("Error", e.getMessage());
        } catch (Exception e) {
            return new Result("Error", "An unexpected error occurred. Please try again later.");
        }
    }
}