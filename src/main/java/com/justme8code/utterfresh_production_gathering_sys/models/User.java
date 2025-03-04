package com.justme8code.utterfresh_production_gathering_sys.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


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
    private Staff staff;

    private String pwd;

    private String email;

    private Long roleId;

    private String phone;

    private Long createdBy;
}
