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
    private Long createdAt;

    @Column
    private Long modifiedAt;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getEditor() {
	return editor;
    }

    public void setEditor(String editor) {
	this.editor = editor;
    }

    public Integer getComplexity() {
	return complexity;
    }

    public void setComplexity(Integer complexity) {
	this.complexity = complexity;
    }

    public Float getEffort() {
	return effort;
    }

    public void setEffort(Float effort) {
	this.effort = effort;
    }

    public Long getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
	this.createdAt = createdAt;
    }

    public Long getModifiedAt() {
	return modifiedAt;
    }

    public void setModifiedAt(Long modifiedAt) {
	this.modifiedAt = modifiedAt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Item other = (Item) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
