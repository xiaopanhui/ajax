package com.example.ajax.bean;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable{
	private static final long serialVersionUID = -7895095264871076651L;
	private Integer id;
	private String name;
	private String address;
	private String phone;


}
