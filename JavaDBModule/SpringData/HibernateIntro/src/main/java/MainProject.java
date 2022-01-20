import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainProject {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager em = entityManagerFactory.createEntityManager();

        printTowns(getAllTowns(em));

        // 2. Remove Objects
        removeObjects(em);

        // 3. Contains Employee
//        printContainsEmployee(em);

        // 4.	Employees with Salary Over 50 000
        printEmployeeWithSalary(em);

        // 5.	Employees from Department
        printEmployeeFromDepartment(em);

        printEmployeeFromDepartmentViaCriteria(em);

        // 6.	Adding a New Address and Updating Employee
//        addNewAddress(em);
//        updateEmployee(em);

        // 7.	Addresses with Employee Count
//        printAddressAndEmployeeCount(em);

        // 8.	Get Employee with Project
//        printEmployeeWithProject(em);

        // 9.	Find Latest 10 Projects
        printLatestProjects(em);

        // 10.	Increase Salaries
//        printIncreasedSalaries(em);

        // 11.	Remove Towns
//        printCountOfTownsRemoved(em);

        // 12.	Find Employees by First Name
        printEmployeeByFirstName(em);

        // 13.	Employees Maximum Salaries
        printMaxSalary(em);
    }

    private static void printMaxSalary(EntityManager em) {
        System.out.println("### printMaxSalary");

        List<Employee> employees = em.
                createQuery("select e from Employee e " +
                        " where e.salary not between 30000 and 70000"+
                        " group by e.department.id" +
                        " order by e.salary desc")
                .getResultList();

        for (Employee emp :
                employees.stream().
                        sorted(Comparator.comparing(e2 -> e2.getDepartment().getId())).collect(Collectors.toList())) {
            System.out.printf("%s %.2f%n",emp.getDepartment().getName(), emp.getSalary());
        }

    }

    private static void printEmployeeByFirstName(EntityManager em) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine().trim();

        List<Employee> employees = em.createQuery("select e from Employee e " +
                "where e.firstName like :firstName order by e.id")
                .setParameter("firstName", pattern+"%")
                .getResultList();

        for (Employee emp : employees) {
            System.out.printf("%s %s - %s - ($%.2f)%n",emp.getFirstName(),
                    emp.getLastName(), emp.getJobTitle(), emp.getSalary());
        }
    }

    private static void printCountOfTownsRemoved(EntityManager em) {

        System.out.println("### printCountOfTownsRemoved");

        Scanner sc = new Scanner(System.in);

        String town = sc.nextLine();

        List<Address> addresses = em.createQuery("Select ad from Address ad where ad.town.name = '"+town+"'")
                .getResultList();

        int countAddresses = addresses.size();

        Town townObject = addresses.get(0).getTown();

        em.getTransaction().begin();

        for (int i = 0; i < addresses.size(); i++) {

            for (Employee emp : addresses.get(i).getEmployees()) {
                emp.setAddress(null);
                emp.setManager(null);
            }

            addresses.get(i).setTown(null);
            addresses.get(i).setEmployees(null);
            em.remove(addresses.get(i));


        }

        em.remove(townObject);

        em.getTransaction().commit();

        if (countAddresses == 1) {
            System.out.printf("%d address in Sofia deleted",countAddresses);
        } else {
            System.out.printf("%d addresses in Seattle deleted",countAddresses);
        }



    }

    private static void printIncreasedSalaries(EntityManager em) {
        System.out.println("### printIncreasedSalaries");

        em.getTransaction().begin();

        List<Employee> employees = em.createQuery("Select em from Employee em where" +
                " em.department.name in " +
                "('Engineering','Tool Design','Marketing','Information Services')").
                getResultList();
        for (Employee emp : employees) {
            BigDecimal newSalary = emp.getSalary().
                    add((BigDecimal.valueOf(0.12d).multiply(emp.getSalary())));
            emp.setSalary(newSalary);

            System.out.printf("%s %s (%.2f)%n",emp.getFirstName(),emp.getLastName(), emp.getSalary());
        }


       em.getTransaction().commit();
    }


    private static void printLatestProjects(EntityManager em) {

        List<Project> projects = em
                .createQuery("Select p from Project p where endDate is null order by startDate desc")
                .getResultList();

        for (Project project : projects.stream().limit(10).
                sorted(Comparator.comparing(Project::getName)).collect(Collectors.toList())) {

            System.out.printf("Project name: %s%n",project.getName());

            System.out.printf("\tProject description:%s%n",project.getDescription());
            System.out.printf("\tProject Start Date:%s%n",project.getStartDate().toString());
            System.out.printf("\tProject End Date:%s%n",project.getEndDate());

        }
    }

    private static void printEmployeeWithProject(EntityManager em) {

        int id = Integer.parseInt(sc.nextLine());

        Employee emp = (Employee)em.createQuery("select e from Employee e where e.id = "+id)
                .getResultList().get(0);

        System.out.printf("%s %s - %s%n",emp.getFirstName(), emp.getLastName(), emp.getJobTitle());

        for (Project pr : emp.getProjects().stream().sorted(Comparator.comparing(Project::getName)).collect(Collectors.toList())) {
            System.out.printf("\t%s%n", pr.getName());
        }
    }

    private static void printAddressAndEmployeeCount(EntityManager em) {
        System.out.println("### printAddressAndEmployeeCount");
        List<?> addresses = em
                .createQuery("select e, count(e.id) as numEmp from Employee e " +
                        "group by e.address.id order by numEmp desc")
                .getResultList();

        for (int i = 0; i< 10; i++) {
            Object[] adrRow = (Object[]) addresses.get(i);
            Employee emp = (Employee) adrRow[0];
            System.out.printf("%s, %s - %d employees%n",emp.getAddress().getText(),
                    emp.getAddress().getTown().getName(),
                    adrRow[adrRow.length-1]);
        }
    }

    private static void printEmployeeFromDepartmentViaCriteria(EntityManager em) {

        System.out.println("### printEmployeeFromDepartmentViaCriteria");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteria = criteriaBuilder.createQuery();

        Root<Employee> empRoot = criteria.from(Employee.class);
        criteria.select(empRoot).where(criteriaBuilder.equal(empRoot.get("department").
                get("id"),6)).orderBy(criteriaBuilder.asc(empRoot.get("salary"))
                ,criteriaBuilder.asc(empRoot.get("id")));

        List<Employee> employees = em.createQuery(criteria).getResultList();


        printEmployees(employees);

    }

    private static void updateEmployee(EntityManager em) {

        System.out.println("### updateEmployee");
        String lastName = sc.nextLine();

        List<Employee> employees = getAllEmployees(em);

        Employee emp = employees.stream().filter(e -> lastName.equals(e.getLastName()))
                .findFirst().orElse(null);

        if (emp != null) {

            em.getTransaction().begin();

            Address address = (Address) em
                    .createQuery("Select a from Address a where text ='Vitoshka 15'")
                    .getResultList().get(0);

            if (address != null) {
                emp.setAddress(address);
                em.flush();
            }

            em.getTransaction().commit();
        }
    }

    private static void addNewAddress(EntityManager em) {
        System.out.println("### addNewAddress");
        em.getTransaction().begin();

        Address address = new Address();
        address.setText("Vitoshka 15");
        Town town = em.find(Town.class, 32);
        address.setTown(town);
        em.persist(address);

        em.getTransaction().commit();
    }

    public static void printEmployees(List<Employee> employees) {
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    private static void printEmployeeFromDepartment(EntityManager em) {
        System.out.println("### printEmployeeFromDepartment");
        List<Employee> employees = em.
                createQuery("select e from Employee e where e.department.id = 6" +
                        " order by salary asc, id asc")
                .getResultList();

        printEmployees(employees);
    }

    private static void printEmployeeWithSalary(EntityManager em) {

        System.out.println("#### printEmployeeWithSalary");

        List<Employee> employees = getAllEmployees(em);

        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);

            if (emp.getSalary().intValue() > 50000) {
                System.out.println(emp.getFirstName());
            }
        }
    }

    private static void printContainsEmployee(EntityManager em) {

        System.out.println("#### printContainsEmployee");

        if (containsEmployee(em)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static boolean containsEmployee(EntityManager em) {
        String[] names = sc.nextLine().split(" ");

        List<Employee> employees = getAllEmployees(em);

        for (Employee employee : employees) {
            if (employee.getFirstName().equals(names[0])
                    && employee.getLastName().equals(names[1])) {
                return true;
            }
        }

        return false;

    }

    public static void removeObjects(EntityManager em) {

        List<Town> towns = getAllTowns(em);

        em.getTransaction().begin();

        for (Town town : towns) {

            if (town.getName().length() > 5) {
                em.detach(town);
            } else {
                town.setName(town.getName().toLowerCase());
            }
        }

        em.flush();

        em.getTransaction().commit();
    }

    public static void printTowns(List<Town> towns) {

        System.out.println("#### printTowns");

        for (Town town : towns) {
            System.out.println(town.getName());
        }
    }

    public static List<Employee> getAllEmployees(EntityManager em) {
        List<Employee> employees = em.createQuery("Select t from Employee t").getResultList();

        return employees;
    }

    public static List<Town> getAllTowns(EntityManager em) {
        List<Town> towns = em.createQuery("Select t from Town t").getResultList();

        return towns;
    }

}
