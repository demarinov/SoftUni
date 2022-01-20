import entities.Diagnoses;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class HospitalDBMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospital");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Patient patient = new Patient();
        patient.setFirstName("Billy");
        patient.setLastName("Sole");
        patient.setAddress("Santa street");

        Visitation visitation = new Visitation();
        visitation.setDate(new Date());
        visitation.setComments("Visit now");
        visitation.setPatient(patient);

        Diagnoses diagnoses = new Diagnoses();
        diagnoses.setName("Minor virus");
        diagnoses.setComments("Slow burner");

        Medicament medicament = new Medicament();
        medicament.setName("Syrop");

        patient.getVisitations().add(visitation);
        patient.getDiagnoses().add(diagnoses);
        patient.getMedicaments().add(medicament);

        em.persist(patient);

        em.getTransaction().commit();
        em.close();
    }
}
