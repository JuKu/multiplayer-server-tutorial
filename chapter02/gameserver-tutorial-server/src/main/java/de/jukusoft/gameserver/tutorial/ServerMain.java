package de.jukusoft.gameserver.tutorial;

import de.jukusoft.gameserver.tutorial.engine.IGameServer;
import de.jukusoft.gameserver.tutorial.engine.GameServerImpl;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by Justin on 25.10.2016.
 */
public class ServerMain {

    public static void main (String[] args) {
        //configure logger to print all to console
        BasicConfigurator.configure();

        //create new game server
        IGameServer server = new GameServerImpl();
    }

}
