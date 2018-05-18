package com.aalegz.hibernate.demo;

import com.aalegz.hibernate.demo.entity.Course;
import com.aalegz.hibernate.demo.entity.Instructor;
import com.aalegz.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class EagerLazyDemo {

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
            //start a transaction
            session.beginTransaction();

            //get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("\nFetch: Instructor: " + tempInstructor);

            //get courses for the instructor
            System.out.println("\nFetch: Courses: " + tempInstructor.getCourses());

            //commit transaction
            session.getTransaction().commit();

            //close the session
            session.close();
            //get courses for the instructor
            System.out.println("\nFetch: Courses: " + tempInstructor.getCourses());


            System.out.println("\nFetch: Done!");
        } finally {
            //add clean up code
            session.close();

            sessionFactory.close();
        }

    }
}
