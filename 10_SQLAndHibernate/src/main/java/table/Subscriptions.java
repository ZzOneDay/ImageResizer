package table;

import CompositeKey.KeyStudentIdCourseId;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Subscriptions {
    @EmbeddedId
    private KeyStudentIdCourseId keyStudentIdCourseId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscription_date")
    private Date subscriptionDate;



    public KeyStudentIdCourseId getKeyStudentIdCourseId() {
        return keyStudentIdCourseId;
    }

    public void setKeyStudentIdCourseId(KeyStudentIdCourseId keyStudentIdCourseId) {
        this.keyStudentIdCourseId = keyStudentIdCourseId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}