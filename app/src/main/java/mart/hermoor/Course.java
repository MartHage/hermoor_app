package mart.hermoor;

public class Course {
    private String course_code, course_name, date, description, result, weight;

    public Course() {
    }

    public Course(String course_code, String course_name, String date, String description, String result, String weight) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.date = date;
        this.description = description;
        this.result = result;
        this.weight = weight;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
