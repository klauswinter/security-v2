package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Service
@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(long id, User updatedUser) {
        User userToBeUpdated = getUserById(id);

        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setSurname(updatedUser.getSurname());
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setJob(updatedUser.getJob());

    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void removeUserById(long id) {
        em.remove(getUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }
}

