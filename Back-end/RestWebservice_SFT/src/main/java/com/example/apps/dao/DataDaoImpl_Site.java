package com.restbody.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.restbody.model.Comment;
import com.restbody.model.Site;

public class DataDaoImpl_Site implements DataDao_Site{
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public boolean addEntity(Site site) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(site);
		tx.commit();
		session.close();

		return false;
	}
	
	@Override
	public Site getEntityByName(String name) throws Exception {
		System.out.println("get site started ");
		session = sessionFactory.openSession();
		
		String hql = "from Site where siteName =:siteName";
		Query query = session.createQuery(hql);
		query.setString("siteName", name);
		List<Site> results = query.list();
		
		System.out.println("getted site : "+results);
		
		session.close();
		return (Site)results.get(0);
		
		/*Comment comment = (Comment) session.load(Comment.class,
				new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return comment;*/
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Site> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Site> siteList = session.createCriteria(Site.class)
				.list();
		tx.commit();
		session.close();
		return siteList;
	}

}
