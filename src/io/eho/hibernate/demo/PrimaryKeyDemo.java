package io.eho.hibernate.demo;

import io.eho.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

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

            // create 3 new student objects
            System.out.println("creating 3 student objects");
            Student tempStudent1 = new Student("John", "Doo", "john" +
                    "@whatever" +
                    ".com");
            Student tempStudent2 = new Student("Mary", "Public", "mary" +
                    "@whatever" +
                    ".com");
            Student tempStudent3 = new Student("Klaas", "Vaak", "klaas" +
                    "@whatever" +
                    ".com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("saving the  students");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("done");

        }
        finally {
            factory.close();
        }

    }
}
