package de.jukusoft.gameserver.tutorial.engine.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

/**
 * Created by Justin on 26.10.2016.
 */
public interface NettyChannelInitializer {

    /**
    * initialize netty channel
     *
     * @param clientID unique clientID
     * @param ctx channel handler context
     * @param pipeline netty channel pipeline
    */
    public void initChannel (final long clientID, ChannelHandlerContext ctx, ChannelPipeline pipeline);

}
