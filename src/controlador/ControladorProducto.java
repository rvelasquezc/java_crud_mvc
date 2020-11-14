package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import modelo.ProductoDAO;
import vista.frmProducto;

public class ControladorProducto implements ActionListener, MouseListener {

    frmProducto vProducto = new frmProducto();
    ProductoDAO pDAO = new ProductoDAO();
    Producto producto = new Producto();

    public ControladorProducto(frmProducto vProducto) {
        this.vProducto = vProducto;
        this.vProducto.btnAgregar.addActionListener(this);
        this.vProducto.btnLimpiar.addActionListener(this);
        this.vProducto.btnModificar.addActionListener(this);
        this.vProducto.btnEliminar.addActionListener(this);
        this.vProducto.btnConsultar.addActionListener(this);
        this.llenarTabla(vProducto.miTabla);
        this.vProducto.miTabla.addMouseListener(this);
    }

    public void limpiar() {
        vProducto.txtCodigo.setText(null);
        vProducto.txtNombre.setText(null);
        vProducto.txtPrecio.setText(null);
        vProducto.txtStock.setText(null);
        vProducto.txtEstado.setText(null);
    }

    public void insertarProducto() {
        producto.setIdProducto(Integer.parseInt(vProducto.txtCodigo.getText()));
        producto.setNombre(vProducto.txtNombre.getText());
        producto.setPrecio(vProducto.txtPrecio.getText());
        producto.setStock(Integer.parseInt(vProducto.txtStock.getText()));
        producto.setEstado(vProducto.txtEstado.getText());
        String respuesta = pDAO.registroProducto(producto);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
            this.llenarTabla(vProducto.miTabla);
            limpiar();
        }
    }

    public void modificarProducto() {
        producto.setIdProducto(Integer.parseInt(vProducto.txtCodigo.getText()));
        producto.setNombre(vProducto.txtNombre.getText());
        producto.setPrecio(vProducto.txtPrecio.getText());
        producto.setStock(Integer.parseInt(vProducto.txtStock.getText()));
        producto.setEstado(vProducto.txtEstado.getText());
        String respuestaActualizar = pDAO.modificarProducto(producto);
        if (respuestaActualizar != null) {
            JOptionPane.showMessageDialog(null, respuestaActualizar);
            this.llenarTabla(vProducto.miTabla);
            limpiar();
        }
    }

    public void eliminarProducto() {
        producto.setIdProducto(Integer.parseInt(vProducto.txtCodigo.getText()));
        String eliminar = pDAO.eliminarProducto(producto.getIdProducto());
        this.llenarTabla(vProducto.miTabla);
        if (eliminar != null) {
            JOptionPane.showMessageDialog(null, "El Registro fue eliminado Correctamente");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "El registro no se puede eliminar");
        }
    }

    public void mostrarProducto() {
        producto.setIdProducto(Integer.parseInt(vProducto.txtCodigo.getText()));
        producto = pDAO.mostrarProducto(producto.getIdProducto());
        vProducto.txtNombre.setText(producto.getNombre());
        vProducto.txtPrecio.setText(producto.getPrecio().toString());
        vProducto.txtStock.setText(producto.getStock().toString());
        vProducto.txtEstado.setText(producto.getEstado());

    }

    public void llenarTabla(JTable datoTabla) {
        DefaultTableModel modeloTable = new DefaultTableModel();
        datoTabla.setModel(modeloTable);
        modeloTable.addColumn("CODIGO");
        modeloTable.addColumn("NOMBRES");
        modeloTable.addColumn("PRECIO");
        modeloTable.addColumn("STOCK");
        modeloTable.addColumn("ESTADO");
        Object[] columna = new Object[5];
        int numFilas = pDAO.listaProducto().size();
        for (int i = 0; i < numFilas; i++) {
            columna[0] = pDAO.listaProducto().get(i).getIdProducto();
            columna[1] = pDAO.listaProducto().get(i).getNombre();
            columna[2] = pDAO.listaProducto().get(i).getPrecio();
            columna[3] = pDAO.listaProducto().get(i).getStock();
            columna[4] = pDAO.listaProducto().get(i).getEstado();
            modeloTable.addRow(columna);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vProducto.btnAgregar) {
            insertarProducto();
        }
        if (e.getSource() == vProducto.btnLimpiar) {
            limpiar();
        }
        if (e.getSource() == vProducto.btnModificar) {
            modificarProducto();
        }
        if (e.getSource() == vProducto.btnEliminar) {
            eliminarProducto();
        }
        if (e.getSource() == vProducto.btnConsultar) {
            mostrarProducto();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        vProducto.txtCodigo.setText(vProducto.miTabla.getValueAt(vProducto.miTabla.getSelectedRow(), 0).toString());
        vProducto.txtNombre.setText(vProducto.miTabla.getValueAt(vProducto.miTabla.getSelectedRow(), 1).toString());
        vProducto.txtPrecio.setText(vProducto.miTabla.getValueAt(vProducto.miTabla.getSelectedRow(), 2).toString());
        vProducto.txtStock.setText(vProducto.miTabla.getValueAt(vProducto.miTabla.getSelectedRow(), 3).toString());
        vProducto.txtEstado.setText(vProducto.miTabla.getValueAt(vProducto.miTabla.getSelectedRow(), 4).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
