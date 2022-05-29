package io.eho.hbmanytomany;

import io.eho.hbmanytomany.entity.Student;
import io.eho.hbmanytomany.entity.Course;
import io.eho.hbmanytomany.entity.Instructor;
import io.eho.hbmanytomany.entity.InstructorDetail;
import io.eho.hbmanytomany.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-many-to-many.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start transaction
            session.beginTransaction();

            // create a course
            Course course = new Course("Joyful Hibernate");

            System.out.println("saving the course\n" + course);
            session.save(course);
            System.out.println("saved the course\n" + course);

            // create the students
            Student student1 = new Student("John", "Doe", "john@luv2code.com");
            Student student2 = new Student("Mary", "Chan", "mary@luv2code.com");
            Student student3 = new Student("Botik", "Zandschulp", "botik@luv2code.com");

            // add students to the course
            course.addStudent(student1);
            course.addStudent(student2);
            course.addStudent(student3);

            // save students
            System.out.println("saving students...");
            session.save(student1);
            session.save(student2);
            session.save(student3);
            System.out.println("saved students: " + course.getStudents());

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
