package io.rene.pokeapilivedatademo.common.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(tableName = "POKEMON")
public class Pokemon {

    @ColumnInfo(name = "URL")
    @SerializedName("url")
    @Expose
    private String url;

    @ColumnInfo(name = "NAME")
    @SerializedName("name")
    @Expose
    private String name;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID")
    private int id;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        Pattern pattern = Pattern.compile("http://pokeapi\\.salestock\\.net/api/v2/pokemon/([0-9]+)/");
        Matcher matcher = pattern.matcher(url);
        matcher.find();
        if (matcher.matches()){
            String result = matcher.group(1);
            setId(Integer.parseInt(result));
        }
    }

//    public static void main(String... args){
//        Pattern pattern = Pattern.compile("http://pokeapi\\.salestock\\.net/api/v2/pokemon/([0-9]+)/");
//        Matcher matcher = pattern.matcher("http://pokeapi.salestock.net/api/v2/pokemon/20/");
//        matcher.find();
//        if (matcher.matches()){
//            String result = matcher.group(1);
//            System.out.println("result: " + result);
//        }
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
