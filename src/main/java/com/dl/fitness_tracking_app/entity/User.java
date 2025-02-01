package com.dl.fitness_tracking_app.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document
public class User implements UserDetails {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    @Indexed(unique = true)
    private String email;
    private String password;
    private List<Workout> workouts;
    private List<Product> products;
    @Field(targetType = FieldType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
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
}
