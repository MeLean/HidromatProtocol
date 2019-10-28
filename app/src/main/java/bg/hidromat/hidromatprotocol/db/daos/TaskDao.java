package bg.hidromat.hidromatprotocol.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import bg.hidromat.hidromatprotocol.db.entities.Task;

@Dao
public interface TaskDao extends DAOGeneric<Task> {

    @Query("SELECT * FROM task_table WHERE protocolId=:protocolId")
    LiveData<List<Task>> getAllTaskFor(long protocolId);
}
