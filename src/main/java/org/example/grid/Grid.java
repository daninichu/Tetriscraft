package org.example.grid;
public class Grid<T> {
    protected final Object[][] grid;

    public Grid(int rows, int cols) {
        if (rows < 1 || cols < 1)
            throw new IllegalArgumentException("rows and cols must be greater than 0");
        grid = new Object[rows][cols];
    }

    public int rows() {
        return grid.length;
    }

    public int cols() {
        return grid[0].length;
    }

    @SuppressWarnings("unchecked")
    public T get(int row, int col) {
        return (T) grid[row][col];
    }

    public void set(int row, int col, T value) {
        grid[row][col] = value;
    }

    public boolean withinBounds(int row, int col) {
        return row >= 0 && row < rows() && col >= 0 && col < cols();
    }
}