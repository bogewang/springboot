package com.bogewang.ch9_1.ch9_1.dao;

import com.bogewang.ch9_1.ch9_1.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
