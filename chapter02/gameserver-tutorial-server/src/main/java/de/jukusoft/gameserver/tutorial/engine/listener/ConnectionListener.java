package de.jukusoft.gameserver.tutorial.engine.listener;

/**
 * Created by Justin on 25.10.2016.
 */
public interface ConnectionListener {

    public void connectionOpened (long clientID);

    public void connectionClosed (long clientID);

}
