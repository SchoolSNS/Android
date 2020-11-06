package com.example.hischool.room;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class Dao_Impl implements Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ChatDataBase> __insertionAdapterOfChatDataBase;

  public Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChatDataBase = new EntityInsertionAdapter<ChatDataBase>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `chat` (`id`,`sender`,`receiver`,`message`,`time`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ChatDataBase value) {
        stmt.bindLong(1, value.getId());
        if (value.getSender() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSender());
        }
        if (value.getReceiver() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getReceiver());
        }
        if (value.getMessage() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMessage());
        }
        stmt.bindLong(5, value.getTime());
      }
    };
  }

  @Override
  public void insert(final ChatDataBase chatDataBase) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChatDataBase.insert(chatDataBase);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ChatDataBase> getMessage(final String sender, final String receiver) {
    final String _sql = "SELECT * FROM chat where (sender = ? and receiver = ?) or (sender = ? and receiver = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    if (sender == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sender);
    }
    _argIndex = 2;
    if (receiver == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, receiver);
    }
    _argIndex = 3;
    if (receiver == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, receiver);
    }
    _argIndex = 4;
    if (sender == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sender);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
      final int _cursorIndexOfReceiver = CursorUtil.getColumnIndexOrThrow(_cursor, "receiver");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final List<ChatDataBase> _result = new ArrayList<ChatDataBase>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ChatDataBase _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpSender;
        _tmpSender = _cursor.getString(_cursorIndexOfSender);
        final String _tmpReceiver;
        _tmpReceiver = _cursor.getString(_cursorIndexOfReceiver);
        final String _tmpMessage;
        _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        final long _tmpTime;
        _tmpTime = _cursor.getLong(_cursorIndexOfTime);
        _item = new ChatDataBase(_tmpId,_tmpSender,_tmpReceiver,_tmpMessage,_tmpTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ChatDataBase> getRecentMessage(final String myName) {
    final String _sql = "select * from ( SELECT id, message, case WHEN sender == ? THEN sender ELSE receiver END as sender, case WHEN sender == ? THEN receiver ELSE sender END as receiver, time from chat where sender == ? or receiver == ? order by id DESC)tab group by receiver, sender";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    if (myName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, myName);
    }
    _argIndex = 2;
    if (myName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, myName);
    }
    _argIndex = 3;
    if (myName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, myName);
    }
    _argIndex = 4;
    if (myName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, myName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
      final int _cursorIndexOfReceiver = CursorUtil.getColumnIndexOrThrow(_cursor, "receiver");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final List<ChatDataBase> _result = new ArrayList<ChatDataBase>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ChatDataBase _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpMessage;
        _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        final String _tmpSender;
        _tmpSender = _cursor.getString(_cursorIndexOfSender);
        final String _tmpReceiver;
        _tmpReceiver = _cursor.getString(_cursorIndexOfReceiver);
        final long _tmpTime;
        _tmpTime = _cursor.getLong(_cursorIndexOfTime);
        _item = new ChatDataBase(_tmpId,_tmpSender,_tmpReceiver,_tmpMessage,_tmpTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
