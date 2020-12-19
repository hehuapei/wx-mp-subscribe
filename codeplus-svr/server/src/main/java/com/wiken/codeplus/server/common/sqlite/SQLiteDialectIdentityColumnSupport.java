package com.wiken.codeplus.server.common.sqlite;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

/**
 * by hehuapei
 * Time 2020-12-10 17:46
 */
public class SQLiteDialectIdentityColumnSupport extends IdentityColumnSupportImpl {
    public SQLiteDialectIdentityColumnSupport(Dialect dialect) {
        super();
    }

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

  /*
    public boolean supportsInsertSelectIdentity() {
    return true; // As specified in NHibernate dialect
  }
  */

    @Override
    public boolean hasDataTypeInIdentityColumn() {
        // As specified in NHibernate dialect
        // FIXME true
        return false;
    }

  /*
    public String appendIdentitySelectToInsert(String insertString) {
    return new StringBuffer(insertString.length()+30). // As specified in NHibernate dialect
      append(insertString).
      append("; ").append(getIdentitySelectString()).
      toString();
  }
  */

    @Override
    public String getIdentitySelectString(String table, String column, int type) {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type) {
        // return "integer primary key autoincrement";
        // FIXME "autoincrement"
        return "integer";
    }
}
