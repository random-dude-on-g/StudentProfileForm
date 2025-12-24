package model;

public class ProfileBean {

    private String name;
    private String studentId;
    private String program;
    private String email;
    private String hobbies;
    private String intro;

    public ProfileBean() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getHobbies() { return hobbies; }
    public void setHobbies(String hobbies) { this.hobbies = hobbies; }

    public String getIntro() { return intro; }
    public void setIntro(String intro) { this.intro = intro; }
}