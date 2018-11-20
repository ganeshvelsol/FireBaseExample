package velsol.com.firebaseexample.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao
{
    @Insert
    void addUser(User user);

    @Query("select * from users")
    public List<User> getData();

}
