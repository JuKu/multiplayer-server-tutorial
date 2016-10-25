package de.jukusoft.gameserver.tutorial.server;

/**
 * interface for game servers
 *
 * Created by Justin on 25.10.2016.
 */
public interface IGameServer extends Server {

    /**
    * add an task which should be executed in specific interval
     *
     * @param intervalInMS interval in milliseconds
     * @param task task to execute
    */
    public void addIntervaledTask (long intervalInMS, Runnable task);

}
