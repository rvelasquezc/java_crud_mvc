package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoDAO extends Conexion {

    private String sql;
    private PreparedStatement ps;
    private String respuesta;
    private ResultSet rs;

    Producto producto = new Producto();

    public String actualizaStock(int cant, int idProducto) {
        try {
            this.conectar();
            sql = "update producto set Stock=? where idProducto =? ";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return respuesta;
    }

    public Producto listaProducto(String codigo) {
        try {
            this.conectar();
            sql = "select * from producto where idProducto=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getString("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return producto;
    }

    public String registroProducto(Producto producto) {
        try {
            this.conectar();
            sql = "insert into producto (idProducto,nombre,precio,stock,estado) values(?,?,?,?,?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getEstado());
            ps.executeUpdate();
            respuesta = "Producto Agregado correctamente!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se registro el producto";
        } finally {
            this.cerrarConexion();
        }

        return respuesta;
    }

    public String modificarProducto(Producto producto) {
        try {
            this.conectar();
            sql = "update producto set nombre=?,precio=?,stock=?,estado=? where idProducto=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getEstado());
            ps.setInt(5, producto.getIdProducto());
            ps.executeUpdate();
            respuesta = "Registro del Producto fue Modificado Correctamente!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "El registro no se puede modificar";

        } finally {
            this.cerrarConexion();
        }
        return respuesta;
    }

    public String eliminarProducto(int codigo) {
        try {
            this.conectar();
            sql = "delete from producto where idProducto=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            respuesta = "Registro eliminado Correctamente!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede eliminar el producto!!";
        } finally {
            this.cerrarConexion();
        }
        return respuesta;
    }

    public Producto mostrarProducto(int codigo) {
        Producto producto = new Producto();
        try {
            this.conectar();
            sql = "select * from producto where idProducto=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            rs.next();
            producto.setIdProducto(rs.getInt("idProducto"));
            producto.setNombre(rs.getString("nombre"));
            producto.setPrecio(rs.getString("precio"));
            producto.setStock(rs.getInt("stock"));
            producto.setEstado(rs.getString("estado"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return producto;
    }

    public ArrayList<Producto> listaProducto() {
        ArrayList<Producto> lista = null;
        try {
            this.conectar();
            sql = "select * from producto";
            ps = getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getString("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setEstado(rs.getString("estado"));
                lista.add(producto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }

        return lista;
    }
}
