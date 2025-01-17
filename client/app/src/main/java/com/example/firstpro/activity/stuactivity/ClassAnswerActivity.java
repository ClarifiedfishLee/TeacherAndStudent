package com.example.firstpro.activity.stuactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.firstpro.R;
import com.example.firstpro.WebService.MyURL;
import com.example.firstpro.WebService.ServerService;
import com.example.firstpro.activity.activityhelper.ActivityCollector;
import com.example.firstpro.activity.activityhelper.MyActivity;
import com.example.firstpro.activity.listviewadapter.ClassListAdapter;
import com.example.firstpro.activity.teactivity.ClassDiscussActivity;
import com.example.firstpro.activity.teactivity.SendActivity;
import com.example.firstpro.data.Class;
import com.example.firstpro.database.AutoLoginStatic;
import com.example.firstpro.database.ClassesSQLIteHelper;
import com.example.firstpro.helper.NetJudgeHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ClassAnswerActivity extends MyActivity {

    private List<Class> list = new ArrayList<Class>();
    private ListView listView;

    private ClassListAdapter classListAdapter;
    private Context context;

    private ServerService sv = new ServerService();
    private String account = AutoLoginStatic.getInstance().getUserNum(this);
    private MyHandler myHandler = new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_answer);
        context =this;

        ButtonReact();
        ListReact();

    }



    private void ButtonReact(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.stu_classes_discuss_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCollector.finishOneActivity(ClassAnswerActivity.class.getName());

            }
        });
    }

    private void ListReact(){
        listView = (ListView) findViewById(R.id.stu_class_discuss_listview);

        //todo:删掉下面内容
//        Class class1 =new Class();
//        Class class2 =new Class();
//        class1.setClass_name("微积分");
//        class1.setClass_id("12E3");
//        class1.setTrue_stu_num(50);
//        class1.setClass_num(60);
//        class1.setMax_stu_num(100);
//        class1.setCourse_credit(5.5);
//        class1.setLocation_Of_Class("正心11");
//        class1.setTime("周一 8:00-9:45，周五 8:00-9:45");
//        list.add(class1);
//
//        class2.setClass_name("计算机组成原理");
//        class2.setClass_id("23R4");
//        class2.setTrue_stu_num(50);
//        class2.setClass_num(48);
//        class2.setMax_stu_num(100);
//        class2.setCourse_credit(4.5);
//        class2.setLocation_Of_Class("正心511");
//        class2.setTime("周二 8:00-9:45，周三 8:00-9:45");
//        list.add(class2);

        getClassFromDB();
//        classListAdapter = new ClassListAdapter(this,list);
//        listView.setAdapter(classListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                //todo：进入问题界面
                Bundle bundle=new Bundle();
                bundle.putString("classid",list.get(position).getClass_id());
                intent.putExtra("bun",bundle);
                intent.setClass(ClassAnswerActivity.this, AnsListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getClassFromDB(){

        //先判断是否联网，联网的话就取远端数据，显示并更新本地数据库
        //ToDo:取远端数据
        boolean find_in_local = true;
        //只要联网了，就取远端数据并更新本地数据库
        if(NetJudgeHelper.isNetworkAvailable(context)){
            find_in_local=false;//就不用本地设置了
            GetMyClass();
        }
        //如果断网，则直接显示本地数据
        if(find_in_local) {
            ClassesSQLIteHelper helper = ClassesSQLIteHelper.getInstance(context);
            SQLiteDatabase db = helper.getReadableDatabase();
            String sql = "select classid,teacheraccount,classname,classnum,credit,location,maxstunum,time,true_stunum from classes";
            Cursor cursor = helper.query(sql, null);
            int size = cursor.getCount();

            String account = AutoLoginStatic.getInstance().getUserNum(context);

            int classid_index = cursor.getColumnIndex("classid");
            int teacheracc_index = cursor.getColumnIndex("teacheraccount");
            int classname_index = cursor.getColumnIndex("classname");
            int classnum_index = cursor.getColumnIndex("classnum");
            int credit_index = cursor.getColumnIndex("credit");
            int location_index = cursor.getColumnIndex("location");
            int max_index = cursor.getColumnIndex("maxstunum");
            int time_index = cursor.getColumnIndex("time");
            int true_index = cursor.getColumnIndex("true_stunum");

            for (int i = 0; i < size; i++) {
                cursor.moveToNext();
                if (cursor.getString(teacheracc_index).equals(account)) {
                    Class myclass = new Class();
                    myclass.setClass_id(cursor.getString(classid_index));
                    myclass.setClass_name(cursor.getString(classname_index));
                    myclass.setTrue_stu_num(cursor.getInt(true_index));
                    myclass.setClass_num(cursor.getInt(classnum_index));
                    myclass.setMax_stu_num(cursor.getInt(max_index));
                    myclass.setCourse_credit(cursor.getDouble(credit_index));
                    myclass.setLocation_Of_Class(cursor.getString(location_index));
                    myclass.setTime(cursor.getString(time_index));

                    list.add(myclass);
                }
            }
            cursor.close();
            db.close();
        }
    }

    private void GetMyClass()  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Class> _classList = sv.getClasses(MyURL.StuURL,false,null,true,account);
                Message msg = myHandler.obtainMessage();
                msg.obj=_classList;
                msg.what = 1;
                myHandler.sendMessage(msg);
            }
        }).start();
    }

    private class MyHandler extends Handler {

        //弱引用持有HandlerActivity , GC 回收时会被回收掉
        private WeakReference<ClassAnswerActivity> weakReference;

        public MyHandler(ClassAnswerActivity activity) {
            this.weakReference = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<Class> classes = (List<Class>) msg.obj;
            switch (msg.what) {
                case 1:
                    list.addAll(classes);
                    classListAdapter = new ClassListAdapter(context,list);
                    listView.setAdapter(classListAdapter);
                    ClassesSQLIteHelper.getInstance(context).update(list,context);
                    break;
            }
        }
    }
}