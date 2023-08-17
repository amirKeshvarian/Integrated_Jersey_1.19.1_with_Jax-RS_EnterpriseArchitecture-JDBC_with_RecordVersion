package org.company.project.common.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
    public static final int ORCLPDB1 = 1;
    public static final int XEPDB1 = 2;
    private ConnectionProvider(){}
    private static final BasicDataSource BASIC_DATA_SOURCE_ORCLPDB1 = new BasicDataSource();
    private static final BasicDataSource BASIC_DATA_SOURCE_XEPDB1 = new BasicDataSource();
    static {
        BASIC_DATA_SOURCE_ORCLPDB1.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        BASIC_DATA_SOURCE_ORCLPDB1.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb1");
        BASIC_DATA_SOURCE_ORCLPDB1.setUsername("amir");
        BASIC_DATA_SOURCE_ORCLPDB1.setPassword("myjava123");
        BASIC_DATA_SOURCE_ORCLPDB1.setMaxIdle(2);
        BASIC_DATA_SOURCE_ORCLPDB1.setMaxTotal(10);//connection pool

        BASIC_DATA_SOURCE_XEPDB1.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        BASIC_DATA_SOURCE_XEPDB1.setUrl("jdbc:oracle:thin:@localhost:1521/xepdb1");
        BASIC_DATA_SOURCE_XEPDB1.setUsername("amir");
        BASIC_DATA_SOURCE_XEPDB1.setPassword("myjava123");
        BASIC_DATA_SOURCE_XEPDB1.setMaxIdle(2);
        BASIC_DATA_SOURCE_XEPDB1.setMaxTotal(10);//connection pool
    }
    public static Connection getConnection(int databaseName) throws SQLException {
        Connection connection = null;
        switch (databaseName){
            case 1 :
                connection = BASIC_DATA_SOURCE_ORCLPDB1.getConnection();
                connection.setAutoCommit(false);
                return connection;
            default:
                connection = BASIC_DATA_SOURCE_XEPDB1.getConnection();
                connection.setAutoCommit(false);
                return connection;
        }
    }
}
