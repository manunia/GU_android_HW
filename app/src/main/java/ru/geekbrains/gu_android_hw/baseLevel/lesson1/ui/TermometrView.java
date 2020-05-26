package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import ru.geekbrains.gu_android_hw.R;

public class TermometrView extends View {

    private int termometrColor = Color.GRAY;
    private int levelColor = Color.RED;
    private RectF termometrRect = new RectF();
    private Rect levelRect = new Rect();
    private RectF headRect = new RectF();
    private Paint termometrPaint;
    private Paint levelPaint;
    private int view_width = 0;
    private int height = 0;

    private int level = 100;

    // Константы
    // Отступ элементов
    private final static int padding = 50;
    // Скругление углов батареи
    private final static int round = 45;
    // Ширина головы батареи
    private final static int headWidth = 40;


    public TermometrView(Context context) {
        super(context);
        init();
    }

    public TermometrView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
        init();
    }

    public TermometrView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context,attrs);
        init();
    }

    public TermometrView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context,attrs);
        init();
    }

    private void init() {
        termometrPaint = new Paint();
        termometrPaint.setColor(termometrColor);
        termometrPaint.setStyle(Paint.Style.FILL);
        levelPaint = new Paint();
        levelPaint.setColor(levelColor);
        levelPaint.setStyle(Paint.Style.FILL);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TermometrView,0,0);
        termometrColor = typedArray.getColor(R.styleable.TermometrView_termometr_color,Color.GRAY);
        levelColor = typedArray.getColor(R.styleable.TermometrView_level_color,Color.RED);
        level = typedArray.getInteger(R.styleable.TermometrView_level,50);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        view_width = w - getPaddingLeft() - getPaddingRight();
        height = h - getPaddingTop() - getPaddingBottom();

        termometrRect.set(padding,padding,
                view_width - padding - headWidth,
                height - padding);
        headRect.set((view_width - 5) - padding - headWidth,
                2 * padding,
                view_width + padding,
                h - 2 * padding);
        levelRect.set(2 * padding,
                2 * padding,
                (int) ((view_width - 2 * padding - headWidth) * ((double) level / (double) 100)),
                h - 2 * padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(termometrRect,round,round,termometrPaint);
        canvas.drawRect(levelRect,levelPaint);
        canvas.drawRoundRect(headRect,round,round,termometrPaint);
        //шкала градусника
        canvas.drawLine(2*padding,height/2, view_width - padding,height/2,new Paint(Color.BLACK));
        canvas.drawLine(view_width /2,2*padding, view_width /2,height-2*padding,new Paint(Color.BLACK));

    }

    public void setLevel(int level) {
        this.level = level+50;
        invalidate();
    }

    public void setView_width(int view_width) {
        this.view_width = view_width;
    }

    public int getView_width() {
        return view_width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
