package de.jukusoft.gameserver.tutorial.engine.netty;

import de.jukusoft.gameserver.tutorial.engine.IGameServer;
import de.jukusoft.gameserver.tutorial.engine.NetworkModule;
import de.jukusoft.gameserver.tutorial.engine.Server;
import de.jukusoft.gameserver.tutorial.engine.config.ServerConfig;
import de.jukusoft.gameserver.tutorial.engine.protocol.MessageReceiver;
import de.jukusoft.gameserver.tutorial.engine.protocol.NetworkMessage;

/**
 * Created by Justin on 25.10.2016.
 */
public class DummyNetworkModule<T extends NetworkMessage> implements NetworkModule<T> {

    @Override
    public void configure(ServerConfig config, IGameServer<T> server) {

    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void shutdown() throws Exception {

    }

    @Override
    public void send(long clientID, T msg) {

    }

    @Override
    public void broadcast(T msg) {

    }

    @Override
    public void setNetworkReceiver(MessageReceiver<T> receiver) {

    }

}
