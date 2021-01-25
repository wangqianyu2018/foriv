package com.example.spring.doc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户DTO")
public class UserDTO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "前面的名", required = true, example = "Michael")
    private String first;
    @Schema(description = "后面的名", required = true, example = "John")
    private String last;
    @Schema(description = "邮箱", required = true, example = "xxx@xxx.com")
    private String email;
    @Schema(description = "密码", required = true, example = "小白菜")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}