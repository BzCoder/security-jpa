package com.example.securityjpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author BaoZhou
 * @date 2018/7/4
 */
@Data
@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

    @Id
    @Column(name="r_id")
    private Long id;

    @Column(name ="r_name")
    private String name;
}
