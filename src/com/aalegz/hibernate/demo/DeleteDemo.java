package com.aalegz.hibernate.demo;

import com.aalegz.hibernate.demo.entity.Instructor;
import com.aalegz.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteDemo {

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

            //get the instructor by the primary key / id
            int theId = 1;

            Instructor tempInstructor =
                    session.get(Instructor.class, theId);

            System.out.println("Found instructor: " +tempInstructor);

            //delete the instructor
            if (tempInstructor != null) {

                System.out.println("\nDeleting: " + tempInstructor);
                //** Also delete detail object because CascadeType.All
                session.delete(tempInstructor);
            }

            //commit transaction
            session.getTransaction().commit();
            System.out.println("\nDone!");
        } finally {
            sessionFactory.close();
        }

    }
}
