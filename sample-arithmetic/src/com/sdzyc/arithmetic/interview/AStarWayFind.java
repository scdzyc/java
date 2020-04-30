package com.sdzyc.arithmetic.interview;

import java.util.ArrayList;
import java.util.List;

/**
 *  A 星寻路法
 * AStarWayFind class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/27 13:40
 */
public class AStarWayFind {

    public static final int[][] MAZE = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
    };

    public static Grid aStarSearch(Grid start, Grid end) {
        List<Grid> openList = new ArrayList<>();
        List<Grid> closeList = new ArrayList<>();

        // 把起点加入openList
        openList.add(start);
        // 主循环，每一轮检查1个当前格节点
        while(openList.size() > 0) {
            // 在openList中查找 F 值最小的节点，将其作为当前方格节点
            Grid currentGrid = findMinGrid(openList);
            // 将当前方格节点从openList中移除
            openList.remove(currentGrid);
            // 当前方格节点进入closeList
            closeList.add(currentGrid);
            // 找到所有临近节点
            List<Grid> neighbors = findNeighbors(currentGrid, openList, closeList);
            for(Grid grid : neighbors) {
                if(!openList.contains(grid)) {
                    // 邻近节点不在openList中，标记为‘父节点’、G、F、H，并放入openList
                    grid.initGrid(currentGrid,end);
                    openList.add(grid);
                }
            }
            // 如果终点在openList中，直接返回终点格子
            for(Grid grid : openList) {
                if((grid.x == end.x) && (grid.y == end.y)) {
                    return grid;
                }
            }
        }
        // openList 已经用尽，仍然没有找到终点，说明终点不可达
        return null;
    }

    private static Grid findMinGrid(List<Grid> openList) {
        Grid tempGrid  = openList.get(0);
        for (Grid grid : openList) {
            if(grid.f < tempGrid.f) {
                tempGrid = grid;
            }
        }
        return tempGrid;
    }

    private static List<Grid> findNeighbors(Grid grid, List<Grid> openList, List<Grid> closeList) {
        List<Grid> grids = new ArrayList<>();
        if(isValidGrid(grid.x, grid.y - 1, openList, closeList)) {
            grids.add(new Grid(grid.x, grid.y - 1));
        }
        if(isValidGrid(grid.x, grid.y + 1, openList, closeList)) {
            grids.add(new Grid(grid.x, grid.y + 1));
        }
        if(isValidGrid(grid.x - 1, grid.y, openList, closeList)) {
            grids.add(new Grid(grid.x - 1, grid.y));
        }
        if(isValidGrid(grid.x + 1, grid.y, openList, closeList)) {
            grids.add(new Grid(grid.x + 1,grid.y));
        }
        return grids;
    }

    private static boolean isValidGrid(int x, int y, List<Grid> openList, List<Grid> closeList) {
        // 是否超过边界
        if(x < 0 || x >= MAZE.length || y < 0 || y >= MAZE[0].length) {
            return false;
        }
        // 是否有障碍物
        if(MAZE[x][y] == 1) {
            return false;
        }
        // 是否已经在OpenList
        if(containGrid(openList, x, y)) {
            return false;
        }
        // 是否已经在CloseList
        if(containGrid(closeList, x, y)) {
            return false;
        }
        return true;
    }

    private static boolean containGrid(List<Grid> grids, int x, int y) {
        for(Grid grid : grids) {
            if((grid.x == x) && (grid.y == y)) {
                return true;
            }
        }
        return false;
    }

    static class Grid {
        public int x;
        public int y;
        public int f;
        public int g;
        public int h;
        public Grid parent;

        public Grid(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void initGrid(Grid parent, Grid end) {
            this.parent = parent;
            if(null != parent) {
                this.g = parent.g + 1;
            }else {
                this.g = 1;
            }
            this.h = Math.abs(this.x - end.x) + Math.abs(this.y - end.y);
            this.f = this.g + this.h;
        }
    }

    public static void main(String[] args) {
        // 设置起点和终点
        Grid start = new Grid(2, 1);
        Grid end = new Grid(2, 5);
        // 搜索迷宫的终点
        Grid resultGrid = aStarSearch(start, end);
        // 回溯迷宫路径
        List<Grid> path = new ArrayList<>();
        while(resultGrid != null) {
            path.add(new Grid(resultGrid.x, resultGrid.y));
            resultGrid = resultGrid.parent;
        }
        // 输出路径
        for (int i = 0; i < MAZE.length; i++) {
            for (int j = 0; j < MAZE[0].length ; j++) {
                if(containGrid(path, i, j)) {
                    System.out.print("*, ");
                }else {
                    System.out.print(MAZE[i][j] + ", ");
                }
            }
            System.out.println();
        }
    }
}
