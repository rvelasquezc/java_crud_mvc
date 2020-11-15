package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.DetalleVenta;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.Ventas;
import modelo.VentasDAO;
import vista.frmVentas;

/**
 *
 * @author rvelasquez
 */
public class ControladorVentas implements ActionListener {

    DefaultTableModel dtm;
    Cliente cliente = new Cliente();
    ClienteDAO cDAO = new ClienteDAO();

    Ventas ventas = new Ventas();
    VentasDAO vDAO = new VentasDAO();
    frmVentas vVentas = new frmVentas();

    Producto producto = new Producto();
    ProductoDAO pDAO = new ProductoDAO();

    DetalleVenta detalleVenta = new DetalleVenta();

    int idProducto;
    int cantidad;
    int stock;
    double total;
    double precioProducto;
    double totalPagar;
    int item = 1;

    public ControladorVentas(frmVentas vVentas) {
        this.vVentas = vVentas;
        this.vVentas.btnBuscarCliente.addActionListener(this);
        this.vVentas.btnBuscarProducto.addActionListener(this);
        this.vVentas.btnAgregar.addActionListener(this);
        this.vVentas.btnGenerarVenta.addActionListener(this);
        fecha();
        generarSerie();
    }

    public void generarSerie() {
        String serie = vDAO.NroSerieVenta();
        if (serie == null) {
            vVentas.txtNumeroFactura.setText("1");
        } else {
            int incremento = Integer.parseInt(serie);
            incremento = incremento + 1;
            vVentas.txtNumeroFactura.setText(String.valueOf(incremento));
        }
    }

    public void buscarCliente() {
        String codigo = vVentas.txtCodigoCliente.getText();
        System.out.println("Valor de codigo cliente: " + codigo);
        if (vVentas.txtCodigoCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un codigo de cliente!!!!");
        } else {
            Cliente cliente = cDAO.listaById(codigo);
            if (cliente.getCui() != null) {
                vVentas.txtNombreCliente.setText(cliente.getNombre());
                vVentas.txtDireccion.setText(cliente.getDireccion());
                vVentas.txtNit.setText(cliente.getCui());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no registrado!!!");
            }
        }
    }

    public void buscarProducto() {
        String codigo = vVentas.txtCodigoProducto.getText();

        if (vVentas.txtCodigoProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingres un codigo de producto!!!");
        } else {
            producto = pDAO.listaProducto(codigo);
            if (String.valueOf(producto.getIdProducto()) != null) {
                vVentas.txtPrecio.setText(producto.getPrecio());
                vVentas.txtStock.setText(String.valueOf(producto.getStock()));
                vVentas.txtNombreProducto.setText(producto.getNombre());
            }
        }
    }

    public void agregarProducto() {
        dtm = (DefaultTableModel) vVentas.tablaDetalle.getModel();
        idProducto = producto.getIdProducto();
        String nombreProducto = vVentas.txtNombreProducto.getText();
        precioProducto = Double.parseDouble(vVentas.txtPrecio.getText());
        cantidad = Integer.parseInt(vVentas.txtCantidad.getText());
        total = cantidad * precioProducto;
        stock = Integer.parseInt(vVentas.txtStock.getText());
        ArrayList lista = new ArrayList();
        if (stock > 0) {
            lista.add(item);
            lista.add(idProducto);
            lista.add(nombreProducto);
            lista.add(cantidad);
            lista.add(precioProducto);
            lista.add(total);
            Object[] objeto = new Object[6];
            objeto[0] = lista.get(0);
            objeto[1] = lista.get(1);
            objeto[2] = lista.get(2);
            objeto[3] = lista.get(3);
            objeto[4] = lista.get(4);
            objeto[5] = lista.get(5);
            dtm.addRow(objeto);
            vVentas.tablaDetalle.setModel(dtm);
            calcularTotal();
            item += item;
        } else {
            JOptionPane.showMessageDialog(null, "Stock del producto no disponible");
        }
    }

    public void calcularTotal() {
        totalPagar = 0;
        for (int i = 0; i < vVentas.tablaDetalle.getRowCount(); i++) {
            cantidad = Integer.parseInt(vVentas.tablaDetalle.getValueAt(i, 3).toString());
            precioProducto = Double.parseDouble(vVentas.tablaDetalle.getValueAt(i, 4).toString());
            totalPagar += (cantidad * precioProducto);
        }
        vVentas.txtTotalPagar.setText(String.valueOf(totalPagar));

    }

    public void guardarVenta() {
        int idVentas = Integer.parseInt(vVentas.txtNumeroFactura.getText());
        int idCliente = Integer.parseInt(vVentas.txtCodigoCliente.getText());
        int idVendedor = 1;
        String numeroVentas = vVentas.txtNumeroFactura.getText();
        String fecha = vVentas.txtFecha.getText();
        double monto = totalPagar;
        String estado = "1";
        ventas.setIdVentas(idVentas);
        ventas.setIdCliente(idCliente);
        ventas.setIdVendedor(idVendedor);
        ventas.setNumeroVentas(numeroVentas);
        ventas.setFechaVentas(fecha);
        ventas.setMonto(monto);
        ventas.setEstado(estado);
        vDAO.guardarVentas(ventas);
    }

    public void guardarDetalleVenta() {
        int idVentas = Integer.parseInt(vVentas.txtNumeroFactura.getText());
        for (int i = 0; i < vVentas.tablaDetalle.getRowCount(); i++) {
            item = Integer.parseInt(vVentas.tablaDetalle.getValueAt(i, 0).toString());
            idProducto = Integer.parseInt(vVentas.tablaDetalle.getValueAt(i, 1).toString());
            cantidad = Integer.parseInt(vVentas.tablaDetalle.getValueAt(i, 3).toString());
            precioProducto = Double.parseDouble(vVentas.tablaDetalle.getValueAt(i, 4).toString());
            detalleVenta.setIdVentas(idVentas);
            detalleVenta.setIdProducto(idProducto);
            detalleVenta.setCantidad(cantidad);
            detalleVenta.setPrecioVenta(precioProducto);
            vDAO.guardarDetalleVentas(detalleVenta);
        }
    }

    public void fecha() {
        Calendar calendar = new GregorianCalendar();
        vVentas.txtFecha.setText("" + calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void actualizaStock() {
        for (int i = 0; i < vVentas.tablaDetalle.getRowCount(); i++) {
            idProducto = Integer.parseInt(vVentas.tablaDetalle.getValueAt(i, 1).toString());
            cantidad = Integer.parseInt(vVentas.tablaDetalle.getValueAt(i, 3).toString());
            int saldo = producto.getStock() - cantidad;
            pDAO.restaStock(saldo, idProducto);
        }
    }

    public void limpiar(){
        limpiarTabla();
        vVentas.txtCodigoCliente.setText("");
        vVentas.txtNombreCliente.setText("");
        vVentas.txtDireccion.setText("");
        vVentas.txtNit.setText("");   
        vVentas.txtCodigoProducto.setText("");
        vVentas.txtNombreProducto.setText("");
        vVentas.txtPrecio.setText("");
        vVentas.txtStock.setText("");
        vVentas.txtCantidad.setText("");
        vVentas.txtTotalPagar.setText("");
    }
    
    public void limpiarTabla(){
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.removeRow(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vVentas.btnBuscarCliente) {
            buscarCliente();

        }
        if (e.getSource() == vVentas.btnBuscarProducto) {
            buscarProducto();
        }
        if (e.getSource() == vVentas.btnAgregar) {
            agregarProducto();

        }
        if (e.getSource() == vVentas.btnGenerarVenta) {
            if (vVentas.txtTotalPagar.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Datos incompletos para generar venta");

            } else {
                guardarVenta();
                guardarDetalleVenta();
                actualizaStock();
                JOptionPane.showMessageDialog(null, "Factura generado con exito !!");
                limpiar();
                generarSerie();
            }

        }

    }

}
