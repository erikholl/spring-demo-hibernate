package io.eho.hbeagervslazy;

import io.eho.hbeagervslazy.entity.Course;
import io.eho.hbeagervslazy.entity.Instructor;
import io.eho.hbeagervslazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            // option2 for ensuring lazy data can be called after closing the
            // session: Hibernate query with HQL

            int id = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                        + "JOIN FETCH i.courses "
                                        + "where i.id=:theInstructorId",
                                        Instructor.class);

            // set parameter query
            query.setParameter("theInstructorId", id);

            // execute query and get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("mycode: Instructor: " + instructor);

            // commit transaction
            session.getTransaction().commit();

            // close session
            session.close();
            System.out.println("\nmycode: session is closed\n");

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
