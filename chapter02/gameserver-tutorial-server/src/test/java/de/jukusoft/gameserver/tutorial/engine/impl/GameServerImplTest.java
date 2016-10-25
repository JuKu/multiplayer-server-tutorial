package de.jukusoft.gameserver.tutorial.engine.impl;

import de.jukusoft.gameserver.tutorial.engine.netty.DummyNetworkModule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Justin on 25.10.2016.
 */
public class GameServerImplTest {

    @Test (expected = NullPointerException.class)
    public void testGameServerStartWithoutNetworkModule () throws Exception {
        //create new game server
        GameServerImpl server = new GameServerImpl();

        //start game server
        server.start();
    }

    @Test (expected = IllegalStateException.class)
    public void testServerShutdown () throws Exception {
        //create new game server
        GameServerImpl server = new GameServerImpl();

        //shutdown server
        server.shutdown();
    }

    @Test
    public void testState () throws Exception {
        //create new game server
        GameServerImpl server = new GameServerImpl();

        //check state
        assertEquals("server isnt running, but state is running.", false, server.isRunning());

        //set dummy network module
        server.setNetworkModule(new DummyNetworkModule());

        //start server
        server.start();

        //check state
        assertEquals("server is running, but state isnt running.", true, server.isRunning());
    }

    @Test
    public void testSetPort () {
        //create new game server
        GameServerImpl server = new GameServerImpl();

        int port = 8888;

        //set port
        server.setPort(port);

        //check port
        assertEquals("port isnt " + port, port, server.getPort());
    }

}
