package com.bogewang.ch9_1.ch9_1.security;

import com.bogewang.ch9_1.ch9_1.dao.SysUserRepository;
import com.bogewang.ch9_1.ch9_1.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserService implements UserDetailsService {      //1
    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {  //2
        SysUser user = userRepository.findByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return user;        //3
    }
}
