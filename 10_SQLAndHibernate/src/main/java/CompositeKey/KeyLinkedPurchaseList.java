package CompositeKey;

import lombok.Data;
import table.Course;
import table.Student;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data

@Embeddable
public class KeyLinkedPurchaseList implements Serializable
{
    @OneToOne
    private Student student;

    @OneToOne
    private Course course;

    public KeyLinkedPurchaseList(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}
