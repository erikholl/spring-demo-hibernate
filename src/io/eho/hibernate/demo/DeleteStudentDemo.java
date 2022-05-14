package io.eho.hibernate.demo;

import io.eho.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-student.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;

            // get a new session and start transaction
//            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on id
            System.out.println("\nGetting student with id: " + studentId);

            Student myStudent = session.get(Student.class, studentId);

            // delete student retrieved from DB
//            System.out.println("deleting student: " + myStudent);
//            session.delete(myStudent);

            // delete student id=2 with direct query
            System.out.println("deleting student with id=2");
            session.createQuery("delete from Student where id=2").executeUpdate();

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("done");

        }
        finally {
            factory.close();
        }

    }

}
