package com.taolearnjava.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//	创建游戏主界面
public class GameIJFrame extends JFrame implements KeyListener, ActionListener {
	//	将二维数组定义到成员变量中，便于其他方法调用
	int[][] data = new int[4][4];
	//	记录 0 在二维数组中的位置
	int x, y = 0;
	//	定义一个变量接收路径
	String path = "image\\animal\\animal3\\";
	//	定义一个正确的二维数组，用来判断是否正确
	int[][] win = {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 14, 15, 0},
	};

	//	定义步数
	int step = 0;

	//	创建JMenuItem对象
	JMenuItem ChangeJMenuItem = new JMenuItem("更换图片");
	JMenuItem ReplayJMenuItem = new JMenuItem("重新游戏");
	JMenuItem ReloginJMenuItem = new JMenuItem("重新登录");
	JMenuItem CloseJMenuItem = new JMenuItem("关闭游戏");
	JMenuItem AccountJMenuItem = new JMenuItem("公共号");

	// 定义空参构造的同时规定窗口设置
	public GameIJFrame() {
		//	初始化页面
		PageSettings();
		//  初始化菜单
		FoundMenu();
		//	初始化数据
		initData();
		//	初始化图片
		initImage();

		//	程序运行时该窗口出现<建议放在最后进行书写>
		this.setVisible(true);
	}

	//	初始化数据
	private void initData() {
		//	创建一个数组，用来存放图片序号
		int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		//	打乱数组中的数字顺序
		Random r = new Random();
		//	遍历数组，得到每一个元素，拿着每一个元素跟随机索引上的数据进行交换
		for (int i = 0; i < tempArr.length; i++) {
			//	获得随机索引
			int index = r.nextInt(tempArr.length);
			//	将拿到的数据跟随机索引上的数据进行更换
			int temp = tempArr[index];
			tempArr[index] = tempArr[i];
			tempArr[i] = temp;
		}

		//	将跟换后的数组添加到二维数组中
		for (int i = 0; i < tempArr.length; i++) {
			if (tempArr[i] == 0) {
				x = i / 4;
				y = i % 4;
			}
			data[i / 4][i % 4] = tempArr[i];
		}
	}

	//	初始化图片
	private void initImage() {
		//	清空原本已经出现的所有图片
		this.getContentPane().removeAll();

		//	当拼图完成时
		if (Victory()) {
			JLabel label = new JLabel(new ImageIcon("image\\win.png"));
			label.setBounds(203, 283, 197, 73);
			this.getContentPane().add(label);
		}

		JLabel stepNum = new JLabel("步数:" + step);
		stepNum.setBounds(50, 30, 100, 20);
		this.getContentPane().add(stepNum);

		//	外循环 --- 将内循环执行四次
		for (int i = 0; i < data.length; i++) {
			//	内循环 --- 在一行中添加4张图片
			for (int j = 0; j < data.length; j++) {
				int num = data[i][j];
				//	创建一个ImageIcon对象
				ImageIcon icon = new ImageIcon(path + num + ".jpg");
				//	创建一个JLabel对象
				JLabel JLabel = new JLabel(icon);
				//	指定图片位置
				JLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
				//	给图片添加边框
				//	添加凹陷边框
				JLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				//	将图片添加到窗口
				this.getContentPane().add(JLabel);
			}
		}

		//	添加背景图片
		JLabel JLabel = new JLabel(new ImageIcon("image\\background.png"));
		JLabel.setBounds(40, 40, 508, 560);
		this.getContentPane().add(JLabel);

		//	刷新界面
		this.getContentPane().repaint();
	}

	//	初始化菜单
	private void FoundMenu() {
		//	创建JMenuBar对象
		JMenuBar jb = new JMenuBar();
		//	创建JMenu对象
		JMenu featureJMenu = new JMenu("功能");
		JMenu aboutJMenu = new JMenu("关于我们");

		//	将JMenuItem对象加入JMenu
		featureJMenu.add(ChangeJMenuItem);
		featureJMenu.add(ReplayJMenuItem);
		featureJMenu.add(ReloginJMenuItem);
		featureJMenu.add(CloseJMenuItem);
		aboutJMenu.add(AccountJMenuItem);

		//	给JMenuItem对象绑定事件
		ChangeJMenuItem.addActionListener(this);
		CloseJMenuItem.addActionListener(this);
		ReloginJMenuItem.addActionListener(this);
		ReplayJMenuItem.addActionListener(this);
		AccountJMenuItem.addActionListener(this);

		//	将JMenu对象加入JMenuBar
		jb.add(featureJMenu);
		jb.add(aboutJMenu);

		//	将JMenuBar对象加入页面
		this.setJMenuBar(jb);
	}

	//	初始化页面
	private void PageSettings() {
		//	定义一个宽603，长680的窗口
		this.setSize(603, 680);
		//	设置窗口标题
		this.setTitle("单机版拼图  V1.0");
		//	设置窗口置顶
		this.setAlwaysOnTop(true);
		//	该窗口居中显示
		this.setLocationRelativeTo(null);
		//	设置窗口退出时虚拟机自动停止
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//	取消默认居中
		this.setLayout(null);
		//	设置当前页面键盘监听事件
		this.addKeyListener(this);
	}

	//	重写接口方法
	@Override
	public void keyTyped(KeyEvent e) {

	}

	//	键盘按键按住时
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//	设置A为显示完整图片
		if (key == 65) {
			//	删除图片
			this.getContentPane().removeAll();

			//	加载完整图片
			JLabel JLabel = new JLabel(new ImageIcon(path + "all.jpg"));
			JLabel.setBounds(83, 134, 420, 420);
			this.getContentPane().add(JLabel);

			//	加载背景图片
			JLabel JLabel1 = new JLabel(new ImageIcon("image\\background.png"));
			JLabel1.setBounds(40, 40, 508, 560);
			this.getContentPane().add(JLabel1);

			//	刷新界面
			this.getContentPane().repaint();
		}
	}

	//	键盘按键松开时
	@Override
	public void keyReleased(KeyEvent e) {
		//	当游戏胜利时，不再运行下面代码
		if (Victory()) {
			return;
		}
		//	监听方向键
		//	上 38；下 40；左 37；右 39
		int key = e.getKeyCode();
		if (key == 39) {
			if (y == 0) {
				return;
			}
			//	将空白方块下方的数字赋值给空白方块
			data[x][y] = data[x][y - 1];
			data[x][y - 1] = 0;
			y--;
			step += 1;
			//	加载图片
			initImage();
		} else if (key == 38) {
			if (x == 3) {
				return;
			}
			//	将空白方块下方的数字赋值给空白方块
			data[x][y] = data[x + 1][y];
			data[x + 1][y] = 0;
			x++;
			step += 1;
			//	加载图片
			initImage();
		} else if (key == 37) {
			if (y == 3) {
				return;
			}
			//	将空白方块下方的数字赋值给空白方块
			data[x][y] = data[x][y + 1];
			data[x][y + 1] = 0;
			y++;
			step += 1;
			//	加载图片
			initImage();
		} else if (key == 40) {
			if (x == 0) {
				return;
			}
			data[x][y] = data[x - 1][y];
			data[x - 1][y] = 0;
			x--;
			step += 1;
			//	加载图片
			initImage();
			//	按下快捷显示后松开还原
		} else if (key == 65) {
			initImage();
		}
	}

	//	判断是否胜利
	public boolean Victory() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[i][j] != win[i][j]) {
					//	只要一点不一样，直接结束
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//	获取当前点击的条目
		Object obj = e.getSource();
		if (obj == ChangeJMenuItem) {
			System.out.println("更换图片");
		}else if (obj == ReplayJMenuItem) {
			System.out.println("重新游戏");
		}else if (obj == ReloginJMenuItem) {
			System.out.println("重新登录");
		}else if (obj == CloseJMenuItem) {
			//	关闭虚拟机
			System.exit(0);
		}else if (obj == AccountJMenuItem) {
			JDialog jd = new JDialog();
			JLabel JLabel = new JLabel(new ImageIcon("image\\about.jpg"));
			JLabel.setBounds(0, 0, 258, 258);
			jd.getContentPane().add(JLabel);
			jd.setSize(344, 344);
			jd.setAlwaysOnTop(true);
			jd.setLocationRelativeTo(null);
			jd.setModal(true);
			jd.setVisible(true);
		}
	}
}
