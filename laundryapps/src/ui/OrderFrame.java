package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.OrderDetailRepo;
import dao.OrderRepo;
import model.Order;
import model.OrderDetail;
import table.TableOrder;
import table.TableService;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblOrder;

	/**
	 * Launch the application.
	 */
	
	OrderRepo repo_od = new OrderRepo();
	List<Order> ls_od;
	String order_id="";
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();
					frame.setVisible(true);
					frame.loadTableOrder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadTableOrder() {
		ls_od = repo_od.show();
		TableOrder tu = new TableOrder(ls_od);
		tblOrder.setModel(tu);
		tblOrder.getTableHeader().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public OrderFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DATA ORDERAN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(51, 43, 181, 37);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
	
		scrollPane.setBounds(51, 129, 653, 323);
		contentPane.add(scrollPane);
		
		tblOrder = new JTable();
		tblOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				order_id = tblOrder.getValueAt(tblOrder.getSelectedRow(),0).toString();
			}
		});
		scrollPane.setViewportView(tblOrder);
		
		JButton btnOrder = new JButton("Buat Orderan");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDetailFrame odf = new OrderDetailFrame();
				odf.setVisible(true);
				odf.loadTableDetail();
				odf.loadTableService();
		
			}
		});
		btnOrder.setBounds(51, 90, 128, 29);
		contentPane.add(btnOrder);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(order_id !="") {
					repo_od.delete(order_id);
				}else {
					JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus");
				}
			}
		});
		btnHapus.setBounds(482, 90, 102, 29);
		contentPane.add(btnHapus);
		
		JButton btnEditdetail = new JButton("Edit/Detail");
		btnEditdetail.setBounds(602, 90, 102, 29);
		contentPane.add(btnEditdetail);
	}
}
