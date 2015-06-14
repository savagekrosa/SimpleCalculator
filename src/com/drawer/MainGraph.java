package com.drawer;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.type.FunctionDrawingData;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.ui.InteractivePanel;

public class MainGraph extends JFrame {

	
	public MainGraph(FunctionDrawingData funcData) {
		super();
		
		@SuppressWarnings("unchecked")
		DataTable table = new DataTable(Double.class, Double.class);
		for (int i = 0; i < funcData.getSteps(); i++) {
			double x = funcData.getStartX() + (funcData.getEndX() - funcData.getStartX()) * i/(funcData.getSteps()-1);
			table.add(x,funcData.getY(x));
		}
		
		DataSeries series = new DataSeries(table, 0, 1);
		XYPlot plot = new XYPlot(series);
		
        getContentPane().add(new InteractivePanel(plot), BorderLayout.CENTER);
        setMinimumSize(getContentPane().getMinimumSize());
        setSize(600, 400);
	}
}
