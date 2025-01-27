package com.example.heartpulsesynch;

public class ModelClass {
    String date = "";
    String time = "";
    String systolic = "";
    String diastolic = "";
    String bloodPressure = "";
    String comment = "";

    /**
     * get input from users from the data entry page
     * Users can give the necessary data to
     * monitor them.
     * @param date
     * @param time
     * @param systolic
     * @param diastolic
     * @param bloodPressure
     * @param comment
     */

    public ModelClass(String date, String time, String systolic, String diastolic, String bloodPressure, String comment) {
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.bloodPressure = bloodPressure;
        this.comment = comment;
    }

    /**
     * get data from user
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * get date
     * @param date
     */

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * set date
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * get time
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * set time
     * @return
     */
    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    /**
     * user can give comment.
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * here user give comment
     * @param comment
     */

    public void setComment(String comment) {
        this.comment = comment;
    }
}