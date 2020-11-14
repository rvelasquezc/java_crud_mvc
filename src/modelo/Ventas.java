
package modelo;

/**
 *
 * @author rvelasquez
 */
public class Ventas {
    int idVentas; 
    int idCliente;
    int idVendedor;
    String numeroVentas;
    String fechaVentas;
    double monto;
    String estado;

    public Ventas() {
    }

    public Ventas(int idVentas, int idCliente, int idVendedor, String numeroVentas, String fechaVentas, double monto, String estado) {
        this.idVentas = idVentas;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.numeroVentas = numeroVentas;
        this.fechaVentas = fechaVentas;
        this.monto = monto;
        this.estado = estado;
    }

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNumeroVentas() {
        return numeroVentas;
    }

    public void setNumeroVentas(String numeroVentas) {
        this.numeroVentas = numeroVentas;
    }

    public String getFechaVentas() {
        return fechaVentas;
    }

    public void setFechaVentas(String fechaVentas) {
        this.fechaVentas = fechaVentas;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
