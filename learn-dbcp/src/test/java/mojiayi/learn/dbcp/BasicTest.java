package mojiayi.learn.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:learn-dbcp.xml" })
public class BasicTest {
	@Value("${oracle.driver}")
	private String driver;
	@Value("${oracle.url}")
	private String url;
	@Value("${oracle.username}")
	private String username;
	@Value("${oracle.password}")
	private String password;
	
	private DataSource setupDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
	
	private void shutdownDataSource(DataSource ds) throws SQLException {
        BasicDataSource bds = (BasicDataSource) ds;
        bds.close();
    }
	
	@Test
	public void queryUserInfo() throws SQLException {
		DataSource ds = setupDataSource();
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		String sql = "select cnickid,crealname from tb_user where cnickid='huasheng'";
		ResultSet rs = st.executeQuery(sql);
		if (rs != null) {
			while(rs.next()) {
				String realname = rs.getString("crealname");
				Assert.assertEquals("李广日", realname);
			}
		}
		shutdownDataSource(ds);
	}
}
