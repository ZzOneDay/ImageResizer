package CompositeKey;

import lombok.*;
import table.Course;
import table.Student;
import javax.persistence.*;
import java.io.Serializable;

//@Data
@ToString
@EqualsAndHashCode
@Getter

@Embeddable
public class KeyStudentIdCourseId implements Serializable {
    @OneToOne
    private Student student;

    @OneToOne
    private Course course;
}
