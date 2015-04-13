import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.*;
import javax.swing.*;

public class Interfaccia extends JFrame{
	
	JTable tbl = new JTable();
	DefaultTableModel dtm = new DefaultTableModel(0, 0);
	JScrollPane scpTable = new JScrollPane();

	JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 30));
	JPanel pnlCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
	JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
	
	JLabel lblNodi = new JLabel("Inserire numero di router");
	JTextField txtNodi = new JTextField(5);
	JButton btnNodi = new JButton("Inserisci");
	
	public Interfaccia(){
		super("Dijkstra");
		
		pnlNorth.add(lblNodi);
		pnlNorth.add(txtNodi);
		pnlNorth.add(btnNodi);
		add(pnlNorth,BorderLayout.NORTH);
		
		dtm.setColumnIdentifiers(new String[]{		
					"From","To","Distance"});
		tbl.setModel(dtm);
		
		scpTable.getViewport().add(tbl);
		pnlSouth.add(scpTable);
		add(pnlSouth,BorderLayout.SOUTH);
		
		btnNodi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtNodi.getText().equals("") && !txtNodi.getText().equals(" ")){
					dtm.addRow(new Object[]{
							"p1","p2","p3"});
				}
			}
		});
		
		JTextField txtFrom = new JTextField("From",6);
		txtFrom.setHorizontalAlignment(JTextField.CENTER);
		txtFrom.setEditable(false);
		pnlCenter.add(txtFrom);
		
		JTextField txtTo = new JTextField("To",6);
		txtTo.setHorizontalAlignment(JTextField.CENTER);
		txtTo.setEditable(false);
		pnlCenter.add(txtTo);
		
		JTextField txtDistance = new JTextField("Distance",6);
		txtDistance.setHorizontalAlignment(JTextField.CENTER);
		txtDistance.setEditable(false);
		pnlCenter.add(txtDistance);
		add(pnlCenter,BorderLayout.CENTER);
		
		setBounds(400, 120, 500, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] argv){
		new Interfaccia();
	}
}
