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


public class RoleDaoUpdateTest extends DBUnitConfig {
	private JdbcRoleDao dao = new JdbcRoleDao();

	public RoleDaoUpdateTest(String name) {
		super(name);
	}

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
//        Role role = new Role(3, "Super Manager");
//		dao.update(role);

		// connection to expected db result
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("expectedUpdateTest.xml"));

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
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE roles_tb SET name = ? WHERE id = ? ;");
        long id = 3L;
        String name = "Head of Department";
        preparedStatement.setString(1, name);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
		IDataSet actualData = tester.getConnection().createDataSet();
		
		// debugging
		ITable table1 = actualData.getTable("ROLES_TB");
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
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "ROLES_TB", ignore);
	}

}
