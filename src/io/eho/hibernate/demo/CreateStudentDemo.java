package io.eho.hibernate.demo;

import io.eho.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-student.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // use session object to save Java object

            // create Student object
            System.out.println("creating new student object");
            Student tempStudent = new Student("Paul", "Wall", "paul@whatever" +
                    ".com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("saving the  student");
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("done");

        }
        finally {
            factory.close();
        }

    }

}
