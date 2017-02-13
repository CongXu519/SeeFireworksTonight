package com.restbody.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.restbody.model.Favorite;

public class DataDaoImpl_Favorite implements DataDao_Favorite{
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public List<Favorite> getEntityByName(String username) throws Exception {
		session = sessionFactory.openSession();
		
		String hql = "from Favorite where userName =:userName";
		Query query = session.createQuery(hql);
		query.setString("userName", username);
		List<Favorite> results = query.list();
		
		return results;

	}

}
