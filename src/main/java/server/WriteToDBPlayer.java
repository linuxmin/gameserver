package server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class WriteToDBPlayer {
    Session session = null;
    Transaction tx = null;
    Player player;

    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        return sessionFactory;
    }



    public WriteToDBPlayer(Player player)
    {
        this.player = player;
        configureSessionFactory();

    }

    public void write(){
        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(this.player);
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
