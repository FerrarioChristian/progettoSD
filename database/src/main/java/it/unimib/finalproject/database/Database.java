package it.unimib.finalproject.database;

import java.util.HashMap;

public class Database<T> {
    private HashMap<String, T> dataMap;

    public Database() {
        dataMap = new HashMap<>();
    }

    public void insertData(String key, T data) {
        dataMap.put(key, data);
        System.out.println("Dati inseriti con successo.");
    }

    public T getData(String key) {
        return dataMap.get(key);
    }

    public void deleteData(String key) {
        if (dataMap.containsKey(key)) {
            dataMap.remove(key);
            System.out.println("Dati eliminati con successo.");
        } else {
            System.out.println("I dati con la chiave " + key + " non esistono.");
        }
    }

    public boolean containsKey(String key) {
        return dataMap.containsKey(key);
    }
}