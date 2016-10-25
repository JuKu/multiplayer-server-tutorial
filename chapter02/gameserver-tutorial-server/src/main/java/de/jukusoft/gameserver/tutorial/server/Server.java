package de.jukusoft.gameserver.tutorial.server;

/**
 * Created by Justin on 25.10.2016.
 */
public interface Server {

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
