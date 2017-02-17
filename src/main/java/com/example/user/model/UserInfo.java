package com.example.user.model;

import javax.persistence.*;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="user_info")
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Id",nullable = false)
    private Integer id;
    @ApiModelProperty(value="用户名")
    @Column(name="username",length=32,nullable = false)
    private String username;
    @ApiModelProperty(value="密码")
    @Column(name="password",length=32)
    private String password;
    @ApiModelProperty(value="用户类型")
    @Column(name="usertype",length=2)
    private String usertype;
    @ApiModelProperty(value="是否可用")
    @Column(name="enabled")
    private Integer enabled;
    @ApiModelProperty(value="真实姓名")
    @Column(name="realname",length=32)
    private String realname;
    @ApiModelProperty(value="QQ")
    @Column(name="qq",length=14)
    private String qq;
    
    @Column(name="email",length=100)
    private String email;
    @ApiModelProperty(value="联系电话")
    @Column(name="tel",length=255)
    private String tel;
}

