package view;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.checkerframework.common.returnsreceiver.qual.This;

import controller.AppController;


public class AppView {

    public JFrame frame;
    public BookPanel bookPanel ;
    public BorrowerPanel borrowerPanel;

    public AppView() {
    	AppController appController = new AppController(this);
    	
    	
        frame = new JFrame();
        frame.setBounds(100, 100, 850, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JTabbedPane tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane);
        
        this.bookPanel = new BookPanel(appController);
        tabbedPane.addTab("Book", this.bookPanel);
        
        
        JPanel borrowerPanel = new JPanel();
        tabbedPane.addTab("Borrower", new BorrowerPanel(appController));
        
        appController.updateData();
        this.frame.setVisible(true);
        
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	public void showIdExitsted() {
		JOptionPane.showMessageDialog(null,"ID has already existed!","Error!",JOptionPane.ERROR_MESSAGE );
	}

	public void showInvalidPrice() {
		JOptionPane.showMessageDialog(null,"Invalid Price!","Error!",JOptionPane.ERROR_MESSAGE);
	}

	public void showEmpty() {
		JOptionPane.showMessageDialog(null, "Empty Field!","Error!",JOptionPane.ERROR_MESSAGE);
	}

	public void showNotSelected() {
		JOptionPane.showMessageDialog(null, "Please Select Book!","Error!",JOptionPane.ERROR_MESSAGE);
	}

	public int showDeleteBookConform() {
		int res = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this item?", "Confirm Deletion", JOptionPane.YES_NO_OPTION,  JOptionPane.WARNING_MESSAGE);
		return (res==JOptionPane.YES_OPTION)?1:0;
	}
    
}
