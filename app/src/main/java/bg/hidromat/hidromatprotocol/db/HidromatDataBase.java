package bg.hidromat.hidromatprotocol.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import bg.hidromat.hidromatprotocol.db.daos.TaskDao;
import bg.hidromat.hidromatprotocol.db.daos.ProtocolDao;
import bg.hidromat.hidromatprotocol.db.entities.Protocol;
import bg.hidromat.hidromatprotocol.db.entities.Task;

@Database(entities = {Protocol.class, Task.class}, version = 1)
@TypeConverters({HidromatDbConverter.class})
public abstract class HidromatDataBase extends RoomDatabase {

    private static HidromatDataBase instance;

    public abstract ProtocolDao protocolDao();

    public abstract TaskDao taskDao();

    public static synchronized HidromatDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HidromatDataBase.class, "hidromat_database")
                    .addCallback(initialPopulationCallback)
                    .fallbackToDestructiveMigration().build();
        }

        return instance;
    }

    private static RoomDatabase.Callback initialPopulationCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialPopulationAsyncTask(instance).execute();
        }
    };

    private static class InitialPopulationAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProtocolDao protocolDao;
        private TaskDao taskDao;

        InitialPopulationAsyncTask(HidromatDataBase db) {
            protocolDao = db.protocolDao();
            taskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Long protocolId = protocolDao.insert(new Protocol(
                    "Протокол №1",
                    "ПРОТОКО ЗА ИЗВЪРШВАНЕ НА ПРОВЕРКА",
                    "С настоаящия протокол се удостоверява, че към дата на съставянето " +
                            "му е извършена проверка на следните обекти:",
                    new String[]{"Иван Иванов"},
                    new String[]{"Цветан Цонев"}
            ));

            taskDao.insert(new Task(protocolId, "Проверка на машина № 1", false));
            taskDao.insert(new Task(protocolId, "Проверка на машина № 2", false));

            return null;
        }
    }
}
