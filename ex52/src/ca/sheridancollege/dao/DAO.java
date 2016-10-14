package ca.sheridancollege.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.sheridancollege.beans.Person;
import ca.sheridancollege.beans.Student;

public class DAO {

	SessionFactory sessionFactory = new Configuration()
			.configure("ca/sheridancollege/config/hibernate.cfg.xml")
			.buildSessionFactory();

	public void populate() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		for (int i = 1; i <= 10; i++) {
			Student student = new Student("Student " + i);
			session.save(student);
		}

		session.getTransaction().commit();
		session.close();
	}
	
	public void savePerson(String fName,String lName,int phoneNumber,String email){
	
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Person person=new Person(phoneNumber,fName,lName,email);
		
		session.save(person);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Person> retreivePerson() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query= session.createQuery("from Person");
		
		
		List<Person> personList= (List<Person>) query.getResultList();
		

		session.getTransaction().commit();
		session.close();
		
		return personList;
	}
	
	public List<Person> queryPerson(String fName,String lName) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CriteriaBuilder criteriaBuilder= session.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = criteriaBuilder.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		criteria.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"), fName), 
				criteriaBuilder.and(criteriaBuilder.equal(root.get("lastName"), lName)),
				criteriaBuilder.or(criteriaBuilder.like(root.get("email"), "%.com"))));
		criteria.orderBy(criteriaBuilder.desc(root.get("phoneNumber")));
		
		List<Person> personList= session.createQuery(criteria).getResultList();
		
		/*Query query= session.createQuery("from Person where firstName=:fName and lastName=:lName");
		
		query.setParameter("fName", fName);
		query.setParameter("lName", lName);
		
		List<Person> personList= (List<Person>) query.getResultList();
		
		*/

		session.getTransaction().commit();
		session.close();
		
		return personList;
	}
	
	public List<Student> studentList(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query= session.createQuery("from Student where id=?");
		
		query.setParameter(0, 5);
		
		List<Student> studentList= (List<Student>) query.getResultList();
		
		System.out.println("Size of list returned is: " + studentList.size());
		
		session.getTransaction().commit();
		session.close();
		
		return studentList;
		
	}

	public Student getStudent() {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Student transientStudent = new Student();
		transientStudent.setName("Justin");
				
		Student temp = session.get(Student.class, 5);
		System.out.println("Student retrieved is " + temp.getName());
		
		temp.setName("Frank");
		
		session.getTransaction().commit();
		session.close();
		
		temp.setName("Frankie");

		return temp;
		
	}
	
	public void deleteStudent() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Student toDelete = session.get(Student.class, 7);
		session.delete(toDelete);

		session.getTransaction().commit();
		session.close();

	}
	
	public void insertStudent(Student student) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(student);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
}
