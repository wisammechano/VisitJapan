package com.recoded.visitjapan;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by wisam on Oct 17 17.
 */

public class Location implements Parcelable {
    private String mCategory, mName, mDesc, mTel, mUrl, mAddr;
    private String mLat, mLong;
    private int mImgRes;
    private ArrayList<Integer> mImgsRes;

    public Location(String mCategory, String mName) {
        this.mCategory = mCategory;
        this.mName = mName;
        this.mImgsRes = new ArrayList<>();
    }

    public Location(){
        this.mImgsRes = new ArrayList<>();
    }

    public String getCategory() {
        return mCategory;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDesc;
    }

    public String getTelephoneNo() {
        return mTel;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getAddress() {
        return mAddr;
    }

    public Uri getLocationUri() {
        return Uri.parse("geo:" + mLat + "," + mLong+"?q=" + mLat + "," + mLong + "(" + mName + ")");
    }

    public int getImgResource() {
        return mImgRes;
    }

    public ArrayList<Integer> getGalleryArray() {
        return mImgsRes;
    }

    public void setDescription(String desc) {
        this.mDesc = desc;
    }

    public void setAddress(String addr) {
        this.mAddr = addr;
    }

    public void setTelephoneNo(String tel) {
        this.mTel = tel;
    }

    public void setImgResouce(int id) {
        this.mImgRes = id;
    }

    public void addImgToGallery(Integer id) {
        this.mImgsRes.add(id);
    }

    public void removeImgFromGallery(Integer id) {
        this.mImgsRes.remove(id);
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public void setLatitude(String latitude) {
        this.mLat = latitude;
    }

    public void setLongitude(String longitude){
        this.mLong = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCategory);
        dest.writeString(this.mName);
        dest.writeString(this.mDesc);
        dest.writeString(this.mTel);
        dest.writeString(this.mUrl);
        dest.writeString(this.mAddr);
        dest.writeString(this.mLat);
        dest.writeString(this.mLong);
        dest.writeInt(this.mImgRes);
        dest.writeList(this.mImgsRes);
    }

    protected Location(Parcel in) {
        this.mCategory = in.readString();
        this.mName = in.readString();
        this.mDesc = in.readString();
        this.mTel = in.readString();
        this.mUrl = in.readString();
        this.mAddr = in.readString();
        this.mLat = in.readString();
        this.mLong = in.readString();
        this.mImgRes = in.readInt();
        this.mImgsRes = new ArrayList<>();
        in.readList(this.mImgsRes, Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
