package com.jmsooo.queue;

import java.util.Scanner;

/**
 * @author jmsooo
 * @description 环形数组队列
 * @create 2021/11/5
 */

public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        //创建一个队列
        CircleArray circleArray = new CircleArray(4);//实际可以存储的数据为3个 有一个约定空间
        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列中取出数据");
            System.out.println("h(head): 显示队列头部数据");
            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出！");
                    break;
                case 'g':
                    try {
                        int res = circleArray.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArray.headQueue();
                        System.out.printf("队列头的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }

}

class CircleArray {
    private int maxSize;    //表示数组的最大容量
    private int front;      //队列头：指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素 front初始值为0
    private int rear;       //队列尾：指向队列的最后一个元素的后一个位置 空出来一个空间作为约定 rear初始值为0
    private int[] arr;      //存放数据

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断是否为已满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据！");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将 rear 后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列为空，不能取出数据！");
        }
        //这里需要分析出 front 是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        int value = arr[front];
        //2.把front后移，考虑取模
        front = (front + 1) % maxSize;
        //3.把临时保存的变量返回
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空，不能显示数据！");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + sizeQueue(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列的头元素，注意不是取出数据
    public int headQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能显示数据！");
        }
        return arr[front];
    }


    //求出当前队列有效数据的个数
    public int sizeQueue() {
        return (rear + maxSize - front) % maxSize;
    }
}
