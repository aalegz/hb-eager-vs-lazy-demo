package com.aalegz.hibernate.demo;

import com.aalegz.hibernate.demo.entity.Course;
import com.aalegz.hibernate.demo.entity.Instructor;
import com.aalegz.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructorDemo {

    public static void main(String[] args) {

        //create session factory
        System.out.println("Session factory creation..");
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        System.out.println("Session factory created!\n");

        //create session
        System.out.println("Session creation");
        Session session = sessionFactory.getCurrentSession();
        System.out.println("Session created!\n");

        try {
            //create the objects
            Instructor tempInstructor =
                    new Instructor("Jommy", "Wooo", "jw@mail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail(
                            "youtube.com/game",
                            "games");

            //associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //start a transaction
            session.beginTransaction();

            //save the instructor
            //** this Also save detail object because CascadeType.ALL
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("\nDone!");
        } finally {
            //add clean up code
            session.close();

            sessionFactory.close();
        }

    }
}
