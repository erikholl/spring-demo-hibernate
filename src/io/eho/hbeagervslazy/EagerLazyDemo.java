package io.eho.hbeagervslazy;

import io.eho.hbeagervslazy.entity.Course;
import io.eho.hbeagervslazy.entity.Instructor;
import io.eho.hbeagervslazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-one-to-many.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start transaction
            session.beginTransaction();

            // get instructor from DB
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);
            System.out.println("mycode: Instructor: " + instructor);

            // option1: load data into memory before closing the session in
            // order to call it later on after session is closed
            System.out.println("mycode: Courses: " + instructor.getCourses());

            // commit transaction
            session.getTransaction().commit();

            // close session
            session.close();
            System.out.println("\nmycode: session is closed\n");

            // retrieve courses from DB - should be possible as we loaded it
            // prior to closing the session
            System.out.println("mycode: Courses: " + instructor.getCourses());

            System.out.println("mycode: done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }

    }
}
