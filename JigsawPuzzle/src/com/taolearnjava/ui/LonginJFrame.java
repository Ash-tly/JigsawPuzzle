package com.taolearnjava.ui;

import javax.swing.*;

//	创建游戏登录窗口
public class LonginJFrame extends JFrame {

	public LonginJFrame(){
		//	创建窗口
		this.setSize(488,430);
		//	设置窗口标题
		this.setTitle("单机版拼图 - 登录");
		//	该窗口居中显示
		this.setLocationRelativeTo(null);
		//	设置窗口退出时虚拟机自动停止
		this.setDefaultCloseOperation(3);
		//	程序运行时该窗口出现<建议放在最后进行书写>
		this.setVisible(true);
	}
}
