package table;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int salary;
    private int age;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "courses",
            joinColumns = {@JoinColumn(name = "teacher_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<Course> coursesList;
}
