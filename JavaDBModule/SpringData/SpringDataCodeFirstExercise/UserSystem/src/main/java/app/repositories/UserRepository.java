package app.repositories;

import app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserName(String userName);

    List<User> findAllByEmailEndingWith(String end);

    List<User> findAllByLastTimeLoggedInBefore(Date dateTime);

    List<User> findAllByIsDeleted(boolean isDeleted);


    void deleteByIsDeleted(boolean deleteStatus);
}
