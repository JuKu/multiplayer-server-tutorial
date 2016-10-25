package de.jukusoft.gameserver.tutorial.engine.netty;

import de.jukusoft.gameserver.tutorial.engine.IGameServer;
import de.jukusoft.gameserver.tutorial.engine.NetworkModule;
import de.jukusoft.gameserver.tutorial.engine.config.ServerConfig;
import de.jukusoft.gameserver.tutorial.engine.protocol.MessageReceiver;
import de.jukusoft.gameserver.tutorial.engine.protocol.NetworkMessage;
import de.jukusoft.gameserver.tutorial.engine.uniqueid.IDGenerator;
import de.jukusoft.gameserver.tutorial.engine.uniqueid.impl.LocalIDGenerator;
import de.jukusoft.gameserver.tutorial.engine.utils.LoggerUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Justin on 25.10.2016.
 */
public class NettyTCPNetworkModule<T extends NetworkMessage> implements NetworkModule<T> {

    /**
    * netty server boss group
    */
    protected EventLoopGroup bossGroup;

    /**
    * netty server worker group
    */
    protected EventLoopGroup workerGroup;

    /**
    * server bootstrap
    */
    protected ServerBootstrap bootstrap;

    /**
    * flag, if module is configured
    */
    protected AtomicBoolean isConfigured = new AtomicBoolean(false);

    /**
    * map with channel options
    */
    protected Map<ChannelOption,Object> channelOptions = new HashMap<>();

    /**
    * server config
    */
    protected ServerConfig config = null;

    /**
    * instance of id generator
    */
    protected IDGenerator idGenerator = new LocalIDGenerator();

    /**
    * instance of logger
    */
    protected Logger logger = null;

    /**
    * default constructor
     *
     * @param channelOptions channel options
    */
    public NettyTCPNetworkModule (Map<ChannelOption,Object> channelOptions) {
        //create new server bootstrap
        this.bootstrap = new ServerBootstrap();

        //set channel options
        this.channelOptions = channelOptions;
    }

    /**
     * default constructor
     */
    public NettyTCPNetworkModule () {
        //create new server bootstrap
        this.bootstrap = new ServerBootstrap();
    }

    public <T> void setChannelOption (ChannelOption<T> key, T value) {
        //check, if network module was already configured
        if (this.isConfigured.get()) {
            throw new IllegalStateException("You have to set channel options before network module was configured.");
        }

        //put option to map
        this.channelOptions.put(key, value);
    }

    @Override
    public void setClientIDGenerator(IDGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public void configure(ServerConfig config, IGameServer<T> server) {
        if (this.isConfigured.get()) {
            throw new IllegalStateException("netty network module was already configured.");
        }

        //save configuration
        this.config = config;

        //save logger
        this.logger = server.getLogger();

        //set flag
        this.isConfigured.set(true);

        //create and set boss and worker group
        bossGroup = new NioEventLoopGroup(config.getNumberOfBossThreads());
        workerGroup = new NioEventLoopGroup(config.getNumberOfWorkerThreads());
        bootstrap.group(bossGroup, workerGroup);

        //use TCP channel
        bootstrap.channel(NioServerSocketChannel.class);

        //set child options
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childOption(ChannelOption.AUTO_READ, true);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);

        //set channel options
        for (Map.Entry<ChannelOption,Object> entry : this.channelOptions.entrySet()) {
            //set channel option
            bootstrap.childOption(entry.getKey(), entry.getValue());
        }

        // set handler which does the pipeline initialization
        bootstrap.handler(new LoggingHandler(LoggerUtils.convertToNettyLogLevel(server.getLogger().getLevel().toString())));
    }

    @Override
    public void start() throws Exception {
        //check, if server is configured
        if (!this.isConfigured.get()) {
            throw new IllegalStateException("Cannot start netty network module, configure module first.");
        }

        //set channel initialization handler
        bootstrap.childHandler(new ChannelInitHandler());

        //start server
        ChannelFuture channelFuture = bootstrap.bind(config.getPort());
    }

    @Override
    public void shutdown() throws Exception {
        //shutdown event loop groups
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    @Override
    public void send(long clientID, NetworkMessage msg) {

    }

    @Override
    public void broadcast(NetworkMessage msg) {

    }

    @Override
    public void setNetworkReceiver(MessageReceiver receiver) {

    }

    private final class ChannelInitHandler extends ChannelHandlerAdapter {

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            //new connection opened

            //get host and ip
            String host = ((InetSocketAddress)ctx.channel().remoteAddress()).getAddress().getHostAddress();
            int port = ((InetSocketAddress)ctx.channel().remoteAddress()).getPort();

            //generate new clientID
            long clientID = idGenerator.generateID();

            logger.debug("new client with ID " + clientID + " connected to server, client ip: " + host + ", client port: " + port);
        }

    }

}
