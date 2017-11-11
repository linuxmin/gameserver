package server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class testxmlplayer {

    public static void writePlayer(final String filename) throws Exception{
        final JAXBContext context = JAXBContext.newInstance(Player.class);
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        try(final OutputStream os = new BufferedOutputStream(new FileOutputStream(filename)))
        {
            final Player pl = new Player(0,"Benjamin","Hartmann",29, "ElMuldo");
             marshaller.marshal(pl,os);
        }
    }

    public static Player readPlayer(final String filename) throws Exception{
        final JAXBContext context = JAXBContext.newInstance(Player.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();

        try(final InputStream is = new BufferedInputStream(new FileInputStream(filename)))
        {
            return (Player) unmarshaller.unmarshal(is);
        }
    }

    public static Player readDBPlayer() throws Exception{
        final JAXBContext context = JAXBContext.newInstance(Player.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();

        try(final InputStream is = new BufferedInputStream(new FileInputStream("kdf")))
        {
            return (Player) unmarshaller.unmarshal(is);
        }
    }
    // initialise logger for testxmlplayer class
    private static final Logger LOGGER = LogManager.getLogger(testxmlplayer.class);

    public static void main(final String[] args) throws Exception{
        String home = System.getProperty("user.home");
        //File f = new File(home + "/.config/gfgd.gfgdf");
        final Player pl = testxmlplayer.readPlayer("/home/linuxmin/gameserver/src/main/resources/requestregisternewplayer.xml");
        final int output = pl.getPlayer_id();
        LOGGER.info("new logrun started" + "\n");
        LOGGER.debug(output);
        //System.out.println(output);

    }
}
