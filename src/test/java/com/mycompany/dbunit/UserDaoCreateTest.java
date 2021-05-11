package test.java.com.mycompany.dbunit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import main.java.com.mycompany.dao.JdbcRoleDao;
import main.java.com.mycompany.dao.JdbcUserDao;
import main.java.com.mycompany.entity.Role;
import main.java.com.mycompany.entity.User;
import org.dbunit.Assertion;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UserDaoCreateTest extends DBUnitConfig {
    private JdbcUserDao dao = new JdbcUserDao();
    private JdbcRoleDao daoRole = new JdbcRoleDao();

    public UserDaoCreateTest(String name) {
        super(name);
    }

    @Before
    protected void setUp() throws Exception {

        super.setUp();
        beforeData = new FlatXmlDataSetBuilder()
                .build(Thread.currentThread().getContextClassLoader().getResourceAsStream("actualCreateTest.xml"));
        tester.setDataSet(beforeData);
        tester.setSetUpOperation(getSetUpOperation());
        tester.onSetup();
    }

    @After
    protected void tearDown() throws Exception {
        tester.setTearDownOperation(getTearDownOperation());
        tester.onTearDown();
    }

    @Test
    public void testCreate() throws Exception {
        @SuppressWarnings("deprecation")
//        User user1 = new User("kate", "kate@bla.com", "123456", "Katherine", "Green", new Date(1992,07,23));
//        Role role = new Role(2,"User");
//        daoRole.create(role);
//        user1.addRole(role);
//        dao.create(user1);

        // connection to expected db result
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(Thread.currentThread().getContextClassLoader().getResourceAsStream("expectedCreateTest.xml"));

        // debugging
        ITable table = expectedData.getTable("USERS_TB");
        ITableMetaData tableMetaData = table.getTableMetaData();
        System.out.println("========>>>>  Meta data " + tableMetaData);
        Column[] columns = tableMetaData.getColumns();
        for (Column col : columns) {
            System.out.println("======>>>  Column " + col);
        }

        // connection to actual db result
        Connection connection = tester.getConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users_tb(LOGIN, EMAIL, password, first_name, last_name, birth_date, role_id) VALUES( ?, ?, ?, ?, ?, ?, ?);");
        preparedStatement.setString(1,"kate");
        preparedStatement.setString(2,"kate@bla.com");
        preparedStatement.setString(3,"123456");
        preparedStatement.setString(4, "Katherine");
        preparedStatement.setString(5, "Green");
        preparedStatement.setDate(6, new java.sql.Date(1992,07,23));
        preparedStatement.setLong(7, 2);
        preparedStatement.executeUpdate();
        IDataSet actualData = tester.getConnection().createDataSet();

        // debugging
        ITable table1 = actualData.getTable("USERS_TB");
        ITableMetaData tableMetaData1 = table1.getTableMetaData();
        System.out.println("========>>>>  Meta data " + tableMetaData1);
        Column[] columns1 = tableMetaData1.getColumns();
        for (Column col : columns1) {
            System.out.println("======>>>  Column from actual dataset " + col);
        }
        System.out.println(table1.getRowCount());
        for (int i = 0; i < table1.getRowCount(); i++) {
            for (int j = 0; j < columns1.length; j++) {

                Date birthDate = (Date) table1.getValue(i, "BIRTH_DATE");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date parsedBirthDate = dateFormat.parse(birthDate.toString());
            }
        }
        // ignoring id
        String[] ignore = {"id"};

        // assertions
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "USERS_TB", ignore);
    }
}
