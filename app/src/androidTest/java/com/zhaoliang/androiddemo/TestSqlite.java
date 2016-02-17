package com.zhaoliang.androiddemo;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.zhaoliang.androiddemo.day03.sqlite.dao.PersonDao;
import com.zhaoliang.androiddemo.day03.sqlite.db.DbOpenHelper;
import com.zhaoliang.androiddemo.day03.sqlite.domain.Person;

import java.util.List;

/**
 * 测试Sqlite的使用
 * Created by pro on 16/2/3.
 */
public class TestSqlite extends AndroidTestCase {

    DbOpenHelper dbOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    PersonDao personDao;

    @Override
    protected void setUp() throws Exception {
        personDao = new PersonDao(getContext());
    }

    /**
     * 测试DbOpenHelper
     */
    public void testDbOperHelper() {

        dbOpenHelper = new DbOpenHelper(getContext());
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    }

    /**
     * 测试通过sql语句插入数据
     */
    public void testInsertBySql() {
        for (int i = 0; i < 10; i++) {
            personDao.insertBySql(new Person(i, "张三" + i, "男", "10000" + i, "北京"));
        }
    }

    /**
     * 测试通过sql语句删除数据
     */
    public void testDeleteBySql() {
        personDao.deleteBySql(1);
    }

    /**
     * 测试通过sql语句更新数据
     */
    public void testUpdateBySql() {
        personDao.updateBySql(new Person(2, "李四", "男", "100000", "北京"));
    }

    /**
     * 查询所有数据
     */
    public void testQueryAllBySql() {
        List<Person> persons = personDao.queryAllBuSql();
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    /**
     * 根据id查询
     */
    public void testQueryBySqlId() {
        Person person = personDao.queryBySqlId(2);
        System.out.println(person);
    }

    /**
     * 分页查询
     */
    public void testQueryBySqlPage() {
        List<Person> persons = personDao.queryBySqlPage(3, 3);
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    /**
     * 插入数据
     */
    public void testInsertByApi() {
        for (int i = 10; i < 20; i++) {
            personDao.insertByApi(new Person(i, "李四" + i, "男", "19999" + i, "北京"));
        }
    }

    /**
     * 删除数据
     */
    public void testDeleteApi() {
        personDao.deleteByApi(19);
    }

    /**
     * 更新数据
     */
    public void testUpdateApi() {
        personDao.updateByApi(new Person(20, "张三", "男", "19999", "北京"));
    }

    /**
     * 查询所有数据
     */
    public void testQueryAllByApi() {
        List<Person> persons = personDao.queryAllByApi();
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    /**
     * 根据Id查询
     */
    public void testQueryByApiId() {
        Person person = personDao.queryByApiId(20);
        System.out.println(person);
    }

    /**
     * 分页查询
     */
    public void testQueryByApiPage() {
        List<Person> persons = personDao.queryByApiPage(3, 2);
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    /**
     * 测试事务
     */
    public void testTranstation() {
        personDao.transtation();
    }
}
