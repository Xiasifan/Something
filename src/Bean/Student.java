package Bean;

import java.util.Date;

public class Student {
	private int stuId;
	private String stuNo;
	private String stuName;
	private String sex;
	private Date birthday;
	private int gradeId = -1;
	private String phoneNo;
	private String stuDesc;

	public Student() {
	}

	public Student(String stuNo, String stuName, String sex, Date birthday, int gradeId, String phoneNo, String stuDesc) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.sex = sex;
		this.birthday = birthday;
		this.gradeId = gradeId;
		this.phoneNo = phoneNo;
		this.stuDesc = stuDesc;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getStuDesc() {
		return stuDesc;
	}

	public void setStuDesc(String stuDesc) {
		this.stuDesc = stuDesc;
	}
}
