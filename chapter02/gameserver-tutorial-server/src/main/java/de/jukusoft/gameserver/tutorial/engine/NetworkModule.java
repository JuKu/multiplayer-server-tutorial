package de.jukusoft.gameserver.tutorial.engine;

import de.jukusoft.gameserver.tutorial.engine.config.ServerConfig;
import de.jukusoft.gameserver.tutorial.engine.listener.ConnectionListener;
import de.jukusoft.gameserver.tutorial.engine.protocol.MessageReceiver;
import de.jukusoft.gameserver.tutorial.engine.protocol.NetworkMessage;
import de.jukusoft.gameserver.tutorial.engine.uniqueid.IDGenerator;

/**
 * Created by Justin on 25.10.2016.
 */
public interface NetworkModule<T extends NetworkMessage> {

    /**
    * set client id generator
     *
     * @param idGenerator instance of id generator
    */
    public void setClientIDGenerator (IDGenerator idGenerator);

    /**
    * configure network module
     *
     * @param config instance of server configuration
    */
    public void configure (ServerConfig config, IGameServer<T> server);

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

    /**
    * set connection listener
     *
     * @param listener connection listener
    */
    public void setConnectionListener (ConnectionListener listener);

}
