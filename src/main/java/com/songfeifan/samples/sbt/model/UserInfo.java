package com.songfeifan.samples.sbt.model;


import com.songfeifan.samples.sbt.model.entity.UserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserInfo implements UserDetails {

    private UserDO userDO;

    public UserInfo(UserDO userDO) {
        this.userDO = userDO;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userDO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDO getUser() {
        return userDO;
    }
}
