
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;




/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    public Connection connectDB() {
        Connection conn = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=Alexmosar@1");
            JOptionPane.showMessageDialog(null, "Conexao estabelecida com o MySql e o banco de dados");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
