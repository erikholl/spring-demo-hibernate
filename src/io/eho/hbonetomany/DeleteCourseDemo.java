package io.eho.hbonetomany;

import io.eho.hbonetomany.entity.Course;
import io.eho.hbonetomany.entity.Instructor;
import io.eho.hbonetomany.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

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

            // get course
            int id = 10;
            Course course = session.get(Course.class, id);

            // delete course
            System.out.println("deleting course: " + course);
            session.delete(course);

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
