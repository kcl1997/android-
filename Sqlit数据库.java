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

    //当数据库未创建时自动执行
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

    //查询返回游标
    public Cursor query(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TBL_NAME,null,null,null,null,null,null);
        return cursor;
    }

    //根据id查询，返回游标
    public Cursor queryById(String _id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TBL_NAME,null,"_id = ?",new String[]{_id},null,null,null);
        return cursor;
    }

    //修改数据，需要重写
    public int updataById(String _id,String title,String context){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put("title", title);
        value.put("context", context);
        return db.update("EventTbl", value, "_id=?", new String[] { _id });
    }


    //删除数据，根据主键_id
    public void del(int id){
        if(db == null) db = getWritableDatabase();
        db.delete(TBL_NAME,"_id = ?",new String[]{String.valueOf(id)});

    }

    //关闭数据库
    public void close(){ if(db != null) db.close(); }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}


-------------------------------------------------------------------------------------------------------------------------------------

            //添加数据
            //创建ContentValues对象，封装记录信息
            ContentValues contentValues = new ContentValues();
            contentValues.put("title",title);
            contentValues.put("context",context);
            //创建数据库工具类DBHelper
            DBHelper helper = new DBHelper(getApplicationContext(),null,null,1);
            helper.insert(contentValues);
            helper.close();

-------------------------------------------------------------------------------------------------------------------------------------
            //查询所有数据，遍历结果
            //查询信息根据id
            DBHelper myDBHelper = new DBHelper(this,null,null,1);
            Cursor cursor = myDBHelper.queryById(s_id);
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                int titleIdx = cursor.getColumnIndex("title");
                int contextIdx = cursor.getColumnIndex("context");
                s_title = cursor.getString(titleIdx);
                s_context = cursor.getString(contextIdx);
            }
-------------------------------------------------------------------------------------------------------------------------------------
            //删除数据
    
    
    
    private void initView() {
        lv_stu_info = findViewById(R.id.lv_activity_stu_list_info);
        myDBHelper = new MyDBHelper(this,null,null,1);
        //查询数据
        checkStuInfo();

        //listView设置删除数据
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //设置ListView单机监听事件

        lv_stu_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 层层获取控件的view
                 */
                LinearLayout layout = (LinearLayout) view;
                CardView cardView = (CardView) layout.getChildAt(0);
                LinearLayout layout1 = (LinearLayout) cardView.getChildAt(0);
                LinearLayout layout2 = (LinearLayout) layout1.getChildAt(0);
                final TextView tv_id = (TextView) layout2.getChildAt(0);

                //Toast.makeText(StuListInfoDeleteActivity.this, "ID = " + tv_id.getText().toString().trim().substring(4), Toast.LENGTH_SHORT).show();
                alertDialogBuilder.setMessage("真的要删除吗？").setPositiveButton("是", new DialogInterface.OnClickListener() {
                    //删除事件
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDBHelper.del(tv_id.getText().toString().trim().substring(4));
                        //重新查询数据
                        checkStuInfo();
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                //显示对话框
                alertDialogBuilder.create().show();
            }
        });


        /**
         * 每次初始化数据后关闭数据库
         */
        myDBHelper.close();
        
    }
            
-------------------------------------------------------------------------------------------------------------------------------------
            //lsitView item_listview 数据源绑定显示数据
            private void getData() {
                //首先获取eventList对象
                dbHelper = new DBHelper(this,null,null,1);
                Cursor cursor = dbHelper.query();
                String [] tbl_col = {"_id","title"};
                int[] idInfo = {R.id.tv_id_item_things,R.id.tv_item_things};
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.item_things,cursor,tbl_col,idInfo);
                lv_menu.setAdapter(adapter);
                myDBHelper.close();
            }

            //可以自定义Adapter，用来整合listView视图
            List<StudentEntity> studentEntityList = new LinkedList<StudentEntity>();
            myDBHelper = new MyDBHelper(this,null,null,1);
            Cursor cursor = myDBHelper.query();
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setsId(cursor.getString(cursor.getColumnIndex("_id")));
                studentEntity.setsName(cursor.getString(cursor.getColumnIndex("name")));
                studentEntity.setsAge(cursor.getString(cursor.getColumnIndex("age")));
                studentEntity.setsSex(cursor.getString(cursor.getColumnIndex("sex")));
                studentEntity.setsClass(cursor.getString(cursor.getColumnIndex("class")));
                studentEntity.setsPhoneNum(cursor.getString(cursor.getColumnIndex("phoneNum")));
                studentEntityList.add(studentEntity);
            }

            StudentInfoAdapter studentInfoAdapter = new StudentInfoAdapter(this,studentEntityList);
            lv_stu_info.setAdapter(studentInfoAdapter);
            myDBHelper.close();

-------------------------------------------------------------------------------------------------------------------------------------
            //更新信息
            helper.updataById(s_id,title,context);



