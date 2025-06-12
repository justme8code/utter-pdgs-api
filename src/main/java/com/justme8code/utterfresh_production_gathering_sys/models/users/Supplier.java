package com.justme8code.utterfresh_production_gathering_sys.models.users;


import com.justme8code.utterfresh_production_gathering_sys.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String emailAddress;
}
