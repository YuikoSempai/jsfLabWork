package DBworkers;

import model.CircleShape;
import model.CoordinateData;
import model.Shape;
import model.SquareShape;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DBWorker {

    private static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(CoordinateData.class)
            .addAnnotatedClass(CircleShape.class)
            .addAnnotatedClass(SquareShape.class)
            .buildSessionFactory();

    public static void addElement(CoordinateData dot, Shape shape) {
        Session session = sessionFactory.getCurrentSession();
        if (dot.getShape().equals("circle")) {
            dot.setCircleShape(new CircleShape(shape.getSize()));
        } else {
            dot.setSquareShape(new SquareShape(shape.getSize()));
        }
        dot.setStatus(false);
        session.beginTransaction();
        session.save(dot);
        session.getTransaction().commit();
    }

    public static List<CoordinateData> getAllElements() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<CoordinateData> dots = session.createQuery("from CoordinateData").getResultList();
        session.getTransaction().commit();
        return dots;
    }

    public static void deleteAllElements() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete CoordinateData").executeUpdate();
        session.getTransaction().commit();
    }
}
