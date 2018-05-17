package Bean;

public class Course {
	private int courseId;
	private int gradeId=-1;
	private String courseName;
	private String courseName2;
	private String courseName3;
	private String courseName4;
	private String courseName5;
	private String courseName6;
	private String courseName7;
	private String courseDesc;

	public Course(int courseId,String courseName, String courseName2, String courseName3, String courseName4, String courseName5, String courseName6, String courseName7, String courseDesc) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseName2 = courseName2;
		this.courseName3 = courseName3;
		this.courseName4 = courseName4;
		this.courseName5 = courseName5;
		this.courseName6 = courseName6;
		this.courseName7 = courseName7;
		this.courseDesc = courseDesc;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseName2() {
		return courseName2;
	}

	public void setCourseName2(String courseName2) {
		this.courseName2 = courseName2;
	}

	public String getCourseName3() {
		return courseName3;
	}

	public void setCourseName3(String courseName3) {
		this.courseName3 = courseName3;
	}

	public String getCourseName4() {
		return courseName4;
	}

	public void setCourseName4(String courseName4) {
		this.courseName4 = courseName4;
	}

	public String getCourseName5() {
		return courseName5;
	}

	public void setCourseName5(String courseName5) {
		this.courseName5 = courseName5;
	}

	public String getCourseName6() {
		return courseName6;
	}

	public void setCourseName6(String courseName6) {
		this.courseName6 = courseName6;
	}

	public String getCourseName7() {
		return courseName7;
	}

	public void setCourseName7(String courseName7) {
		this.courseName7 = courseName7;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public Course() {
	}
}
