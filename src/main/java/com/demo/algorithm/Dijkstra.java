package com.demo.algorithm;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法，单源最短路径算法
 * 贪心
 * 有权边
 */
public class Dijkstra {
    int n; // 有n个点
    int[] dis;  //辅助数组，代表当前找到的起始点到各个点的最短距离。
    boolean[] mark; //计算过的点打上标记
    int[][] graphs; //[1][2] 表示点1到点2的路径长度，-1代表没有路

    public Dijkstra(int n, int[] dis, boolean[] mark, int[][] graphs) {
        this.n = n;
        this.dis = dis;
        this.mark = mark;
        this.graphs = graphs;
    }

    /**
     * 计算某个点到图中各个点的最短路径
     * @param x
     */
    public void shortestPath(int x){

        for(int i=0;i<n;i++){
            dis[i] = graphs[x][i];
        }
        mark[x] = true;
        //遍历每一个点
        int count = 1;
        while (count < n){
            count++;
            //取没遍历过的最短路径对应的下标
            int min = Integer.MAX_VALUE;
            int loc = -1;
            for(int j=0;j<n;j++){
                if(!mark[j] && dis[j] < min && dis[j] != -1){
                    min = dis[j];
                    loc = j;
                }
            }
            if(loc == -1) break;
            mark[loc] = true; //标记这个点
            for(int k = 0;k<n;k++){
                if(graphs[loc][k] != -1 && (dis[k] == -1 || dis[k] > dis[loc]+graphs[loc][k])){
                    dis[k] = dis[loc] + graphs[loc][k];
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] graphs = {{0,-1,10,-1,30,100},{-1,0,5,-1,-1,-1},{-1,-1,0,50,-1,-1},{-1,-1,-1,0,-1,10},{-1,-1,-1,20,0,60},{-1,-1,-1,-1,-1,0}};
        int n=6;
        int[] dis = new int[n];
        for(int d:dis){
            d = -1;
        }
        boolean[] mark = new boolean[n];
        Dijkstra dijkstra = new Dijkstra(n,dis,mark,graphs);

        dijkstra.shortestPath(0);
        System.out.println(Arrays.toString(dis));
    }
}
