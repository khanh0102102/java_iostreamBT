import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John Doe", 20, 3.5));
        students.add(new Student("Alice Smith", 21, 3.8));
        students.add(new Student("Bob Johnson", 22, 3.9));

        writeStudentsToXML(students);
    }

    public static void writeStudentsToXML(List<Student> students) {
        try {
            FileWriter fileWriter = new FileWriter("student_list.xml");
            fileWriter.write("<student_list>\n");

            for (Student student : students) {
                fileWriter.write("    <student>\n");
                fileWriter.write("        <name>" + student.name + "</name>\n");
                fileWriter.write("        <age>" + student.age + "</age>\n");
                fileWriter.write("        <gpa>" + student.gpa + "</gpa>\n");
                fileWriter.write("    </student>\n");
            }

            fileWriter.write("</student_list>");
            fileWriter.close();
            System.out.println("File XML được tạo thành công!");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }
}
