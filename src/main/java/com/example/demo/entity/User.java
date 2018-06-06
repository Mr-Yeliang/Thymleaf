package com.example.demo.entity;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

//	@NotEmpty(message = "名称不能为空")
//	@Length(min = 2, max = 6, message = "长度为2~6位")
	private String name;

//	@NotNull(message = "年龄不能为空")
//	@Min(message = "年龄不能小于1", value = 1)
//	@Max(message = "最大年龄不得超过150", value = 150)
	private Integer age;

	private String sex;

//	@NotEmpty(message = "Email不能为空")
//	@Email
	private String email;

	private String hoby;

	public User() {
	}

	public User(String id, String name, Integer age, String sex, String email, String hoby) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.email = email;
		this.hoby = hoby;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHoby() {
		return hoby;
	}

	public void setHoby(String hoby) {
		this.hoby = hoby;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", email=" + email + ", hoby="
				+ hoby + "]";
	}

}
