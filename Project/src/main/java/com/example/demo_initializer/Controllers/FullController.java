package com.example.demo_initializer.Controllers;

import com.example.demo_initializer.Repositories.*;
import com.example.demo_initializer.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class FullController {
    private AdminRepository adminRepository;
    private HotelRepository hotelRepository;
    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private List<Observer> observers = new ArrayList<Observer>();

    @Autowired
    public FullController(AdminRepository adminRepository, HotelRepository hotelRepository, BookingRepository bookingRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.hotelRepository = hotelRepository;
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }




    public List<Admin> getAllAdmins()
    {
        return adminRepository.findAll();
    }

    public List<Booking>getAllBookings()
    {

        return bookingRepository.findAll();
    }

    public List<Hotel> getAllHotels()
    {
        return hotelRepository.findAll();
    }

    public List <Room> getAllRooms()
    {
        return roomRepository.findAll();
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public Admin createAdmin(String username,String password)
    {
        Admin foo = adminRepository.findById(username)
                .orElse(null);
        if(foo != null)
        {
            return null;
        }
        Admin admin = new Admin(username,password);

        adminRepository.save(admin);

        return admin;
    }

    public User createUser(User user)
    {
        User foo = userRepository.findById(user.getUsername())
                .orElse(null);
        if(foo != null)
        {
            return null;
        }

        userRepository.save(user);

        return user;

    }
    public Hotel createHotel(Hotel hotel)
    {
        Hotel foo = hotelRepository.findById(hotel.getHotelId())
                .orElse(null);
        if(foo != null)
        {
            return null;
        }

        hotelRepository.save(hotel);

        return hotel;

    }
    public Room createRoom(Room room,String roomtype)
    {
        Room foo = roomRepository.findById(room.getRoomId())
                .orElse(null);
        if(foo != null)
        {
            return null;
        }
        RoomFactory myRoomFactory=new RoomFactory();
        Room newRoom=myRoomFactory.getRoom(roomtype);

        roomRepository.save(newRoom);

        return newRoom;

    }
    public Booking createBooking(Booking booking)
    {
        Booking foo = bookingRepository.findById(booking.getId())
                .orElse(null);
        if(foo != null)
        {
            return null;
        }

        bookingRepository.save(booking);

        return booking;

    }

    public Admin updateAdmin(Admin newAdminDetails,String username)
    {
        Admin foo = adminRepository.findById(username).orElse(null);
        if(foo != null)
        {
            adminRepository.deleteById(username);
            foo.setUsername( newAdminDetails.getUsername());
            foo.setPassword(newAdminDetails.getPassword());
            adminRepository.save(foo);
            return foo;
        }
        else
        {
            newAdminDetails.setUsername(username);
            adminRepository.save(newAdminDetails);
            return newAdminDetails;
        }
    }

    public Booking updateBooking(Booking newBooking,Long id)
    {
        Booking foo= bookingRepository.findById(id).orElse(null);
        if(foo != null)
        {
            foo.setUser(newBooking.getUser());
            foo.setNbOfNights((newBooking.getNbOfNights()));
            foo.setPrice(newBooking.getPrice());
            foo.setRoom(newBooking.getRoom());
            bookingRepository.save(foo);
            return foo;
        }
        else
        {
            newBooking.setId(id);
            bookingRepository.save(newBooking);
            return newBooking;
        }
    }

    public Hotel updateHotel(Hotel hotel,Long id)
    {
        Hotel foo= hotelRepository.findById(id).orElse(null);
        if(foo != null)
        {
            foo.setCity(hotel.getCity());
            foo.setHotelName(hotel.getHotelName());
            foo.setPhone(hotel.getPhone());
            foo.setStreet(hotel.getStreet());
            hotelRepository.save(foo);
            return foo;
        }
        else
        {
            hotel.setHotelId(id);
            hotelRepository.save(hotel);
            return hotel;
        }
    }

    public Room updateRoom(Room room, Long id)
    {
        Room foo = roomRepository.findById(id).orElse(null);
        if (foo != null) {
            foo.setFree(room.isFree());
            foo.setCapacity(room.getCapacity());
            foo.setRoomNb(room.getRoomNb());
            foo.setFloor(room.getFloor());
            foo.setPricePerNight(room.getPricePerNight());
            roomRepository.save(foo);
            return foo;
        } else {
            room.setRoomId(id);
            roomRepository.save(room);
            return room;
        }
    }

    public User updateUser(User newUser,String username)
    {
        User foo = userRepository.findById(username).orElse(null);
        if(foo != null)
        {
            userRepository.deleteById(username);
            foo.setUsername(newUser.getUsername());
            foo.setCity(newUser.getCity());
            foo.setEmail(newUser.getEmail());
            foo.setPassword(newUser.getPassword());
            foo.setPhone(newUser.getPhone());
            userRepository.save(foo);
            return foo;
        }
        else
        {
            newUser.setUsername(username);
            userRepository.save(newUser);
            return newUser;
        }
    }

    public Admin deleteAdmin(String username)
    {

        Admin foo = adminRepository.findById(username).orElse(null);
        if(foo!= null)
        {
            adminRepository.deleteById(username);
            return foo;
        }
        else
            return null;

    }

    public Booking deleteBooking(Long id) {

        Booking foo = bookingRepository.findById(id).orElse(null);
        if (foo != null) {
            bookingRepository.deleteById(id);
            return foo;
        } else
            return null;
    }

    public Hotel deleteHotel(Long id)
    {

        Hotel foo = hotelRepository.findById(id).orElse(null);
        if (foo != null) {
            hotelRepository.deleteById(id);
            return foo;
        } else
            return null;

    }

    public Room deleteRoom(Long id)
    {
        Room foo = roomRepository.findById(id).orElse(null);
        if (foo != null) {
            roomRepository.deleteById(id);
            return foo;
        } else
            return null;
    }

    public User deleteUser(String username)
    {
        User foo = userRepository.findById(username).orElse(null);
        if (foo != null) {
            userRepository.deleteById(username);
            return foo;
        } else
            return null;
    }

    public Admin deleteAllAdmins()
    {
        adminRepository.deleteAll();

        return null;
    }
    public Booking deleteAllBookings()
    {
        bookingRepository.deleteAll();

        return null;
    }
    public Hotel deleteAllHotels()
    {
        hotelRepository.deleteAll();

        return null;
    }
    public Room deleteAllRooms()
    {
        roomRepository.deleteAll();

        return null;
    }
    public User deleteAllUsers()
    {
        userRepository.deleteAll();

        return null;
    }

    public List<Booking> getLowCost(double price)
    {
        return bookingRepository.findByPriceLessThan(price);
    }

    public List<Hotel> findByHotelNameAndAndCity(String hotelname ,String city)
    {
        return hotelRepository.findByHotelNameAndAndCity(hotelname,city);
    }


    public void addObserver(Observer observer){
        this.observers.add(observer);

    }

    public void notifyAllObservers(String news)
    {
        for(Observer obs: observers)
        {
            obs.update(news);
        }
    }

    public List<User> checkRooms() {
        List<User> usersList = userRepository.findAll();
        for (User usr : usersList) {
            this.addObserver(usr);
        }

        List<Room> roomsList = roomRepository.findAll();
        boolean freeFlag = false;
        for (Room rm : roomsList) {
            if (rm.isFree())
                freeFlag = true;
        }
        if (freeFlag)
            notifyAllObservers("Free rooms available");
        else
            notifyAllObservers("No free rooms!");
        //pentru salvare update-uri in observatori
        for (User usr : usersList) {
            userRepository.save(usr);
        }
        return usersList;
    }






}
