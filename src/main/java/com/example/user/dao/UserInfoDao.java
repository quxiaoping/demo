package com.example.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.user.model.*;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer>,JpaSpecificationExecutor<UserInfo> {
}

