package main;

import controlador.ControladorVendedor;
import vista.frmLogin;



public class LoginMain {

    public static void main(String[] args) {
        frmLogin login = new frmLogin();       
        ControladorVendedor ctrl = new ControladorVendedor(login);
         login.setVisible(true);
         
         
    }
    
}
