package de.jukusoft.gameserver.tutorial.engine.config;

import de.jukusoft.gameserver.tutorial.common.GameConstants;

/**
 * Created by Justin on 25.10.2016.
 */
public class ServerConfig {

    /**
     * port in which server socket will bind
     */
    protected int port = GameConstants.DEFAULT_PORT;

    /**
     * number of threads in thread pool, per default 2
     */
    protected int nOfBossThreads = GameConstants.DEFAULT_BOSS_THREADS;
    protected int nOfWorkerThreads = GameConstants.DEFAULT_WORKER_THREADS;

    public void setPort(int port) {
        //set port
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void setBossThreads(int nOfBossThreads) {
        this.nOfBossThreads = nOfBossThreads;
    }

    public int getNumberOfBossThreads () {
        return this.nOfBossThreads;
    }

    public void setWorkerThreads(int nOfWorkerThreads) {
        this.nOfWorkerThreads = nOfWorkerThreads;
    }

    public int getNumberOfWorkerThreads () {
        return this.nOfWorkerThreads;
    }

}
