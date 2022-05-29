package io.eho.hbmanytomany;

import io.eho.hbmanytomany.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo {

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
            // start transaction
            session.beginTransaction();

            // get the student Mary from DB
            int id = 2;
            Student student = session.get(Student.class, id);

            // diagnostics
            System.out.println("\nLoaded student: " + student);
            System.out.println("Courses for student: " + student.getCourses());

            // create more courses
            Course course1 = new Course("Rubik's cube - how to speed cube");
            Course course2 = new Course("Chocolate figure melting");

            // add mary to new courses
            course1.addStudent(student);
            course2.addStudent(student);

            // save the new courses
            System.out.println("\nsaving courses...");
            session.save(course1);
            session.save(course2);
            System.out.println("\nsaved courses: " + course1 + "\n" + course2);

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
