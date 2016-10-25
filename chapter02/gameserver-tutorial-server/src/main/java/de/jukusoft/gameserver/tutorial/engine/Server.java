package de.jukusoft.gameserver.tutorial.engine;

import de.jukusoft.gameserver.tutorial.engine.listener.ConnectionListener;
import de.jukusoft.gameserver.tutorial.engine.protocol.NetworkMessage;
import org.apache.log4j.Logger;

/**
 * Created by Justin on 25.10.2016.
 */
public interface Server<T extends NetworkMessage> {

    /**
    * set port, on which server should bind
     *
     * @param port server port
    */
    public void setPort (int port);

    /**
    * set number of boss threads
     *
     * @param nOfBossThreads number of boss threads
    */
    public void setBossThreads (int nOfBossThreads);

    /**
    * set number of worker threads
     *
     * @param nOfWorkerThreads number of worker threads
    */
    public void setWorkerThreads (int nOfWorkerThreads);

    /**
    * set instance of logger
     *
     * @param logger instance of logger
    */
    public void setLogger (Logger logger);

    /**
    * get instance of logger
     *
     * @return instance of logger
    */
    public Logger getLogger ();

    /**
    * set connection listener
     *
     * @param listener instance of connection listener
    */
    public void setConnectionListener (ConnectionListener listener);

    /**
    * set network module
     *
     * @param networkModule instance of network module
    */
    public void setNetworkModule (NetworkModule<T> networkModule);

    /**
    * checks, if server is running
     *
     * @return true, if server is running
    */
    public boolean isRunning ();

    /**
    * start server
    */
    public void start () throws Exception;

    /**
    * shutdown server
    */
    public void shutdown () throws Exception;

}
