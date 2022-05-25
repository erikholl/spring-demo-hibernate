package io.eho.hbonetomanyuni;

import io.eho.hbonetomanyuni.entity.Course;
import io.eho.hbonetomanyuni.entity.Instructor;
import io.eho.hbonetomanyuni.entity.InstructorDetail;
import io.eho.hbonetomanyuni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndReviewsDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-one-to-many-uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start transaction
            session.beginTransaction();

            // create a course
            Course course = new Course("Modular synthesis - waveforms");
            System.out.println("saving the course\n" + course);

            // add some reviews
            course.addReview(new Review("Top course!"));
            course.addReview(new Review("Dumb course!"));
            course.addReview(new Review("Lovely course!"));

            // save the course ... and leverage the cascade all :)
            session.save(course);

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
