
package com.example.calculator;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 数字
     */

    private Button btn_0;

    private Button btn_1;

    private Button btn_2;

    private Button btn_3;

    private Button btn_4;

    private Button btn_5;

    private Button btn_6;

    private Button btn_7;

    private Button btn_8;

    private Button btn_9;

    /**

     * 运算符

     */

    private Button btn_plus;

    private Button btn_minus;

    private Button btn_multiply;

    private Button btn_divide;

    private Button btn_equal;

    /**

     * 其他

     */

    private Button btn_point;
    private Button btn_c;
    private  Button btn_ac;

    /**

     * 结果

     */

    private EditText mResultText;

    /**

     * 已经输入的字符

     */

    private String existedText = "0";

    /**

     * 是否计算过

     */

    private boolean isCounted = false;

    /**

     * 以负号开头，且运算符不是是减号

     * 例如：-21×2

     */

    private boolean startWithOperator = false;

    /**

     * 以负号开头，且运算符是减号

     * 例如：-21-2

     */

    private boolean startWithSubtract = false;

    /**

     * 不以负号开头，且包含运算符

     * 例如：21-2

     */

    private boolean noStartWithOperator = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

        initEvent();

    }

    /**

     * 初始化控件

     */

    private void initView() {

        /**

         * 数字

         */

        btn_0 = findViewById(R.id.btn_0);

        btn_1 = findViewById(R.id.btn_1);

        btn_2 = findViewById(R.id.btn_2);

        btn_3 = findViewById(R.id.btn_3);

        btn_4 = findViewById(R.id.btn_4);

        btn_5 = findViewById(R.id.btn_5);

        btn_6 = findViewById(R.id.btn_6);

        btn_7 = findViewById(R.id.btn_7);

        btn_8 = findViewById(R.id.btn_8);

        btn_9 = findViewById(R.id.btn_9);

        /**

         * 运算符

         */

        btn_plus = findViewById(R.id.btn_plus);

        btn_minus = findViewById(R.id.btn_minus);

        btn_multiply = findViewById(R.id.btn_multiply);

        btn_divide = findViewById(R.id.btn_divide);

        btn_equal = findViewById(R.id.btn_equal);

        /**

         * 其他

         */

        btn_point = findViewById(R.id.btn_point);
        btn_c = findViewById(R.id.btn_c);
        btn_ac = findViewById(R.id.btn_ac);

        /**

         * 结果

         */

        mResultText = findViewById(R.id.et_input);

        /**

         * 已经输入的字符

         */

        existedText = mResultText.getText().toString();

    }

    /**

     * 初始化事件

     */

    private void initEvent() {

        btn_0.setOnClickListener(this);

        btn_1.setOnClickListener(this);

        btn_2.setOnClickListener(this);

        btn_3.setOnClickListener(this);

        btn_4.setOnClickListener(this);

        btn_5.setOnClickListener(this);

        btn_6.setOnClickListener(this);

        btn_7.setOnClickListener(this);

        btn_8.setOnClickListener(this);

        btn_9.setOnClickListener(this);


        btn_plus.setOnClickListener(this);

        btn_minus.setOnClickListener(this);

        btn_multiply.setOnClickListener(this);

        btn_divide.setOnClickListener(this);

        btn_equal.setOnClickListener(this);


        btn_point.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_ac.setOnClickListener(this);


    }

    /**

     * 点击事件

     * @param v  点击的控件

     */

    @Override

    public void onClick(View v) {


        switch (v.getId()){

            /**

             * 数字

             */

            case R.id.btn_0:

                existedText = isOverRange(existedText,"0");

                break;

            case R.id.btn_1:

                existedText = isOverRange(existedText,"1");

                break;

            case R.id.btn_2:

                existedText = isOverRange(existedText,"2");

                break;

            case R.id.btn_3:

                existedText = isOverRange(existedText,"3");

                break;

            case R.id.btn_4:

                existedText = isOverRange(existedText,"4");

                break;

            case R.id.btn_5:

                existedText = isOverRange(existedText,"5");

                break;

            case R.id.btn_6:

                existedText = isOverRange(existedText,"6");

                break;

            case R.id.btn_7:

                existedText = isOverRange(existedText,"7");

                break;

            case R.id.btn_8:

                existedText = isOverRange(existedText,"8");

                break;

            case R.id.btn_9:

                existedText = isOverRange(existedText,"9");

                break;

            /**

             * 运算符

             */

            case R.id.btn_plus:

                /**

                 * 判断已有的字符是否是科学计数

                 * 是 置零

                 * 否 进行下一步

                 *

                 * 判断表达式是否可以进行计算

                 * 是 先计算再添加字符

                 * 否 添加字符

                 *

                 * 判断计算后的字符是否是 error

                 * 是 置零

                 * 否 添加运算符

                 */

                if (!existedText.contains("e")) {

                    if (judgeExpression()) {

                        existedText = getResult();

                        if (existedText.equals("error")){

                        } else {

                            existedText += "+";

                        }

                    } else {

                        if (isCounted) {

                            isCounted = false;

                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("-")) {

                            existedText = existedText.replace("-", "+");

                        } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {

                            existedText = existedText.replace("×", "+");

                        } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {

                            existedText = existedText.replace("÷", "+");

                        } else if (!(existedText.substring(existedText.length() - 1)).equals("+")) {

                            existedText += "+";

                        }

                    }

                } else {

                    existedText = "0";

                }

                break;

            case R.id.btn_minus:

                if (!existedText.contains("e")) {

                    if (judgeExpression()) {

                        existedText = getResult();

                        if (existedText.equals("error")){

                        } else {

                            existedText += "-";

                        }

                    } else {

                        if (isCounted) {

                            isCounted = false;

                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("+")) {

//                    Log.d("Anonymous", "onClick: " + "进入减法方法");

                            existedText = existedText.replace("+", "-");

                        } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {

                            existedText = existedText.replace("×", "-");

                        } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {

                            existedText = existedText.replace("÷", "-");

                        } else if (!(existedText.substring(existedText.length() - 1)).equals("-")) {

                            existedText += "-";

                        }

                    }

                } else {

                    existedText = "0";

                }

                break;

            case R.id.btn_multiply:

                if (!existedText.contains("e")) {

                    if (judgeExpression()) {

                        existedText = getResult();

                        if (existedText.equals("error")){

                        } else {

                            existedText += "×";

                        }

                    } else {

                        if (isCounted) {

                            isCounted = false;

                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("+")) {

                            existedText = existedText.replace("+", "×");

                        } else if ((existedText.substring(existedText.length() - 1)).equals("-")) {

                            existedText = existedText.replace("-", "×");

                        } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {

                            existedText = existedText.replace("÷", "×");

                        } else if (!(existedText.substring(existedText.length() - 1)).equals("×")) {

                            existedText += "×";

                        }

                    }

                } else {

                    existedText = "0";

                }

                break;

            case R.id.btn_divide:

                if (!existedText.contains("e")) {

                    if (judgeExpression()) {

                        existedText = getResult();

                        if (existedText.equals("error")){

                        } else {

                            existedText += "÷";

                        }

                    } else {

                        if (isCounted) {

                            isCounted = false;

                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("+")) {

                            existedText = existedText.replace("+", "÷");

                        } else if ((existedText.substring(existedText.length() - 1)).equals("-")) {

                            existedText = existedText.replace("-", "÷");

                        } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {

                            existedText = existedText.replace("×", "÷");

                        } else if (!(existedText.substring(existedText.length() - 1)).equals("÷")) {

                            existedText += "÷";

                        }

                    }

                } else {

                    existedText = "0";

                }

                break;
            case R.id.btn_equal:

                existedText = getResult();

                isCounted = true;

                break;

            /**

             * 其他

             */

            case R.id.btn_point:

                /**

                 * 判断是否运算过

                 * 否

                 *   判断是否有运算符，有 判断运算符之后的数字，无 判断整个数字

                 *   判断数字是否过长，是则不能添加小数点，否则可以添加

                 *   判断已经存在的数字里是否有小数点

                 * 是

                 *   字符串置为 0.

                 */

                if (!isCounted){

                    if (existedText.contains("+") || existedText.contains("-") ||

                            existedText.contains("×") || existedText.contains("÷") ){

                        String param1 = null;

                        String param2 = null;

                        if (existedText.contains("+")) {

                            param1 = existedText.substring(0, existedText.indexOf("+"));

                            param2 = existedText.substring(existedText.indexOf("+") + 1);

                        } else if (existedText.contains("-")) {

                            param1 = existedText.substring(0, existedText.indexOf("-"));

                            param2 = existedText.substring(existedText.indexOf("-") + 1);

                        } else if (existedText.contains("×")) {

                            param1 = existedText.substring(0, existedText.indexOf("×"));

                            param2 = existedText.substring(existedText.indexOf("×") + 1);

                        } else if (existedText.contains("÷")) {

                            param1 = existedText.substring(0, existedText.indexOf("÷"));

                            param2 = existedText.substring(existedText.indexOf("÷") + 1);

                        }

                        Log.d("Anonymous param1",param1);

                        Log.d("Anonymous param2",param2);

                        assert param2 != null;
                        boolean isContainedDot = param2.contains(".");

                        if (param2.length() >= 9){


                        } else if (!isContainedDot){

                            if (param2.equals("")){

                                existedText += "0.";

                            } else {

                                existedText += ".";

                            }

                        } else {

                            return;

                        }

                    } else {

                        boolean isContainedDot = existedText.contains(".");

                        if (existedText.length() >= 9){

                        } else if (!isContainedDot){

                            existedText += ".";

                        } else {

                            return;

                        }

                    }

                    isCounted = false;

                } else {

                    existedText = "0.";

                    isCounted = false;

                }
                break;

            case R.id.btn_ac:

                if (existedText.equals("error")){

                    existedText = "0";

                }
                if (existedText.length() > 0) {

                    existedText = "0";

                }
                break;

            case R.id.btn_c:

                /**

                 * 字符串长度大于 0 时才截取字符串

                 * 如果长度为 1，则直接把字符串设置为 0

                 */

                if (existedText.equals("error")){

                    existedText = "0";

                } else if (existedText.length() > 0){

                    if (existedText.length() == 1) {

                        existedText = "0";

                    }
                    else {

                        existedText = existedText.substring(0,existedText.length()-1);

                    }

                }

                break;

        }

        /**

         * 设置显示

         */

        mResultText.setText(existedText);

    }

    /**

     * 进行运算，得到结果

     * @return  返回结果

     */

    private String getResult() {

        /**

         * 结果

         */

        String tempResult = null;

        /**

         * 两个String类型的参数

         */

        String param1;

        String param2;

        /**

         * 转换后的两个double类型的参数

         */

        double arg1;

        double arg2;

        double result;

        getCondition();

        /**

         * 如果有运算符，则进行运算

         * 没有运算符，则把已经存在的数据再传出去

         */

        if ( startWithOperator || noStartWithOperator || startWithSubtract) {

            if (existedText.contains("+")) {

                /**

                 * 先获取两个参数

                 */

                param1 = existedText.substring(0, existedText.indexOf("+"));

                param2 = existedText.substring(existedText.indexOf("+") + 1);

                /**

                 * 如果第二个参数为空，则还是显示当前字符

                 */

                if (param2.equals("")){

                    tempResult = existedText;

                } else {

                    /**

                     * 转换String为Double

                     * 计算后再转换成String类型

                     * 进行正则表达式处理

                     */

                    arg1 = Double.parseDouble(param1);

                    arg2 = Double.parseDouble(param2);

                    result = arg1 + arg2;

                    tempResult = String.format("%f", result);

                    tempResult = subZeroAndDot(tempResult);

                }

            } else if (existedText.contains("×")) {

                param1 = existedText.substring(0, existedText.indexOf("×"));

                param2 = existedText.substring(existedText.indexOf("×") + 1);


                if (param2.equals("")){

                    tempResult = existedText;

                } else {

                    arg1 = Double.parseDouble(param1);

                    arg2 = Double.parseDouble(param2);

                    result = arg1 * arg2;

                    tempResult = String.format("%f", result);

                    tempResult = subZeroAndDot(tempResult);

                }

            } else if (existedText.contains("÷")) {

                param1 = existedText.substring(0, existedText.indexOf("÷"));

                param2 = existedText.substring(existedText.indexOf("÷") + 1);


                switch (param2) {
                    case "0":

                        tempResult = "error";

                        break;
                    case "":

                        tempResult = existedText;

                        break;
                    default:

                        arg1 = Double.parseDouble(param1);

                        arg2 = Double.parseDouble(param2);

                        result = arg1 / arg2;

                        tempResult = String.format("%f", result);

                        tempResult = subZeroAndDot(tempResult);

                        break;
                }

            } else if (existedText.contains("-")) {

                /**

                 * 这里是以最后一个 - 号为分隔去取出两个参数

                 * 进到这个方法，必须满足有运算公式

                 * 而又避免了第一个参数是负数的情况

                 */

                param1 = existedText.substring(0, existedText.lastIndexOf("-"));

                param2 = existedText.substring(existedText.lastIndexOf("-") + 1);

                if (param2.equals("")){

                    tempResult = existedText;

                } else {

                    arg1 = Double.parseDouble(param1);

                    arg2 = Double.parseDouble(param2);

                    result = arg1 - arg2;

                    tempResult = String.format("%f", result);

                    tempResult = subZeroAndDot(tempResult);

                }

            }

            /**

             * 如果数据长度大于等于10位，进行科学计数

             *

             * 如果有小数点，再判断小数点前是否有十个数字，有则进行科学计数

             */

            assert tempResult != null;
            if (tempResult.length() >= 10) {

                tempResult = String.format("%e", Double.parseDouble(tempResult));

            } else if (tempResult.contains(".")) {

                if (tempResult.substring(0, tempResult.indexOf(".")).length() >= 10) {

                    tempResult = String.format("%e", Double.parseDouble(tempResult));

                }

            }

        } else {

            tempResult = existedText;

        }

        return tempResult;

    }

    /**

     * 先判断是否按过等于号

     * 是 按数字则显示当前数字

     * 否 在已有的表达式后添加字符

     *

     * 判断数字是否就是一个 0

     * 是 把字符串设置为空字符串。

     *   1、打开界面没有运算过的时候，AC键归零或删除完归零的时候，会显示一个 0

     *   2、当数字是 0 的时候，设置成空字符串，再按 0 ，数字会还是 0，不然可以按出 000 这种数字

     * 否 添加按下的键的字符

     *

     * 判断数字是否包含小数点

     * 是 数字不能超过十位

     * 否 数字不能超过九位

     *

     * 进行上面的判断后，再判断数字是否超过长度限制

     * 超过不做任何操作

     * 没超过可以再添数字

     */

    private String isOverRange(String existedText, String s) {

        /**

         * 判断是否计算过

         */

        if (!isCounted){

            /**

             * 判断是否是科学计数

             * 是 文本置零

             */

            if (existedText.contains("e")){

                existedText = "0";

            }

            /**

             * 判断是否只有一个 0

             * 是 文本清空

             */

            if (existedText.equals("0")){

                existedText = "";

            }

            /**

             * 判断是否有运算符

             * 是 判断第二个数字

             * 否 判断整个字符串

             */

            if (existedText.contains("+") || existedText.contains("-") ||

                    existedText.contains("×") || existedText.contains("÷")){

                /**

                 * 包括运算符时 两个数字 判断第二个数字

                 * 两个参数

                 */

                String param2 = null;

                if (existedText.contains("+")){

                    param2 = existedText.substring(existedText.indexOf("+")+1);

                } else if (existedText.contains("-")){

                    param2 = existedText.substring(existedText.indexOf("-")+1);

                } else if (existedText.contains("×")){

                    param2 = existedText.substring(existedText.indexOf("×")+1);

                } else if (existedText.contains("÷")){

                    param2 = existedText.substring(existedText.indexOf("÷")+1);

                }

//            Log.d("Anonymous param1",param1);

//            Log.d("Anonymous param2",param2);

                if (existedText.substring(existedText.length()-1).equals("+") ||

                        existedText.substring(existedText.length()-1).equals("-") ||

                        existedText.substring(existedText.length()-1).equals("×") ||

                        existedText.substring(existedText.length()-1).equals("÷")){

                    existedText += s;

                } else {

                    assert param2 != null;
                    if (param2.contains(".")){

                        if (param2.length() >= 10){

                        } else {

                            existedText += s;

                        }

                    } else {

                        if (param2.length() >= 9){

                        } else {

                            existedText += s;

                        }

                    }

                }

            } else {

                /**

                 * 不包括运算符时 一个数字

                 */

                if (existedText.contains(".")){

                    if (existedText.length() >= 10){

                    } else {

                        existedText += s;

                    }

                } else {

                    if (existedText.length() >= 9){

                    } else {

                        existedText += s;

                    }

                }

            }

            isCounted = false;

        } else {

            existedText = s;

            isCounted = false;

        }

        return existedText;

    }

    /**

     * 使用java正则表达式去掉多余的.与0

     * @param s 传入的字符串

     * @return 修改之后的字符串

     */

    public static String subZeroAndDot(String s){

        if(s.indexOf(".") > 0){

            s = s.replaceAll("0+?$", "");//去掉多余的0

            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉

        }

        return s;

    }

    /**

     * 判断表达式

     *

     * 为了按等号是否进行运算

     * 以及出现两个运算符（第一个参数如果为负数的符号不计）先进行运算再添加运算符

     */

    private boolean judgeExpression() {

        getCondition();

        String tempParam2;

        if ( startWithOperator || noStartWithOperator || startWithSubtract) {

            if (existedText.contains("+")) {

                /**

                 * 先获取第二个参数

                 */

                tempParam2 = existedText.substring(existedText.indexOf("+") + 1);

                /**

                 * 如果第二个参数为空，表达式不成立

                 */

                return !tempParam2.equals("");

            } else if (existedText.contains("×")) {

                tempParam2 = existedText.substring(existedText.indexOf("×") + 1);

                return !tempParam2.equals("");

            } else if (existedText.contains("÷")) {

                tempParam2 = existedText.substring(existedText.indexOf("÷") + 1);

                return !tempParam2.equals("");

            } else if (existedText.contains("-")) {

                /**

                 * 这里是以最后一个 - 号为分隔去取出两个参数

                 * 进到这个方法，必须满足有运算公式

                 * 而又避免了第一个参数是负数的情况

                 */

                tempParam2 = existedText.substring(existedText.lastIndexOf("-") + 1);
                return !tempParam2.equals("");

            }
        }
        return false;
    }

    /**

     * 取得判断条件

     */

    private void getCondition() {

        /**

         * 以负号开头，且运算符不是是减号

         * 例如：-21×2

         */

        startWithOperator = existedText.startsWith("-") && ( existedText.contains("+") ||

                existedText.contains("×") || existedText.contains("÷") );

        /**

         * 以负号开头，且运算符是减号

         * 例如：-21-2

         */

        startWithSubtract = existedText.startsWith("-") && ( existedText.lastIndexOf("-") != 0 );

        /**

         * 不以负号开头，且包含运算符

         * 例如：21-2

         */

        noStartWithOperator = !existedText.startsWith("-") && ( existedText.contains("+") ||

                existedText.contains("-") || existedText.contains("×") || existedText.contains("÷"));

    }
}