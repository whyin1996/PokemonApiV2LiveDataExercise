package io.rene.pokeapilivedatademo.common.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "POKEMON")
public class Pokemon {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    public int id;

    @ColumnInfo(name = "URL")
    @SerializedName("url")
    @Expose
    private String url;

    @ColumnInfo(name = "NAME")
    @SerializedName("name")
    @Expose
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
