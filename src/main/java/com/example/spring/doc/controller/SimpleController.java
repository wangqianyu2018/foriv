package com.example.spring.doc.controller;

import com.example.spring.doc.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import netscape.javascript.JSObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "SimpleController", description = "API文档标签")
@RestController
public class SimpleController {

    @Operation(hidden = true)
    @GetMapping("/")
    public String sayHello() {
        return "Hello,World!";
    }

    @Operation(
            summary = "welcome",
            description = "欢迎",
            method = MediaType.TEXT_PLAIN_VALUE,
            security = {@SecurityRequirement(name = "bearer-key")},
            responses = {
                    @ApiResponse(description = "欢迎语", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = @Schema(implementation = String.class)))
            })
    @GetMapping("/welcome")
    public String home() {
        return "Hello, nice to meet you!";
    }


    @Operation(
            summary = "心跳",
            description = "返回：版本号和发布时间",
            method = MediaType.APPLICATION_JSON_VALUE,
            parameters = @Parameter(ref = "#/components/parameters/myHeader1"))
    @Parameters({})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "参数填写错误"),
            @ApiResponse(responseCode = "500", description = "接口异常")
    })
    @GetMapping("/heartbeat")
    public Object heartBeat() throws JSONException {
        System.out.println("get heartBeat request");
        Map<String, String> res = new HashMap();
        res.put("version" , "x.y.z");
        res.put("releasedAt" , "2020-01-01T10:12:12.123Z");
        return res;
    }

    @Operation(
            summary = "新增用户",
            description = "返回：新增用户id",
            method = MediaType.APPLICATION_JSON_VALUE,
            parameters = @Parameter(ref = "#/components/parameters/myHeader1"))
    @Parameters({
            @Parameter(name = "first", description = "前面的名字", required = true, schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, example = "Michael"),
            @Parameter(name = "last", description = "后面的名字", schema = @Schema(implementation = String.class), in = ParameterIn.QUERY, example = "John"),
            @Parameter(name = "email", description = "邮箱", schema = @Schema(implementation = String.class), in = ParameterIn.QUERY, example = "xxx@xxx.com"),
            @Parameter(name = "password", description = "密码", schema = @Schema(implementation = String.class), in = ParameterIn.QUERY, example = "xxx@xxx.com"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "参数填写错误"),
            @ApiResponse(responseCode = "500", description = "接口异常")
    })
    @PostMapping("/user")
    public Object addUser(@RequestBody UserDTO user) throws JSONException {
        System.out.println("get add request");
        String uuid = UUID.randomUUID().toString();
        Map<String, String> res = new HashMap();
        res.put("id" , uuid);
        return res;
    }

    @Operation(
            summary = "查询所有用户",
            description = "返回：所有用户数据",
            method = MediaType.TEXT_PLAIN_VALUE,
            security = {@SecurityRequirement(name = "basicScheme")})
    @GetMapping("/user/all")
    public List<UserDTO> getUserAll() {
        return null;
    }

    @Operation(
            summary = "根据id查询用户",
            description = "返回：用户数据",
            method = MediaType.TEXT_PLAIN_VALUE)
    @GetMapping("/user/{id}")
    public UserDTO getUserById(@Parameter(description = "用户id", required = true, example = "1") @PathVariable("id") Long id) {
        return null;
    }

    @Operation(
            summary = "保存用户",
            description = "返回：新增用户id",
            method = MediaType.APPLICATION_JSON_VALUE,
            parameters = @Parameter(ref = "#/components/parameters/myHeader1"))
    @Parameters({
            @Parameter(name = "access_token", description = "授权码", required = true, schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, example = "abc"),
            @Parameter(name = "id", description = "用户id", schema = @Schema(implementation = Long.class), in = ParameterIn.QUERY, example = "123456"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "参数填写错误"),
            @ApiResponse(responseCode = "500", description = "接口异常")
    })
    @PostMapping("/user/save")
    public Long saveUser(@RequestBody UserDTO user) {
        return 1L;
    }

    @Operation(
            summary = "根据id删除用户",
            description = "无返回",
            method = MediaType.TEXT_PLAIN_VALUE,
            parameters = @Parameter(ref = "#/components/headers/myHeader2"))
    @DeleteMapping("/user/delete")
    public void deleteUser(
            @Parameter(name = "id", description = "用户id", required = true, example = "1")
            @RequestParam("id") Long id) {
    }
}