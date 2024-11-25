package table;

import javax.swing.table.AbstractTableModel;

import model.Costumer;
import model.Order;
import model.User;

import java.util.List;

public class TableOrder extends AbstractTableModel {
	List<Order> ls;
	private String[] columnNames = {"Order ID", "ID Pelanggan", "Tanggal","Status", "Status Pembayaran", "Total"};
	public TableOrder(List<Order> ls) {
		this.ls = ls;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ls.size();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		 return columnNames[column];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return ls.get(rowIndex).getId();
		case 1:
			return ls.get(rowIndex).getId_pelanggan();
		case 2:
			return ls.get(rowIndex).getTanggal();
		case 3:
			return ls.get(rowIndex).getStatus();
		case 4:
			return ls.get(rowIndex).getStatus_pembayaran();
		case 5:
			return ls.get(rowIndex).getTotal();
		default:
			return null;
		}
	}

}
