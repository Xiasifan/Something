package Bean;

public class Grade {
	private int id;
	private String GradeName;
	private String GradeDesc;

	public Grade() {
	}

	public Grade(String gradeName, String gradeDesc) {
		GradeName = gradeName;
		GradeDesc = gradeDesc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGradeName() {
		return GradeName;
	}

	public void setGradeName(String gradeName) {
		GradeName = gradeName;
	}

	public String getGradeDesc() {
		return GradeDesc;
	}

	public void setGradeDesc(String gradeDesc) {
		GradeDesc = gradeDesc;
	}
}
