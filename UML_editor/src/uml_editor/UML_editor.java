package uml_editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

import Button.AssociationButton;
import Button.Button;
import Button.ClassButton;
import Button.CompositionButton;
import Button.GeneralizationButton;
import Button.SelectButton;
import Button.UseCaseButton;

public class UML_editor extends JFrame {
	private static ArrayList<Button> buttons = new ArrayList<Button>();
	private UML_canvas canvas= new UML_canvas();
	// pre_setting in constructor
	UML_editor() {
		setWindowProperty();
		// Menu Bar of frame
		JMenuBar menuBar = new JMenuBar();
		JMenu File = new JMenu("File");
		menuBar.add(File);
		JMenu Edit = new JMenu("Edit");
		menuBar.add(Edit);
		JMenuItem group = new Menu_group();
		JMenuItem chg_obj_name = new Menu_ChgObjName();
		JMenuItem ungroup = new Menu_ungroup();
		Edit.add(group);
		Edit.add(chg_obj_name);
		Edit.add(ungroup);
		
		// button panel setting
		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(120, 1000));
		// btnPanel.setBackground(Color.GREEN);
		GridLayout gridlayout = new GridLayout(6, 1);
		btnPanel.setLayout(gridlayout);
		
		buttons.add(new SelectButton());
		buttons.add(new AssociationButton());
		buttons.add(new GeneralizationButton());
		buttons.add(new CompositionButton());
		buttons.add(new ClassButton());
		buttons.add(new UseCaseButton());
		
		for (int i = 0; i < buttons.size(); i++) {
			btnPanel.add(buttons.get(i));
		}

		// splitPane to split canvas and btnPanel
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(btnPanel);
		splitPane.setRightComponent(canvas);
		splitPane.setResizeWeight(0.05);

		// add components to frame
		this.add(splitPane, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
	}

	
	public static void resetButtons(){
		for(Button btn: buttons){
			btn.resetButton();
		}
	}
	
	
	
	public void setWindowProperty() {
		setTitle("UML Editor");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);
		setVisible(true);

	}
	


}