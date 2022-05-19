package io.eho.hbonetoonebi.demo;

import io.eho.hbonetoonebi.demo.entity.Instructor;
import io.eho.hbonetoonebi.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-one-to-one-uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId = 23;
            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);

            // print the instructor detail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            // print the associated instructor
            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("transaction done!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("closing factory");
            session.close();
            factory.close();
        }

        System.out.println("completed");
    }
}
