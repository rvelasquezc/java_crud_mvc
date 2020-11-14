package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.VendedorDAO;
import modelo.Vendedor;
import vista.frmPrincipal;
import vista.frmLogin;
import vista.frmVendedor;

public class ControladorVendedor implements ActionListener, MouseListener {

    frmLogin vlogin = new frmLogin();
    VendedorDAO vDAO = new VendedorDAO();
    Vendedor usuario = new Vendedor();
    frmVendedor vVendedor = new frmVendedor();

    public ControladorVendedor(frmLogin vlogin, VendedorDAO vDAO, Vendedor usuario, frmVendedor vVendedor) {
        this.vlogin = vlogin;
        this.vDAO = vDAO;
        this.usuario = usuario;
        this.vVendedor = vVendedor;
    }

    public ControladorVendedor(frmLogin login) {
        this.vlogin = login;
        this.vlogin.btnLogin.addActionListener(this);
        this.vlogin.btnRegresar.addActionListener(this);
    }

    public ControladorVendedor(frmVendedor vVendedor) {
        this.vVendedor = vVendedor;
        this.vVendedor.btnAgregar.addActionListener(this);
        this.vVendedor.btnLimpiar.addActionListener(this);
        this.vVendedor.btnModificar.addActionListener(this);
        this.vVendedor.btnEliminar.addActionListener(this);
        this.vVendedor.btnConsultar.addActionListener(this);
        this.llenarTabla(vVendedor.miTabla);
        this.vVendedor.miTabla.addMouseListener(this);
    }

    public void validar() {
        String user = vlogin.txtUsuario.getText();
        String pass = vlogin.txtPassword.getText();
        if (vlogin.txtUsuario.getText().equals("") || vlogin.txtPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "credenciales vacio intenta de nuevo ");
        } else {
            usuario = vDAO.validaVendedor(user, pass);
            System.out.println("user y pass " + user + " " + pass);
            if ((usuario.getUsuario() != null) && (usuario.getPassword() != null)) {
                frmPrincipal principal = new frmPrincipal();
                principal.setVisible(true);
                //dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un usuario y contraseña valida");
                vlogin.txtUsuario.requestFocus();
            }
        }
    }

    public void insertarVendedor() {
        usuario.setIdVendedor(Integer.parseInt(vVendedor.txtCodigo.getText()));
        usuario.setCui(vVendedor.txtCui.getText());
        usuario.setNombre(vVendedor.txtNombre.getText());
        usuario.setTelefono(vVendedor.txtTelefono.getText());
        usuario.setEstado(vVendedor.txtEstado.getText());
        usuario.setUsuario(vVendedor.txtUsuario.getText());
        usuario.setPassword(vVendedor.txtPassword.getText());
        String respuesta = vDAO.registrarVendedor(usuario);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, "Usuario ingresado !");
        }
    }

    public void modificarVendedor() {
        usuario.setIdVendedor(Integer.parseInt(vVendedor.txtCodigo.getText()));
        usuario.setCui(vVendedor.txtCui.getText());
        usuario.setNombre(vVendedor.txtNombre.getText());
        usuario.setTelefono(vVendedor.txtTelefono.getText());
        usuario.setEstado(vVendedor.txtEstado.getText());
        usuario.setUsuario(vVendedor.txtUsuario.getText());
        String respuestaActualizar = vDAO.modificarVendedor(usuario);
        if (respuestaActualizar != null) {
            JOptionPane.showMessageDialog(null, respuestaActualizar);
            this.llenarTabla(vVendedor.miTabla);
            limpiar();

        }
    }

    public void eliminarVendedor() {
        usuario.setIdVendedor(Integer.parseInt(vVendedor.txtCodigo.getText()));
        String respuestaEliminar = vDAO.eliminarVendedor(usuario.getIdVendedor());
        this.llenarTabla(vVendedor.miTabla);
        if (respuestaEliminar != null) {
            JOptionPane.showMessageDialog(null, "Registro Eliminado Correctamente!!!");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Registro no fue eliminado");
        }
    }

    public void mostrarVendedor() {
        usuario.setIdVendedor(Integer.parseInt(vVendedor.txtCodigo.getText()));
        usuario = vDAO.mostrarVendedor(usuario.getIdVendedor());
        vVendedor.txtCui.setText(usuario.getCui());
        vVendedor.txtNombre.setText(usuario.getNombre());
        vVendedor.txtTelefono.setText(usuario.getTelefono());
        vVendedor.txtEstado.setText(usuario.getEstado());
        vVendedor.txtUsuario.setText(usuario.getUsuario());

    }

    public void llenarTabla(JTable datoTabla) {
        DefaultTableModel modeloTable = new DefaultTableModel();
        datoTabla.setModel(modeloTable);
        modeloTable.addColumn("CODIGO");
        modeloTable.addColumn("DNI");
        modeloTable.addColumn("NOMBRES");
        modeloTable.addColumn("TELEFONO");
        modeloTable.addColumn("ESTADO");
        modeloTable.addColumn("USUARIO");
        Object[] columna = new Object[6];
        int numFilas = vDAO.listaPersona().size();
        for (int i = 0; i < numFilas; i++) {
            columna[0] = vDAO.listaPersona().get(i).getIdVendedor();
            columna[1] = vDAO.listaPersona().get(i).getCui();
            columna[2] = vDAO.listaPersona().get(i).getNombre();
            columna[3] = vDAO.listaPersona().get(i).getTelefono();
            columna[4] = vDAO.listaPersona().get(i).getEstado();
            columna[5] = vDAO.listaPersona().get(i).getUsuario();
            modeloTable.addRow(columna);
        }
    }

    public void limpiar() {
        vVendedor.txtCodigo.setText(null);
        vVendedor.txtCui.setText(null);
        vVendedor.txtNombre.setText(null);
        vVendedor.txtTelefono.setText(null);
        vVendedor.txtEstado.setText(null);
        vVendedor.txtUsuario.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vlogin.btnLogin) {
            validar();
        }
        if (e.getSource() == vVendedor.btnAgregar) {
            insertarVendedor();
        }
        if (e.getSource() == vVendedor.btnLimpiar) {
            limpiar();
        }
        if (e.getSource() == vVendedor.btnModificar) {
            modificarVendedor();
        }
        if (e.getSource() == vVendedor.btnEliminar) {
            eliminarVendedor();
        }
        if (e.getSource() == vVendedor.btnConsultar) {
            mostrarVendedor();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        vVendedor.txtCodigo.setText(vVendedor.miTabla.getValueAt(vVendedor.miTabla.getSelectedRow(), 0).toString());
        vVendedor.txtCui.setText(vVendedor.miTabla.getValueAt(vVendedor.miTabla.getSelectedRow(), 1).toString());
        vVendedor.txtNombre.setText(vVendedor.miTabla.getValueAt(vVendedor.miTabla.getSelectedRow(), 2).toString());
        vVendedor.txtTelefono.setText(vVendedor.miTabla.getValueAt(vVendedor.miTabla.getSelectedRow(), 3).toString());
        vVendedor.txtEstado.setText(vVendedor.miTabla.getValueAt(vVendedor.miTabla.getSelectedRow(), 4).toString());
        vVendedor.txtUsuario.setText(vVendedor.miTabla.getValueAt(vVendedor.miTabla.getSelectedRow(), 5).toString());
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
