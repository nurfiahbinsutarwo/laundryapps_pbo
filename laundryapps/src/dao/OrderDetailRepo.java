package dao;

import model.Costumer;
import model.OrderDetail;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import confg.Database;
public class OrderDetailRepo implements OrderDetailDao{
	
	 private Connection connection;
	 final String insert = "INSERT INTO order_detail (order_id, service_id, harga,jumlah,total) VALUES (?,?,?,?,?);";
	 final String delete = "DELETE FROM order_detail WHERE id=?;";
	 final String update = "UPDATE order_detail SET order_id=?, service_id=?, harga=?, jumlah=?, total=? WHERE id=?;";
	
	 
	public OrderDetailRepo() {
		connection = Database.koneksi();
	}


	@Override
	public void save(OrderDetail cs) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, cs.getOrder_id());
			st.setString(2, cs.getService_id());
			st.setString(3, cs.getHarga());
			st.setString(4, cs.getJumlah());
			st.setString(5, cs.getTotal());
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
	public void update(OrderDetail cs) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, cs.getOrder_id());
			st.setString(2, cs.getService_id());
			st.setString(3, cs.getHarga());
			st.setString(4, cs.getJumlah());
			st.setString(5, cs.getTotal());
			st.setString(6, cs.getId());
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
	public String total(String order_id) {
		String query_total = "SELECT sum(total) as total from order_detail WHERE order_id="+"'"+order_id+"'"+";";
		Statement st;
		ResultSet rs;
		String result="";
		try {
			st = connection.createStatement();
			 rs = st.executeQuery(query_total);
			 if(rs.next()) {
				 result = ""+rs.getDouble(1);
			 }else {
				 result="0";
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}


	@Override
	public List<OrderDetail> show(String order_id) {
		List<OrderDetail> ls=null;
		try {
			ls = new ArrayList<OrderDetail>();
			Statement st = connection.createStatement();
			String select = "SELECT * FROM order_detail  where order_id="+"'"+order_id+"'"+";";
			ResultSet rs = st.executeQuery(select);
			while(rs.next()) {
				OrderDetail cs = new OrderDetail();
				cs.setId(rs.getString("id"));
				cs.setOrder_id(rs.getString("order_id"));
				cs.setService_id(rs.getString("service_id"));
				cs.setHarga(rs.getString("harga"));
				cs.setJumlah(rs.getString("jumlah"));
				cs.setTotal(rs.getString("total"));
				ls.add(cs);
			}
			
		}catch(SQLException e) {
			Logger.getLogger(OrderDetailDao.class.getName()).log(Level.SEVERE, null, e);
		}
		return ls;
	}
	
}
