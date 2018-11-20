package velsol.com.firebaseexample.RoomDatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users")
public class User
{
    @PrimaryKey(autoGenerate = true)
   private int id;
    @ColumnInfo(name = "user_name")
    private String Name;

    @ColumnInfo(name = "user_Email")
    private String Email;

    @ColumnInfo(name = "user_mobile")
    private String Mobile;

    public User() {
    }

    public User(String name, String email, String mobile) {
        Name = name;
        Email = email;
        Mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
