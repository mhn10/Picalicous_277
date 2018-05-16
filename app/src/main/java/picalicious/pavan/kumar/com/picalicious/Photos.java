package picalicious.pavan.kumar.com.picalicious;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "photos")
public class Photos {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name="path")
   public String filepath = "";

    @ColumnInfo(name="labels")
    public  String labels = "";

    //constructor
    public  Photos(@NonNull String filepath, String labels) {
        this.filepath=filepath; this.labels=labels;
    }

   //getter
    public String getPhotos(){
        return this.filepath;
    }

    public String getLabel(){
        return this.labels;
    }

    public void setFilepath(@NonNull String filepath) {
        this.filepath = filepath;
    }

    public void setLabels( String labels) {
        this.labels = labels;
    }







}
