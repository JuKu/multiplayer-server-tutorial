package de.jukusoft.gameserver.tutorial.engine.impl;

import de.jukusoft.gameserver.tutorial.engine.IGameServer;
import de.jukusoft.gameserver.tutorial.engine.NetworkModule;
import de.jukusoft.gameserver.tutorial.engine.config.ServerConfig;
import de.jukusoft.gameserver.tutorial.engine.listener.ConnectionListener;
import de.jukusoft.gameserver.tutorial.engine.protocol.NetworkMessage;
import org.apache.log4j.Level;
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
    * flag, if server is already running
    */
    protected AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
    * instance of server configuration
    */
    protected ServerConfig config = new ServerConfig();

    /**
    * instance of network module
    */
    protected NetworkModule<T> networkModule = null;

    /**
    * instance of logger
    */
    protected Logger logger = Logger.getRootLogger();

    /**
    * instance of connection listener
    */
    protected ConnectionListener connListener = null;

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
        this.config.setPort(port);
    }

    @Override
    public int getPort() {
        return this.config.getPort();
    }

    @Override
    public void setBossThreads(int nOfBossThreads) {
        //check, if server is already running
        if (this.isRunning.get()) {
            //throw exception
            throw new IllegalStateException("You cannot set port number, if server is already running.");
        }

        this.config.setBossThreads(nOfBossThreads);
    }

    @Override
    public void setWorkerThreads(int nOfWorkerThreads) {
        //check, if server is already running
        if (this.isRunning.get()) {
            //throw exception
            throw new IllegalStateException("You cannot set port number, if server is already running.");
        }

        this.config.setWorkerThreads(nOfWorkerThreads);
    }

    @Override
    public void setLogger(Logger logger) {
        //check, if logger is null
        if (logger == null) {
            //dont log anything
            this.logger = Logger.getLogger("zerologger");
            this.logger.setLevel(Level.OFF);

            return;
        }

        this.logger = logger;
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public void setConnectionListener(ConnectionListener listener) {
        this.connListener = listener;
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
        this.logger.info("try to start game server now.");

        //check, if server is already running
        if (this.isRunning.get()) {
            throw new IllegalStateException("server is already running.");
        }

        //check, if network module is set
        if (this.networkModule == null) {
            throw new NullPointerException("network module is null, set network module with method setNetworkModule() first.");
        }

        this.logger.info("configure network module now.");

        //configure network module
        this.networkModule.configure(this.config, this);

        this.logger.info("start game server...");

        //start network module
        this.networkModule.start();

        this.logger.info("game server started successfully!");

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
