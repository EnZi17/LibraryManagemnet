package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AppController;

public class BorrowerPanel extends JPanel{
	AppController appController ;
	public BorrowerPanel(AppController appController) {
		 this.appController= appController;
		 JButton button = new JButton("Hihi2");
		 button.addActionListener(appController);
		 add(button);
	}
}
