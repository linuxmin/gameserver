package server;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class QueryDBOpenGame {
     Player player;

    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        return sessionFactory;
    }

    QueryDBOpenGame(Player player){
        this.player = player;
    }

    public Game insert(Player player){
        //    this.player = player;
        configureSessionFactory();
        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Game game = session.createNamedQuery("get_open_game", Game.class)
                    .getSingleResult();
            game.setPlayer2_id(player.getPlayer_id());
            session.save(game);
             session.flush();
            tx.commit();
            return game;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            Game game = new Game(-1);
            return game;

        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public boolean query()
    {
        //    this.player = player;
        configureSessionFactory();
        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.createNamedQuery("get_open_game", Game.class)
                    .getSingleResult();
            return true;
            //session.save(player);
           // session.flush();
          //  tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            if(session != null){
                session.close();
            }
        }
    }
}
