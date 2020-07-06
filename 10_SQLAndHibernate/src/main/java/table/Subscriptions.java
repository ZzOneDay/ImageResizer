package table;

import CompositeKey.KeyStudentIdCourseId;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data

@Entity
public class Subscriptions {
    @EmbeddedId
    private KeyStudentIdCourseId keyStudentIdCourseId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscription_date")
    private Date subscriptionDate;
}