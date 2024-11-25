package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CostumerRepo;
import dao.OrderDetailRepo;
import dao.OrderRepo;
import dao.ServiceRepo;
import dao.UserRepo;
import listener.DataListener;
import model.Costumer;
import model.Order;
import model.OrderDetail;
import model.Service;
import model.User;
import table.TableOrderDetail;
import table.TableService;
import table.TableUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderDetailFrame extends JFrame implements DataListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTanggal;
	private JTextField txtTanggalPengambilan;
	private JTextField txtHarga;
	private JTextField txtJumlah;
	private JTextField txtTotal;
	private JTable tableService;

	/**
	 * Launch the application.
	 */
	
	CostumerRepo cs = new CostumerRepo();
	List<Costumer> ls;
	
//	service
	ServiceRepo sr = new ServiceRepo();
	List<Service> ls_service;
	public String id_service;
	public String id_pelanggan="";
	public static String txt_pelanggan="";
	
//	detail
	OrderDetailRepo repo_od = new OrderDetailRepo();
	List<OrderDetail> ls_od;
	public String id_order_detail;
	
	
	private JTable tableOrderDetail;
	private JTextField txtTrx;
	private JButton btnBatalDetail;
	private JTextField txtPelanggan;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetailFrame frame = new OrderDetailFrame();
					frame.setVisible(true);
					frame.loadTableService();
					frame.loadTableDetail();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	load table service
	public void loadTableService() {
		ls_service = sr.show();
		TableService tu = new TableService(ls_service);
		tableService.setModel(tu);
		tableService.getTableHeader().setVisible(true);
	}
//	set total
	public double total(String jumlah) {
		double result =0;
		if(jumlah.isEmpty()) {
			 result=0;
		}else {
			result = Double.parseDouble(jumlah) * Double.parseDouble(txtHarga.getText());
		}
		return result;
	}
	
//	load table detail
	public void loadTableDetail() {
		ls_od = repo_od.show(txtTrx.getText());
		TableOrderDetail tu = new TableOrderDetail(ls_od);
		tableOrderDetail.setModel(tu);
		tableOrderDetail.getTableHeader().setVisible(true);
	}
//	reset
	public void reset() {
		txtHarga.setText("");
		txtJumlah.setText("");
		txtTotal.setText("");
		id_service=null;
		id_order_detail=null;
	}

	/**
	 * Create the frame.
	 */
	public OrderDetailFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 22, 271, 680);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pelanggan");
		lblNewLabel.setBounds(20, 98, 91, 13);
		panel.add(lblNewLabel);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(20, 346, 91, 13);
		panel.add(lblTotal);
		
		JLabel txtTotalOrder = new JLabel("Rp. 10.000");
		txtTotalOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTotalOrder.setBounds(20, 361, 91, 13);
		panel.add(txtTotalOrder);
		
		JComboBox cbxPembayaran = new JComboBox();
		cbxPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Cash", "Transfer"}));
		cbxPembayaran.setBounds(20, 401, 226, 30);
		panel.add(cbxPembayaran);
		
		JLabel lblPembayaran = new JLabel("Pembayaran");
		lblPembayaran.setBounds(20, 384, 91, 13);
		panel.add(lblPembayaran);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setBounds(20, 155, 91, 13);
		panel.add(lblTanggal);
		
		txtTanggal = new JTextField();
		txtTanggal.setBounds(20, 173, 226, 30);
		panel.add(txtTanggal);
		txtTanggal.setColumns(10);
		
		
		txtTanggalPengambilan = new JTextField();
		txtTanggalPengambilan.setColumns(10);
		txtTanggalPengambilan.setBounds(20, 231, 226, 30);
		panel.add(txtTanggalPengambilan);
		
		JLabel lblTanggalPengambilan = new JLabel("Tanggal Pengambilan");
		lblTanggalPengambilan.setBounds(20, 213, 141, 13);
		panel.add(lblTanggalPengambilan);
		
		JComboBox cbxStatus = new JComboBox();
		cbxStatus.setModel(new DefaultComboBoxModel(new String[] {"Proses", "Selesai"}));
		cbxStatus.setBounds(20, 287, 226, 30);
		panel.add(cbxStatus);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(20, 270, 91, 13);
		panel.add(lblStatus);
		
		JLabel lblStatusPembayaran = new JLabel("Status Pembayaran");
		lblStatusPembayaran.setBounds(20, 443, 91, 13);
		panel.add(lblStatusPembayaran);
		
		JComboBox cbxStatusPembayaran = new JComboBox();
		cbxStatusPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Lunas", "Draft"}));
		cbxStatusPembayaran.setBounds(20, 460, 226, 30);
		panel.add(cbxStatusPembayaran);
		
		txtTrx = new JTextField();
		txtTrx.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtTrx.getText() != "") {
					loadTableDetail();
				
				}
			}
		});
		txtTrx.setText("TRX-000001");
		txtTrx.setColumns(10);
		txtTrx.setBounds(20, 58, 226, 30);
		panel.add(txtTrx);
		
		JLabel lblOrderId = new JLabel("Order ID");
		lblOrderId.setBounds(20, 40, 91, 13);
		panel.add(lblOrderId);
		
		JButton btnSimpanOrder = new JButton("Simpan");
		btnSimpanOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderRepo order_repo = new OrderRepo();
				
				if(id_pelanggan !="") {
					Order order = new Order();
					order.setId(txtTrx.getText());
					order.setId_pelanggan(id_pelanggan);
					order.setTanggal(txtTanggal.getText());
					order.setTanggal_pengambilan(txtTanggalPengambilan.getText());
					order.setStatus(cbxStatus.getSelectedItem().toString());
					order.setStatus_pembayaran(cbxStatusPembayaran.getSelectedItem().toString());
					order.setPembayaran(cbxPembayaran.getSelectedItem().toString());
					order.setTotal(txtTotalOrder.getText());
					order_repo.save(order);
					JOptionPane.showMessageDialog(null, "Order Berhasil Disimpan");
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih Pelanggan terlebih dahulu");
				}
				
			}
		});
		btnSimpanOrder.setBounds(20, 521, 91, 30);
		panel.add(btnSimpanOrder);
		
		JButton btnBatalOrder = new JButton("Batal");
		btnBatalOrder.setBounds(155, 521, 91, 30);
		panel.add(btnBatalOrder);
		
		txtPelanggan = new JTextField();
		txtPelanggan.setText(txt_pelanggan);
		txtPelanggan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DialogPelanggan dialog = new DialogPelanggan(OrderDetailFrame.this);
	            dialog.setVisible(true);
			}
		});
	
		txtPelanggan.setEditable(false);
		txtPelanggan.setColumns(10);
		txtPelanggan.setBounds(20, 115, 226, 30);
		panel.add(txtPelanggan);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(305, 190, 497, 206);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtHarga = new JTextField();
		txtHarga.setEditable(false);
		txtHarga.setColumns(10);
		txtHarga.setBounds(10, 28, 226, 30);
		panel_1.add(txtHarga);
		
		JLabel lblHargakg = new JLabel("Harga/Kg");
		lblHargakg.setBounds(10, 10, 91, 13);
		panel_1.add(lblHargakg);
		
		txtJumlah = new JTextField();
		txtJumlah.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String value_jumlah = txtJumlah.getText().toString();
				txtTotal.setText(""+total(value_jumlah));
				
			}
		});

		txtJumlah.setColumns(10);
		txtJumlah.setBounds(10, 96, 226, 30);
		panel_1.add(txtJumlah);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(10, 78, 91, 13);
		panel_1.add(lblJumlah);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(261, 96, 226, 30);
		panel_1.add(txtTotal);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setBounds(261, 78, 91, 13);
		panel_1.add(lblTotal_1);
		
		JButton btnSimpanDetail = new JButton("Simpan");
		btnSimpanDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order_detail == null) {
				OrderDetail od = new OrderDetail();
				od.setOrder_id(txtTrx.getText());
				od.setService_id(id_service);
				od.setHarga(txtHarga.getText());
				od.setJumlah(txtJumlah.getText());
				od.setTotal(txtTotal.getText());
				repo_od.save(od);
				JOptionPane.showMessageDialog(null, "berhasil disimpan");
				loadTableDetail();
				reset();
				txtTotalOrder.setText(""+repo_od.total(txtTrx.getText()));
				}
			}
		});
		btnSimpanDetail.setBounds(10, 150, 91, 30);
		panel_1.add(btnSimpanDetail);
		
		JButton btnUbahDetail = new JButton("Ubah");
		btnUbahDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order_detail != null) {
					OrderDetail od = new OrderDetail();
					od.setOrder_id(txtTrx.getText());
					od.setService_id(id_service);
					od.setHarga(txtHarga.getText());
					od.setJumlah(txtJumlah.getText());
					od.setTotal(txtTotal.getText());
					od.setId(id_order_detail);
					repo_od.update(od);
					loadTableDetail();
					reset();
					txtTotalOrder.setText(""+repo_od.total(txtTrx.getText()));
				}else{
					JOptionPane.showMessageDialog(null, "silahkan pilih order terlebih dahulu");
				}
			}
		});
		btnUbahDetail.setBounds(111, 150, 91, 30);
		panel_1.add(btnUbahDetail);
		
		JButton btnHapusDetail = new JButton("Hapus");
		btnHapusDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order_detail != null) {
					repo_od.delete(id_order_detail);
					reset();
					loadTableDetail();
					txtTotalOrder.setText(""+repo_od.total(txtTrx.getText()));
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di hapus");
				}
			}
		});
		btnHapusDetail.setBounds(212, 150, 91, 30);
		panel_1.add(btnHapusDetail);
		
		btnBatalDetail = new JButton("Batal");
		btnBatalDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBatalDetail.setBounds(313, 150, 91, 30);
		panel_1.add(btnBatalDetail);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(305, 406, 497, 296);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	
		scrollPane_1.setBounds(10, 10, 477, 254);
		panel_2.add(scrollPane_1);
		
		tableOrderDetail = new JTable();
		tableOrderDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_order_detail = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),0).toString();
				id_service = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),2).toString();
				txtHarga.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),3).toString());
				txtTotal.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),5).toString());
				txtJumlah.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),4).toString());
			}
		});
		scrollPane_1.setViewportView(tableOrderDetail);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		scrollPane.setBounds(307, 44, 495, 136);
		contentPane.add(scrollPane);
		
		tableService = new JTable();
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_service = tableService.getValueAt(tableService.getSelectedRow(),0).toString();
				txtHarga.setText(tableService.getValueAt(tableService.getSelectedRow(),3).toString());
				
				if(!txtJumlah.getText().isEmpty()) {
					txtTotal.setText(""+total(txtJumlah.getText()));
				}
			}
			
		});
		scrollPane.setViewportView(tableService);
		
		JLabel lblLayanan_1 = new JLabel("Layanan");
		lblLayanan_1.setBounds(305, 22, 91, 13);
		contentPane.add(lblLayanan_1);
		
		txtTotalOrder.setText(""+repo_od.total(txtTrx.getText()));
		
	}

	@Override
	public void onDataReceived(String id, String nama) {
		txtPelanggan.setText(nama);
		id_pelanggan=id;
		
	}


}
