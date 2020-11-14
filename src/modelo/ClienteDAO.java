package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ClienteDAO extends Conexion {

    private String sql;
    private PreparedStatement ps;
    private String respuesta;
    private ResultSet rs;

    Cliente cliente = new Cliente();

    public String registroCliente(Cliente cliente) {
        try {
            this.conectar();
            sql = "insert into cliente(idCliente,cui,nombres,direccion,estado)values(?,?,?,?,?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getCui());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getEstado());
            ps.executeUpdate();
            respuesta = "Cliente fue almacenado correctametne!!!";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "Datos de cliente no fue ingresado!!!";
        } finally {
            this.cerrarConexion();
        }
        return respuesta;

    }

    public String modificarCliente(Cliente cliente) {
        try {
            this.conectar();
            sql = "update cliente set cui=?,nombres=?,direccion=?,estado=? where idCliente=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, cliente.getCui());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getEstado());
            ps.setInt(5, cliente.getIdCliente());
            ps.executeUpdate();
            respuesta = "Registro Cliente fue modificado correctamente!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "El registro no se puede modificar!!!";
        } finally {
            this.cerrarConexion();
        }
        return respuesta;
    }

    public String eliminarCliente(int codigo) {
        try {
            this.conectar();
            sql = "delete from cliente where idCliente=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            respuesta = "Registro eliminado correctamente!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede aliminar el registro!!!";
        } finally {
            this.cerrarConexion();
        }

        return respuesta;
    }

    public Cliente mostrarCliente(int codigo) {
        Cliente cliente = new Cliente();
        try {
            this.conectar();
            sql = "select * from cliente where idCliente=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            rs.next();
            cliente.setIdCliente(rs.getInt("idCliente"));
            cliente.setCui(rs.getString("cui"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setDireccion(rs.getString("direccion"));
            cliente.setEstado(rs.getString("estado"));
        } catch (Exception e) {
        } finally {
            this.cerrarConexion();
        }

        return cliente;
    }

    public ArrayList<Cliente> listaCliente() {
        ArrayList<Cliente> lista = null;
        try {
            this.conectar();
            sql = "select * from cliente";
            ps = getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCui(rs.getString("cui"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEstado(rs.getString("estado"));
                lista.add(cliente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }

        return lista;
    }
    
    public Cliente listaById(String idcliente) {
        try {
            this.conectar();
            sql = "select * from cliente where idCliente=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, idcliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCui(rs.getString("cui"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEstado(rs.getString("estado"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            this.cerrarConexion();
        }
        return cliente;
    }
}
