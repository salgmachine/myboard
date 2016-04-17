package me.bcfh.myboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import lombok.Data;

@Data
@Indexed
@Entity
@Table
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String title;

    @Column
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String text;

    @Column
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String editor;

    @Column
    private Integer complexity;

    @Column
    private Float effort;

    @Column
    private Integer verticalIndex;

    @Column
    private Long createdAt;

    @Column
    private Long modifiedAt;

    @Column
    private Integer state;

}