package com.example.user.service;

import com.example.user.dao.*;
import com.example.user.enums.ErrorCodeEnum;
import com.example.user.exception.*;
import com.example.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userinfoDao;

    @Transactional
    public UserInfo addUserInfo(UserInfo userinfo) {
        return userinfoDao.save(userinfo);
    }

    @Transactional
    public UserInfo updateUserInfo(UserInfo userinfo) throws BusinessException {
        UserInfo userinfoUpdate = userinfoDao.findOne(userinfo.getId());
        if (userinfoUpdate==null){
            throw new BusinessException(ErrorCodeEnum.USER_INFO_NOTFOUND);
        }
        /*TODO: need add logic eg:
        if (user.getName()!=null) {
            userUpdate.setName(user.getName());
        }
        if (user.getAge()!=0) {
            userUpdate.setAge(user.getAge());
        }
        */
        userinfoDao.save(userinfoUpdate);
        return userinfoUpdate;
    }

    @Transactional
    public void deleteUserInfoById(Integer id) throws BusinessException {
        UserInfo userinfoDelete = userinfoDao.findOne(id);
        if (userinfoDelete==null) {
            throw new BusinessException(ErrorCodeEnum.USER_INFO_NOTFOUND);
        }
        userinfoDao.delete(id);
    }

    public UserInfo getUserInfoById(Integer id) throws BusinessException {
        UserInfo userinfo = userinfoDao.findOne(id);
        if (userinfo==null) {
            throw new BusinessException(ErrorCodeEnum.USER_INFO_NOTFOUND);
        }
        return userinfo;
    }

	public Page<UserInfo> getAllUserInfo(final String query, /*String fields, */String sortby,Integer page, Integer pageSize) {
        if (page == null) {
            page = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        PageRequest pageRequest;
        if (sortby != null && sortby.length() > 0) {
            List<Order> orders = new ArrayList<Order>();
            String[] sortFields = sortby.split(",");
            for (String sortField : sortFields) {
                String[] orderbys = sortField.split(":");
                if(orderbys[1].equals("desc")) {
                    orders.add(new Order(Sort.Direction.DESC,orderbys[0]));
                } else if (orderbys[1].equals("asc")) {
                    orders.add(new Order(Sort.Direction.ASC, orderbys[0]));
                }
            }
            pageRequest = new PageRequest(page, pageSize,new Sort(orders));
        }else{
            pageRequest = new PageRequest(page,pageSize);
        }

        return userinfoDao.findAll(new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> preList = new ArrayList<Predicate>();
                if (query != null && query.length() > 0) {
                    String[] queryFields = query.split(",");
                    for (String queryField : queryFields) {
                        String[] queryKv = queryField.split(":");
                        preList.add(cb.equal(root.get(queryKv[0]),queryKv[1]));
                    }
                }
                return cq.where(preList.toArray(new Predicate[preList.size()])).getRestriction();
            }
        },pageRequest);
    }
}
