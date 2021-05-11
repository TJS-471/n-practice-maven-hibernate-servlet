package test.java.com.mycompany.dbunit;

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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


public class UserDaoFindAllTest extends DBUnitConfig {
	private JdbcUserDao dao = new JdbcUserDao();

	public UserDaoFindAllTest(String name) {
		super(name);
	}

	@Before
	protected void setUp() throws Exception {
		
		super.setUp();
		beforeData = new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("actualUserFindAllTest.xml"));
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
//        List<User> allUsers = dao.findAll();

		// connection to expected db result
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("expectedUserFindAllTest.xml"));

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
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users_tb ;");
        preparedStatement.executeQuery();
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
		String[] ignore = { "id" };

		// assertions 
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "USERS_TB", ignore);
	}
}
