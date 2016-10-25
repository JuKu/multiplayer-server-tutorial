package de.jukusoft.gameserver.tutorial.engine.impl;

import de.jukusoft.gameserver.tutorial.common.GameConstants;
import de.jukusoft.gameserver.tutorial.engine.IGameServer;
import de.jukusoft.gameserver.tutorial.engine.NetworkModule;
import de.jukusoft.gameserver.tutorial.engine.protocol.NetworkMessage;
import org.apache.log4j.Logger;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Justin on 25.10.2016.
 */
public class GameServerImpl<T extends NetworkMessage> implements IGameServer<T> {

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
    * instance of network module
    */
    protected NetworkModule<T> networkModule = null;

    /**
    * instance of logger
    */
    protected Logger logger = Logger.getRootLogger();

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
    public void setNetworkModule(NetworkModule<T> networkModule) {
        //check, if server is already running
        if (this.isRunning.get()) {
            //throw exception
            throw new IllegalStateException("You cannot set port number, if server is already running.");
        }

        //check, if network module is null
        if (networkModule == null) {
            //throw exception
            throw new NullPointerException("network module is null.");
        }

        //set network module
        this.networkModule = networkModule;
    }

    @Override
    public boolean isRunning() {
        return this.isRunning.get();
    }

    @Override
    public void start() throws Exception {
        //check, if server is already running
        if (this.isRunning.get()) {
            throw new IllegalStateException("server is already running.");
        }

        //check, if network module is set
        if (this.networkModule == null) {
            throw new NullPointerException("network module is null, set network module with method setNetworkModule() first.");
        }

        //configure network module
        this.networkModule.configure(this);

        //start network module
        this.networkModule.start();

        this.isRunning.set(true);
    }

    @Override
    public void shutdown() throws Exception {
        //check, if server isnt running
        if (!this.isRunning.get()) {
            throw new IllegalStateException("Cannot shutdown server, server isnt running.");
        }

        //shutdown network module
        this.networkModule.shutdown();

        //set running flag to false
        this.isRunning.set(false);
    }
}
