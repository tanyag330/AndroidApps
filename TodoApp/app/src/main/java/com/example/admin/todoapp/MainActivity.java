package com.example.admin.todoapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.todoapp.DB.TaskTable;
import com.example.admin.todoapp.models.TaskModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="logs";
    ImageButton datepicker,btnAddTask;
    int myear,mmonth,mday;
    RecyclerView tasklist;
    EditText etAddTask;
   static SQLiteDatabase mDB;
    static ArrayList<TaskModel>tasks;
    static TaskAdapter Adapter;
    private float x1,x2;
    static final int MIN_DISTANCE = 50;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    DeleteFragment fragment;


    public void setdate(View v)
    {

        final Calendar c = Calendar.getInstance();
       final int mYear = c.get(Calendar.YEAR);
      final int  mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               if(year>=mYear && monthOfYear>=mMonth && dayOfMonth>=mDay) {
                   myear = year;
                   mmonth = monthOfYear;
                   mday = dayOfMonth;
               }
                else
                   Toast.makeText(MainActivity.this, "Please select a valid date", Toast.LENGTH_SHORT).show();


            }
        },mYear,mMonth,mDay);

        dialog.show();

    }

    public void initialize_components()
    {
        datepicker=(ImageButton)findViewById(R.id.date_select);
        tasklist=(RecyclerView)findViewById(R.id.rv_tasks);
        etAddTask=(EditText)findViewById(R.id.et_task_add);
        Calendar c=Calendar.getInstance();
        myear=c.get(Calendar.YEAR);
        mmonth=c.get(Calendar.MONTH);
        mday=c.get(Calendar.DAY_OF_MONTH);
        tasks=new ArrayList<>();
        fragment=new DeleteFragment();

    }

    public void update_database(View v)
    {

        String task_name=etAddTask.getText().toString();
        if(task_name.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter a task ", Toast.LENGTH_SHORT).show();
            return;
        }
        task_name=task_name.substring(0,1).toUpperCase()+task_name.substring(1);

        TaskModel newtask=new TaskModel(task_name,""+mday+"-"+mmonth+"-"+myear);
        ContentValues value=new ContentValues();
        value.put(TaskTable.Columns.NAME,newtask.getName());
        value.put(TaskTable.Columns.DEADLINE,newtask.getDeadline());
        value.put(TaskTable.Columns.DONE,newtask.isDone());
        newtask.setId(mDB.insert(TaskTable.tb_name,null,value));
        tasks.add(newtask);
        Collections.sort(tasks, new Comparator<TaskModel>() {
            @Override
            public int compare(TaskModel lhs, TaskModel rhs) {
                return lhs.getDeadline().compareTo(rhs.getDeadline());
            }
        });

        Adapter.notifyDataSetChanged();
    }

    public void set_recyclerview()
    {
        //tasklist.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.RED).sizeResId(150).marginResId(30, 100).build());

     Adapter=new TaskAdapter(tasks);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        tasklist.setLayoutManager(layoutManager);

        tasklist.setAdapter(Adapter);
        Adapter.notifyDataSetChanged();
    }

    public static void load_data()
    {

        Cursor c=mDB.query(
                TaskTable.tb_name,TaskTable.projection,null,null,null,null,null);
        tasks.clear();
        while(c.moveToNext())
        {
            int id= c.getInt(c.getColumnIndex(TaskTable.Columns.ID));
                String name=     c.getString(c.getColumnIndex(TaskTable.Columns.NAME));
                String date=     c.getString(c.getColumnIndex(TaskTable.Columns.DEADLINE));
                int done=     c.getInt(c.getColumnIndex(TaskTable.Columns.DONE));

            Log.d(TAG, "load_data: name " + name);
                TaskModel obj=new TaskModel(name,date);
                obj.setId(id);
                obj.setDone(done);

             tasks.add(obj);
        }
        Collections.sort(tasks, new Comparator<TaskModel>() {
            @Override
            public int compare(TaskModel lhs, TaskModel rhs) {
                return lhs.getDeadline().compareTo(rhs.getDeadline());
            }
        });
        Adapter.notifyDataSetChanged();

        c.close();


    }

    public void delete_Db()
    {
        mDB.delete(TaskTable.tb_name, TaskTable.Columns.DONE + "=" + "1", null);
        load_data();

    }

   public  void handleShakeEvent(int count)
    {
        fragment.show(getSupportFragmentManager(),"delete");
    }

    void set_Accelero()
    {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {

				  handleShakeEvent(count);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize_components();
        mDB=DatabaseCreator.openWriteableDatabse(this);
        set_recyclerview();
        load_data();
        set_Accelero();
        fragment.setmListener(new DeleteFragment.NoticeDialogListener() {
            @Override
            public void onDialogPositiveClick() {
             delete_Db();
            }
            @Override
            public void onDialogNegativeClick(DialogFragment dialog) {

            }
        });

    }
    public class TaskViewHolder extends RecyclerView.ViewHolder
    {
        TextView task_name,task_date;
        public TaskViewHolder(View itemView) {
            super(itemView);
            task_name= (TextView) itemView.findViewById(R.id.tv_li_name);
            task_date=(TextView)itemView.findViewById(R.id.tv_li_date);
        }
    }


    public void datastatuschange(long id,int done)
    {
        ContentValues values=new ContentValues();
        values.put(TaskTable.Columns.DONE,done);
        String selection = TaskTable.Columns.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        mDB.update(TaskTable.tb_name,values,selection,selectionArgs);
    }

    public void delete_data(long id)
    {
        String selection = TaskTable.Columns.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        mDB.delete(TaskTable.tb_name,selection,selectionArgs);
    }


    public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
        ArrayList<TaskModel> mlist;

        public TaskAdapter(ArrayList<TaskModel> mlist) {
            this.mlist = mlist;
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View Itemview = getLayoutInflater().inflate(R.layout.task_list_item, parent, false);
            TaskViewHolder holder = new TaskViewHolder(Itemview);
            return holder;
        }

        @Override
        public void onBindViewHolder(final TaskViewHolder holder, final int position) {

            final TaskModel task = mlist.get(position);
            holder.task_name.setText(task.getName());
            holder.task_date.setText(task.getDeadline());
            if (task.getDone() == 1)
                holder.task_name.setPaintFlags(holder.task_name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            if (task.getDone() == 0)
                holder.task_name.setPaintFlags(0);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TaskModel obj = mlist.get(position);
                    long id = obj.getId();
                    obj.setDone((obj.getDone() + 1) % 2);
                    datastatuschange(id, obj.getDone());

                    if (obj.getDone() == 1) {
                        holder.task_name.setPaintFlags(holder.task_name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        holder.task_name.setPaintFlags(0);
                    }

                }
            });

            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    TaskModel obj = mlist.get(position);
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            x1 = event.getX();
                            break;
                        case MotionEvent.ACTION_UP:
                            x2 = event.getX();
                            float deltaX = x2 - x1;
                            if (deltaX < 0) {

                            } else if (deltaX > MIN_DISTANCE) {
                                Toast.makeText(MainActivity.this,
                                        "Deleted",
                                        Toast.LENGTH_SHORT).show();
                                //delete object

                                tasks.remove(position);
                                Adapter.notifyDataSetChanged();

                                //remove from database
                                delete_data(obj.getId());
                            }
                            break;
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mlist.size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }


    @Override
    protected void onPause() {

        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}

