/**
 * 项目名: Chapter6
 * 包名:   kcl.com.chapter6
 * 文件名: DBHelper
 * 创建者: kcl
 * 创建时间：2019/5/20 5:21 PM
 * 描述: Sqlite数据哭帮助类
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Event.db";
    private static final String TBL_NAME = "EventTbl";
    private SQLiteDatabase db;

    //构造函数
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        String CREATE_TBL = "create table EventTbl(_id integer primary key autoincrement,title text,context text)";
        db.execSQL(CREATE_TBL);
    }

    //插入
    public void insert(ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TBL_NAME,null,values);
        db.close();
    }

    //查询
    public Cursor query(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TBL_NAME,null,null,null,null,null,null);
        return cursor;
    }

    //根据id查询
    public Cursor queryById(String _id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TBL_NAME,null,"_id = ?",new String[]{_id},null,null,null);
        return cursor;
    }

    //修改数据
    public int updataById(String _id,String title,String context){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put("title", title);
        value.put("context", context);
        return db.update("EventTbl", value, "_id=?", new String[] { _id });
    }


    //删除
    public void del(int id){
        if(db == null) db = getWritableDatabase();
        db.delete(TBL_NAME,"_id = ?",new String[]{String.valueOf(id)});

    }

    //关闭数据库
    public void close(){ if(db != null) db.close(); }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
