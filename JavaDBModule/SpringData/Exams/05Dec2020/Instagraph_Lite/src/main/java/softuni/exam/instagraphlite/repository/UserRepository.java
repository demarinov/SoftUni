package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByUserName(String userName);

    List<User> findUserByUserName(String userName);

    @Query("select u from User u order by u.posts.size desc, u.id asc")
    List<User> getUsersOrderedByCountOfPosts();
}
