package picalicious.pavan.kumar.com.picalicious;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities =  {Photos.class}, version = 1)
public abstract class PhotosDatabase extends RoomDatabase{

    public abstract PhotosDAO photosDAO();
    private static PhotosDatabase INSTANCE;

    public static PhotosDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PhotosDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PhotosDatabase.class, "photo_database").allowMainThreadQueries().build();
                }
            }

        }
        return INSTANCE;
    }
/*
    public static void insertData(final PhotosDatabase database, final Photos photo){
        database.runInTransaction(()->{
            database.photosDAO().insert(Photo);
        });
    }
*/

}
