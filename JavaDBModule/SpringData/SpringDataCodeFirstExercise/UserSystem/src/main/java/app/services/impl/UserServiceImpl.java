package app.services.impl;

import app.models.Album;
import app.models.User;
import app.repositories.UserRepository;
import app.services.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void persist(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findUsersByEmailEnding(String end) {
        return userRepository.findAllByEmailEndingWith(end);
    }

    @Override
    public List<User> findUsersNotLoggedAfterDate(Date dateTime) {
        return userRepository.findAllByLastTimeLoggedInBefore(dateTime);
    }

    @Override
    public void delete(User user) {

        if (user.isDeleted()) {
            for (Album album : user.getAlbums()) {
                album.setUser(null);
            }
            userRepository.delete(user);
        }
    }

    @Override
    public void deactivateUsersBeforeDate(Date date) {
        List<User> usersNotLogged = findUsersNotLoggedAfterDate(date);

        for (User user : usersNotLogged) {
            user.setDeleted(true);
        }
    }

    @Override
    public void deleteUsers() {
        List<User> users = userRepository.findAllByIsDeleted(true);

        for (User user : users) {

            for (Album album : user.getAlbums()) {
                album.setUser(null);
            }

            for (User friend : user.getFriends()) {
                friend.getUsers().remove(user);
            }

//            deleteJoinTable();

            for (User friendUser : user.getUsers()) {
                friendUser.getFriends().remove(user);
            }

//            deleteFromJoinTable(user.getId());

            userRepository.delete(user);
        }

    }

    private void insertIntoJoinTable(Integer id, Integer friend_id) {

        entityManager.createNativeQuery("insert into users_users where values(:id, :friend_id)")
                .setParameter("id", id)
                .setParameter("friend_id", friend_id).executeUpdate();


    }

    private void deleteFromJoinTable(int id) {

        entityManager.createNativeQuery("delete from users_users where friend_id = :id")
                .setParameter("id", id).executeUpdate();


    }

    private void deleteJoinTable() {
        Session session = entityManager.unwrap(Session.class);
//        entityManager.createQuery("")
//                .executeUpdate();
        session.createSQLQuery("delete from users_users").executeUpdate();

    }


}
