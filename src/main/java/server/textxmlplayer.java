package server;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.*;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;


public class textxmlplayer {
    public static void main(final String[] args) throws SAXException, IOException{
        player pl = new player(0,"Benjamin","Hartmann",29, "ElMuldo");
    }
}
