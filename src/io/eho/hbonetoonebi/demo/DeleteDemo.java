package io.eho.hbonetoonebi.demo;

import io.eho.hbonetoonebi.demo.entity.Instructor;
import io.eho.hbonetoonebi.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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

            // get instructor by primary key / id
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);
            System.out.println("Found instructor: " + instructor);

            // delete the instructor
            if (instructor != null) {
                System.out.println("Deleting: " + instructor);
                // will also delete the associated object instructorDetail
                session.delete(instructor);
            }
            // commit transaction
            session.getTransaction().commit();
            System.out.println("deletion done!!");
        }
        finally {
            System.out.println("closing factory");
            factory.close();
        }

        System.out.println("completed");
    }
}
