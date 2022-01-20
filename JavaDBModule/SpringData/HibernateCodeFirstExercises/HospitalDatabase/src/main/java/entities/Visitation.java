package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="visitation")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date", nullable = false)
    private Date date;

    @Column(name="comments")
    private String comments;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private Patient patient;

    public Visitation() {
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
