package table;

import CompositeKey.KeyStudentIdCourseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter

@Entity
public class Subscriptions {
    @EmbeddedId
    private KeyStudentIdCourseId keyStudentIdCourseId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscription_date")
    private Date subscriptionDate;
}