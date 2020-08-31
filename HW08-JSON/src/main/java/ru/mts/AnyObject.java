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
    private double[] doubles = {0.577215665, 1.414213562, 2.718281828, 3.1415926536, 4.669201609, 5.6, 6.283185307, 7.8, 8.88, 9.81};
    private String[] strings = {"a","b","c"};
    private List<Integer> listInteger = new ArrayList();
    private List<Boolean> listBoolean = new ArrayList();
    private Boolean aBoolean = true;

    //Constructor
    public AnyObject(int int1, String string, int int2) {
        this.int1 = int1;
        this.int2 = int2;
        this.string = string;
        for ( long i: longs) {
            listInteger.add((int) i);
        }
        listBoolean.add(false);
        listBoolean.add(true);
        listBoolean.add(true);
    }

    public AnyObject() {
        for ( long i: longs) {
            listInteger.add((int) i);
        }
        listBoolean.add(false);
        listBoolean.add(true);
        listBoolean.add(true);
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

    public void setDoubles(double[] doubles) {
        this.doubles = doubles;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public void setListBoolean(List<Boolean> listBoolean) {
        this.listBoolean = listBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
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

    public double[] getDoubles() {
        return doubles;
    }

    public String[] getStrings() {
        return strings;
    }

    public List<Boolean> getListBoolean() {
        return listBoolean;
    }

    public Boolean getaBoolean() {
        return aBoolean;
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
