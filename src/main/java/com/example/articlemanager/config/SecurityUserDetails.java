//package com.example.articlemanager.config;
//
//import com.example.articlemanager.pojo.Role;
//import com.example.articlemanager.pojo.User;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//public class SecurityUserDetails implements UserDetails {
//
//    private String username;
//    private String password;
//
//
//    @Enumerated(EnumType.STRING)
//    private Role role
//    public SecurityUserDetails(User user){
//        this.username=user.getUsername();
//        this.password = user.getPassword();
//
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
