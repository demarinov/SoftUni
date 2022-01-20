import entities.Course;
import entities.Person;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class UniversityMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("university");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person studentOne = new Student(5.6d);
        studentOne.setFirstName("Rado");
        studentOne.setLastName("Morkov");
        studentOne.setPhoneNumber("11243243");

        Person studentTwo = new Student(5.0d);
        studentTwo.setFirstName("Pesho");
        studentTwo.setLastName("Tirel");
        studentTwo.setPhoneNumber("2222222");

        Person teacher = new Teacher(3.4d);
        teacher.setFirstName("Pedro");
        teacher.setLastName("Rolev");
        teacher.setPhoneNumber("1111111");

        Course courseOne = new Course();
        courseOne.setName("Learn Programming");
        courseOne.setCredits(2);
        courseOne.setDescription("Low level programming");
        courseOne.setStartDate(new Date());

        Course courseTwo = new Course();
        courseTwo.setName("Learn XML");
        courseTwo.setCredits(2);
        courseTwo.setDescription("XML for beginners");
        courseTwo.setStartDate(new Date());

        courseOne.setTeacher(teacher);
        courseTwo.setTeacher(teacher);

        studentOne.getCourses().add(courseOne);
        studentTwo.getCourses().add(courseTwo);

        em.persist(teacher);
        em.persist(studentOne);
        em.persist(studentTwo);
        em.persist(courseOne);
        em.persist(courseTwo);

        em.getTransaction().commit();
        em.close();

    }
}
