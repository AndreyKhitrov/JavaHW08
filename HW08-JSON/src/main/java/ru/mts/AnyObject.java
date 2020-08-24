package ru.mts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AnyObject {

    private int int1;
    private int int2;
    private String string;
    private long[] longs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private List<Integer> listInteger = new ArrayList();

    //Constructor
    public AnyObject(int int1, String string, int int2) {
        this.int1 = int1;
        this.int2 = int2;
        this.string = string;
        for ( long i: longs) {
            listInteger.add((int) i);
        }
    }

    public AnyObject() {
        for ( long i: longs) {
            listInteger.add((int) i);
        }
    }

    //Setters
    public void setInt1(int int1) {
        this.int1 = int1;
    }

    public void setInt2(int int2) {
        this.int2 = int2;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setLongs(long[] longs) {
        this.longs = longs;
    }

    public void setListInteger(List<Integer> listInteger) {
            this.listInteger = listInteger;
    }

    //Getters
    public int getInt1() {
        return int1;
    }

    public int getInt2() {
        return int2;
    }

    public String getString() {
        return string;
    }

    public long[] getLongs() {
        return longs;
    }

    public List<Integer> getListInteger() {
        return listInteger;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnyObject anyObject = (AnyObject) o;
        return int1 == anyObject.int1 &&
                int2 == anyObject.int2 &&
                Objects.equals(string, anyObject.string) &&
                Arrays.equals(longs, anyObject.longs) &&
                Objects.equals(listInteger, anyObject.listInteger);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(int1, int2, string, listInteger);
        result = 31 * result + Arrays.hashCode(longs);
        return result;
    }
}
