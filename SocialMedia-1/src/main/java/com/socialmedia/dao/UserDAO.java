package com.socialmedia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.socialmedia.entity.User;

@Repository
@Transactional
public class UserDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public User getActiveUser(String userName) {
		User activeUser = new User();
		short enabled = 1;
		List<?> list = entityManager.createQuery("SELECT u FROM User u WHERE userName=? and enabled=?")
				.setParameter(1, userName).setParameter(2, enabled).getResultList();
		if (!list.isEmpty()) {
			activeUser = (User) list.get(0);
		}
		return activeUser;
	}

	public User getUserById(int userId) {
		return entityManager.find(User.class, userId);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		String hql = "FROM User as usr ORDER BY usr.userId";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}

	public void addUser(User user) {
		entityManager.persist(user);
	}

	public void updateUser(User user) {
		User usr = getUserById(user.getUserId());
		usr.setFullName(user.getFullName());
		usr.setCountry(user.getCountry());
		entityManager.flush();
	}

	public void deleteUser(int userId) {
		entityManager.remove(getUserById(userId));
	}

	public boolean userExists(String title, String category) {
		String hql = "FROM User as usr WHERE usr.title = ? and usr.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title).setParameter(2, category).getResultList()
				.size();
		return count > 0 ? true : false;
	}

}