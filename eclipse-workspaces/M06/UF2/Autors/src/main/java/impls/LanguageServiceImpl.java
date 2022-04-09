package impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnectionJDBC;
import pojos.Idiomes;
import service.LanguageService;

public class LanguageServiceImpl implements LanguageService {

    @Override
    public boolean createOne(Idiomes language) {
        while (true) {
            try {
                if (readOne(language.getCodi()) == null) {
                    String sql = "INSERT INTO `idiomes`(`codi`, `descripcio`) VALUES (?,?)";
                    Connection con = DBConnectionJDBC.getConnection();
                    PreparedStatement st = con.prepareStatement(sql);

                    st.setInt(1, language.getCodi());
                    st.setString(2, language.getDescripcio());

                    st.executeUpdate();
                    st.close();
                    con.close();
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public Idiomes readOne(int id) {
        // Query to execute:
        String sqlUser = "SELECT * FROM Idiomes WHERE codi = ?";

        Connection con = null;
        PreparedStatement stLanguage = null;
        ResultSet resultLanguage = null;
        try {
            con = DBConnectionJDBC.getConnection();
            stLanguage = con.prepareStatement(sqlUser);
            stLanguage.setInt(1, id);

            resultLanguage = stLanguage.executeQuery();

            while (resultLanguage.next()) {
                Idiomes language = new Idiomes(resultLanguage.getInt(1), resultLanguage.getString(2));
                return language;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                resultLanguage.close();
                stLanguage.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteOne(int id) {
        while (true) {
            try {
                if (readOne(id) != null) {
                    String sql = "DELETE FROM Idiomes WHERE codi = ?";
                    Connection con = DBConnectionJDBC.getConnection();
                    PreparedStatement st = con.prepareStatement(sql);
                    st.setInt(1, id);

                    st.executeUpdate();
                    st.close();
                    con.close();

                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
