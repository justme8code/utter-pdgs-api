package com.justme8code.utterfresh_production_gathering_sys.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.justme8code.utterfresh_production_gathering_sys.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @OneToOne(cascade = CascadeType.ALL)  // Cascade to save Staff automatically
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private Staff staff;

    private String pwd;

    private String email;

    private String phone;

    private Long createdBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
