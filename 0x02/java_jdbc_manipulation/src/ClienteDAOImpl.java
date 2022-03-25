import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAOImpl implements ClienteDAO{

    String nomeTabela = "Cliente";
    @Override
    public Connection connect(String urlConexao) {
        Connection cnn = null;
        try{
            cnn = DriverManager.getConnection(urlConexao);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }/*finally {
            if(cnn != null){
                try {
                    cnn.close();
                } catch (SQLException e) {
                    System.out.println("Erro conexão " + e.getMessage());
                }
            }
        }*/
        return cnn;
    }

    private void scrip(String query, String urlConexao){
        Statement stm = null;
        Connection cx = connect(urlConexao);
        try {
            stm = cx.createStatement();
            stm.executeQuery(query);
            System.out.println(">>>>>" +stm.getResultSet().getCursorName());
            cx.close();
        } catch (SQLException e) {
            System.out.println("Erro script "+ e.getMessage());
        }
    }

    @Override
    public void createTable(String urlConexao) {
        scrip(
                String.format("CREATE TABLE IF NOT EXISTS %s (%s,%s,%s,%s,%s);", nomeTabela, "id INTEGER PRIMARY KEY AUTOINCREMENT","cpf TEXT", "rg text", "nome TEXT", "idade INTEGER"),
                urlConexao);

    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        scrip(
                String.format("insert into %s(cpf, rg, nome, idade) values ('%s','%s','%s',%d);", nomeTabela ,cliente.getCpf(), cliente.getRg(), cliente.getNome(), cliente.getIdade()),
                url_conexao
        );
    }

    @Override
    public void selectAll(String urlConexao) {
        scrip("select * from " + nomeTabela, urlConexao);

    }

    @Override
    public void update(String urlConexao, Integer id, String name, Integer idade) {
       scrip(
                String.format(
                        "update %s set nome = '%s', idade = %d" +
                                " where id == " + id,
                        nomeTabela, name, idade )
                , urlConexao);
    }

    @Override
    public void delete(String urlConexao, Integer id) {
        scrip(
                String.format("delete from %s where id = %s", nomeTabela, id), urlConexao);


    }
}
