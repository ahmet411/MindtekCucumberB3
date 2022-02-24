package utilities;

import java.sql.*;
import java.util.*;

    public class JDBC_to_delete {

        public static void main(String[] args) throws SQLException {
            String URL = "jdbc:postgresql://localhost/HR";
            String username = "postgres";
            String password = "Eldar$123";
            String query = "select * from employees limit 3";


            Connection connection;
            Statement statement;
            ResultSet resultSet;
            ResultSetMetaData resultSetMetaData;
            List<Map<String, Object>> firstData = new ArrayList<>();

            connection = DriverManager.getConnection(URL, username, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);
            resultSetMetaData = resultSet.getMetaData();

            // resultSet methods
            System.out.println("===resultSet===");
            resultSet.next();
            System.out.println(resultSet.getString("employee_id"));
            System.out.println(resultSet.getString("first_name"));
            System.out.println(resultSet.getString("last_name"));
            System.out.println(resultSet.findColumn("employee_id"));
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getObject("first_name")+"object");

            resultSet.next();
            System.out.println(resultSet.getString("employee_id"));
            System.out.println(resultSet.getString("first_name"));
            System.out.println(resultSet.getString("last_name"));

            resultSet.next();
            System.out.println(resultSet.getString("employee_id"));
            System.out.println(resultSet.getString("first_name"));
            System.out.println(resultSet.getString("last_name"));
            System.out.println(resultSet.last()+"last row");
            System.out.println("===resultSet===");

            //while loop way
            ResultSet resultSet1 = statement.executeQuery(query);
            System.out.println("===resultSet with while loop===");
            while(resultSet1.next()){
                System.out.println(resultSet1.getString("employee_id"));
                System.out.println(resultSet1.getString("first_name"));
                System.out.println(resultSet1.getString("last_name"));
            }
            System.out.println("===resultSet with while loop===");

            //resultSetMetaData
            System.out.println("===Meta Data===");
            System.out.println(resultSetMetaData.getColumnName(1));
            System.out.println(resultSetMetaData.getColumnCount());
            System.out.println(resultSetMetaData.getTableName(1));
            System.out.println("===Meta Data===");

            //to insert data to list of maps
            ResultSet resultSet2 = statement.executeQuery(query);
            while (resultSet2.next()){
                Map<String, Object> row = new HashMap<>(); //captures row with every rotation
                // It is looping through each column of current row and stores to map
                for (int i=1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.put(resultSetMetaData.getColumnName(i), resultSet2.getObject(resultSetMetaData.getColumnName(i)));
                }
                firstData.add(row);

            }

            System.out.println(firstData); //returns List
            System.out.println(firstData.get(0)); //returns map
            System.out.println(firstData.get(0).get("employee_id"));
            System.out.println(firstData.get(1).get("first_name"));
            System.out.println(firstData.get(1));
            System.out.println(firstData.get(2));
            //System.out.println(firstData.get(3));

            connection.close();
        }
    }


