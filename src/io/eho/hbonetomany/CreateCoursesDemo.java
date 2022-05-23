package io.eho.hbonetomany;

import io.eho.hbonetomany.entity.Course;
import io.eho.hbonetomany.entity.Instructor;
import io.eho.hbonetomany.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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

            // create some courses
            Course course1 = new Course("Air Guitar - The Ultimate Guide");
            Course course2 = new Course("Pinball Masterclass");

            // add course to instructor
            instructor.add(course1);
            instructor.add(course2);

            // save courses
            session.save(course1);
            session.save(course2);

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
