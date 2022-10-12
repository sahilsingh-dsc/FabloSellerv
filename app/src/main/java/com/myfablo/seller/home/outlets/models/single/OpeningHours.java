package com.myfablo.seller.home.outlets.models.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpeningHours {

    @SerializedName("0")
    @Expose
    private List<String> _0 = null;
    @SerializedName("1")
    @Expose
    private List<String> _1 = null;
    @SerializedName("2")
    @Expose
    private List<String> _2 = null;
    @SerializedName("3")
    @Expose
    private List<String> _3 = null;
    @SerializedName("4")
    @Expose
    private List<String> _4 = null;
    @SerializedName("5")
    @Expose
    private List<String> _5 = null;
    @SerializedName("6")
    @Expose
    private List<String> _6 = null;

    /**
     * No args constructor for use in serialization
     */
    public OpeningHours() {
    }

    /**
     * @param _0
     * @param _1
     * @param _2
     * @param _3
     * @param _4
     * @param _5
     * @param _6
     */
    public OpeningHours(List<String> _0, List<String> _1, List<String> _2, List<String> _3, List<String> _4, List<String> _5, List<String> _6) {
        super();
        this._0 = _0;
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
    }

    public List<String> get0() {
        return _0;
    }

    public void set0(List<String> _0) {
        this._0 = _0;
    }

    public List<String> get1() {
        return _1;
    }

    public void set1(List<String> _1) {
        this._1 = _1;
    }

    public List<String> get2() {
        return _2;
    }

    public void set2(List<String> _2) {
        this._2 = _2;
    }

    public List<String> get3() {
        return _3;
    }

    public void set3(List<String> _3) {
        this._3 = _3;
    }

    public List<String> get4() {
        return _4;
    }

    public void set4(List<String> _4) {
        this._4 = _4;
    }

    public List<String> get5() {
        return _5;
    }

    public void set5(List<String> _5) {
        this._5 = _5;
    }

    public List<String> get6() {
        return _6;
    }

    public void set6(List<String> _6) {
        this._6 = _6;
    }

}