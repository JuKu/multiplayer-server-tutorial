package de.jukusoft.gameserver.tutorial.engine.protocol;

/**
 * Created by Justin on 25.10.2016.
 */
public interface MessageReceiver<T extends NetworkMessage> {

    /**
    * message received from client
     *
     * @param senderID unique ID of client, which has sended this message
     * @param msg instance of network message
    */
    public void messageReceived (long senderID, long roomID, T msg);

}
