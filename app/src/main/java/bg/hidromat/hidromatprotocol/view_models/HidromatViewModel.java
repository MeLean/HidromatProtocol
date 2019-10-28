package bg.hidromat.hidromatprotocol.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bg.hidromat.hidromatprotocol.db.entities.Protocol;
import bg.hidromat.hidromatprotocol.repositories.HidromatRepository;

public class HidromatViewModel extends AndroidViewModel {
    private HidromatRepository repository;
    private LiveData<List<Protocol>> protocols;
    private LiveData<Protocol> selectedProtocol;
    private LiveData<String[]> tasksForSelectedProtocol;

    public HidromatViewModel(@NonNull Application application) {
        super(application);

        repository = new  HidromatRepository(application);
        protocols = repository.getAllProtocols();

    }

    public void manageVoidCall(Protocol protocol, HidromatRepository.TaskType taskType){
       repository.manageVoidCall(protocol, taskType);
    }

    public LiveData<List<Protocol>> getAllProtocols(){
        return protocols;
    }




}
