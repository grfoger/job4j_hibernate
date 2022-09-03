package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/scripts/004_init_orders.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndUpdateAndFindAll() {
        OrdersStore store = new OrdersStore(pool);

        Order base = store.save(Order.of("name1", "description1"));
        Order update = Order.of("name2", "description2");
        update.setId(base.getId());
        store.update(update);

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description2"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrdersAndFindByIdAndByName() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name", "description1"));
        store.save(Order.of("name", "description2"));

        List<Order> byName = (List<Order>) store.findByName("name");
        Order rsl = store.findById(2);

        assertThat(byName.size(), is(2));
        assertThat(rsl.getDescription(), is("description2"));
    }

    @After
    public void dropTable() throws SQLException{
        pool.getConnection().prepareStatement("drop table orders").executeUpdate();
    }
}