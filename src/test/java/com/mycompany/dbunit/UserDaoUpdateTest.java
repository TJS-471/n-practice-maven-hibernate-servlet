package test.java.com.mycompany.dbunit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import main.java.com.mycompany.dao.JdbcRoleDao;
import main.java.com.mycompany.dao.JdbcUserDao;
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

//@RunWith(JUnit4.class)
public class UserDaoUpdateTest extends DBUnitConfig {
    private JdbcUserDao dao = new JdbcUserDao();
    private JdbcRoleDao daoRole = new JdbcRoleDao();

    public UserDaoUpdateTest(String name) {
        super(name);
    }

//    public UserDaoUpdateTest() {
//    }

    @Before
    protected void setUp() throws Exception {

        super.setUp();
        beforeData = new FlatXmlDataSetBuilder()
                .build(Thread.currentThread().getContextClassLoader().getResourceAsStream("actualUpdateTest.xml"));
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
    public void testUpdate() throws Exception {
        @SuppressWarnings("deprecation")
 //       User user1 = new User(2,"kate", "kate@bla.com", "123456", "Kate", "Brown", new Date(1992,07,23));
//        jdbc.entity.Role role = new Role("user");
//        daoRole.create(role);
//        user1.addRole(role);
//        dao.update(user1);

        // connection to expected db result
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(Thread.currentThread().getContextClassLoader().getResourceAsStream("expectedUpdateTest.xml"));

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
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users_tb SET login = ?, email = ?, password = ?, first_name = ?, last_name = ?, birth_date = ? , role_id = ? WHERE id = ? ;");
        preparedStatement.setString(1,"kate");
        preparedStatement.setString(2,"kate@bla.com");
        preparedStatement.setString(3,"123456");
        preparedStatement.setString(4, "Kate");
        preparedStatement.setString(5, "Brown");
        preparedStatement.setDate(6, new java.sql.Date(1992,07,23));
        preparedStatement.setLong(7, 2);
        preparedStatement.setLong(8, 2);
        preparedStatement.executeUpdate();
        IDataSet actualData = tester.getConnection().createDataSet();

        // debugging
        ITable table1 = actualData.getTable("USERS_TB");
        ITableMetaData tableMetaData1 = table1.getTableMetaData();
        System.out.println("========>>>>  Meta data " + tableMetaData1);
        Column[] columns1 = tableMetaData1.getColumns();
        for (Column col : columns1) {
            System.out.println("======>>>  Column " + col);
        }
        System.out.println(table1.getRowCount());

        // ignoring id
        String[] ignore = {"id"};

        // assertions
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "USERS_TB", ignore);
    }

}
