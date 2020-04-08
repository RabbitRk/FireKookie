package com.jikook.firekookie;

import java.util.HashMap;

public class User {
    String address, username;

    public User(String address, String username) {
        this.address = address;
        this.username = username;
    }

    /*
    * I dont know how to convert a modal class object into a HashMap
    * I hope this method will helps
    * */
    public HashMap<String, Object> to_map()
    {
        HashMap<String, Object> result = new HashMap<>();
        result.put("address", address);
        result.put("username", username);
        return result;

    }
}
