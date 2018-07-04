package com.ik.moojing.db;

import com.ik.MoojingApp;

public class SqlLiteHelper {
    private  static SQLHelper sqlHelper;
    public static SqlLiteHelper sqlLiteHelper=null;

    public static SqlLiteHelper getInstance() {
      if (sqlLiteHelper==null){
          sqlLiteHelper=new SqlLiteHelper();
      }
      return sqlLiteHelper;
    }

    /** 获取数据库Helper */
    public static SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(MoojingApp.getInstance());
        return sqlHelper;
    }

    public static void closeSql(){
        if (sqlHelper != null)
            sqlHelper.close();
    }
}
