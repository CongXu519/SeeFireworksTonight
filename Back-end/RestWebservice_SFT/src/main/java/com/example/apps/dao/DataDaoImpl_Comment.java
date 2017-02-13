package com.restbody.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.restbody.model.Comment;
import com.restbody.model.Favorite;

public class DataDaoImpl_Comment implements DataDao_Comment {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Comment comment) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(comment);
		tx.commit();
		session.close();

		return false;
	}

	@Override
	public Comment getEntityById(long id) throws Exception {
		session = sessionFactory.openSession();
		Comment comment = (Comment) session.load(Comment.class,
				new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return comment;
	}
	
	@Override
	public List<Comment> getEntityByName(String name) throws Exception {
		session = sessionFactory.openSession();
		String hql = "from Comment where siteName =:siteName";
		Query query = session.createQuery(hql);
		query.setString("siteName", name);
		List<Comment> results = query.list();
		
		return results;
		
		/*Comment comment = (Comment) session.load(Comment.class,
				new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return comment;*/
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Comment> commentList = session.createCriteria(Comment.class)
				.list();
		tx.commit();
		session.close();
		return commentList;
	}
	
	@Override
	public boolean deleteEntity(long id)
			throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Comment.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

}
