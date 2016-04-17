package me.bcfh.myboard.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table
public class BoardColumn {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String title;

    @OneToOne
    private BoardColumnConfig config;
    
    private transient List<Object> items;

}
