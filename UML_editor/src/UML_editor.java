
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
/*
 * The main frame of the UML editor applicaiton
 */

public class UML_editor extends JFrame {
	public static String mode;
	public ArrayList<JButton> buttons = new ArrayList<JButton>();
	private UML_canvas canvas;
	// pre_setting in constructor
	UML_editor() {
		canvas = new UML_canvas();
		// this.setLayout(new BorderLayout());

		// Menu Bar of frame
		JMenuBar menuBar = new JMenuBar();
		JMenu File = new JMenu("File");
		menuBar.add(File);
		JMenu Edit = new JMenu("Edit");
		menuBar.add(Edit);
		JMenuItem group = new JMenuItem("Group");
		JMenuItem chg_obj_name = new JMenuItem("Change Object Name");
		Edit.add(group);
		Edit.add(chg_obj_name);
		group.addActionListener(new MenuActionListener());
		chg_obj_name.addActionListener(new MenuActionListener());
		
		// button panel setting
		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(120, 1000));
		// btnPanel.setBackground(Color.GREEN);
		GridLayout gridlayout = new GridLayout(6, 1);
		btnPanel.setLayout(gridlayout);
		JButton select = createButton("SELECT");
		buttons.add(select);
		JButton assoc = createButton("ASSOC");
		buttons.add(assoc);
		JButton gener = createButton("GENER");
		buttons.add(gener);
		JButton compos = createButton("COMPOS");
		buttons.add(compos);
		JButton Class = createButton("CLASS");
		buttons.add(Class);
		JButton usecase = createButton("USE_CASE");
		buttons.add(usecase);
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).addActionListener(new BtnListener());
			btnPanel.add(buttons.get(i));
		}

		// splitPane to split canvas and btnPanel
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(btnPanel);
		splitPane.setRightComponent(canvas);
		splitPane.setResizeWeight(0.05);

		// add components to frame
		// this.add(btnPanel, BorderLayout.WEST);
		// this.add(new UML_canvas(), BorderLayout.EAST);
		this.add(splitPane, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
	}

	

	public static void main(String[] args) {
		UML_editor editor = new UML_editor();
		editor.setWindowProperty();
	}
	
	private static JButton createButton(String Name) {
		JButton btn = new JButton(Name);
		btn.setName(Name);
		btn.setPreferredSize(new Dimension(100, 100));
		return btn;
	}
	
	public void setWindowProperty() {
		setTitle("UML Editor");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public class BtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				// clear other buttons' color
				resetButton();
				JButton btn = ((JButton) e.getSource());
				btn.setBackground(Color.black);
				btn.setForeground(Color.black);
				btn.setOpaque(true);
				mode = e.getActionCommand();
				

			}

		}
	}

	// menuActionListener for menuItem
	public class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Change Object Name") && UML_canvas.selected_object!=null){
				JOptionPane chgNamePane = new JOptionPane();
//				chgNamePane.showConfirmDialog(null, "change or not", "Object Name alternation", JOptionPane.OK_CANCEL_OPTION);
				String rename = JOptionPane.showInputDialog(null, "Enter object new name: ");
				if(rename!=null){
					UML_canvas.selected_object.name = rename;
				}
				repaint();
				UML_canvas.selected_object = null;
			}else if(e.getActionCommand().equals("Group")){
				System.out.println("group");
			}
		}

	}

	public void resetButton() {
		/* 把所有的btn都設成沒有被選取 */
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setBackground(Color.white);
			buttons.get(i).setOpaque(true);
		}
		// reset click_count when mode changed
		UML_canvas.click_count = 0;
	}

}
