package com.demo.algorithm;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 图
 * 广度优先遍历 BFS  broad first search
 * 深度优先遍历 DFS  depth first search
 */
public class GraphsTest {

    int n;     //地图的行
    int m;     //地图的列
    int dx;    //小美位置x
    int dy;    //小美位置y
    int[][] data;       //表示地图的邻接矩阵
    boolean[][] mark;   //标记数据是否走过该位置

    int[][] next = {{0,1},{1,0},{-1,0},{0,-1}}; //点的四个方向,简化if else这种判断

    public GraphsTest(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
        this.n = n;
        this.m = m;
        this.dx = dx;
        this.dy = dy;
        this.data = data;
        this.mark = mark;
    }

    /**
     * 问题，解救小美，小美在迷宫的dx,dy位置被困了，你从起点去救小美。中间有些障碍。其实就是判断是否有路径可以到达dx,dy
     * 广度遍历
     * x,y 代表你的位置 就是求（x,y） => （dx,dy） 能不能到
     * @param x
     * @param y
     */
    public void bfs(int x,int y){
        //结束条件
        if(x < 0 || x >= n || y < 0 || y >= m) return;
        if(x == dx && y == dy){
            System.out.println("小美和我在起点，不用找啊 =。=");
            return;
        }

        mark[x][y] = true;
        //先将起始位置放入队列
        Queue<Point> queue = new ArrayBlockingQueue<>(n*m);
        Point start = new Point();
        start.x = x;
        start.y = y;
        queue.add(start);

        //遍历队列里的点
        while (!queue.isEmpty()){
            Point point = queue.poll();
            for(int i=0;i<4;i++){
                int nextx = point.x + next[i][0];
                int nexty = point.y + next[i][1];
                if(nextx < 0 || nextx >= n || nexty < 0 || nexty >= m) continue;
                //找到了，返回
                if(nextx == dx && nexty == dy){
                    System.out.println("找到小美啦，哦耶~~~");
                    return;
                }else{
                    //没找到，如果这个位置没有障碍物，且没有走过，加入队列
                    if( !mark[nextx][nexty] && data[nextx][nexty] == 0){
                        mark[nextx][nexty] = true;
                        Point point1 = new Point();
                        point1.x = nextx;
                        point1.y = nexty;
                        queue.add(point1);
                    }
                }
            }
        }

        //队列都遍历空了,说明没找到
        System.out.println("找不到小美，5555~~~");
    }

    /**
     * 能找到小美的最少步数
     * @param x
     * @param y
     * @param step
     */
    String[] arr;
    int minStep = Integer.MAX_VALUE;
    public void dfs(int x,int y,int step){ //x,y表示当前位置，step表示走过的路径长度
        mark[x][y] = true;
        System.out.println(x+","+y+"  step="+step);
        if(x == dx && y==dy){
            minStep = step < minStep ? step:minStep;
            System.out.println("bingo~");
            System.out.println(Arrays.toString(arr));
            return;
        }
        for(int i=0;i<4;i++){
            int nextx = x+next[i][0];
            int nexty = y+next[i][1];
            if(nextx < 0 || nextx >= n || nexty < 0 || nexty >= m) continue;
            if(data[nextx][nexty] == 0 && !mark[nextx][nexty]){
                arr[step] = nextx+","+nexty;
                mark[nextx][nexty] = true;
                dfs(nextx,nexty,step+1);
                //回溯
                //遍历所有可走路径，那么一个点可能经过好多遍。一个路径走完，那么往回退的时候，要把那个点的标记清除
                mark[nextx][nexty] = false;
                arr[step] = null;
            }
        }

    }

    public static void main(String[] args) {
        int n = 5;
        int m = 4;
        int[][] data = {{0,0,1,0},{0,0,0,0},{0,0,1,0},{0,1,0,1},{0,0,0,1}};
        boolean[][] mark = new boolean[n][m];
        GraphsTest graphs = new GraphsTest(n,m,3,2,data,mark);
//        graphs.bfs(0,0);
        graphs.arr = new String[20];
        graphs.dfs(0,0,0);
        System.out.println("找到小美的最少路径长度是："+graphs.minStep);
    }

}

class Point{
    int x;
    int y;
}
