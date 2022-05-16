package io.eho.hbcrudbasics.exercise;

import io.eho.hbcrudbasics.exercise.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteEmployee {

    public static void main(String[] args) {

        // create session-factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-ee.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start transaction
            session.beginTransaction();

            // set id to delete from DB
            int eeId = 5;

            // retrieve employee with id
            System.out.println("\ngetting employee with id: " + eeId);
            Employee eeToDelete = session.get(Employee.class, eeId);

            // deleting employee retrieved from DB
            System.out.println("\ndeleting employee: " + eeToDelete);
            session.delete(eeToDelete);
        } finally {
            // close session / factory
            System.out.println("closing factory");
            factory.close();
        }

        // confirm completion
        System.out.println("done!");





    }
}
