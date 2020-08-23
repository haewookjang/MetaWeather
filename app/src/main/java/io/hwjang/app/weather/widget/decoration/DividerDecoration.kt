package io.hwjang.app.weather.widget.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.hwjang.app.weather.R
import io.hwjang.app.weather.util.dpToPx

class DividerDecoration(private val context: Context) :
    RecyclerView.ItemDecoration() {
    private var linePaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.divider)
        strokeWidth = 1F.dpToPx(context)
    }
    private val lineWidth = linePaint.strokeWidth

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        drawHorizontalLine(canvas, parent)
    }


    private fun drawHorizontalLine(canvas: Canvas, parent: RecyclerView) {
        (0 until parent.childCount).forEach { i ->
            val row = parent.getChildAt(i) as ViewGroup
            if (i == 0) {
                // header
                drawLine(row.left, row.top, row.right, row.top, canvas)
            }
            // bottom
            drawLine(row.left, row.bottom, row.right, row.bottom, canvas)
            drawVerticalLine(canvas, row)
        }
    }

    private fun drawVerticalLine(canvas: Canvas, row: ViewGroup) {
        val childCount = row.childCount
        (0 until childCount).forEach { index ->
            val cell = row.getChildAt(index)
            drawLine(
                cell.left.toFloat() + lineWidth,
                row.top.toFloat(),
                cell.left.toFloat() + lineWidth,
                row.bottom.toFloat(), canvas
            )
            if (index == childCount - 1) {
                drawLine(
                    row.right.toFloat() - lineWidth,
                    row.top.toFloat(),
                    row.right.toFloat() - lineWidth,
                    row.bottom.toFloat(), canvas
                )
            }
        }
    }

    private fun drawLine(left: Int, top: Int, right: Int, bottom: Int, canvas: Canvas) {
        drawLine(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), canvas)
    }

    private fun drawLine(left: Float, top: Float, right: Float, bottom: Float, canvas: Canvas) {
        canvas.drawLine(left, top, right, bottom, linePaint)
    }

}