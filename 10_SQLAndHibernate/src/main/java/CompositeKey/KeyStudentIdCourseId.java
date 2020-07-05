package CompositeKey;

import lombok.Data;
import table.Course;
import table.Student;
import table.Teacher;

import javax.persistence.*;
import java.io.Serializable;

@Data

@Embeddable
public class KeyStudentIdCourseId implements Serializable {
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;
}
