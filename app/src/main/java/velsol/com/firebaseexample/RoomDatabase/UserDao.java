package velsol.com.firebaseexample.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao
{
    //inserting the data into database
    @Insert
    void addUser(User user);

    //reading the data from the database
    @Query("select * from users")
    public List<User> getData();

}
