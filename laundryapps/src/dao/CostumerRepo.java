package dao;

import model.Costumer;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import confg.Database;
public class CostumerRepo implements CostumerDao{
	 private Connection connection;
	 final String insert = "INSERT INTO costumer (nama, alamat, hp) VALUES (?,?,?);";
	 final String select = "SELECT * FROM costumer;";
	 final String delete = "DELETE FROM costumer WHERE id=?;";
	 final String update = "UPDATE costumer SET nama=?, alamat=?, hp=? WHERE id=?;";
	 
	public CostumerRepo() {
		connection = Database.koneksi();
	}


	@Override
	public void save(Costumer cs) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, cs.getNama());
			st.setString(2, cs.getAlamat());
			st.setString(3, cs.getHp());
			st.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	

	@Override
	public List<Costumer> show() {
		List<Costumer> ls=null;
		try {
			ls = new ArrayList<Costumer>();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(select);
			while(rs.next()) {
				Costumer cs = new Costumer();
				cs.setId(rs.getString("id"));
				cs.setNama(rs.getString("nama"));
				cs.setAlamat(rs.getString("alamat"));
				cs.setHp(rs.getString("hp"));
				ls.add(cs);
			}
			
		}catch(SQLException e) {
			Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
		}
		return ls;
	}


	@Override
	public void delete(String id) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(delete);
			st.setString(1, id);
			st.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				st.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public void update(Costumer cs) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, cs.getNama());
			st.setString(2, cs.getAlamat());
			st.setString(3, cs.getHp());
			st.setString(4, cs.getId());
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				st.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
