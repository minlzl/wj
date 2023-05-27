package com.lzl.wj.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "uuser")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String username;
    private String password;
    private String salt;
}
