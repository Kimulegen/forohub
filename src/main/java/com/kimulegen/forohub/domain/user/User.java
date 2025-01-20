package com.kimulegen.forohub.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "user")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean active;

    public User(RegisterUserDTO registeruserDTO){
        this.name = registeruserDTO.name();
        this.email = registeruserDTO.email();
        this.password = registeruserDTO.password();
    }

    public User(RegisterUserDTO registerUserDTO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.name = registerUserDTO.name();
        this.username = registerUserDTO.username();
        this.password = bCryptPasswordEncoder.encode(registerUserDTO.password());
        this.active = true;
    }

    public void updateUser(UpdateUserDTO updateUserDTO) {
        if (updateUserDTO.name() != null) {
            this.name = updateUserDTO.name();
        }
        if (updateUserDTO.email() != null){
            this.email = updateUserDTO.email();
        }
    }

    public void deactivateUser(){
        this.active = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public String getUsername() { return this.username; }

    @Override
    public boolean isEnabled() { return this.active; }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getImplementationClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() !=null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode(){
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

