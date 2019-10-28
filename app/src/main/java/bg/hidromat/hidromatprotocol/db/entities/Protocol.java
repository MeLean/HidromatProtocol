package bg.hidromat.hidromatprotocol.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "protocol_table")
public class Protocol {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description_text")
    private String descriptionText;

    @ColumnInfo(name = "signers_by_client")
    private String[] signersByClient;

    @ColumnInfo(name = "signers_by_firm")
    private String[] signersByFirm;

    public Protocol(String name, String title, String descriptionText, String[] signersByClient, String[] signersByFirm) {
        this.name = name;
        this.title = title;
        this.descriptionText = descriptionText;
        this.signersByClient = signersByClient;
        this.signersByFirm = signersByFirm;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String[] getSignersByClient() {
        return signersByClient;
    }

    public void setSignersByClient(String[] signersByClient) {
        this.signersByClient = signersByClient;
    }

    public String[] getSignersByFirm() {
        return signersByFirm;
    }

    public void setSignersByFirm(String[] signersByFirm) {
        this.signersByFirm = signersByFirm;
    }

}
