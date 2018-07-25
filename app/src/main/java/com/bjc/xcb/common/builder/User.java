package com.bjc.xcb.common.builder;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private final String mFirstName;
    private final String mLastName;
    private final String mGender;
    private final int mAge;
    private final String mPhone;

    private User(Builder builder) {
        mFirstName = builder.mFirstName;
        mLastName = builder.mLastName;
        mGender = builder.mGender;
        mAge = builder.mAge;
        mPhone = builder.mPhone;
    }

    public static final class Builder {
        private String mFirstName;
        private String mLastName;
        private String mGender;
        private int mAge;
        private String mPhone;

        public Builder() {
        }

        public Builder setFirstName(String val) {
            mFirstName = val;
            return this;
        }

        public Builder setLastName(String val) {
            mLastName = val;
            return this;
        }

        public Builder setGender(String val) {
            mGender = val;
            return this;
        }

        public Builder setAge(int val) {
            mAge = val;
            return this;
        }

        public Builder setPhone(String val) {
            mPhone = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mFirstName);
        dest.writeString(this.mLastName);
        dest.writeString(this.mGender);
        dest.writeInt(this.mAge);
        dest.writeString(this.mPhone);
    }

    protected User(Parcel in) {
        this.mFirstName = in.readString();
        this.mLastName = in.readString();
        this.mGender = in.readString();
        this.mAge = in.readInt();
        this.mPhone = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


}
