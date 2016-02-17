package com.zhaoliang.androiddemo.day02.storage.util;

import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 存储工具类
 * Created by pro on 16/2/2.
 */
public class StorageUtil {

    /**
     * 向存储中写数据
     *
     * @param path     context.getFilesDir() + File.separator + "internal.txt"
     * @param username
     * @param password
     * @return
     */
    public static boolean saveToStorage(String path, String username, String password) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write((username + "##" + password).getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 从存储中读数据
     *
     * @param path context.getFilesDir() + File.separator + "internal.txt")
     * @return
     */
    public static Map<String, String> getFormStorage(String path) {
        Map<String, String> result = null;
        BufferedReader reader = null;
        try {
            result = new HashMap<>();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String readLine = reader.readLine();
            String[] split = readLine.split("##");
            result.put("username", split[0]);
            result.put("password", split[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用SharedPerferences保存数据
     *
     * @param sharedPreferences
     * @param username
     * @param password
     * @return
     */
    public static boolean saveToSharedPerferences(SharedPreferences sharedPreferences, String username, String password) {
        return sharedPreferences.edit().putString("username", username).putString("password", password).commit();
    }

    /**
     * 从sharedPerferences中读取数据
     * @param sharedPerferences
     * @return
     */
    public static Map<String, String> getFromSharedPerferences(SharedPreferences sharedPerferences) {
        Map<String, String> result = new HashMap<>();
        result.put("username", sharedPerferences.getString("username", ""));
        result.put("password", sharedPerferences.getString("password", ""));
        return result;
    }
}
