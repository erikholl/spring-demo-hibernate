package io.eho.hbcrudbasics.exercise;

import io.eho.hbcrudbasics.exercise.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Random;

public class CreateEmployee {

    public static void main(String[] args) {

        // create session-factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-ee.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        // create employees
        String[] firstNames = {"John", "Erik", "Lisa", "Roger", "Phoebe"};
        String[] lastNames = {"Doo", "Foo", "Woo", "Boo", "Goo", "Too"};
        String[] companies = {"4MS", "3M", "2A", "11AND", "SUPER7"};
        Employee[] employees = new Employee[5];

        for (int i = 0; i < 5; i++) {
            Random r = new Random();
            int index1 = r.nextInt(firstNames.length);
            int index2 = r.nextInt(lastNames.length);
            int index3 = r.nextInt(companies.length);
//            String firstName = firstNames[index1];
            Employee ee = new Employee(firstNames[index1], lastNames[index2],
                                       companies[index3]);
            employees[i] = ee;
        }

        try {
            // start transaction
            session.beginTransaction();

            // save employee(s) to DB
            for (Employee employee : employees) {
                session.save(employee);
            }

            // commit transaction
            session.getTransaction().commit();

        } finally {
            // close factory / session
            factory.close();
        }

    }

}
