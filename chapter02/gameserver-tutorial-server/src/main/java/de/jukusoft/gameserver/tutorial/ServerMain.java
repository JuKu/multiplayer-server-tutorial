package de.jukusoft.gameserver.tutorial;

import de.jukusoft.gameserver.tutorial.engine.impl.GameServerImpl;
import de.jukusoft.gameserver.tutorial.engine.IGameServer;
import de.jukusoft.gameserver.tutorial.engine.netty.NettyTCPNetworkModule;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Created by Justin on 25.10.2016.
 */
public class ServerMain {

    public static void main (String[] args) {
        //configure logger to print all to console
        BasicConfigurator.configure();

        //create and configure new logger
        Logger logger = Logger.getLogger("GameServer");

        //create and configure new game server
        IGameServer server = new GameServerImpl();
        server.setPort(1234);
        server.setBossThreads(2);
        server.setWorkerThreads(2);

        //because we have some abstraction, we have to set an network module, in this case we will use netty with TCP
        server.setNetworkModule(new NettyTCPNetworkModule());

        try {
            //start game server
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
