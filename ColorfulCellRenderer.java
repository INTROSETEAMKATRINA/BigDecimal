import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorfulCellRenderer extends DefaultTableCellRenderer {

	    private Color bgColor;
	    private Color fgColor;
	    private ArrayList<Integer> colorfulColumns;
	    
	    public ColorfulCellRenderer(Color bg, Color fg, ArrayList<Integer> cc) {
	        bgColor = bg;
	        fgColor = fg;
	        colorfulColumns = cc;
	        this.setHorizontalAlignment(JLabel.CENTER);
	    }
	    
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
	        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
	        
	        if(isSelected) {
	        	if(colorfulColumns.contains(col)) {
	        		label.setBackground(bgColor.darker());
	        		label.setForeground(Color.WHITE);
	        	}
	        	else {
	        		label.setBackground(new Color(154,217,255,100));
	        		label.setForeground(Color.BLACK);
	        	}
		    } else {
		    	label.setBackground(bgColor);
		    	label.setForeground(fgColor);
		    }
        	
	        return label;
	   }
}