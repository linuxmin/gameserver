package server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class QueryDBOpenGame {
    private static final Logger LOGGER = LogManager.getLogger(QueryDBOpenGame.class);
     Integer player_id;

    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        return sessionFactory;
    }

    QueryDBOpenGame(Integer player_id){
        this.player_id = player_id;
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
            LOGGER.debug(game);
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

    public Integer query(Integer player_id)
    {
        //    this.player = player;
        configureSessionFactory();
        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Game game = session.createNamedQuery("get_game_id_player", Game.class).setParameter("id",player_id)
                    .getSingleResult();
            Integer game_id = game.getGame_id();
            session.flush();
            tx.commit();
            return game_id;
            //session.save(player);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return 0;
        } finally {
            if(session != null){
                session.close();
            }
        }
    }
}
