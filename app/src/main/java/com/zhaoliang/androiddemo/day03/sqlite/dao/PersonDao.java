package com.zhaoliang.androiddemo.day03.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhaoliang.androiddemo.day03.sqlite.db.DbOpenHelper;
import com.zhaoliang.androiddemo.day03.sqlite.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Person数据增删该查
 * Created by pro on 16/2/3.
 */
public class PersonDao {

    DbOpenHelper dbOpenHelper;
    SQLiteDatabase db;

    public PersonDao(Context context) {
        dbOpenHelper = new DbOpenHelper(context);
        db = dbOpenHelper.getWritableDatabase();
    }

    /**
     * 插入
     *
     * @param person
     */
    public void insertBySql(Person person) {
        db.execSQL("insert into person(name, sex, salary, address) values(?, ?, ?, ?)", new String[]{person.name, person.sex, person.salary, person.address});
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteBySql(int id) {
        db.execSQL("delete from person where _id = ?", new String[]{String.valueOf(id)});
    }

    /**
     * 更新
     *
     * @param person
     */
    public void updateBySql(Person person) {
        db.execSQL("update person set name = ?, sex = ?, salary = ? where _id = ?", new String[]{person.name, person.sex, person.salary, String.valueOf(person.id)});
    }

    /**
     * 查询全部
     *
     * @return
     */
    public List<Person> queryAllBuSql() {
        List<Person> persons = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from person", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String salary = cursor.getString(cursor.getColumnIndex("salary"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            persons.add(new Person(id, name, sex, salary, address));
        }
        return persons;
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public Person queryBySqlId(int id) {
        Person person = null;
        Cursor cursor = db.rawQuery("select * from person where _id = ?", new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            person = new Person(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("sex")),
                    cursor.getString(cursor.getColumnIndex("salary")),
                    cursor.getString(cursor.getColumnIndex("address")));
        }
        return person;
    }

    /**
     * 分页查询
     *
     * @param pageSize
     * @param page
     * @return
     */
    public List<Person> queryBySqlPage(int pageSize, int page) {
        List<Person> persons = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from person limit ? offset ?", new String[]{String.valueOf(pageSize), String.valueOf(pageSize * (page - 1))});
        while (cursor.moveToNext()) {
            persons.add(new Person(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("sex")),
                    cursor.getString(cursor.getColumnIndex("salary")),
                    cursor.getString(cursor.getColumnIndex("address"))));
        }
        return persons;
    }

    /**
     * 通过api插入数据
     *
     * @param person
     */
    public void insertByApi(Person person) {
        ContentValues values = new ContentValues();
        values.put("name", person.name);
        values.put("sex", person.sex);
        values.put("salary", person.salary);
        values.put("address", person.address);
        db.insert("person", null, values);
    }

    /**
     * 根据id删除数据
     *
     * @param id
     */
    public void deleteByApi(int id) {
        db.delete("person", "_id = ?", new String[]{String.valueOf(id)});
    }

    /**
     * 更新数据
     *
     * @param person
     */
    public void updateByApi(Person person) {
        ContentValues values = new ContentValues();
        values.put("name", person.name);
        values.put("sex", person.sex);
        values.put("salary", person.salary);
        values.put("address", person.address);
        db.update("person", values, "_id = ?", new String[]{String.valueOf(person.id)});
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Person> queryAllByApi() {
        List<Person> persons = new ArrayList<>();
        Cursor person = db.query("person", null, null, null, null, null, null);
        while (person.moveToNext()) {
            persons.add(new Person(person.getInt(person.getColumnIndex("_id")), person.getString(person.getColumnIndex("name")),
                    person.getString(person.getColumnIndex("sex")),
                    person.getString(person.getColumnIndex("salary")),
                    person.getString(person.getColumnIndex("address"))));
        }
        return persons;
    }

    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    public Person queryByApiId(int id) {
        Person person = null;
        Cursor query = db.query("person", null, " _id = ?", new String[]{String.valueOf(id)}, null, null, null);
        while (query.moveToNext()) {
            person = new Person(query.getInt(query.getColumnIndex("_id")),
                    query.getString(query.getColumnIndex("name")),
                    query.getString(query.getColumnIndex("sex")),
                    query.getString(query.getColumnIndex("salary")),
                    query.getString(query.getColumnIndex("address")));
        }
        return person;
    }

    /**
     * 分页查询
     *
     * @param pageSize
     * @param page
     * @return
     */
    public List<Person> queryByApiPage(int pageSize, int page) {
        List<Person> persons = new ArrayList<>();
        Cursor person = db.query("person", null, null, null, null, null, null, (pageSize * (page - 1)) + "," + pageSize);
        while (person.moveToNext()) {
            persons.add(new Person(person.getInt(person.getColumnIndex("_id")),
                    person.getString(person.getColumnIndex("name")),
                    person.getString(person.getColumnIndex("sex")),
                    person.getString(person.getColumnIndex("salary")),
                    person.getString(person.getColumnIndex("address"))));
        }
        return persons;
    }

    public void transtation() {
        try {
            db.beginTransaction();
            updateBySql(new Person(18, "李四", "男", "19999", "北京"));
            //int i = 3 / 0;
            updateBySql(new Person(20, "张三", "男", "19998", "北京"));
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * 打开数据库连接
     *
     * @param context
     */
    public void open(Context context) {
        if (dbOpenHelper == null) {
            dbOpenHelper = new DbOpenHelper(context);
            if (db == null) {
                db = dbOpenHelper.getWritableDatabase();
            }
        }
    }

    /**
     * 关闭连接释放资源
     */
    public void relese() {
        if (db != null) {
            db.close();
            db = null;
            dbOpenHelper = null;
        }
    }
}
