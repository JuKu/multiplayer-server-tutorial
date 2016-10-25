package de.jukusoft.gameserver.tutorial.engine;

import de.jukusoft.gameserver.tutorial.engine.protocol.MessageReceiver;
import de.jukusoft.gameserver.tutorial.engine.protocol.NetworkMessage;

/**
 * Created by Justin on 25.10.2016.
 */
public interface NetworkModule<T extends NetworkMessage> {

    /**
    * configure network module
     *
     * @param server instance of game server to get configuration
    */
    public void configure (IGameServer server);

    /**
    * start network module
    */
    public void start () throws Exception;

    /**
    * shutdown network module
    */
    public void shutdown () throws Exception;

    /**
    * send message to client with specific id
     *
     * @param clientID unique id of client
     * @param msg network message which should be sended to client
    */
    public void send (long clientID, T msg);

    /**
    * send message to every client
     *
     * @param msg instance of message
    */
    public void broadcast (T msg);

    /**
    * set network receiver
    */
    public void setNetworkReceiver (MessageReceiver<T> receiver);

}
