package picalicious.pavan.kumar.com.picalicious;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class PhotosDAO_Impl implements PhotosDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPhotos;

  public PhotosDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPhotos = new EntityInsertionAdapter<Photos>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `photos`(`path`,`labels`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Photos value) {
        if (value.filepath == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.filepath);
        }
        if (value.labels == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.labels);
        }
      }
    };
  }

  @Override
  public void insert(Photos photo) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPhotos.insert(photo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<String> getPhotoswithLabels(String l) {
    final String _sql = "SELECT path from photos WHERE labels LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (l == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, l);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
