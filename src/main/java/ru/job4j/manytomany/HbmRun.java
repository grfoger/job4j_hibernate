package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.manytomany.model.Author;
import ru.job4j.manytomany.model.Book;


public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book lotr = Book.of("Lord of the Rings");
            Book tcon = Book.of("The Chronicles of Narnia");
            Book mc = Book.of("Mere Christianity");

            Author tolkien = Author.of("JRR Tolkien");
            tolkien.getBooks().add(lotr);
            tolkien.getBooks().add(mc);

            Author lewis = Author.of("CS Lewis");
            lewis.getBooks().add(tcon);
            lewis.getBooks().add(mc);

            session.persist(tolkien);
            session.persist(lewis);

            Author author = session.get(Author.class, tolkien.getId());
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
