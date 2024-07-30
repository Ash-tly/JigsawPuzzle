package Text;

import java.util.Random;

//	打乱图片顺序底层逻辑
public class QRarr {
	/*
	 * 定义一个一位数组，1~15
	 * 将他们的顺序打乱后添加到二维数组中
	 */
	public static void main(String[] args) {
		//	定义数组
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		int[][] arr2 = new int[4][4];

		//	创建随机数
		Random r = new Random();

		//	遍历数组
		for (int i = 0; i < arr.length; i++) {
			//	使随机数的取值在一维数组的长度之间
			int index = r.nextInt(arr.length);
			//	将随机索引 index 处的元素值存储在临时变量 temp 中
			int temp = arr[index];
			//	将当前循环索引 i 处的元素值移动到随机索引 index 处
			arr[index] = arr[i];
			//	将之前存储在 temp 中的原始随机索引 index 处的元素值放回到当前循环索引 i 处
			arr[i] = temp;
		}

		//	遍历打乱后的数组
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		//	将一位数组加到二维数组中
		for (int i = 0; i < arr.length; i++) {
			/*
			* 当 i = 0 时，i / 4 = 0，i % 4 = 0， 即 将 arr[i] 添加到 arr2[0][0] 位置
			*/
			arr2[i / 4][i % 4] = arr[i];
		}
	}
}
