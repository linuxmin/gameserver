package server;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

//import org.hibernate.mapping.List;


public class TestDBPersonToXML {
    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        return sessionFactory;
    }

    public static void main(final String[] args) throws Exception{
        configureSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Player pl = session.createNamedQuery("get_player_by_first_name", Player.class)
                .setParameter("name","Franco")
                .getSingleResult();
    //    System.out.println(pl.getLast_name());
        JAXBContext context = JAXBContext.newInstance(Player.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(pl,new File("/home/linuxmin/gameserver/src/main/resources/response.xml"));
        //marshaller.marshal(pl,System.out);
        //System.out.println();

    }
}

