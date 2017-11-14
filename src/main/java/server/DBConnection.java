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

    DBConnection(){
        try{

            session = sessionFactory.openSession();


        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();

        }
    }

    public void close (){
        if(session != null){
            session.close();
        }
    }

    public void write(Object object){
        tx = session.beginTransaction();
        session.save(object);
        session.flush();
        tx.commit();
    }

    public boolean query(String name, String cl) throws Exception{
        try {
            session.createNamedQuery(name, )
                    .getSingleResult();
            return true;
        }catch(Exception e){
            return false;
        }

    }
}
