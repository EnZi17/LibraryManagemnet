package view;

import java.awt.FlowLayout;
import java.util.stream.IntStream;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DatePanel extends JPanel{
	
	public JComboBox<Integer> dayComboBox;
	public JComboBox<String> monthComboBox;
	public JComboBox<Integer> yearComboBox;

	public DatePanel() {
		
		setLayout(new FlowLayout());
        dayComboBox = new JComboBox<>();
        IntStream.rangeClosed(1, 31).forEach(dayComboBox::addItem);
         monthComboBox = new JComboBox<>(new String[]{
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        });
         yearComboBox = new JComboBox<>();
        IntStream.rangeClosed(1, 2100).forEach(yearComboBox::addItem);

        add(dayComboBox);
        add(monthComboBox);
        add(yearComboBox);
	}
	
}
