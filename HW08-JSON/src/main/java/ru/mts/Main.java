package ru.mts;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Start");
        Gson gson = new Gson();
        AnyObject obj = new AnyObject(22, "test", 10);
        long[] longs = {123456789,987654321,1029384756};
        obj.setLongs(longs);
        String json = gson.toJson(obj);
        System.out.println(json);

        MyGson myGson = new MyGson();
        String myJson = myGson.toJson(obj);
        System.out.println(myJson);
        AnyObject obj2 = gson.fromJson(myJson, AnyObject.class);
        System.out.println(obj.equals(obj2));
    }
}
