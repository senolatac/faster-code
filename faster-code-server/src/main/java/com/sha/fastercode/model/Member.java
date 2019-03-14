package com.sha.fastercode.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "member")
public class Member implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+.*$")
    @Length(min=2, max=255)
    @Column(name = "name")
    private String name;

    @Column(name = "membership_date")
    private LocalDateTime membershipDate;
}
