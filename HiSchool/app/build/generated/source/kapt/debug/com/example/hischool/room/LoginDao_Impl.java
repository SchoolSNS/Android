package com.example.hischool.room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LoginDao_Impl implements LoginDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LoginData> __insertionAdapterOfLoginData;

  private final EntityDeletionOrUpdateAdapter<LoginData> __deletionAdapterOfLoginData;

  public LoginDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLoginData = new EntityInsertionAdapter<LoginData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `login` (`id`,`token`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LoginData value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getToken() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getToken());
        }
      }
    };
    this.__deletionAdapterOfLoginData = new EntityDeletionOrUpdateAdapter<LoginData>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `login` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LoginData value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
  }

  @Override
  public void insert(final LoginData loginData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLoginData.insert(loginData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final LoginData loginData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfLoginData.handle(loginData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<LoginData>> getAll() {
    final String _sql = "SELECT * FROM login";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"login"}, false, new Callable<List<LoginData>>() {
      @Override
      public List<LoginData> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfToken = CursorUtil.getColumnIndexOrThrow(_cursor, "token");
          final List<LoginData> _result = new ArrayList<LoginData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LoginData _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpToken;
            _tmpToken = _cursor.getString(_cursorIndexOfToken);
            _item = new LoginData(_tmpId,_tmpToken);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
