package be.ehb.boodschapen.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BoodDAO {
    @Query("SELECT * FROM Bood ORDER BY title")
    LiveData<List<Bood>> getEveryBood();

    @Insert
    void insertBood(Bood n);

    @Delete
    void delete(Bood n);
}
