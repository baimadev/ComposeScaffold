package com.holderzone.library.composescaffold.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.holderzone.library.composescaffold.util.ext.wdp

@OptIn(ExperimentalFoundationApi::class)
internal class LazyGridScopeImpl : LazyGridScope {
    private val intervals = IntervalList<LazyItemScope.(Int) -> (@Composable () -> Unit)>()

    val totalSize get() = intervals.totalSize

    fun contentFor(index: Int, scope: LazyItemScope): @Composable () -> Unit {
        val interval = intervals.intervalForIndex(index)
        val localIntervalIndex = index - interval.startIndex

        return interval.content(scope, localIntervalIndex)
    }

    override fun item(content: @Composable LazyItemScope.() -> Unit) {
        intervals.add(1) { @Composable { content() } }
    }

    override fun items(count: Int, itemContent: @Composable LazyItemScope.(index: Int) -> Unit) {
        intervals.add(count) {
            @Composable { itemContent(it) }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalGridList(
    nColumns: Int,
    nRows: Int,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    content: LazyGridScope.() -> Unit
) {
    val scope = LazyGridScopeImpl()
    scope.apply(content)

    val rows = (scope.totalSize + nColumns - 1) / nColumns
    LazyRow(
        modifier = modifier.fillMaxSize(),
        state = state,
    ) {
        items(rows) { rowIndex ->
            Column {
                for (columnIndex in 0 until nRows) {
                    val itemIndex = rowIndex * nColumns + columnIndex
                    if (itemIndex < scope.totalSize) {
                        Box(
                            modifier = Modifier.weight(1f, fill = true),
                            propagateMinConstraints = true
                        ) {
                            Row() {
                                scope.contentFor(itemIndex, this@items).invoke()

                                Spacer(modifier = Modifier.width(33.wdp))
                            }
                        }

                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}


internal class IntervalHolder<T>(
    val startIndex: Int,
    val size: Int,
    val content: T
)

internal class IntervalList<T> {
    private val intervals = mutableListOf<IntervalHolder<T>>()
    internal var totalSize = 0
        private set

    fun add(size: Int, content: T) {
        if (size == 0) {
            return
        }

        val interval = IntervalHolder(
            startIndex = totalSize,
            size = size,
            content = content
        )
        totalSize += size
        intervals.add(interval)
    }

    fun intervalForIndex(index: Int) =
        if (index < 0 || index >= totalSize) {
            throw IndexOutOfBoundsException("Index $index, size $totalSize")
        } else {
            intervals[findIndexOfHighestValueLesserThan(intervals, index)]
        }

    /**
     * Finds the index of the [list] which contains the highest value of [IntervalHolder.startIndex]
     * that is less than or equal to the given [value].
     */
    private fun findIndexOfHighestValueLesserThan(list: List<IntervalHolder<T>>, value: Int): Int {
        var left = 0
        var right = list.lastIndex

        while (left < right) {
            val middle = (left + right) / 2

            val middleValue = list[middle].startIndex
            if (middleValue == value) {
                return middle
            }

            if (middleValue < value) {
                left = middle + 1

                // Verify that the left will not be bigger than our value
                if (value < list[left].startIndex) {
                    return middle
                }
            } else {
                right = middle - 1
            }
        }

        return left
    }
}
