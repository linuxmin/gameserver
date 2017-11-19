package server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBConnection {
    private static final Logger LOGGER = LogManager.getLogger(DBConnection.class);
    private static SessionFactory sessionFactory =  new Configuration()
            .configure()
            .buildSessionFactory();
    Session session = null;
    Transaction tx = null;

    DBConnection(){}

    public void close (){
        if(session != null){
            session.close();
        }
    }

    public void write(DBObject dbObject){
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        try {
            session.save(dbObject);
            session.flush();
            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
            session.close();
        }
    }

    public boolean query(String name) throws Exception{
        try {
            session = sessionFactory.openSession();
            session.createNamedQuery(name)
                    .getSingleResult();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            if(session != null){
                session.close();
            }
        }

    }
}
