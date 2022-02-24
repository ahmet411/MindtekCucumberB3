package utilities;

import java.sql.SQLException;
import java.util.*;

public class JDBCUtilsTest_to_delete {

    public static void main(String[] args) throws SQLException {

        String query = "select * from employees limit 5";

        // 1. Step
        JDBCUtils.establishConnection();

        // 2. Step
        List<Map<String,Object>> data= JDBCUtils.runQuery(query);
        System.out.println(data.get(0).get("first_name"));

        // 3. Step
        JDBCUtils.closeDatabase();



    }
}
