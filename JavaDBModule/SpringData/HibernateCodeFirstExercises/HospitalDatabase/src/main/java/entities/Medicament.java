package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="medicament")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "medicaments", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Patient> patients;

    public Medicament() {
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

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
