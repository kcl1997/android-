        myDBHelper = new MyDBHelper(this,null,null,1);
        //查询数据
        checkStuInfo();

        //listView设置删除数据
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //设置ListView单机监听事件

        lv_stu_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final long tempId = id;
                alertDialogBuilder.setMessage("真的要删除吗？").setPositiveButton("是", new DialogInterface.OnClickListener() {
                    //删除事件
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDBHelper.del((int)tempId);
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

        Toast.makeText(this, "数据库关闭之前", Toast.LENGTH_SHORT).show();

        /**
         * 每次初始化数据后关闭数据库
         */
        myDBHelper.close();
