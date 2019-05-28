# Custom-Control-Group

Custom-Control-Group is an android library, for radio-group and radio-buttons. It's rendering based on layout direction (LTR/RTL). 

![image](https://user-images.githubusercontent.com/50660616/57979056-939c7000-7a31-11e9-852d-9cbeec8d5a72.jpg)


## How to use:
#### 1. In your build.gradle (Project) add:

```bash
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
#### 2. In your build.gradle (app) add dependency:
```bash
	dependencies {
	        implementation 'com.github.salmannisar2151:Custom-Control-Group:v1.5'
	}
```
## Customization include

```python
1. Border radius.
2. Selected radio-button background color.
3. Deselected radio-button border color.
4. Selected radio-button text color.
5. Deselected radio-button text color.
6. Set radio-button border stroke.
```

## Sample code
### 1. In XML, you need to add RadioButtonStyle:
* ####  if you want to make 'app:custom_scrollable = true ' you have to place CustomRadioGroup in HorizontalScrollView.
```python
<com.salman.segmentedradiogroup.CustomRadioGroup
        android:id="@+id/rg_custom_group"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:custom_backgroundColor="@color/colorPrimary"
        app:custom_borderColor="@color/colorPrimary"
        app:custom_borderWidth="5"
        app:custom_cornerRadius="10"
        app:custom_radioButtonPadding="@dimen/test_radio_button_padding"
        app:custom_scrollable="false"
        app:custom_selectedTextColor="@color/white"
        app:custom_unselectedTextColor="@color/colorPrimary">

        <RadioButton
            style="@style/RadioButtonStyle"
            android:text="Radio Button 1" />

        <RadioButton
            style="@style/RadioButtonStyle"
            android:text="Radio Button 2" />

        <RadioButton
            style="@style/RadioButtonStyle"
            android:text="Radio Button 3" />
    </com.salman.segmentedradiogroup.CustomRadioGroup>
```
### 2. In Java:
* #### Add Radio Button.
```python
customRadioGroup.addRadioButton("RadioButton1");
```
* #### Set selected radio button background color:
```python
customRadioGroup.setsBackgroundColor(getResources().getColor(R.color.colorPrimary));
```
* #### Set deselected radio button border color:
```python
customRadioGroup.setuBorderColor(getResources().getColor(R.color.colorPrimary));
```
* #### Set selected radio button text color:
```python
customRadioGroup.setsTextColor(R.color.white);
```
* #### Set deselected radio button text color:
```python
customRadioGroup.setuTextColor(R.color.colorPrimary);
```
* #### Set radio button corner radius:
```python
customRadioGroup.setCornerRadius(5);
```
* #### Set radio button border stroke:
```python
customRadioGroup.setStrokeWidth(3);
```
* #### Set radio button padding:
```python
customRadioGroup.setPadding(getResources().getDimensionPixelSize(R.dimen.margin_20));
```



## License
The MIT License (MIT)

Copyright (c) 2019 Salman Nisar

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
