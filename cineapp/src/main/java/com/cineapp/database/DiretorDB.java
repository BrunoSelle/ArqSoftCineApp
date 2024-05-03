package com.cineapp.database;

import com.cineapp.model.Diretor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Selle
 */
public class DiretorDB {


    /**
     * Traz um diretor
     *
     * @return CINEAPP_DIRETOR
     * @param codDiretor int
     * @throws SQLException
     */
    public static Diretor getDiretorByCodigo(int codDiretor) throws SQLException {
        Connection con = DataBase.getConexao();
        if (con == null) {
            return null;
        }
        
        String sql;

        sql = "SELECT COD_DIRETOR, NOME FROM CINEAPP_DIRETOR WHERE COD_DIRETOR = " + codDiretor;

        Statement stmt = null;
        ResultSet rs   = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            Diretor dir = new Diretor();
            if (rs.next()) {
                dir.setCodDiretor(rs.getInt("COD_DIRETOR"));
                dir.setNome(rs.getString("NOME"));
            }
            return dir;
        } catch (SQLException e) {
            DataBase.registraLogErro(e, sql);
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }

    /**
     * Traz a lista de diretores
     *
     * @return List<DIRETOR>
     * @throws SQLException
     */
    public static List<Diretor> getDiretores() throws SQLException {
        Connection con = DataBase.getConexao();
        if (con == null) {
            return null;
        }
        
        String sql;

        sql = "SELECT COD_DIRETOR, NOME FROM CINEAPP_DIRETOR ORDER BY COD_DIRETOR DESC ";

        Statement stmt = null;
        ResultSet rs   = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            List<Diretor> listaDiretores = new ArrayList<Diretor>();
            while (rs.next()) {
                Diretor dir = new Diretor();
                dir.setCodDiretor(rs.getInt("COD_DIRETOR"));
                dir.setNome(rs.getString("NOME"));
                listaDiretores.add(dir);
            }
            return listaDiretores;
        } catch (SQLException e) {
            DataBase.registraLogErro(e, sql);
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }

    /**
     * Inclui diretor (CINEAPP_DIRETOR).
     * @param Diretor dir
     */
    public static void insertDiretorDB(Diretor dir) throws SQLException {
        Connection con  = DataBase.getConexao();

        String sql      = "";
        PreparedStatement stmt = null;
        try {
            
            sql =   "INSERT INTO CINEAPP_DIRETOR                " +
                    "   (COD_PRODUTO,                           " +
                    "   NOME)                                   " +
                    "VALUES ((SELECT NVL(MAX(COD_PRODUTO),0)+1  " +
                    " FROM CINEAPP_DIRETOR),                    " +
                    "                      ?)                   " ;

           stmt = con.prepareStatement(sql);
           stmt.setString(1, dir.getNome());
           stmt.execute();
            
        } catch (SQLException e) {
            DataBase.registraLogErro(e, sql);
            throw e;
        } catch (Exception e) {
            DataBase.registraLogErro(e, sql);
            throw e;
        } finally {
            if (stmt != null) {
            stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    /**
     * Edita diretor (CINEAPP_DIRETOR).
     * @param Diretor dir
     */
    public static void updateDiretorDB(Diretor dir) throws SQLException {
        Connection con  = DataBase.getConexao();

        String sql      = "";
        PreparedStatement stmt = null;
        try {
            
            sql =   "UPDATE CINEAPP_DIRETOR " +
                    " SET NOME = ?          " +
                    " WHERE COD_PRODUTO = ? " ;

           stmt = con.prepareStatement(sql);
           stmt.setString(1, dir.getNome());
           stmt.setInt(2, dir.getCodDiretor());
           stmt.execute();
            
        } catch (SQLException e) {
            DataBase.registraLogErro(e, sql);
            throw e;
        } catch (Exception e) {
            DataBase.registraLogErro(e, sql);
            throw e;
        } finally {
            if (stmt != null) {
            stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

     /**
     * Exclui diretor (CINEAPP_DIRETOR).
     * @param Diretor dir
     */
    public static void deleteDiretorDB(int codDiretor) throws SQLException {
        Connection con  = DataBase.getConexao();

        String sql      = "";
        PreparedStatement stmt = null;
        try {
            
            sql =   "DELETE FROM CINEAPP_DIRETOR WHERE COD_DIRETOR = ? ";

           stmt = con.prepareStatement(sql);
           stmt.setInt(1, codDiretor);
           stmt.execute();

        } catch (SQLException e) {
            DataBase.registraLogErro(e, sql);
            throw e;
        } catch (Exception e) {
            DataBase.registraLogErro(e, sql);
            throw e;
        } finally {
            if (stmt != null) {
            stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
