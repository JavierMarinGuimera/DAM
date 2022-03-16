package impls.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnectionJDBC;
import pojos.Perfils;
import pojos.Usuaris;
import services.UsersService;

public class UsersServiceImplJDBC implements UsersService {

    @Override
    public List<Usuaris> readAll() {
        try {
            // Query to execute:
            String sql = "SELECT * FROM usuaris";
            Connection con = DBConnectionJDBC.getConnection();

            PreparedStatement st = con.prepareStatement(sql);
            ResultSet result = st.executeQuery();

            List<Usuaris> users = new ArrayList<>();
            while (result.next()) {
                users.add(readOne(result.getString(1)));
            }

            result.close();
            st.close();
            con.close();

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean createOne(Usuaris user) {
        while (true) {
            try {
                if (readOne(user.getIdUsuari()) == null) {
                    String sql = "INSERT INTO `usuaris`(`idUsuari`, `password`, `nom`, `cognoms`, `correu`, `perfil`, `numcolegiat`, `especialitat`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    Connection con = DBConnectionJDBC.getConnection();
                    PreparedStatement st = con.prepareStatement(sql);

                    st.setString(1, user.getIdUsuari());
                    st.setString(2, user.getPassword());
                    st.setString(3, user.getNom());
                    st.setString(4, user.getCognoms());
                    st.setString(5, user.getCorreu());
                    st.setInt(6, user.getPerfils().getCodi());

                    if (user.getNumcolegiat() != null) {
                        st.setInt(7, user.getNumcolegiat());
                    } else {
                        st.setNull(7, Types.NULL);
                    }

                    if (user.getEspecialitat() != null) {
                        st.setString(8, user.getEspecialitat());
                    } else {
                        st.setNull(8, Types.NULL);
                    }

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
    public Usuaris readOne(String user_id) {
        // Query to execute:
        String sqlUser = "SELECT * FROM usuaris WHERE idUsuari  = ?";

        Connection con = null;
        PreparedStatement stUser = null;
        ResultSet resultUser = null;
        try {
            con = DBConnectionJDBC.getConnection();
            stUser = con.prepareStatement(sqlUser);
            stUser.setString(1, user_id);

            resultUser = stUser.executeQuery();

            // This will have every user obtained:
            while (resultUser.next()) {
                // Getting de Profile from the current user here:
                String sqlProfile = "SELECT * FROM perfils WHERE codi  = ?";
                PreparedStatement stProfile = con.prepareStatement(sqlProfile);
                stProfile.setInt(1, resultUser.getInt(2));
                ResultSet resultProfile = stProfile.executeQuery();

                Perfils perfil = null;
                // This will have every profile obtained:
                while (resultProfile.next()) {
                    perfil = new Perfils(resultProfile.getInt(1), resultProfile.getString(2));
                }

                resultProfile.close();
                stProfile.close();

                Usuaris user = new Usuaris(resultUser.getString(1), perfil, resultUser.getString(3),
                        resultUser.getString(4), resultUser.getString(5), resultUser.getString(6));

                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                resultUser.close();
                stUser.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateOne(Usuaris user) {
        if (readOne(user.getIdUsuari()) != null) {
            String sql = "UPDATE usuaris SET password = ?, nom = ?, cognoms = ?, correu = ?, perfil  = ?, numcolegiat = ?, especialitat = ? WHERE idUsuari = ?";

            Connection con = DBConnectionJDBC.getConnection();

            try {
                con.setAutoCommit(false);
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, user.getPassword());
                st.setString(2, user.getNom());
                st.setString(3, user.getCognoms());
                st.setString(4, user.getCorreu());
                st.setInt(5, user.getPerfils().getCodi());

                if (user.getNumcolegiat() != null) {
                    st.setInt(6, user.getNumcolegiat());
                } else {
                    st.setNull(6, Types.NULL);
                }

                if (user.getEspecialitat() != null) {
                    st.setString(7, user.getEspecialitat());
                } else {
                    st.setNull(7, Types.NULL);
                }

                st.setString(8, user.getIdUsuari());

                st.executeUpdate();
                con.commit();

                st.close();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    return false;
                }

                return false;
            } finally {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteOne(String user_id) {
        while (true) {
            try {
                if (readOne(user_id) != null) {
                    String sql = "DELETE FROM usuaris WHERE idUsuari = ?";
                    Connection con = DBConnectionJDBC.getConnection();
                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, user_id);

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
