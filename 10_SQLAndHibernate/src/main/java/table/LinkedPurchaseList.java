package table;

import CompositeKey.KeyLinkedPurchaseList;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class LinkedPurchaseList {

    @EmbeddedId
    private KeyLinkedPurchaseList linkedPurchaseList;

    public LinkedPurchaseList(KeyLinkedPurchaseList linkedPurchaseList) {
        this.linkedPurchaseList = linkedPurchaseList;
    }

//    @Column(name = "student_id")
//    private int studentId;
//
//    @Column(name = "course_id")
//    private int courseId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    @Column(name = "course_price")
    private int coursePrice;

}
