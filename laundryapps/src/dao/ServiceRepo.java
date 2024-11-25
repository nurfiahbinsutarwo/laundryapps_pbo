package dao;

import model.Costumer;
import model.Service;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import confg.Database;
public class ServiceRepo implements ServiceDao{
	 private Connection connection;
	 final String insert = "INSERT INTO service (jenis, satuan, harga) VALUES (?,?,?);";
	 final String select = "SELECT * FROM service;";
	 final String delete = "DELETE FROM service WHERE id=?;";
	 final String update = "UPDATE service SET jenis=?, satuan=?, harga=? WHERE id=?;";
	 
	public ServiceRepo() {
		connection = Database.koneksi();
	}


	@Override
	public void save(Service cs) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, cs.getJenis());
			st.setString(2, cs.getSatuan());
			st.setString(3, cs.getHarga());
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
	public List<Service> show() {
		List<Service> ls=null;
		try {
			ls = new ArrayList<Service>();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(select);
			while(rs.next()) {
				Service cs = new Service();
				cs.setId(rs.getString("id"));
				cs.setJenis(rs.getString("jenis"));
				cs.setSatuan(rs.getString("satuan"));
				cs.setHarga(rs.getString("harga"));
				ls.add(cs);
			}
			
		}catch(SQLException e) {
			Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, e);
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
	public void update(Service cs) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, cs.getJenis());
			st.setString(2, cs.getSatuan());
			st.setString(3, cs.getHarga());
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
