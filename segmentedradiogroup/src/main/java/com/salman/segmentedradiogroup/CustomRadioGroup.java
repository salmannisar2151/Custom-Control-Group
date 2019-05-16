package com.salman.segmentedradiogroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CustomRadioGroup extends RadioGroup {
    private final int SINGLE_ITEM_VALUE = 0;
    private final int START_ITEM_VALUE = 1;
    private final int MID_ITEM_VALUE = 2;
    private final int END_ITEM_VALUE = 3;

    private int sBackgroundColor; // selected
    private int uBorderColor; // unselected
    private Context context;
    private ColorStateList colorStateList;
    private int strokeWidth;
    private int padding;
    private int cornerRadius;
    private int[][] states;
    private int[] colors;
    private boolean isScrollable;

    private StateListDrawable singleItemSelector, startItemSelector, midItemSelector, endItemSelector;

    private void init() {
        setOrientation(HORIZONTAL);
        setDefaultValues();
    }

    private void setAllSelectors() {

        singleItemSelector = createSelector(createItemSingle(true, SINGLE_ITEM_VALUE),
                createItemSingle(false, SINGLE_ITEM_VALUE));

        startItemSelector = createSelector(createItemSingle(true, START_ITEM_VALUE),
                createItemSingle(false, START_ITEM_VALUE));

        midItemSelector = createSelector(createItemSingle(true, MID_ITEM_VALUE),
                createItemSingle(false, MID_ITEM_VALUE));

        endItemSelector = createSelector(createItemSingle(true, END_ITEM_VALUE),
                createItemSingle(false, END_ITEM_VALUE));
    }

    private void setDefaultValues() {
        sBackgroundColor = getResources().getColor(R.color.colorPrimary);
        uBorderColor = getResources().getColor(R.color.colorPrimary);
        strokeWidth = 5;
        cornerRadius = 5;
        padding = getResources().getDimensionPixelSize(R.dimen.radio_button_padding);
        isScrollable = false;
        if (colors == null)
            colors = new int[]{
                    getResources().getColor(R.color.white),
                    getResources().getColor(R.color.colorPrimary)
            };
        if (states == null)
            states = new int[][]{
                    new int[]{android.R.attr.state_checked},
                    new int[]{-android.R.attr.state_checked}
            };
        colorStateList = new ColorStateList(states, colors);
    }

    private void updateLastRBAdded(RadioButton radioButton, StateListDrawable currentSelector) {
        radioButton.setTextColor(colorStateList);
        radioButton.setBackground(currentSelector);
    }

    private RadioButton addRadioButton(String title, StateListDrawable background, ColorStateList color) {
        RadioButton radioButton = (RadioButton) LayoutInflater.from(context).inflate(R.layout.radio_button_item, null, false);
        LinearLayout.LayoutParams p;
        if (isScrollable) {
            p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        } else {
            p = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
            p.weight = 1;
        }
        radioButton.setLayoutParams(p);
        radioButton.setGravity(Gravity.CENTER);
        radioButton.setBackground(background);
        radioButton.setTextColor(color);
        radioButton.setPadding(padding, padding, padding, padding);
        radioButton.setText(title);
        addView(radioButton);
        return radioButton;
    }

    private StateListDrawable createSelector(GradientDrawable selected, GradientDrawable unselected) {
        StateListDrawable res = new StateListDrawable();

        res.addState(new int[]{android.R.attr.state_checked}, selected);
        res.addState(new int[]{}, unselected);

        return res;
    }

    @SuppressLint("NewApi")
    private GradientDrawable createItemSingle(boolean selected, int posValue) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);

        if (posValue == SINGLE_ITEM_VALUE)
            shape.setCornerRadii(new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius});
        else if (posValue == MID_ITEM_VALUE)
            shape.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, 0, 0});

        if (getLayoutDirection() == LAYOUT_DIRECTION_LTR) {
            if (posValue == START_ITEM_VALUE)
                shape.setCornerRadii(new float[]{cornerRadius, cornerRadius, 0, 0, 0, 0, cornerRadius, cornerRadius});
            else if (posValue == END_ITEM_VALUE)
                shape.setCornerRadii(new float[]{0, 0, cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0, 0});
        } else {
            if (posValue == START_ITEM_VALUE)
                shape.setCornerRadii(new float[]{0, 0, cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0, 0});
            else if (posValue == END_ITEM_VALUE)
                shape.setCornerRadii(new float[]{cornerRadius, cornerRadius, 0, 0, 0, 0, cornerRadius, cornerRadius});
        }

        if (selected)
            shape.setColor(sBackgroundColor);
        else {
            shape.setColor(getResources().getColor(R.color.transparent));
            shape.setStroke(strokeWidth, uBorderColor);
        }
        return shape;
    }

    public void setsBackgroundColor(int sBackgroundColor) {
        this.sBackgroundColor = sBackgroundColor;
        updateAllRadioButtonsBackground();
    }

    public void setuBorderColor(int uBorderColor) {
        this.uBorderColor = uBorderColor;
        updateAllRadioButtonsBackground();
    }

    public void updateAllRadioButtonsBackground() {

        if (getChildCount() > 0) {
            if (getChildCount() == 1) {
                RadioButton radioButton = (RadioButton) getChildAt(0);
                radioButton.setPadding(padding, padding, padding, padding);
                setAllSelectors();
                updateLastRBAdded(radioButton, singleItemSelector);
            } else {
                for (int i = 0; i < getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) getChildAt(i);
                    radioButton.setPadding(padding, padding, padding, padding);
                    setAllSelectors();
                    if (i == 0)
                        updateLastRBAdded(radioButton, startItemSelector);
                    else if (i < getChildCount() - 1)
                        updateLastRBAdded(radioButton, midItemSelector);
                    else
                        updateLastRBAdded(radioButton, endItemSelector);

                }
            }
        }
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomRadioGroup, 0, 0);
        sBackgroundColor = typedArray.getColor(R.styleable.CustomRadioGroup_custom_backgroundColor, sBackgroundColor);
        uBorderColor = typedArray.getColor(R.styleable.CustomRadioGroup_custom_borderColor, uBorderColor);
        strokeWidth = typedArray.getInt(R.styleable.CustomRadioGroup_custom_borderWidth, strokeWidth);
        colors[0] = typedArray.getColor(R.styleable.CustomRadioGroup_custom_selectedTextColor, colors[0]);
        colors[1] = typedArray.getColor(R.styleable.CustomRadioGroup_custom_unselectedTextColor, colors[1]);
        cornerRadius = typedArray.getInt(R.styleable.CustomRadioGroup_custom_cornerRadius, cornerRadius);
        padding = typedArray.getDimensionPixelSize(R.styleable.CustomRadioGroup_custom_radioButtonPadding, padding);
        isScrollable = typedArray.getBoolean(R.styleable.CustomRadioGroup_custom_scrollable, isScrollable);

        typedArray.recycle();

        setAllSelectors();

    }

    public RadioButton addRadioButton(String title, int id) {
        RadioButton radioButton = addRadioButton(title);
        radioButton.setId(id);
        return radioButton;
    }

    public RadioButton addRadioButton(String title) {
        setAllSelectors();
        if (!isScrollable)
            setWeightSum(getChildCount() + 1);
        if (getChildCount() == 0)
            return addRadioButton(title, singleItemSelector, colorStateList);
        else {
            if (getChildCount() == 1)
                updateLastRBAdded((RadioButton) getChildAt(getChildCount() - 1), startItemSelector);
            else
                updateLastRBAdded((RadioButton) getChildAt(getChildCount() - 1), midItemSelector);

            return addRadioButton(title, endItemSelector, colorStateList);
        }


    }

    public void setsTextColor(int sTextColor) {
        setAllSelectors();
        colors[0] = getResources().getColor(sTextColor);
        updateAllRadioButtonsBackground();
    }

    public void setuTextColor(int uTextColor) {
        setAllSelectors();
        colors[1] = getResources().getColor(uTextColor);
        updateAllRadioButtonsBackground();
    }

    public void removeTab() {
        removeViewAt(getChildCount() - 1);
        if (!isScrollable)
            setWeightSum(getWeightSum() - 1);
        setAllSelectors();
        if (getChildCount() > 0)
            if (getChildCount() == 1)
                updateLastRBAdded((RadioButton) getChildAt(getChildCount() - 1), singleItemSelector);
            else
                updateLastRBAdded((RadioButton) getChildAt(getChildCount() - 1), endItemSelector);
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        updateAllRadioButtonsBackground();
    }


    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        updateAllRadioButtonsBackground();
    }

    public void setChecked(int position) {
        if (position < getChildCount()) {
            ((RadioButton) getChildAt(position)).setChecked(true);
        }
    }

    public void removeRadioButtonAt(int position) {
        if (position < getChildCount()) {
            removeViewAt(position);
        }
    }
}
