package be.ehb.boodschapen.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BoodViewModel extends AndroidViewModel {
    private final LiveData<List<Bood>> sharedBoods;
    private final BoodDb mDb;

    public BoodViewModel(@NonNull Application application) {
        super(application);
        mDb = BoodDb.getInstance(application);
        sharedBoods = mDb.getBoodDAO().getEveryBood();
    }

    public LiveData<List<Bood>> getSharedBoods() { return sharedBoods; }

    public void insertBood(Bood n){
        BoodDb.dbExecutor.execute(()-> mDb.getBoodDAO().insertBood(n));
    }

    public void deleteBood(Bood n){
        BoodDb.dbExecutor.execute(()-> mDb.getBoodDAO().delete(n));
    }
}
