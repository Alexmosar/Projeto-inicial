
/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    ArrayList<ProdutosDTO> listagemVendidos = new ArrayList<>();
    public int cadastrarProduto (ProdutosDTO produto) {
       conn = new conectaDAO().connectDB();
       int atual;
       try{ 
          prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
          prep.setString(1, produto.getNome());
          prep.setInt(2, produto.getValor());
          prep.setString(3, produto.getStatus());
          atual = prep.executeUpdate();
          
        return atual;
       }catch (SQLException ex){
           JOptionPane.showMessageDialog(null, "Erro ao cadastrar" + ex.getMessage());
           return ex.getErrorCode();
       } 
    }
    
    public List<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        try{
            prep = this.conn.prepareStatement("SELECT *FROM produtos");
            resultset = prep.executeQuery();
            listagem = new ArrayList<>();
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            return listagem;            
        }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "Erro ao cadastrar" + ex.getMessage());
        }
        return null;
    }
     public int venderProduto (ProdutosDTO produto) {
       conn = new conectaDAO().connectDB();
       int atual;
       try{ 
           String sql = "update produtos SET status=? where Id = ?";
          prep = conn.prepareStatement(sql);
          prep.setString(1, produto.getStatus());
          prep.setInt(2, produto.getId());
          atual = prep.executeUpdate();
          
        return atual;
       }catch (SQLException ex){
           JOptionPane.showMessageDialog(null, "Erro ao cadastrar" + ex.getMessage());
           return ex.getErrorCode();
       } 
    }
    public List<ProdutosDTO> listarProdutosVendidos(){
        conn = new conectaDAO().connectDB();
        try{
            String sql = "SELECT*FROM produtos WHERE status like ?";
            prep = this.conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            resultset = prep.executeQuery();
            listagemVendidos = new ArrayList<>();
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagemVendidos.add(produto);
            }
            return listagemVendidos;            
        }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "Erro ao cadastrar" + ex.getMessage());
        }
        return null;
    }
    
        
}

