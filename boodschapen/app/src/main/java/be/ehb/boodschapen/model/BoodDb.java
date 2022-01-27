package be.ehb.boodschapen.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.ehb.boodschapen.model.util.DateConverter;

@Database(version = 2, entities = {Bood.class})
@TypeConverters({DateConverter.class})
public abstract class BoodDb extends RoomDatabase {

    private static BoodDb instance;

    public static BoodDb getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context,
                    BoodDb.class,
                    "BoodDb.db").build();
        }
        return instance;
    }

    public abstract BoodDAO getBoodDAO();

    static final ExecutorService dbExecutor = Executors.newFixedThreadPool(2);

}
