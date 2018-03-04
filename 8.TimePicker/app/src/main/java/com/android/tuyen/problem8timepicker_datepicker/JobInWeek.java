package com.android.tuyen.problem8timepicker_datepicker;

/**
 * Created by Tuyen on 3/1/2018.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JobInWeek {
    private String title;
    private String description;
    private Date dateFinish;
    private Date hoursFinish;

    public JobInWeek() {
        super();
    }

    public JobInWeek(String title, String description, Date dateFinish, Date hoursFinish) {
        this.title = title;
        this.description = description;
        this.dateFinish = dateFinish;
        this.hoursFinish = hoursFinish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getHoursFinish() {
        return hoursFinish;
    }

    public void setHoursFinish(Date hoursFinish) {
        this.hoursFinish = hoursFinish;
    }

    /**
     * lấy định dạng ngày
     *
     * @param d
     * @return
     */
    public String getDateFormat(Date d) {
        SimpleDateFormat dft = new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }

    /**
     * lấy định dạng giờ phút
     *
     * @param d
     * @return
     */
    public String getHourFormat(Date d) {
        SimpleDateFormat dft = new
                SimpleDateFormat("hh:mm a", Locale.getDefault());
        return dft.format(d);
    }

    @Override
    public String toString() {
        return this.title + "-" +
                getDateFormat(this.dateFinish) + "-" +
                getHourFormat(this.hoursFinish);
    }


}
