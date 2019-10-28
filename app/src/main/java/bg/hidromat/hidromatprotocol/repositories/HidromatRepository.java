package bg.hidromat.hidromatprotocol.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

import bg.hidromat.hidromatprotocol.db.HidromatDataBase;
import bg.hidromat.hidromatprotocol.db.daos.ProtocolDao;
import bg.hidromat.hidromatprotocol.db.daos.TaskDao;
import bg.hidromat.hidromatprotocol.db.entities.Protocol;
import bg.hidromat.hidromatprotocol.db.entities.Task;

public class HidromatRepository {

    private ProtocolDao protocolDao;
    private TaskDao taskDao;
    private LiveData<List<Protocol>> protocols;

    public HidromatRepository(Application application){
        HidromatDataBase db =  HidromatDataBase.getInstance(application);
        protocolDao = db.protocolDao();
        taskDao = db.taskDao();
        protocols = protocolDao.getAllProtocols();
    }

    public void manageVoidCall(Protocol protocol, TaskType taskType){
        new manageProtocolVoidRequestAsyncTask(protocolDao, taskType).execute(protocol);
    }

    public LiveData<List<Protocol>> getAllProtocols(){
        return protocols;
    }

    public void getAllTasksNames(long protocolId){
        new manageTaskResultRequestAsyncTask(taskDao, protocolId).execute();
    }


    private static class manageTaskResultRequestAsyncTask extends AsyncTask<Void, Void, LiveData<List<Task>>>{
        private TaskDao taskDao;
        private long protocolId;

        manageTaskResultRequestAsyncTask(TaskDao taskDao, long protocolId){
            this.taskDao = taskDao;
            this.protocolId =protocolId;
        }

        @Override
        protected LiveData<List<Task>> doInBackground(Void... voids) {
            return taskDao.getAllTaskFor(protocolId);
        }
    }

    private static class manageProtocolVoidRequestAsyncTask extends AsyncTask<Protocol, Void, Void>{
        private ProtocolDao protocolDao;
        private TaskType taskType;

        manageProtocolVoidRequestAsyncTask(ProtocolDao protocolDao, TaskType taskType){
            this.protocolDao = protocolDao;
            this.taskType = taskType;
        }

        @Override
        protected Void doInBackground(Protocol... protocols) {
            switch (taskType){
                case INSERT: protocolDao.insert(protocols[0]);break;
                case UPDATE: protocolDao.update(protocols[0]);break;
                case DELETE: protocolDao.delete(protocols[0]);break;
                default: throw new UnsupportedOperationException("Task type: " + taskType + " is not supported!");

            }

            return null;
        }

    }

    private static class manageProtocolResultRequestAsyncTask extends AsyncTask<Void, Void, LiveData<List<Protocol>>>{
        private ProtocolDao protocolDao;
        private TaskType taskType;

        manageProtocolResultRequestAsyncTask(ProtocolDao protocolDao, TaskType taskType){
            this.protocolDao = protocolDao;
            this.taskType = taskType;
        }


        @Override
        protected LiveData<List<Protocol>> doInBackground(Void... voids) {
            if (taskType == TaskType.GET_ALL) {
                return protocolDao.getAllProtocols();
            }
            throw new UnsupportedOperationException("Task type: " + taskType + " is not supported!");
        }
    }


    public enum TaskType{
        INSERT,
        UPDATE,
        DELETE,
        GET_ALL
    }
}
