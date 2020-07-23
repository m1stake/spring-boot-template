import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.*;

public class DbTest {

//    @Test
    public void t() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.88.3.137;DatabaseName=automessage",
                "sa", "testHR123");

        Statement stat =conn.createStatement();
        ResultSet rs = stat.executeQuery(" SELECT Name FROM SysObjects Where XType='U' ORDER BY Name");

        while (rs.next()) {
            System.out.println(rs.getObject(1));
        }

        rs.close();
        stat.close();
        conn.close();
    }

    @Test
    public void passEncode() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }

}
