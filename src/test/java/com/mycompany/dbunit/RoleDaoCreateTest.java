package test.java.com.mycompany.dbunit;

import main.java.com.mycompany.dao.JdbcRoleDao;
import org.dbunit.Assertion;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RoleDaoCreateTest extends DBUnitConfig {
    private JdbcRoleDao dao = new JdbcRoleDao();

    public RoleDaoCreateTest(String name) {
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
//        Role role = new Role("User");
//        dao.create(role);

        // connection to expected db result
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(Thread.currentThread().getContextClassLoader().getResourceAsStream("expectedCreateTest.xml"));

        // debugging
        ITable table = expectedData.getTable("ROLES_TB");
        ITableMetaData tableMetaData = table.getTableMetaData();
        System.out.println("========>>>>  Meta data " + tableMetaData);
        Column[] columns = tableMetaData.getColumns();
        for (Column col : columns) {
            System.out.println("======>>>  Column " + col);
        }

        // connection to actual db result
        Connection connection = tester.getConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO roles_tb(name) VALUES( ? );");
        preparedStatement.setString(1, "User");
        preparedStatement.executeUpdate();
        IDataSet actualData = tester.getConnection().createDataSet();


        // debugging
        ITable table1 = actualData.getTable("ROLES_TB");
        ITableMetaData tableMetaData1 = table1.getTableMetaData();
        System.out.println("========>>>>  Meta data " + tableMetaData1);
        Column[] columns1 = tableMetaData1.getColumns();
        for (Column col : columns1) {
            System.out.println("======>>>  Column from actual dataset " + col);
        }
        System.out.println(table1.getRowCount());

        // ignoring id
        String[] ignore = {"id"};

        // assertions
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "ROLES_TB", ignore);
    }
}
