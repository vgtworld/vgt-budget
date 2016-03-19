package pl.vgtworld.budget.core.database;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class LiquibaseSetup {

	private static final String STAGE = "development";

	private static final String SCHEMA_FILE = "liquibase/schema.xml";

	@Resource(lookup = "java:jboss/datasources/vgt-budget")
	private DataSource ds;

	@PostConstruct
	protected void bootstrap() {
		ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(getClass().getClassLoader());
		try (Connection connection = ds.getConnection()) {
			JdbcConnection jdbcConnection = new JdbcConnection(connection);
			Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);

			Liquibase liquiBase = new Liquibase(SCHEMA_FILE, resourceAccessor, db);
			liquiBase.update(STAGE);
		} catch (SQLException | LiquibaseException e) {
			throw new RuntimeException(e);
		}

	}
}