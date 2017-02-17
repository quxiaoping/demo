package com.example.user.controller;

import com.example.user.dto.Result;
import com.example.user.enums.ErrorCodeEnum;
import com.example.user.exception.*;
import com.example.user.model.*;
import com.example.user.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(value = "UserController", description = "oprations for User")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="addUser", notes="Create User")
    @RequestMapping(value="", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> addUser(@RequestBody User user) {
        Result<User> r = new Result<>(ErrorCodeEnum.OK,userService.addUser(user));
        return r;
    }

    @ApiOperation(value="updateUser", notes="Update User")
    @RequestMapping(value="", method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user) throws BusinessException {
        Result<User> r = new Result<>(ErrorCodeEnum.OK,userService.updateUser(user));
        return r;
    }

    @ApiOperation(value="deleteUserById", notes="Delete User By Id")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Result<String> deleteUserById(@PathVariable Integer id) throws BusinessException {
        userService.deleteUserById(id);
        Result<String> r = new Result<>(ErrorCodeEnum.OK,"");
        return r;
    }

    @ApiOperation(value="getUser", notes="Get User Info By Id")
    @RequestMapping(value="/{id}", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> getUser(@PathVariable Integer id) throws BusinessException {
        Result<User> r = new Result<>(ErrorCodeEnum.OK,userService.getUserById(id));
        return r;
    }

	@ApiOperation(value="getAllUser", notes="Get All User info")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query",name = "query",required = false, value = "Filter. e.g. col1:v1,col2:v2 ...",dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "fields",required = false,value = "Fields returned. e.g. col1,col2 ...",dataType = "String"),*/
            @ApiImplicitParam(paramType = "query",name = "sortby",required = false,value = "Order corresponding to each sortby field. e.g. col1:desc,col2:asc ...",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",required = false,value = "Limit the size of result set. Must be an integer",dataType = "Int"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",required = false,value = "Start position of result set. Must be an integer",dataType = "Int")})
    @RequestMapping(value="", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Page<User>> getAllUser(@RequestParam(value = "query",required = false) String query,
                                 /*@RequestParam(value = "fields",required = false) String fields,*/
                                 @RequestParam(value = "sortby",required = false) String sortby,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "pagesize",required = false) Integer pagesize) {
        Result<Page<User>> r = new Result<>(ErrorCodeEnum.OK,userService.getAllUser(query,/*fields,*/sortby,page,pagesize));
        return r;
    }
}
