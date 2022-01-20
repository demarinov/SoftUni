package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="diagnoses")
public class Diagnoses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "comments")
    private String comments;

    @ManyToMany(mappedBy = "diagnoses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Patient> patients;

    public Diagnoses() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
