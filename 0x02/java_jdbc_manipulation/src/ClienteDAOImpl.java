import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAOImpl implements ClienteDAO{

    String nomeTabela;
    @Override
    public Connection connect(String urlConexao) {
        Connection cnn = null;
        try{
            cnn = DriverManager.getConnection(urlConexao);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(cnn != null){
                try {
                    cnn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return cnn;
    }

    private void scrip(String query, String urlConexao){
        Statement stm = null;
        try {
            stm = connect(urlConexao).createStatement();
            stm.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createTable(String urlConexao) {
        scrip(
                String.format("create table %s(%s,%s,%s,%s)", nomeTabela, "cpf text", "rg text", "nome text", "idade integer"),
                urlConexao);
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        scrip(
                String.format("insert into %s values (%s,%s,%s,%d);", nomeTabela ,cliente.getCpf(), cliente.getRg(), cliente.getNome(), cliente.getIdade()),
                url_conexao
        );
    }

    @Override
    public void selectAll(String urlConexao) {
        scrip("select * from " + nomeTabela, urlConexao);

    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        scrip(
                String.format(
                        "update table %s set nome = %s, idade = %d" +
                                " where id == " + id,
                        nomeTabela, name, idade )
                , urlConexao);
    }

    @Override
    public void delete(String urlConexao, int id) {
        scrip(
                String.format("delete from %s where id = %s", nomeTabela, id), urlConexao);
    }
}
