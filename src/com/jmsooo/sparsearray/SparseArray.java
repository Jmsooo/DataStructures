package com.jmsooo.sparsearray;

/**
 * @author jmsooo
 * @description 稀疏数组
 * @create 2021/11/4
 */

public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        //0：表示没有棋子  1：表示黑子  2：表示蓝子
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][6] = 1;
        chessArr[6][8] = 2;
        System.out.println("原始的二维数组：");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d  ",data);
            }
            System.out.println();
        }

        //将二维数组 转为 稀疏数组
        //1.先遍历二维数组 得到非0数据的格式
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++){
            for (int j = 0; j < chessArr.length; j++){
                if(chessArr[i][j] != 0){
                    sum++;
                }
            }
        }

        //2.创建对于的稀疏数组
        int sparseArr[][] = new int[sum +1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr.length;
        sparseArr[0][2] = sum;
        //遍历二维数组 给非0的值存放到 sparseArr 中
        int count = 0;//记录第几个非0数据
        for (int i = 0; i < chessArr.length; i++){
            for (int j = 0; j < chessArr.length; j++){
                if(chessArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        System.out.println();
        System.out.println("二维数组 ==> 稀疏数组：");
        for (int i = 0; i < sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        System.out.println();
        System.out.println("稀疏数组 ==> 二维数组:");
        int sparseArrToChess[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++){
            sparseArrToChess[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        for (int[] row : sparseArrToChess) {
            for (int data : row) {
                System.out.printf("%d  ",data);
            }
            System.out.println();
        }
    }

}
