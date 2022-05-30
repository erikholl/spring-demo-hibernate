package io.eho.hbmanytomany;

import io.eho.hbmanytomany.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForMaryDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-many-to-many.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start transaction test comment
            session.beginTransaction();

            // get the student Mary from DB
            int id = 2;
            Student student = session.get(Student.class, id);

            // diagnostics
            System.out.println("\nLoaded student: " + student);
            System.out.println("Courses for student: " + student.getCourses());

            // delete student
            System.out.println("\n deleting student: " + student);
            session.delete(student);

            // commit transaction
             session.getTransaction().commit();

            System.out.println("done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }

    }
}
