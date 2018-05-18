package com.aalegz.hibernate.demo;

import com.aalegz.hibernate.demo.entity.Instructor;
import com.aalegz.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetInstructorDetailDemo {

    public static void main(String[] args) {

        //create session factory
        System.out.println("Session factory creation..");
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        System.out.println("Session factory created!\n");

        //create session
        System.out.println("Session creation");
        Session session = sessionFactory.getCurrentSession();
        System.out.println("Session created!\n");

        try {


            //start a transaction
            session.beginTransaction();

            //get the instructor detail object
            int theId = 2;

            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);

            //print the instructor detail object
            System.out.println("\ntempInstructorDetail: " + tempInstructorDetail);

            //print the associated instructor
            System.out.println("\nthe associated instructor: " +
                               tempInstructorDetail.getInstructor());

            //commit transaction
            session.getTransaction().commit();
            System.out.println("\nDone!");
        } finally {
            sessionFactory.close();
        }

    }
}
