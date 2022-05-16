package io.eho.hbonetooneuni.demo;

import io.eho.hbonetooneuni.demo.entity.Instructor;
import io.eho.hbonetooneuni.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-one-to-one-uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        // create instructor and instructorDetail objects
//        Instructor instructor = new Instructor("Chad", "Darby", "darby" +
//                "@luv2code.com");
//
//        InstructorDetail instructorDetail = new InstructorDetail(
//                "http://www.luv2code.come/youtube",
//                "Luv 2 code!!");

        Instructor instructor = new Instructor("Yo", "da", "yoda@tatooine.to");

        InstructorDetail instructorDetail = new InstructorDetail(
                "http://www.rebels.sx",
                "zen");


        // associate the objects
        instructor.setInstructorDetail(instructorDetail);

        try {
            // start a transaction
            session.beginTransaction();

            // save instructor - Instructor has cascade type ALL >>
            // instructorDetail will also be saved
            System.out.println("saving instructor: " + instructor);
            session.save(instructor);

            // commit transaction
            session.getTransaction().commit();
        }
        finally {
            // close factory / session
            System.out.println("closing factory");
            factory.close();
        }

        System.out.println("done!!");

    }
}
