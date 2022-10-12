package com.myfablo.seller.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllOrderResponseData {

    @SerializedName("all_jobs")
    @Expose
    private List<AllOrderResponseDataDetails> allJobs = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("edit_job_status")
    @Expose
    private Integer editJobStatus;

    /**
     * No args constructor for use in serialization
     */
    public AllOrderResponseData() {
    }

    /**
     * @param count
     * @param allJobs
     * @param editJobStatus
     */
    public AllOrderResponseData(List<AllOrderResponseDataDetails> allJobs, Integer count, Integer editJobStatus) {
        super();
        this.allJobs = allJobs;
        this.count = count;
        this.editJobStatus = editJobStatus;
    }

    public List<AllOrderResponseDataDetails> getAllJobs() {
        return allJobs;
    }

    public void setAllJobs(List<AllOrderResponseDataDetails> allJobs) {
        this.allJobs = allJobs;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getEditJobStatus() {
        return editJobStatus;
    }

    public void setEditJobStatus(Integer editJobStatus) {
        this.editJobStatus = editJobStatus;
    }

}