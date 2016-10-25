package de.jukusoft.gameserver.tutorial;

import de.jukusoft.gameserver.tutorial.engine.impl.GameServerImpl;
import de.jukusoft.gameserver.tutorial.engine.IGameServer;
import de.jukusoft.gameserver.tutorial.engine.netty.NettyTCPNetworkModule;
import de.jukusoft.gameserver.tutorial.engine.protocol.NetworkMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by Justin on 25.10.2016.
 */
public class ServerMain {

    public static void main (String[] args) {
        //configure logger to print all to console
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);

        //create and configure new logger
        Logger logger = Logger.getLogger("GameServer");
        logger.setLevel(Level.INFO);

        //create and configure new game server
        IGameServer server = new GameServerImpl();
        server.setPort(1234);
        server.setBossThreads(2);
        server.setWorkerThreads(2);

        //because we have some abstraction, we have to set an network module, in this case we will use netty with TCP
        NettyTCPNetworkModule<NetworkMessage> networkModule = new NettyTCPNetworkModule<>();

        //set channel initializer
        networkModule.setNettyChannelInitializer((final long clientID, ChannelHandlerContext ctx, ChannelPipeline
        pipeline) -> {
            //create new logging handler
            LoggingHandler loggingHandler = new LoggingHandler("Game Server", LogLevel.INFO);

            //add logging handler to pipeline
            pipeline.addLast("logger", loggingHandler);

            //TODO: add codec
        });

        server.setNetworkModule(networkModule);

        try {
            //start game server
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
