package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ClienteDAO;
import modelo.Cliente;
import vista.frmCliente;

public class ControladorCliente implements ActionListener, MouseListener {

    frmCliente vCliente = new frmCliente();
    ClienteDAO cDao = new ClienteDAO();
    Cliente cliente = new Cliente();

    public ControladorCliente(frmCliente vCliente) {
        this.vCliente = vCliente;

        this.vCliente.btnAgregar.addActionListener(this);
        this.vCliente.btnLimpiar.addActionListener(this);
        this.vCliente.btnModificar.addActionListener(this);
        this.vCliente.btnEliminar.addActionListener(this);
        this.vCliente.btnConsultar.addActionListener(this);
        this.llenarTabla(vCliente.miTabla);
        this.vCliente.miTabla.addMouseListener(this);

    }

    public void limpiar() {
        vCliente.txtCodigo.setText(null);
        vCliente.txtNit.setText(null);
        vCliente.txtNombre.setText(null);
        vCliente.txtDireccion.setText(null);
        vCliente.txtEstado.setText(null);
    }

    public void insertarCliente() {
        cliente.setIdCliente(Integer.parseInt(vCliente.txtCodigo.getText()));
        cliente.setCui(vCliente.txtNit.getText());
        cliente.setNombre(vCliente.txtNombre.getText());
        cliente.setDireccion(vCliente.txtDireccion.getText());
        cliente.setEstado(vCliente.txtEstado.getText());
        String respuesta = cDao.registroCliente(cliente);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
            limpiar();
        }
    }

    public void modificarCliente() {
        cliente.setIdCliente(Integer.parseInt(vCliente.txtCodigo.getText()));
        cliente.setCui(vCliente.txtNit.getText());
        cliente.setNombre(vCliente.txtNombre.getText());
        cliente.setDireccion(vCliente.txtDireccion.getText());
        cliente.setEstado(vCliente.txtEstado.getText());
        String actualizar = cDao.modificarCliente(cliente);
        if (actualizar != null) {
            JOptionPane.showMessageDialog(null, actualizar);
            this.llenarTabla(vCliente.miTabla);
            limpiar();
        }
    }

    public void eliminarCliente() {
        cliente.setIdCliente(Integer.parseInt(vCliente.txtCodigo.getText()));
        String eliminar = cDao.eliminarCliente(cliente.getIdCliente());
        this.llenarTabla(vCliente.miTabla);
        if (eliminar != null) {
            JOptionPane.showMessageDialog(null, "Registro eliminado con Exito!");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Registro no se puede eliminar!");
        }
    }

    public void mostrarCliente() {
        cliente.setIdCliente(Integer.parseInt(vCliente.txtCodigo.getText()));
        cliente = cDao.mostrarCliente(cliente.getIdCliente());
        vCliente.txtNit.setText(cliente.getCui());
        vCliente.txtNombre.setText(cliente.getNombre());
        vCliente.txtDireccion.setText(cliente.getDireccion());
        vCliente.txtEstado.setText(cliente.getEstado());
    }

    public void llenarTabla(JTable datoTabla) {
        DefaultTableModel modeloTable = new DefaultTableModel();
        datoTabla.setModel(modeloTable);
        modeloTable.addColumn("CODIGO");
        modeloTable.addColumn("NIT");
        modeloTable.addColumn("NOMBRE");
        modeloTable.addColumn("DIRECCION");
        modeloTable.addColumn("ESTADO");
        Object[] columna = new Object[5];
        int numFilas = cDao.listaCliente().size();
        for (int i = 0; i < numFilas; i++) {
            columna[0] = cDao.listaCliente().get(i).getIdCliente();
            columna[1] = cDao.listaCliente().get(i).getCui();
            columna[2] = cDao.listaCliente().get(i).getNombre();
            columna[3] = cDao.listaCliente().get(i).getDireccion();
            columna[4] = cDao.listaCliente().get(i).getEstado();
            modeloTable.addRow(columna);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vCliente.btnAgregar) {
            insertarCliente();
        }
        if (e.getSource() == vCliente.btnLimpiar) {
            limpiar();
        }
        if (e.getSource() == vCliente.btnModificar) {
            modificarCliente();
        }
        if (e.getSource() == vCliente.btnEliminar) {
            eliminarCliente();
        }
        if (e.getSource() == vCliente.btnConsultar) {
            mostrarCliente();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        vCliente.txtCodigo.setText(vCliente.miTabla.getValueAt(vCliente.miTabla.getSelectedRow(), 0).toString());
        vCliente.txtNit.setText(vCliente.miTabla.getValueAt(vCliente.miTabla.getSelectedRow(), 1).toString());
        vCliente.txtNombre.setText(vCliente.miTabla.getValueAt(vCliente.miTabla.getSelectedRow(), 2).toString());
        vCliente.txtDireccion.setText(vCliente.miTabla.getValueAt(vCliente.miTabla.getSelectedRow(), 3).toString());
        vCliente.txtEstado.setText(vCliente.miTabla.getValueAt(vCliente.miTabla.getSelectedRow(), 4).toString());
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
