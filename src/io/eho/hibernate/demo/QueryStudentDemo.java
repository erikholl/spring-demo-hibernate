package io.eho.hibernate.demo;

import io.eho.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-student.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> students =
                    session.createQuery("from Student").getResultList();

            // display students
            displayStudents(students);

            // query students last name = Doo
            students = session.createQuery("from Student s where s.lastName =" +
                                                   " 'Doo'").getResultList();

            // display students
            System.out.println("\nStudents who have last name Doo");
            displayStudents(students);

            // query students: lastName = Doe OR firstName = Daffy
            students = session.createQuery("from Student s where s.lastName =" +
                                                   " 'Doo' OR s.firstName = " +
                                                   "'Daffy'").getResultList();
            System.out.println("\nStudents who have last name of Doo or first" +
                                       " name of Daffy");
            displayStudents(students);

            // query students: email LIKE '%whatever.com'
            students = session.createQuery("from Student s where s.email LIKE" +
                                                   " '%gmail.com'").getResultList();
            System.out.println("\nStudents who have email ending with " +
                                       "whatever.com");
            displayStudents(students);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("done!");

        }
        finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }

}
