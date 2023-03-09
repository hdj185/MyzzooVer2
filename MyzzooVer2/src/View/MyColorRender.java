package View;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class MyColorRender extends DefaultTableCellRenderer {
	int tab;	//tab이 1이면 종목 순위, 2이면 보유 주식, 0이면 기타
	
	MyColorRender() {
		this.tab = 0;
	}
	MyColorRender(int tab) {
		this.tab = tab;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		String s = table.getModel().getValueAt(row, column).toString();
		int flag = 0; //plus면 flag > 0, minus면 flag < 0, 그외 0 
		
		/* 주식 리스트 판별 */
		if(tab == 1 && column == 1) {
			String s2 = table.getModel().getValueAt(row, column + 1).toString();
			int num1 = Integer.parseInt(s.replace(",", ""));	//현재가 int형 변환
			int num2 = Integer.parseInt(s2.replace(",", ""));	//전일가 int형 변환
			
			if (column == 1 && num1 > num2)
				flag = 1;
			else if (column == 1 && num1 < num2)
				flag = -1;
			
			
		} else if(tab == 2) { /* 보유 주식 판별 */
			//num에서 기호 모두 빼기
			double num = 0;
			
			if(column == 1)
				num = Double.parseDouble(s.replace(",", ""));
			else if(column == 2)
				num = Double.parseDouble(s.replace("%", ""));
			
			//평가손익 판별
			if(num > 0) flag = 1;
			else if(num < 0) flag = -1;
		} else if (tab == 0) {
			double num = 0;
			
			if(column == 2)
				num = Double.parseDouble(s.replace("%", ""));
			else if(column == 3)
				num = Double.parseDouble(s.replace(",", ""));
			
			if(num > 0) flag = 1;
			else if(num < 0) flag = -1;
		}
		
		//plus면 빨강, minus면 blue, 그외 검정
		if (flag > 0)		comp.setForeground(Color.red);
		else if (flag < 0)	comp.setForeground(Color.blue);			
		else				comp.setForeground(Color.black);
		
		//가운데 정렬
		setHorizontalAlignment(JLabel.CENTER);
		
		return comp;
	}
}
