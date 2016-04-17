package me.bcfh.myboard.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class BoardColumnConfig {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Board board;
    
    @Column
    private Integer state;

    @Column
    private Boolean visible;
    
    @Column
    private Integer maxItems;
    
    @Column
    private Integer maxEffort;
    
    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private List<BoardColumnConfig> showWith;
    
    
}
