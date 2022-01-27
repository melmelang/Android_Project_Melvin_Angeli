package be.ehb.boodschapen.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BoodDAO {
    @Query("SELECT * FROM Bood ORDER BY title")
    LiveData<List<Bood>> getEveryBood();

    @Query("SELECT * FROM Bood WHERE Title MATCH :title")
    LiveData<List<Bood>> searchSelectedBood(String title);

    @Insert
    void insertBood(Bood n);

    @Delete
    void delete(Bood n);
}
