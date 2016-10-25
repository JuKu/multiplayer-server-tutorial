package de.jukusoft.gameserver.tutorial.engine.netty;

import de.jukusoft.gameserver.tutorial.engine.IGameServer;
import de.jukusoft.gameserver.tutorial.engine.NetworkModule;
import de.jukusoft.gameserver.tutorial.engine.protocol.MessageReceiver;
import de.jukusoft.gameserver.tutorial.engine.protocol.NetworkMessage;

/**
 * Created by Justin on 25.10.2016.
 */
public class NettyNetworkModule<T extends NetworkMessage> implements NetworkModule {

    @Override
    public void configure(IGameServer server) {

    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void shutdown() throws Exception {

    }

    @Override
    public void send(long clientID, NetworkMessage msg) {

    }

    @Override
    public void broadcast(NetworkMessage msg) {

    }

    @Override
    public void setNetworkReceiver(MessageReceiver receiver) {

    }

}
