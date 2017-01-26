package db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.Statement;

/**
 * @author kawasima
 */
public class V1__CreateCampaign implements JdbcMigration {
    @Override
    public void migrate(Connection connection) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE campaign (" +
                    "campaign_id INTEGER DEFAULT 1 NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "title VARCHAR(30) NOT NULL," +
                    "statement CLOB NOT NULL," +
                    "goal INTEGER," +
                    "create_user_id INTEGER NOT NULL" +
                    ")"
            );
        }
    }
}
