package com.example.hrms.biz.user.controller.rest;

import com.example.hrms.biz.user.model.User;
import com.example.hrms.biz.user.model.dto.UserDTO;
import com.example.hrms.biz.user.service.UserService;
import com.example.hrms.common.http.model.Result;
import com.example.hrms.common.http.model.ResultPageData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "User API v1")
@RestController
@RequestMapping("/api/v1/users/rest")
public class UserRestController {

    private final UserService userService;


    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "List users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDTO.Resp.class)))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @GetMapping("/list")
    public ResultPageData<List<UserDTO.Resp>> listUsers(@RequestParam(required = false) List<Long> departmentIds,
                                                        @RequestParam(required = false) List<String> roles) {
        List<User> userList = userService.searchUsers(departmentIds, roles);
        int total = userList.size();
        return new ResultPageData<>(null, total, userList);
    }

    @Operation(summary = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Result.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    @PostMapping("/create")
    public Result createUser(@RequestBody UserDTO.Req req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setDepartmentId(req.getDepartmentId());
        user.setRole(req.getRole());
        user.setIsSupervisor(req.isSupervisor());
        user.setStatus(req.getStatus());

        userService.insertUser(user);
        return new Result();
    }
}