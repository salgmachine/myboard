package me.bcfh.myboard.model;

public class ErrorDto {

    private String message;

    public ErrorDto() {
	// TODO Auto-generated constructor stub
    }

    public ErrorDto(String message) {
	super();
	this.message = message;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

}
