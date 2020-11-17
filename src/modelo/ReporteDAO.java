
package modelo;

import java.util.HashMap;
import java.util.Map;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rvelasquez
 */
public class ReporteDAO extends Conexion {
    
    public void reporteFactura(int codigo){
         try {
            this.conectar();
            Map idVentas = new HashMap();
            idVentas.put("idVentas", codigo);
            JasperReport reporte = null;
            String path = "src\\reportes\\reporteFactura.jasper";
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            System.out.println("valor de la conexion" + this.getMiConexion());
            JasperPrint jprint = JasperFillManager.fillReport(reporte,idVentas, this.getMiConexion());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    }
    
}
