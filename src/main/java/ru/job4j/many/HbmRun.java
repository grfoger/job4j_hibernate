package ru.job4j.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.many.model.Brand;
import ru.job4j.many.model.Model;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();

            session.beginTransaction();
            Model a = Model.of("Type A");
            Model b = Model.of("Type B");
            Model c = Model.of("Type C");
            Model d = Model.of("Type D");
            Model e = Model.of("Type E");

            session.save(a);
            session.save(b);
            session.save(c);
            session.save(d);
            session.save(e);

            Brand cheetah = Brand.of("Cheetah");
            cheetah.addModel(session.load(Model.class, a.getId()));
            cheetah.addModel(session.load(Model.class, b.getId()));
            cheetah.addModel(session.load(Model.class, c.getId()));
            cheetah.addModel(session.load(Model.class, d.getId()));
            cheetah.addModel(session.load(Model.class, e.getId()));
            session.save(cheetah);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
