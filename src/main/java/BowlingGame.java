public class BowlingGame {

   public int getBowlingScore(String bowlingCode) {
		int count = 0;
		int len = bowlingCode.length();
		int[][] corrent = new int[12][4];
		int pos = 0;
		int i = 0;
		System.out.println(bowlingCode.charAt(len-1));
		if(bowlingCode.charAt(len-1)!='|'){
			if(bowlingCode.charAt(len-2)!='|'){
				for(int n=0;n<2;n++){
				if (bowlingCode.charAt(n+len-2) == 'X'){
					corrent[10][n] = 10;
				}
				else if(bowlingCode.charAt(n+len-2) == '-')
					corrent[10][n] =0;
				else
					corrent[10][n] = bowlingCode.charAt(n+len-2) - '0';
				}
			}
			else{
				if (bowlingCode.charAt(len-1) == 'X'){
					corrent[10][0] = 10;
				}
				else if(bowlingCode.charAt(len-1) == '-')
					corrent[10][0] =0;
				else
					corrent[10][0] = bowlingCode.charAt(len-1) - '0';
			}
			
		}
		while (pos < len && i < 10) {
			if (bowlingCode.charAt(pos) == 'X') {
				corrent[i][0] = 10;
				pos++;
			} else if (bowlingCode.charAt(pos) == '|') {
				i++;
				pos++;
				continue;
			} else {
				for (int k = 0; k < 2; k++) {
					if (bowlingCode.charAt(pos) == '-') {
						corrent[i][k] = 0;
						pos++;
					}
					if (bowlingCode.charAt(pos) == '/') {
						corrent[i][k] = 10 - corrent[i][k - 1];
						pos++;
					} else {
						corrent[i][k] = bowlingCode.charAt(pos) - '0';
						pos++;
					}
				}
			}
		
		}
		
			for (int i1 = 0; i1 < corrent.length - 1; i1++) {// 前10轮
				if (corrent[i1][0] == 10 && i1 != 9) {
					if (corrent[i1 + 1][0] == 10 && i1 < 9) { // 前9轮，一次10分
																// 就记后面2投球的分数
						corrent[i1][2] = corrent[i1][0] + corrent[i1 + 1][0]
								+ corrent[i1 + 2][0];
					} else if (i1 == 9) {// 第10轮10分
						// 记第10轮2次的分数
						corrent[i1][2] = corrent[i1][0] + corrent[i1 + 1][0]
								+ corrent[i1 + 1][1];
					}
				} else {
					if (corrent[i1][0] + corrent[i1][1] == 10 && i1 < 9) {// 前9轮
																			// 补中10分
																			// 加上下轮第一次的分数
						corrent[i1][2] = corrent[i1][0] + corrent[i1][1]
								+ corrent[i1 + 1][0];
					} else {
						corrent[i1][2] = corrent[i1][0] + corrent[i1][1];// 2次不足10分

					}
				}
			}
			// 求累计积分
			int sum = corrent[0][2];
			for (int i1 = 1; i1 < corrent.length; i1++) {
				sum += corrent[i1][2];
			}
			return sum;
		}


}
