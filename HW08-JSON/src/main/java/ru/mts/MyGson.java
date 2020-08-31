package ru.mts;

import javax.json.*;

import java.lang.reflect.*;
import java.util.*;

public class MyGson {
    private Map<String, Object> map = new HashMap();
    private Method[] methods;
    private Field[] fieldsAll;
    private Class cl;
    private static Object[] args = {};

    public MyGson() {
    }

    @Override
    public String toString() {
        return "MyGson{}";
    }

    public String toJson(Object object) throws Exception {
        if (object == null) {
            throw new Exception();
        }
        cl = object.getClass();
        methods = cl.getMethods();
        fieldsAll = cl.getDeclaredFields();
        for (Field f : fieldsAll) {
            for (Method method : methods) {
                findMethod(f, method, object);
            }
        }
        var jsonObject = Json.createObjectBuilder(map).build();
        return jsonObject.toString();
    }

    private void findMethod(Field f, Method method, Object object) throws Exception {
        if ((method.getName().startsWith("get") || method.getName().startsWith("is"))
                && method.getName().toLowerCase().endsWith(f.getName().toLowerCase())
                && method.getReturnType().getTypeName().equals(f.getType().getTypeName())
        ) {
            addMap(f, method, object);
        }
    }

    private void addMap(Field f, Method method, Object object) throws Exception {
        if (!method.getReturnType().isArray()) {
            map.put(f.getName(), method.invoke(object, args));
        } else {
            MyTypeAdapter myTypeAdapter = new MyTypeAdapter();
            myTypeAdapter.setList(object, f, method);
            map.put(f.getName(), myTypeAdapter.getList());
        }
    }

    private static class MyTypeAdapter {
        private static Object[] args1 = {};
        List<?> list = new ArrayList<>();

        public void setList(Object object, Field f, Method m) throws Exception {
            Type type = m.getReturnType().componentType();
            switch (type.getTypeName()) {
                case "int":
                    list = arrayToList(toObject((int[]) m.invoke(object, args1)));
                    break;
                case "double":
                    list = arrayToList(toObject((double[]) m.invoke(object, args1)));
                    break;
                case "long":
                    list = arrayToList(toObject((long[]) m.invoke(object, args1)));
                    break;
                case "float":
                    list = arrayToList(toObject((float[]) m.invoke(object, args1)));
                    break;
                case "boolean":
                    list = arrayToList(toObject((boolean[]) m.invoke(object, args1)));
                    break;
                case "java.lang.String":
                    list = arrayToList(toObject((String[]) m.invoke(object, args1)));
                    break;
                case "char":
                    list = arrayToList(toObject((char[]) m.invoke(object, args1)));
                    break;
            }
        }

        public List<?> getList() {
            return (List<?>) this.list;
        }

        public <T> List<T> arrayToList(T[] array) throws Exception {
            List<T> list = new ArrayList<>();
            for (T a : array) {
                list.add(a);
            }
            return list;
        }

        public static Integer[] toObject(int[] array) {
            Integer[] result = new Integer[array.length];
            for (int i = 0; i < array.length; i++) {
                result[i] = Integer.valueOf(array[i]);
            }
            return result;
        }

        public static String[] toObject(String[] array) {
            String[] result = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                result[i] = String.valueOf(array[i]);
            }
            return result;
        }

        public static Double[] toObject(double[] array) {
            Double[] result = new Double[array.length];
            for (int i = 0; i < array.length; i++) {
                result[i] = Double.valueOf(array[i]);
            }
            return result;
        }

        public static Long[] toObject(long[] array) {
            Long[] result = new Long[array.length];
            for (int i = 0; i < array.length; i++) {
                result[i] = Long.valueOf(array[i]);
            }
            return result;
        }

        public static Float[] toObject(float[] array) {
            Float[] result = new Float[array.length];
            for (int i = 0; i < array.length; i++) {
                result[i] = Float.valueOf(array[i]);
            }
            return result;
        }

        public static Boolean[] toObject(boolean[] array) {
            Boolean[] result = new Boolean[array.length];
            for (int i = 0; i < array.length; i++) {
                result[i] = Boolean.valueOf(array[i]);
            }
            return result;
        }

        public static Character[] toObject(char[] array) {
            Character[] result = new Character[array.length];
            for (int i = 0; i < array.length; i++) {
                result[i] = Character.valueOf(array[i]);
            }
            return result;

        }
    }
}

