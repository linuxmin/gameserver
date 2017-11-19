package server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class WriteToDB {

    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        return sessionFactory;
    }



    public WriteToDB(DBObject dbObject)
    {


        configureSessionFactory();
        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(dbObject);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }
}
