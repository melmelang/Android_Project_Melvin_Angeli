package be.ehb.boodschapen.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BoodViewModel extends AndroidViewModel {
    private LiveData<List<Bood>> sharedBoods;
    private LiveData<List<Bood>> searchedBoods;
    private BoodDb mDb;
    private ExecutorService mRunDb = Executors.newFixedThreadPool(2);

    public BoodViewModel(@NonNull Application application) {
        super(application);
        mDb = BoodDb.getInstance(application);
        sharedBoods = mDb.getBoodDAO().getEveryBood();
    }

    public LiveData<List<Bood>> getSharedBoods() { return sharedBoods; }

    public LiveData<List<Bood>> getSearchSelectedBood(String title) {
        BoodDb.dbExecutor.execute(()->{
            searchedBoods = mDb.getBoodDAO().searchSelectedBood(title);
        });
        return searchedBoods;
    }

    public void insertBood(Bood n){
        BoodDb.dbExecutor.execute(()->{
            mDb.getBoodDAO().insertBood(n);
        });
    }

    public void deleteBood(Bood n){
        BoodDb.dbExecutor.execute(()->{
            mDb.getBoodDAO().delete(n);
        });
    }
}
