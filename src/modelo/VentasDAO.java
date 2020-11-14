package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rvelasquez
 */
public class VentasDAO extends Conexion {

    PreparedStatement ps;
    ResultSet rs;
    Ventas ventas = new Ventas();
    String sql;
    int respuesta;

    public int guardarVentas(Ventas ventas) {
        try {
            this.conectar();
            sql = "insert into ventas (idVentas,Cliente_idCliente, Vendedor_idVendedor, NumeroVentas, FechaVentas, Monto, Estado)values (?,?,?,?,?,?,?)";
            ps = this.getMiConexion().prepareStatement(sql);
            ps.setInt(1, ventas.getIdVentas());
            ps.setInt(2, ventas.getIdCliente());
            ps.setInt(3, ventas.getIdVendedor());
            ps.setString(4, ventas.getNumeroVentas());
            ps.setString(5, ventas.getFechaVentas());
            ps.setDouble(6, ventas.getMonto());
            ps.setString(7, ventas.getEstado());

            respuesta = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            this.cerrarConexion();
        }
        return respuesta;
    }

    public int guardarDetalleVentas(DetalleVenta detalle) {
        try {
            this.conectar();
            sql = "insert into detalle_ventas (Producto_idProducto,Ventas_idVentas, Cantidad, PrecioVenta)values (?,?,?,?)";
            ps = this.getMiConexion().prepareStatement(sql);
            ps.setInt(1, detalle.getIdProducto());
            ps.setInt(2, detalle.getIdVentas());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioVenta());
            respuesta = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            this.cerrarConexion();
        }
        return respuesta;
    }

}
