package de.jukusoft.gameserver.tutorial.engine.uniqueid.impl;

import de.jukusoft.gameserver.tutorial.engine.uniqueid.IDGenerator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Justin on 26.10.2016.
 */
public class LocalIDGenerator implements IDGenerator {

    /**
    * last unique ID
    */
    protected AtomicLong lastID = new AtomicLong(0);

    @Override
    public long generateID() {
        return this.lastID.incrementAndGet();
    }

}
