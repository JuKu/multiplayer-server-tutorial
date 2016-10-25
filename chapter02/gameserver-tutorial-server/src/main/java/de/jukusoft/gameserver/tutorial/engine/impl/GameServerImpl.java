package de.jukusoft.gameserver.tutorial.engine.impl;

import de.jukusoft.gameserver.tutorial.common.GameConstants;
import de.jukusoft.gameserver.tutorial.engine.IGameServer;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Justin on 25.10.2016.
 */
public class GameServerImpl implements IGameServer {

    /**
    * scheduled executor service
    */
    protected ScheduledExecutorService executorService = null;

    /**
    * port in which server socket will bind
    */
    protected int port = GameConstants.DEFAULT_PORT;

    /**
    * flag, if server is already running
    */
    protected AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
    * number of threads in thread pool, per default 2
    */
    protected int nOfBossThreads = 2;
    protected int nOfWorkerThreads = 2;

    /**
    * default constructor
    */
    public GameServerImpl () {
        //
    }

    @Override
    public void addIntervaledTask(long intervalInMS, Runnable task) {

    }

    @Override
    public void setPort(int port) {
        //check, if server is already running
        if (this.isRunning.get()) {
            //throw exception
            throw new IllegalStateException("You cannot set port number, if server is already running.");
        }

        //set port
        this.port = port;
    }

    @Override
    public void setBossThreads(int nOfBossThreads) {
        //check, if server is already running
        if (this.isRunning.get()) {
            //throw exception
            throw new IllegalStateException("You cannot set port number, if server is already running.");
        }

        this.nOfBossThreads = nOfBossThreads;
    }

    @Override
    public void setWorkerThreads(int nOfWorkerThreads) {
        //check, if server is already running
        if (this.isRunning.get()) {
            //throw exception
            throw new IllegalStateException("You cannot set port number, if server is already running.");
        }

        this.nOfWorkerThreads = nOfWorkerThreads;
    }

    @Override
    public boolean isRunning() {
        return this.isRunning.get();
    }

    @Override
    public void start() throws Exception {
        this.isRunning.set(true);
    }

    @Override
    public void shutdown() throws Exception {
        this.isRunning.set(false);
    }
}
