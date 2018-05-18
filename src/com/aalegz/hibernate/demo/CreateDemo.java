package com.aalegz.hibernate.demo;

import com.aalegz.hibernate.demo.entity.Instructor;
import com.aalegz.hibernate.demo.entity.InstructorDetail;
import com.aalegz.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateDemo {

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
            //create the objects
            /*Instructor tempInstructor =
                    new Instructor("John", "Goaf", "gerrr@mail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail(
                            "youtube.com/channel",
                            "hockey");*/
            Instructor tempInstructor =
                    new Instructor("Bobby", "Knoxwell", "bk@mail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail(
                            "youtube.com/cha",
                            "football");

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
            sessionFactory.close();
        }

    }
}
