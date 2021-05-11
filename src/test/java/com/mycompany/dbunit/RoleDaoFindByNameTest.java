package test.java.com.mycompany.dbunit;

import main.java.com.mycompany.dao.JdbcRoleDao;
import main.java.com.mycompany.entity.Role;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RoleDaoFindByNameTest extends DBUnitConfig {
    private JdbcRoleDao dao = new JdbcRoleDao();

    public RoleDaoFindByNameTest(String name) {
        super(name);
    }

    @Before
    protected void setUp() throws Exception {

        super.setUp();
        beforeData = new FlatXmlDataSetBuilder()
                .build(Thread.currentThread().getContextClassLoader().getResourceAsStream("datasetRoleFindByNameTest.xml"));
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
//		String name = "User";
//		Role roleByName = dao.findByName(name);
        // connection to expected db result
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(Thread.currentThread().getContextClassLoader().getResourceAsStream("expectedFindByNameTest.xml"));

        ITable expectedDataTable = expectedData.getTable("roles_tb");

        // debugging
        ITable table = expectedData.getTable("ROLES_TB");
        ITableMetaData tableMetaData = table.getTableMetaData();
        System.out.println("========>>>>  Meta data " + tableMetaData);
        Column[] columns = tableMetaData.getColumns();
        for (Column col : columns) {
            System.out.println("======>>>  Column " + col);
        }
        // connection to actual db result
        ITable actualDataTable = tester.getConnection().createQueryTable("result_name", "SELECT * FROM roles_tb WHERE name = 'User';");


        // debugging
//        ITable table1 = actualData.getTable("ROLES_TB");
//        ITableMetaData tableMetaData1 = table1.getTableMetaData();
//        System.out.println("========>>>>  Meta data " + tableMetaData1);
//        Column[] columns1 = tableMetaData1.getColumns();
//        for (Column col : columns1) {
//            System.out.println("======>>>  Column " + col);
//        }
//        System.out.println(table1.getRowCount());

        // ignoring id
        String[] ignore = {"id"};

        // assertions
//        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "ROLES_TB", ignore);

        Assertion.assertEqualsIgnoreCols(expectedDataTable, actualDataTable, ignore);

    }
}
