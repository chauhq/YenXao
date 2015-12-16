package com.project.chauhq.yenxao.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ChauHQ
 */

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Product implements Parcelable {
    private String urlImage;
    private String type;
    private int money;

    protected Product(Parcel in) {
        urlImage = in.readString();
        type = in.readString();
        money = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(urlImage);
        dest.writeString(type);
        dest.writeInt(money);
    }

}