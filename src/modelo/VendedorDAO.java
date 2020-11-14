package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VendedorDAO extends Conexion {

    private String sql;
    private PreparedStatement ps;
    private String respuesta;
    private ResultSet rs;
    Vendedor vend = new Vendedor();

    public String registrarVendedor(Vendedor usu) {
        try {
            this.conectar();
            sql = "insert into vendedor(idVendedor, cui, nombre, telefono, estado, usuario, password)values(?,?,?,?,?,?,?);";
            ps = this.getMiConexion().prepareStatement(sql);
            ps.setInt(1, usu.getIdVendedor());
            ps.setString(2, usu.getCui());
            ps.setString(3, usu.getNombre());
            ps.setString(4, usu.getTelefono());
            ps.setString(5, usu.getEstado());
            ps.setString(6, usu.getUsuario());
            ps.setString(7, usu.getPassword());
            ps.executeUpdate();
            respuesta = "Registro almacenado correctamente !!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede almacenar el registro";
        } finally {
            this.cerrarConexion();
        }
        return respuesta;
    }//regist

    public Vendedor validaVendedor(String usuario, String password) {
        try {
            this.conectar();
            sql = "select * from vendedor where usuario=? and password=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                vend.setIdVendedor(rs.getInt("idVendedor"));
                vend.setCui(rs.getString("cui"));
                vend.setNombre(rs.getString("nombre"));
                vend.setTelefono(rs.getString("telefono"));
                vend.setEstado(rs.getString("estado"));
                vend.setUsuario(rs.getString("usuario"));
                vend.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vend;
    }//fn valida
    
    public String modificarVendedor(Vendedor vendedor) {
        try {
            this.conectar();
            sql = "update vendedor set cui=?,nombre=?,telefono=?,estado=?,usuario=? where idvendedor=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, vendedor.getCui());
            ps.setString(2, vendedor.getNombre());
            ps.setString(3, vendedor.getTelefono());
            ps.setString(4, vendedor.getEstado());
            ps.setString(5, vendedor.getUsuario());
            ps.setInt(6, vendedor.getIdVendedor());
            ps.executeUpdate();
            respuesta = "Registro modificado Correctamente!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "El registro no se puedo Modificar!!";
        } finally {
            this.cerrarConexion();
        }

        return respuesta;
    }

    public String eliminarVendedor(int codigo) {
        try {
            this.conectar();
            sql = "delete from vendedor where idvendedor=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            respuesta = "Registro eliminado correctamente !!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede eliminar el regristro!!!";
        } finally {
            this.cerrarConexion();
        }
        return respuesta;
    }

    public Vendedor mostrarVendedor(int codigo) {
        Vendedor vendedor = new Vendedor();
        try {
            this.conectar();
            sql = "select * from vendedor where idvendedor=?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            rs.next();
            vendedor.setIdVendedor(rs.getInt("idvendedor"));
            vendedor.setCui(rs.getString("cui"));
            vendedor.setNombre(rs.getString("nombre"));
            vendedor.setTelefono(rs.getString("telefono"));
            vendedor.setEstado(rs.getString("estado"));
            vendedor.setUsuario(rs.getString("usuario"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return vendedor;
    }

    public ArrayList<Vendedor> listaPersona() {
        ArrayList<Vendedor> lista = null;
        try {
            this.conectar();
            sql = "select * from vendedor";
            ps = getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setIdVendedor(rs.getInt("idvendedor"));
                vendedor.setCui(rs.getString("cui"));
                vendedor.setNombre(rs.getString("nombre"));
                vendedor.setTelefono(rs.getString("telefono"));
                vendedor.setEstado(rs.getString("estado"));
                vendedor.setUsuario(rs.getString("usuario"));
                lista.add(vendedor);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return lista;
    }
    
    

}//clase
