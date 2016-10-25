package de.jukusoft.gameserver.tutorial.engine.uniqueid;

/**
 * interface for unique ID Generator, so we can support also local unique IDs and also distributed unique ids.
 *
 * Created by Justin on 26.10.2016.
 */
public interface IDGenerator {

    /**
    * generate new uniqueID
     *
     * @return unique id
    */
    public long generateID ();

}
