package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CostumerRepo;
import dao.UserRepo;
import listener.DataListener;
import model.Costumer;
import model.User;
import table.TableCostumer;
import table.TableUser;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DialogPelanggan extends JDialog {
	 private DataListener listener;
	CostumerRepo usr = new CostumerRepo();
	List<Costumer> ls;
	public String id;
	private JTable tablePelanggan;
	
	public DialogPelanggan(DataListener listener) {
		this.listener = listener;
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		 setSize(450, 249);
		 setLocationRelativeTo(null);
		setTitle("Data Pelanggan");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(10, 10, 416, 132);
		getContentPane().add(scrollPane);
		
		tablePelanggan = new JTable();
		tablePelanggan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 listener.onDataReceived(tablePelanggan.getValueAt(tablePelanggan.getSelectedRow(),0).toString(), tablePelanggan.getValueAt(tablePelanggan.getSelectedRow(),1).toString());
//				getData();
				dispose();
			}
		});
		scrollPane.setViewportView(tablePelanggan);
		loadTable();
	}
	
	public void loadTable() {
		ls = usr.show();
		TableCostumer tu = new TableCostumer(ls);
		tablePelanggan.setModel(tu);
		tablePelanggan.getTableHeader().setVisible(true);
	}

}

