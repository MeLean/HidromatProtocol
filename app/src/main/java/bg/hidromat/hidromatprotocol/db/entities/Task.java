package bg.hidromat.hidromatprotocol.db.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "task_table",
        foreignKeys = @ForeignKey(entity = Protocol.class,
        parentColumns = "id",
        childColumns = "protocolId",
        onDelete = CASCADE))
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long protocolId;

    private String name;

    private boolean isChecked;

    public Task(long protocolId, String name, boolean isChecked) {
        this.protocolId = protocolId;
        this.name = name;
        this.isChecked = isChecked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(long protocolId) {
        this.protocolId = protocolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
