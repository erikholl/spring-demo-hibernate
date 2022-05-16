package io.eho.hbcrudbasics.exercise;

import io.eho.hbcrudbasics.exercise.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReadEmployee {

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

            // retrieve employee by id
            System.out.println("getting employee from DB based on id");
            List<Employee> eeListById =
                    session.createQuery("from Employee e where e.id='2'").getResultList();
            displayEmployees(eeListById);

            // retrieve employees by company
            System.out.println("getting employees from DB based on company");
            List<Employee> eeListByCompany = session.createQuery("from " +
                                                                         "Employee e where e.company LIKE '4MS'").getResultList();
            displayEmployees(eeListByCompany);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

        System.out.println("done!");
    }

    private static void displayEmployees(List<Employee> eeList) {
        for (Employee employee : eeList) {
            System.out.println(employee);
        }
    }
}
