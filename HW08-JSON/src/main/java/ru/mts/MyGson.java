package ru.mts;

import javax.json.Json;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyGson {

    public MyGson() {
    }

    @Override
    public String toString() {
        return "MyGson{}";
    }

    public String toJson(Object object) throws Exception{
        if (object == null) {    throw new Exception();     }
        Map<String, Object> map = new HashMap();
        Object[] args = {};
        Class cl = object.getClass();
        Method[] methods = cl.getMethods();
        Field[] fieldsAll = cl.getDeclaredFields();
        for (Field f:fieldsAll){
            for (Method method : methods) {
                if(
                        (method.getName().startsWith("get")||method.getName().startsWith("is"))
                                && method.getName().toLowerCase().endsWith(f.getName().toLowerCase())
                                && method.getReturnType().getTypeName().equals(f.getType().getTypeName())
                ){
                    if(!method.getReturnType().getTypeName().contains("[]")){
                        map.put(f.getName(), method.invoke(object, args));
                    }
                    else{
                        switch (method.getReturnType().getComponentType().getName()) {
                            case "int":
                                List<Integer> list = new ArrayList<>();
                                for (int o : (int[]) method.invoke(object, args)) {
                                    list.add(o);
                                }
                                map.put(f.getName(), list);
                                break;
                            case "double":
                                List<Double> listDouble = new ArrayList<>();
                                for (double o : (double[]) method.invoke(object, args)) {
                                    listDouble.add(o);
                                }
                                map.put(f.getName(), listDouble);
                                break;
                            case "long":
                                List<Long> listLong = new ArrayList<>();
                                for (long o : (long[]) method.invoke(object, args)) {
                                    listLong.add(o);
                                }
                                map.put(f.getName(), listLong);
                                break;
                            case "float":
                                List<Float> listFloat = new ArrayList<>();
                                for (float o : (float[]) method.invoke(object, args)) {
                                    listFloat.add(o);
                                }
                                map.put(f.getName(), listFloat);
                                break;
                            case "boolean":
                                List<Boolean> listBoolean = new ArrayList<>();
                                for (boolean o : (boolean[]) method.invoke(object, args)) {
                                    listBoolean.add(o);
                                }
                                map.put(f.getName(), listBoolean);
                                break;
                            case "java.lang.String":
                                List<String> listString = new ArrayList<>();
                                for (String s : (String[]) method.invoke(object, args)) {
                                    listString.add(s);
                                }
                                map.put(f.getName(), listString);
                                break;
                        }
                    }
                }
            }
        }
        var jsonObject = Json.createObjectBuilder(map).build();
        return jsonObject.toString();
    }
}
