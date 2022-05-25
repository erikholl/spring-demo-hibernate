package io.eho.hbonetomanyuni;

import io.eho.hbonetomanyuni.entity.Course;
import io.eho.hbonetomanyuni.entity.Instructor;
import io.eho.hbonetomanyuni.entity.InstructorDetail;
import io.eho.hbonetomanyuni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo {

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

            // get the course
            int id = 10;
            Course course = session.get(Course.class, id);

            // print the course
            System.out.println("course: " + course);

            // print the course reviews
            System.out.println("course reviews: " + course.getReviews());

            // delete the course
            System.out.println("deleting the course and its reviews");
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
