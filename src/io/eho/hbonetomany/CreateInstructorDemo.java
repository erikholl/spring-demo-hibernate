package io.eho.hbonetomany;

import io.eho.hbonetomany.entity.Course;
import io.eho.hbonetomany.entity.Instructor;
import io.eho.hbonetomany.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

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

            // create objects
            Instructor tempInstructor = new Instructor("Susan", "Public",
                                                       "susan.public@luv2code" +
                                                               ".com");

            InstructorDetail tempInstructorDetail = new InstructorDetail(
                    "http://www.youtube.com", "Video Games");

            // associate objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start transaction
            session.beginTransaction();

            // save instructor
            System.out.println("saving instructor: " + tempInstructor);
            session.save(tempInstructor);

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
