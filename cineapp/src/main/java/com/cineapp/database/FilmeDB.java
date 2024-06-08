package com.cineapp.database;

import com.cineapp.model.Diretor;
import com.cineapp.model.Filme;
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
public class FilmeDB {
    
    /**
     * Traz um filme
     *
     * @return Filme
     * @param codFilme int
     * @throws SQLException
     */
    public static Filme getFilmeByCodigo(int codFilme) throws SQLException {
        Connection con = DataBase.getConexao();
        if (con == null) {
            return null;
        }
        
        String sql;

        sql =   " SELECT FIL.*, DIR.NOME AS NOME_DIRETOR                        " +
                " FROM CINEAPP_FILMES FIL                                       " +
                " JOIN CINEAPP_DIRETOR DIR ON FIL.COD_DIRETOR = DIR.COD_DIRETOR " +
                " WHERE FIL.COD_FILME =                                         " + codFilme;

        Statement stmt = null;
        ResultSet rs   = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            Filme movie = new Filme();
            if (rs.next()) {
                movie.setCodFilme(rs.getInt("COD_FILME"));
                movie.setCodDiretor(rs.getInt("COD_DIRETOR"));
                movie.setNomeFilme(rs.getString("NOME"));
                movie.setNomeDiretor(rs.getString("NOME_DIRETOR"));
                movie.setDescricao(rs.getString("DESCRICAO"));
                movie.setGenero(rs.getString("GENERO"));
            }
            return movie;
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
     * Traz a lista de filmes
     *
     * @return List<CINEAPP_FILMES>
     * @throws SQLException
     */
    public static List<Filme> getFilmes() throws SQLException {
        Connection con = DataBase.getConexao();
        if (con == null) {
            return null;
        }
        
        String sql;

        sql =   " SELECT FIL.*, DIR.NOME AS NOME_DIRETOR                        " +
                " FROM CINEAPP_FILMES FIL                                       " +
                " JOIN CINEAPP_DIRETOR DIR ON FIL.COD_DIRETOR = DIR.COD_DIRETOR " +
                " ORDER BY COD_FILME                                            ";


        Statement stmt = null;
        ResultSet rs   = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            List<Filme> listaFilmes = new ArrayList<Filme>();
            while (rs.next()) {
                Filme movie = new Filme();
                movie.setCodFilme(rs.getInt("COD_FILME"));
                movie.setCodDiretor(rs.getInt("COD_DIRETOR"));
                movie.setNomeFilme(rs.getString("NOME"));
                movie.setNomeDiretor(rs.getString("NOME_DIRETOR"));
                movie.setDescricao(rs.getString("DESCRICAO"));
                movie.setGenero(rs.getString("GENERO"));
                listaFilmes.add(movie);
            }
            return listaFilmes;
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
     * Inclui filme (CINEAPP_FILMES).
     * @param movie Filme
     */
    public static void insertFilmeDB(Filme movie) throws SQLException {
        Connection con  = DataBase.getConexao();

        String sql      = "";
        PreparedStatement stmt = null;
        try {
            
        sql =       "INSERT INTO CINEAPP_FILMES             " +
                    "   (COD_FILME ,                        " +
                    "   NOME,                               " +
                    "   DESCRICAO,                          " +
                    "   GENERO,                             " +
                    "   COD_DIRETOR)                        " +
                    "VALUES ((SELECT NVL(MAX(COD_FILME),0)+1" +
                    " FROM CINEAPP_FILMES),                 " +
                    "                      ?,               " +
                    "                      ?,               " +
                    "                      ?,               " +
                    "                      ?)               " ;

           stmt = con.prepareStatement(sql);
           stmt.setString(1, movie.getNomeFilme());
           stmt.setString(2, movie.getDescricao());
           stmt.setString(3, movie.getGenero());
           stmt.setInt(4, movie.getCodDiretor());
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
     * Edita valores de um filme (CINEAPP_FILMES).
     * @param movie Filme
     */
    public static void updateFilmeDB(Filme movie) throws SQLException {
        Connection con  = DataBase.getConexao();

        String sql      = "";
        PreparedStatement stmt = null;
        try {
            
            sql =   "UPDATE CINEAPP_FILMES  " +
                    " SET NOME = ?,         " +
                    "     DESCRICAO = ?,    " +
                    "     GENERO = ?,       " +
                    "     COD_DIRETOR = ?   " +
                    " WHERE COD_FILME = ?   " ;

           stmt = con.prepareStatement(sql);
           stmt.setString(1, movie.getNomeFilme());
           stmt.setString(2, movie.getDescricao());
           stmt.setString(3, movie.getGenero());
           stmt.setInt(4, movie.getCodDiretor());
           stmt.setInt(5, movie.getCodFilme());
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
     * Exclui filme (CINEAPP_FILMES).
     * @param codFilme int
     */
    public static void deleteFilmeDB(int codFilme) throws SQLException {
        Connection con  = DataBase.getConexao();

        String sql      = "";
        PreparedStatement stmt = null;
        try {
            
            sql =   "DELETE FROM CINEAPP_FILMES WHERE COD_FILME = ? ";

           stmt = con.prepareStatement(sql);
           stmt.setInt(1, codFilme);

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
