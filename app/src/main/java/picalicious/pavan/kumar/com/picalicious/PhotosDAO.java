package picalicious.pavan.kumar.com.picalicious;

import android.arch.persistence.room.Dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PhotosDAO {

    @Insert
    void insert(Photos photo);

    @Query("SELECT path from photos WHERE labels LIKE '%' || :l || '%'")
    public List<String> getPhotoswithLabels(String l);
}
