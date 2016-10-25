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
        if (port <= 0) {
            throw new IllegalArgumentException("port number has to be greater than 0.");
        }

        //set port
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void setBossThreads(int nOfBossThreads) {
        if (nOfBossThreads <= 0) {
            throw new IllegalArgumentException("number of boss threads cannot be 0 or lesser.");
        }

        this.nOfBossThreads = nOfBossThreads;
    }

    public int getNumberOfBossThreads () {
        return this.nOfBossThreads;
    }

    public void setWorkerThreads(int nOfWorkerThreads) {
        if (nOfBossThreads <= 0) {
            throw new IllegalArgumentException("number of worker threads cannot be 0 or lesser.");
        }

        this.nOfWorkerThreads = nOfWorkerThreads;
    }

    public int getNumberOfWorkerThreads () {
        return this.nOfWorkerThreads;
    }

}
