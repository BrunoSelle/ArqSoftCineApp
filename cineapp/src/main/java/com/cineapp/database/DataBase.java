package com.cineapp.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Giuliano
 */
public class DataBase {

    public static Connection getConexao() {

        Connection res = null;
        Statement stmt = null;

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            DataSource ds = (DataSource) envContext.lookup("jdbc/SRH_DB");
            
            if (envContext == null) {
                throw new Exception("No Context");
            }
            if (ds == null) {
                throw new Exception("No DataSource");
            }
            res = ds.getConnection();
            stmt = res.createStatement();
            stmt.executeQuery("ALTER SESSION SET NLS_DATE_FORMAT = 'DD/MM/YYYY'");
            stmt.executeQuery("ALTER SESSION SET OPTIMIZER_MODE = RULE");

            return res;
        } catch (Exception e) {
            registraLogErro(e);
            return null;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static Connection getConexao(String operador, String senha) {

        Connection res = null;
        Statement stmt = null;

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            DataSource ds = (DataSource) envContext.lookup("jdbc/SRH_DB");

            if (envContext == null) {
                throw new Exception("No Context");
            }
            if (ds == null) {
                throw new Exception("No DataSource");
            }

            res = ds.getConnection(operador, senha);
            stmt = res.createStatement();
            stmt.executeQuery("ALTER SESSION SET NLS_DATE_FORMAT = 'DD/MM/YYYY'");

            return res;
        } catch (Exception e) {
            registraLogErro(e);
            return null;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static void registraLog(String texto) {
        System.out.println("-------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Data: " + sdf.format(GregorianCalendar.getInstance().getTime()));
        System.out.println("Texto: " + texto);
    }

    public static void registraLogErro(Exception e) {
        System.out.println("-------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Data: " + sdf.format(GregorianCalendar.getInstance().getTime()));
        System.out.println("Erro: " + e.getMessage());
    }

    public static void registraLogErro(Exception e, String sql) {
        System.out.println("-------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Data: " + sdf.format(GregorianCalendar.getInstance().getTime()));
        System.out.println("Erro: " + e.getMessage() + "\nSQL.: " + sql);
    }

    public static void registraLogErro(SQLException e) {
        System.out.println("-------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Data: " + sdf.format(GregorianCalendar.getInstance().getTime()));
        System.out.println("Erro......: " + e.getErrorCode() + " - "
                + e.getMessage() + "\nSQL State : " + e.getSQLState());
    }

    public static void registraLogErro(SQLException e, String sql) {
        System.out.println("-------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Data: " + sdf.format(GregorianCalendar.getInstance().getTime()));
        System.out.println("Erro: " + e.getErrorCode() + " - "
                + e.getMessage() + "\nSQL.: " + sql);
    }
}
