package com.example.demo_initializer.components;

/**
 * Clasa Design Pattern Factory
 */
public class RoomFactory {

    /**
     *
     * @param RoomType tipul camerei care se doresete a fi instantiate de tipul string
     * @return Subclasa a clasei Room de tipul specific dorit
     */
    public Room getRoom(String RoomType)
    {
        if(RoomType==null)
            return null;
        if(RoomType.equals("RegularRoom"))
        {
            return new RegularRoom();
        }
        if(RoomType.equals("ConferenceRoom"))
        {
            return new ConferenceRoom();
        }
        if(RoomType.equals("PremiumRoom"))
        {
            return new PremiumRoom();
        }
        return null;
    }

}
