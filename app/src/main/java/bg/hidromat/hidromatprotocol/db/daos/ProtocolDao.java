package bg.hidromat.hidromatprotocol.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import bg.hidromat.hidromatprotocol.db.entities.Protocol;
import bg.hidromat.hidromatprotocol.db.entities.Task;

@Dao
public interface ProtocolDao extends DAOGeneric<Protocol> {
    @Query("SELECT * FROM protocol_table")
    LiveData<List<Protocol>> getAllProtocols();
}
