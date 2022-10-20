package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.CoordinateData;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.SQLException;

@Local
@Stateless
public class CoordinateDao {
    private EntityTransaction transaction;
    private EntityManager manager;

    protected EntityManager getEntityManager() {
        if (this.manager == null) {
            this.manager = Persistence.createEntityManagerFactory("postgres").createEntityManager();
        }
        return manager;
    }

    private EntityTransaction getTransaction() {
        transaction = getEntityManager().getTransaction();
        return transaction;
    }

    public CoordinateData createCoordinateData(CoordinateData entity) throws SQLException {
        if (!this.getTransaction().isActive()) {
            getTransaction().begin();
        }
        try {
            getEntityManager().persist(entity);
            getEntityManager().flush();
            getTransaction().commit();
            return entity;
        } catch (Exception e) {
            System.out.println("--------> CreateException : " + entity.getClass().toString() + " - " + e.getMessage());
            //TODO:: add logger
            if (!this.getTransaction().isActive()) {
                getTransaction().begin();
            }
            getTransaction().rollback();
            throw new SQLException(e.getMessage());
        } finally {
            getTransaction().commit();
        }
    }
}
