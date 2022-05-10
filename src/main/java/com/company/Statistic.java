package com.company;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Statistic {

    LocalDate localDate = LocalDate.parse("2022-03-04");

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Integer getUserMonth() {
            Connection con = null;
            Statement st = null;
            try {
                con = getConnection();
                st = con.createStatement();
                ResultSet resultSet = st.executeQuery("SELECT count(*) FROM users WHERE start_date >= date_trunc('month', CURRENT_DATE);");
                while (resultSet.next()) {
                    Integer count = resultSet.getInt("count");
                    return count;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            return null;
    }

    public Integer getUserDay() {
        Connection con = null;
        Statement st = null;
        try {
            con = getConnection();
            st = con.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT count(*) FROM users WHERE start_date >= current_date;");
            while (resultSet.next()) {
                Integer count = resultSet.getInt("count");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return null;
    }

    public Integer getUserAllTime() {
        Connection con = null;
        Statement st = null;
        try {
            con = getConnection();
            st = con.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT count(*) FROM users");
            while (resultSet.next()) {
                Integer count = resultSet.getInt("count");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return null;
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/java_db_database",
                            "java_db_user", "12345");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return con;
    }
}
