package app.repositories;

import app.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where year(e.birthday) < :year order by e.salary desc")
    List<Employee> findEmployeesByBirthdayBefore(@Param(value="year")int year);
}
