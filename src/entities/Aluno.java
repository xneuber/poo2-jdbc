package entities;

import java.util.Date;

public class Aluno {
	
	private int id = -1;
	private String nome;
	private String sexo;
	private Date dt_nasc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	@Override
	public String toString() {
		return id + "\t" + nome + "\t" + sexo + "\t" + dt_nasc;
	}

}