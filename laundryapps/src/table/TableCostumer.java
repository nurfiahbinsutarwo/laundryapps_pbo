package table;

import javax.swing.table.AbstractTableModel;

import model.Costumer;
import model.User;

import java.util.List;

public class TableCostumer extends AbstractTableModel {
	List<Costumer> ls;
	private String[] columnNames = {"ID", "Nama", "Alamat","HP"};
	public TableCostumer(List<Costumer> ls) {
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
		return 4;
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
			return ls.get(rowIndex).getNama();
		case 2:
			return ls.get(rowIndex).getAlamat();
		case 3:
			return ls.get(rowIndex).getHp();
		default:
			return null;
		}
	}

}
