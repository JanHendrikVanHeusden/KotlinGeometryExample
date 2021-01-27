package nl.jhvh.kotlin.basics

import kotlin.random.Random

interface SudokuSolvable {
    val grid: Grid
    fun solve()
    fun giveHint()
    fun checkSolution(): Boolean
}

interface Grid {
    val size: Int
    fun fixCell(value: Int, x: Int, y: Int)
    fun setCell(value: Int, x: Int, y: Int)
}

class SudokuSolver(override val grid: Grid) : SudokuSolvable {
    private fun randomGridVal() = Random.nextInt(1, grid.size + 1)

    override fun solve() = println("Solved!")
    override fun giveHint() = println("Hint: point (${randomGridVal()}, ${randomGridVal()}) is ${randomGridVal()}")
    override fun checkSolution() = Random.nextBoolean().also { println(if (it) "Correct!" else "Wrong...") }
}

class PuzzleGrid(override val size: Int) : Grid {
    override fun fixCell(value: Int, x: Int, y: Int) = println("Cell ($x, $y) has a fixed value of $value")
    override fun setCell(value: Int, x: Int, y: Int) = println("Cell ($x, $y) is set to $value")
}

class SudokuPuzzle(grid: Grid) :
    Grid by PuzzleGrid(grid.size),
    SudokuSolvable by SudokuSolver(grid)

fun main() {
    val puzzle = SudokuPuzzle(PuzzleGrid(9))
    puzzle.fixCell(7, x = 2, y = 4)
    puzzle.giveHint()
    puzzle.solve()
    puzzle.checkSolution()
}