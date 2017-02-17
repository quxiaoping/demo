package com.example.user.controller;

import com.example.user.dto.Result;
import com.example.user.enums.ErrorCodeEnum;
import com.example.user.exception.*;
import com.example.user.model.*;
import com.example.user.service.UserInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userinfo")
@Api(value = "UserInfoController", description = "oprations for UserInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userinfoService;

    @ApiOperation(value="addUserInfo", notes="Create UserInfo")
    @RequestMapping(value="", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<UserInfo> addUserInfo(@RequestBody UserInfo userinfo) {
        Result<UserInfo> r = new Result<>(ErrorCodeEnum.OK,userinfoService.addUserInfo(userinfo));
        return r;
    }

    @ApiOperation(value="updateUserInfo", notes="Update UserInfo")
    @RequestMapping(value="", method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<UserInfo> updateUserInfo(@RequestBody UserInfo userinfo) throws BusinessException {
        Result<UserInfo> r = new Result<>(ErrorCodeEnum.OK,userinfoService.updateUserInfo(userinfo));
        return r;
    }

    @ApiOperation(value="deleteUserInfoById", notes="Delete UserInfo By Id")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Result<String> deleteUserInfoById(@PathVariable Integer id) throws BusinessException {
        userinfoService.deleteUserInfoById(id);
        Result<String> r = new Result<>(ErrorCodeEnum.OK,"");
        return r;
    }

    @ApiOperation(value="getUserInfo", notes="Get UserInfo Info By Id")
    @RequestMapping(value="/{id}", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<UserInfo> getUserInfo(@PathVariable Integer id) throws BusinessException {
        Result<UserInfo> r = new Result<>(ErrorCodeEnum.OK,userinfoService.getUserInfoById(id));
        return r;
    }

	@ApiOperation(value="getAllUserInfo", notes="Get All UserInfo info")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query",name = "query",required = false, value = "Filter. e.g. col1:v1,col2:v2 ...",dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "fields",required = false,value = "Fields returned. e.g. col1,col2 ...",dataType = "String"),*/
            @ApiImplicitParam(paramType = "query",name = "sortby",required = false,value = "Order corresponding to each sortby field. e.g. col1:desc,col2:asc ...",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",required = false,value = "Limit the size of result set. Must be an integer",dataType = "Int"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",required = false,value = "Start position of result set. Must be an integer",dataType = "Int")})
    @RequestMapping(value="", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Page<UserInfo>> getAllUserInfo(@RequestParam(value = "query",required = false) String query,
                                 /*@RequestParam(value = "fields",required = false) String fields,*/
                                 @RequestParam(value = "sortby",required = false) String sortby,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "pagesize",required = false) Integer pagesize) {
        Result<Page<UserInfo>> r = new Result<>(ErrorCodeEnum.OK,userinfoService.getAllUserInfo(query,/*fields,*/sortby,page,pagesize));
        return r;
    }
}
