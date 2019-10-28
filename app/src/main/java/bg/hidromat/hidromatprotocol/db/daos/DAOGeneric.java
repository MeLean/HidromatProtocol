package bg.hidromat.hidromatprotocol.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface DAOGeneric<T extends Object> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(T obj);

    @Update
    void update(T obj);

    @Delete
    void delete(T obj);
}
