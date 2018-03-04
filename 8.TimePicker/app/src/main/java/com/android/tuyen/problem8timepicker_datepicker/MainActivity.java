package com.android.tuyen.problem8timepicker_datepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {
    private TextView tvDay, tvTime;
    private EditText edtCongViec, edtNoiDung;
    private Button btnDay, btnTime, btnThemCV;
    private ListView listViewCV;
    private ArrayList<JobInWeek> arrayList = new ArrayList<>();
    private ArrayAdapter<JobInWeek> adapter = null;
    private Calendar calendar;
    private Date dateFinish, hourFinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getDefaultInfor();

    }

    void initView() {
        tvDay = findViewById(R.id.tv_day);
        tvTime = findViewById(R.id.tv_time);
        edtCongViec = findViewById(R.id.edt_cong_viec);
        edtNoiDung = findViewById(R.id.edt_noi_dung);
        btnDay = findViewById(R.id.btn_date);
        btnTime = findViewById(R.id.btn_time);
        btnThemCV = findViewById(R.id.btn_themCV);
        listViewCV = findViewById(R.id.list_view_congviec);
        btnDay.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnThemCV.setOnClickListener(this);
        listViewCV.setOnItemClickListener(this);
        listViewCV.setOnItemLongClickListener(this);

        adapter = new ArrayAdapter<JobInWeek>(this, android.R.layout.simple_list_item_1, arrayList);
        listViewCV.setAdapter(adapter);
    }

    /**
     * Hàm lấy các thông số mặc định khi lần đầu tiền chạy ứng dụng
     */
    public void getDefaultInfor() {
        //lấy ngày hiện tại của hệ thống
        calendar = Calendar.getInstance();
        SimpleDateFormat dft = null;
        //Định dạng ngày / tháng /năm
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(calendar.getTime());
        //hiển thị lên giao diện
        tvDay.setText(strDate);
        //Định dạng giờ phút am/pm
        dft = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String strTime = dft.format(calendar.getTime());
        //đưa lên giao diện
        tvTime.setText(strTime);
        //lấy giờ theo 24 để lập trình theo Tag
        dft = new SimpleDateFormat("HH:mm", Locale.getDefault());
        tvTime.setTag(dft.format(calendar.getTime()));

        edtCongViec.requestFocus();
        //gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
        dateFinish = calendar.getTime();
        hourFinish = calendar.getTime();
    }


    public void showDayPickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                tvDay.setText(
                        (dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year);
                //Lưu vết lại biến ngày hoàn thành
                calendar.set(year, monthOfYear, dayOfMonth);
                dateFinish = calendar.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = tvDay.getText() + "";
        String strArrtmp[] = s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1]) - 1;
        int nam = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(
                MainActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }

    /**
     * Hàm hiển thị TimePickerDialog
     */
    public void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {
                //Xử lý lưu giờ và AM,PM
                String s = hourOfDay + ":" + minute;
                int hourTam = hourOfDay;
                if (hourTam > 12)
                    hourTam = hourTam - 12;
                tvTime.setText
                        (hourTam + ":" + minute + (hourOfDay > 12 ? " PM" : " AM"));
                //lưu giờ thực vào tag
                tvTime.setTag(s);
                //lưu vết lại giờ vào hourFinish
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                hourFinish = calendar.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong TimePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = tvTime.getTag() + "";
        String strArr[] = s.split(":");
        int gio = Integer.parseInt(strArr[0]);
        int phut = Integer.parseInt(strArr[1]);
        TimePickerDialog time = new TimePickerDialog(
                MainActivity.this,
                callback, gio, phut, true);
        time.setTitle("Chọn giờ hoàn thành");
        time.show();
    }

    /**
     * Hàm xử lý đưa công việc vào ListView khi nhấn nút Thêm Công việc
     */
    public void processAddJob() {
        String title = edtCongViec.getText() + "";
        String description = edtNoiDung.getText() + "";
        JobInWeek job = new JobInWeek(title, description, dateFinish, hourFinish);
        arrayList.add(job);
        adapter.notifyDataSetChanged();
        //sau khi cập nhật thì reset dữ liệu và cho focus tới editCV
        edtCongViec.setText("");
        edtNoiDung.setText("");
        edtCongViec.requestFocus();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_date:
                showDayPickerDialog();
                break;
            case R.id.btn_time:
                showTimePickerDialog();
                break;
            case R.id.btn_themCV:
                processAddJob();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(MainActivity.this,
                arrayList.get(position).getDescription(),
                Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        arrayList.remove(position);
        adapter.notifyDataSetChanged();
        return false;
    }
}