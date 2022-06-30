package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.hql.model.Candidate;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            /*
            Candidate one = Candidate.of("Junior Vasiliy", 3, 130.0);
            Candidate two = Candidate.of("Middle Nikolay", 5, 250.0);
            Candidate three = Candidate.of("Senior Alexey", 10, 400.0);

            session.save(one);
            session.save(two);
            session.save(three);
*/
            Query query = session.createQuery("from Candidate");
            for (Object cand : query.list()) {
                System.out.println(cand);
            }
/*
            Query query = session.createQuery("from Candidate c where c.id = 2");
            System.out.println(query.uniqueResult());

            Query query = session.createQuery("from Candidate c where c.name = :fName");
            query.setParameter("fName", "Junior Vasiliy");
            for (Object cand : query.list()) {
                System.out.println(cand);
            }

            session.createQuery("update Candidate c set c.name = :newName,"
                            + " c.experience = :newExp, "
                            + " c.salary = :newSalary "
                            + "where c.id = :fId")
                    .setParameter("newName", "Middle Vasiliy")
                    .setParameter("newExp", 4)
                    .setParameter("newSalary", 180.0)
                    .setParameter("fId", 1)
                    .executeUpdate();

            session.createQuery("delete from Candidate where id = :fId")
                .setParameter("fId", 2)
                .executeUpdate();

            session.createQuery("insert into Candidate (name, experience, salary) "
                + "select 'Vasiliy', c.experience + 1, c.salary + 20.0  "
                + "from Candidate c where c.id = :fId")
                .setParameter("fId", 1)
                .executeUpdate();
            */


            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}