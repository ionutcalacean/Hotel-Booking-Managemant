package com.example.demo_initializer.components;

public class RoomFactory {


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
