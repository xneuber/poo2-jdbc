package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	Connection con;
	String sql;
	PreparedStatement pst;
	private Aluno aluno;
	
	
	public void salvar(Aluno a) throws IOException {
		
		try {
			pst("INSERT INTO aluno (id, nome, sexo, dt_nasc) VALUES ((SELECT max(id)+1 FROM aluno), ?,  ?, ?)");
			pst(a);
			System.out.println("\nCadastro do aluno "+ a.toString() +" realizado com sucesso!");
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void apagar(int id) throws IOException {
		try {
			pst("delete from aluno where id = ?");
			pst.setInt(1, id);
			//System.out.println("\nAluno "+ printAlunoById(id) +"excluido com sucesso!");
			pst.executeUpdate();
			conOut();
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
	}
	
	public void alterar(Aluno a) throws IOException {
		try {
			pst("UPDATE aluno SET nome=?, sexo=?, dt_nasc=? WHERE id=?");
			pst(a);
			System.out.println("Dados do aluno atualizados para:\n" + a.toString());
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public List<Aluno> listar() throws IOException, SQLException {
		try {
			conIn();
			Statement statement= con.createStatement();
			String sql = "Select * from aluno";
			ResultSet resultSet = statement.executeQuery(sql);
			List<Aluno> alunos = new ArrayList<>();
			while (resultSet.next()) {
				aluno = new Aluno();
				aluno.setId(resultSet.getInt(1));
				aluno.setNome(resultSet.getString(2));
				aluno.setSexo(resultSet.getString(3));
				aluno.setDt_nasc(resultSet.getDate(4));
				alunos.add(aluno);
				System.out.println(aluno.toString());
				}
			conOut();
			return alunos;
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	//PRIVATES
	private void conOut() throws SQLException {
		//DB.fechaConexao();
		System.out.println("Conexão fechada com sucesso !");
		
	}

	private void conIn() throws IOException, SQLException {
		con = DB.getConexao();
		System.out.println("Conexão realizada com sucesso !");
	}
	
	private void pst(String sql) throws IOException, SQLException {
		conIn();
		pst = con.prepareStatement(sql);
	}
	
	private void pst(Aluno a) throws SQLException {
		pst.setString(1, a.getNome());
		pst.setString(2, a.getSexo());
		Date dataSql = new Date(a.getDt_nasc().getTime());
		pst.setDate(3, dataSql);
		if (a.getId() > -1) pst.setInt(4, a.getId());
		pst.executeUpdate();
		conOut();
	}
	
	private String printAlunoById (int id) throws IOException, SQLException {
		pst("Select * from aluno where id = ?");
		ResultSet resultSet = pst.executeQuery();
		Aluno a = new Aluno();
		a.setId(resultSet.getInt(1));
		a.setNome(resultSet.getString(2));
		a.setSexo(resultSet.getString(3));
		a.setDt_nasc(resultSet.getDate(4));
		return a.toString();
	}
}