package com.tecnostore.persistencia;

import com.tecnostore.config.ConexionDB;
import com.tecnostore.model.Celular;
import com.tecnostore.model.Gama;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDAOImpl implements ICelularDAO {

    @Override
    public Celular registrar(Celular celular) throws SQLException {
        String sql = "INSERT INTO celulares (marca, modelo, precio, stock, sistema_operativo, gama) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, celular.getMarca());
            ps.setString(2, celular.getModelo());
            ps.setDouble(3, celular.getPrecio());
            ps.setInt(4, celular.getStock());
            ps.setString(5, celular.getSistema_operativo());
            ps.setString(6, celular.getGama().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    celular.setId_celular(rs.getInt(1));
                }
            }
        }
        return celular;
    }

    @Override
    public void actualizar(Celular celular) throws SQLException {
        String sql = "UPDATE celulares SET marca = ?, modelo = ?, precio = ?, stock = ?, sistema_operativo = ?, gama = ? WHERE id_celular = ?";

        try (Connection con = ConexionDB.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, celular.getMarca());
            ps.setString(2, celular.getModelo());
            ps.setDouble(3, celular.getPrecio());
            ps.setInt(4, celular.getStock());
            ps.setString(5, celular.getSistema_operativo());
            ps.setString(6, celular.getGama().name());
            ps.setInt(7, celular.getId_celular());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM celulares WHERE id_celular = ?";

        try (Connection con = ConexionDB.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Celular> listar() throws SQLException {
        String sql = "SELECT id_celular, marca, modelo, precio, stock, sistema_operativo, gama FROM celulares";
        List<Celular> listaCelulares = new ArrayList<>();

        try (Connection con = ConexionDB.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Celular celular = construirCelularDesdeResultSet(rs);
                listaCelulares.add(celular);
            }
        }
        return listaCelulares;
    }

    @Override
    public Celular buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_celular, marca, modelo, precio, stock, sistema_operativo, gama FROM celulares WHERE id_celular = ?";

        try (Connection con = ConexionDB.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Si encuentra el registro, construye el objeto Celular y lo retorna
                    return construirCelularDesdeResultSet(rs);
                }
            }
        }
        // Si no encuentra ninguna coincidencia, retorna null
        return null;
    }

    /**
     * Método auxiliar privado para evitar duplicar código al mapear los datos del ResultSet a la Entidad.
     */
    private Celular construirCelularDesdeResultSet(ResultSet rs) throws SQLException {
        Celular celular = new Celular();
        celular.setId_celular(rs.getInt("id_celular"));
        celular.setMarca(rs.getString("marca"));
        celular.setModelo(rs.getString("modelo"));
        celular.setPrecio(rs.getDouble("precio"));
        celular.setStock(rs.getInt("stock"));
        celular.setSistema_operativo(rs.getString("sistema_operativo"));

        String gamaString = rs.getString("gama");
        celular.setGama(Gama.valueOf(gamaString.toUpperCase()));

        return celular;
    }
}