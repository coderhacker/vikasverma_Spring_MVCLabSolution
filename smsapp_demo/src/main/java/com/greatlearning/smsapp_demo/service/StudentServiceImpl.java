package com.greatlearning.smsapp_demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.greatlearning.smsapp_demo.entity.Student;

@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;

	// create session
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}

	@Transactional
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		List<Student> Student = session.createQuery("from Student", Student.class).list();

		tx.commit();

		return Student;

	}

	@Transactional
	public Student findById(int theId) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class, theId);

		tx.commit();
		return student;

	}

	@Transactional
	public void save(Student theStudent) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(theStudent);
		tx.commit();

	}

	@Transactional
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();

		try {
			Student book = session.get(Student.class, theId);
			session.delete(book);
		} finally {
			tx.commit();
		}

	}

}
