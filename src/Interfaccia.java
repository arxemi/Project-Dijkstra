import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.*;

public class Interfaccia extends JFrame{
	
	private JTable tbl = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel(0, 0);
	private JScrollPane scpTable = new JScrollPane();

	private JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
	private JPanel pnlGlobalCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
	private JPanel pnlCenter = new JPanel(new GridLayout(4,2));
	private JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

	private JLabel lblNodi = new JLabel("Set number of routers");
	private JTextField txtNodi = new JTextField(5);
	private JButton btnNodi = new JButton("Enter");

	private JLabel lblFrom = new JLabel("From");
	private JTextField txtInputFrom = new JTextField(5);
	private JLabel lblTo = new JLabel("To");
	private JTextField txtInputTo = new JTextField(5);
	private JLabel lblDistance = new JLabel("Distance");
	private JTextField txtInputDistance = new JTextField(5);
	private JButton btnEntry = new JButton("Add entry");

	private MatriceAdiacenze mtrDcz;

	final Font globalFont = new Font("Arial", Font.BOLD, 16);

	public Interfaccia(){
		super("Dijkstra");

		pnlNorth.setBackground(Color.cyan);
		pnlCenter.setBackground(Color.cyan);
		pnlGlobalCenter.setBackground(Color.cyan);
		pnlSouth.setBackground(Color.cyan);

		lblDistance.setFont(globalFont);
		lblFrom.setFont(globalFont);
		lblNodi.setFont(globalFont);
		lblTo.setFont(globalFont);


		txtNodi.setHorizontalAlignment(JTextField.CENTER);
		txtNodi.setFont(globalFont);
		pnlNorth.add(lblNodi);
		pnlNorth.add(txtNodi);
		pnlNorth.add(btnNodi);
		add(pnlNorth, BorderLayout.NORTH);
		
		dtm.setColumnIdentifiers(new String[]{
				"From", "To", "Distance"});
		tbl.setModel(dtm);
		
		scpTable.getViewport().add(tbl);
		pnlSouth.add(scpTable);
		add(pnlSouth, BorderLayout.SOUTH);

		pnlCenter.add(lblFrom);
		txtInputFrom.setHorizontalAlignment(JTextField.CENTER);
		txtInputFrom.setFont(globalFont);
		pnlCenter.add(txtInputFrom);
		pnlCenter.add(lblTo);
		txtInputTo.setHorizontalAlignment(JTextField.CENTER);
		txtInputTo.setFont(globalFont);
		pnlCenter.add(txtInputTo);
		pnlCenter.add(lblDistance);
		txtInputDistance.setHorizontalAlignment(JTextField.CENTER);
		txtInputDistance.setFont(globalFont);
		pnlCenter.add(txtInputDistance);
		pnlCenter.add(new JLabel());
		pnlCenter.add(btnEntry);
		pnlGlobalCenter.add(pnlCenter);

		add(pnlGlobalCenter, BorderLayout.CENTER);

		btnNodi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNodi.getText().equals("") && !txtNodi.getText().equals("0")) {
					try {
						mtrDcz = new MatriceAdiacenze(Integer.parseInt(txtNodi.getText()));
					} catch (NumberFormatException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		btnEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtInputFrom.getText().equals("") && !txtInputTo.getText().equals("") && !txtInputDistance.getText().equals("")
						&& mtrDcz instanceof MatriceAdiacenze) {
					dtm.addRow(new Object[]{
							txtInputFrom.getText(), txtInputTo.getText(), txtInputDistance.getText()
					});
				}
			}
		});

		setResizable(false);
		setBounds(450, 40, 500, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] argv){
		new Interfaccia();
	}
}