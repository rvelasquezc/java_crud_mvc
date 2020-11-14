package modelo;

/**
 *
 * @author rvelasquez
 */
public class DetalleVenta {

    int idDetalleVentas;
    int idProducto;
    int idVentas;
    int cantidad;
    double precioVenta;

    public DetalleVenta() {
    }

    public DetalleVenta(int idDetalleVentas, int idProducto, int idVentas, int cantidad, double precioVenta) {
        this.idDetalleVentas = idDetalleVentas;
        this.idProducto = idProducto;
        this.idVentas = idVentas;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public int getIdDetalleVentas() {
        return idDetalleVentas;
    }

    public void setIdDetalleVentas(int idDetalleVentas) {
        this.idDetalleVentas = idDetalleVentas;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    
    
    

}
