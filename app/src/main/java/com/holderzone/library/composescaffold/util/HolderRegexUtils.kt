package com.holderzone.library.composescaffold.util

import java.util.regex.Pattern

/**
 * @author terry
 * @date 2018/09/07 上午10:08
 */
object HolderRegexUtils {

    /**
     * 手机号
     */
    private const val REGEX_TEL = "^[1]\\d{10}$"

    /**
     * 手机号合法，允许保留
     */
    private const val REGEX_TEL_INPUT_LEGAL = "^(\\d{0})|(1)|(1\\d{0,10})$"

    /**
     * 菜品数量（重量）
     */
    private const val REGEX_DISH_COUNT = "^(0|[1-9]\\d{0,7})(\\.\\d{0,3})?$"

    /**
     * 菜品数量（重量）合法，允许保留
     */
    private const val REGEX_DISH_COUNT_INPUT_LEGAL =
        "^(\\d{0})|(0)|([1-9]\\d{0,7})|(0\\.\\d{0,3})|([1-9]\\d{0,7}\\.\\d{0,3})$"

    /**
     * 数量
     */
    private const val REGEX_COUNT = "^(0|[1-9]\\d{0,1})(\\.\\d{0,2})?$"

    /**
     * 数量合法，允许保留
     */
    private const val REGEX_COUNT_INPUT_LEGAL =
        "^(\\d{0})|(0)|([1-9]\\d{0,2})|(0\\.\\d{0,2})|([1-9]\\d{0,2}\\.\\d{0,2})$"

    /**
     * 退菜原因
     */
    private const val REGEX_RETREAT_REASON = "[a-zA-Z0-9\\u4E00-\\u9FA5]{1,20}"

    /**
     * 退菜原因合法，允许保留
     */
    private const val REGEX_RETREAT_REASON_INPUT_LEGAL = "[a-zA-Z0-9\\u4E00-\\u9FA5]{0,20}"

    /**
     * 菜品备注
     */
    private const val REGEX_DISHES_REMARK = "[a-zA-Z0-9\\u4E00-\\u9FA5]{1,20}"

    /**
     * 菜品备注合法，允许保留
     */
    private const val REGEX_DISHES_REMARK_INPUT_LEGAL = "[a-zA-Z0-9\\u4E00-\\u9FA5]{0,20}"

    /**
     * 折扣
     */
    private const val REGEX_DISCOUNT = "^[0-9](\\.\\d{0,2})?|(10)$"

    /**
     * 折扣合法，允许保留
     */
    private const val REGEX_DISCOUNT_INPUT_LEGAL = "^(\\d{0})|([0-9](\\.\\d{0,2})?|(10))|(10)$"

    /**
     * 金额
     */
    private const val REGEX_MONEY = "^(0|[1-9]\\d{0,9})(\\.\\d{0,2})?$"


    /**
     * 金额
     */
    private const val REGEX_MONEY_DINNER = "^(0|[1-9]\\d{0,8})(\\.\\d{0,2})?$"

    /**
     * 金额合法，允许保留
     */
    private const val REGEX_MONEY_INPUT_LEGAL =
        "^(\\d{0})|(0)|([1-9]\\d{0,9})|(0\\.\\d{0,2})|([1-9]\\d{0,9}\\.\\d{0,2})$"



    /**
     * 正餐金额合法，允许保留
     */
    private const val REGEX_MONEY_INPUT_LEGAL_DINNER =
            "^(\\d{0})|(0)|([1-9]\\d{0,8})|(0\\.\\d{0,2})|([1-9]\\d{0,8}\\.\\d{0,2})$"



    /**
     * 编号
     */
    private const val REGEX_CODE = "^(\\d{1,25})$"

    /**
     * 编号合法，允许保留
     */
    private const val REGEX_CODE_INPUT_LEGAL = "^(\\d{0,25})$"

    /**
     * 数字
     */
    private const val REGEX_NUMBER = "^(0|[1-9]\\d{0,25})(\\.\\d{0,2})?$"

    /**
     * 数字合法，允许保留
     */
    private const val REGEX_NUMBER_INPUT_LEGAL =
        "^(\\d{0})|(0)|([1-9]\\d{0,25})|(0\\.\\d{0,2})|([1-9]\\d{0,25}\\.\\d{0,2})$"

    /**
     * 正数数字
     */
    private const val REGEX_POSITIVE_NUMBER = "^0\\.\\d{1,2}|[1-9]\\d{0,25}(\\.\\d{0,2})?\$"

    /**
     * 正数数字合法，允许保留
     */
    private const val REGEX_POSITIVE_NUMBER_INPUT_LEGAL =
        "^(\\d{0})|(0)|([1-9]\\d{0,25})|(0\\.\\d{0,2})|([1-9]\\d{0,25}\\.\\d{0,2})$"

    /**
     * 6位密码
     */
    private const val REGEX_PWD = "^(\\d{6})$"

    /**
     * 6位密码
     */
    private const val REGEX_PWD_INPUT_LEGAL = "^(\\d{0,6})$"

    /**
     * 验证手机号
     */
    fun isTel(input: CharSequence): Boolean {
        return isMatch(REGEX_TEL, input)
    }

    /**
     * 验证手机号是否合法，以允许保留
     */
    fun isTelInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_TEL_INPUT_LEGAL, input)
    }

    /**
     * 重量
     */
    fun isDishCount(input: CharSequence): Boolean {
        return isMatch(REGEX_DISH_COUNT, input)
    }

    /**
     * 重量合法，允许保留
     */
    fun isDishCountInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_DISH_COUNT_INPUT_LEGAL, input)
    }

    /**
     * 点菜数量
     */
    fun isCount(input: CharSequence): Boolean {
        return isMatch(REGEX_COUNT, input)
    }

    /**
     * 点菜数量合法，允许保留
     */
    fun isCountInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_COUNT_INPUT_LEGAL, input)
    }

    /**
     * 退菜原因
     *
     * @param input
     * @return
     */
    fun isRetreatReason(input: CharSequence): Boolean {
        return isMatch(REGEX_RETREAT_REASON, input)
    }

    /**
     * 退菜原因合法，允许保留
     *
     * @param input
     * @return
     */
    fun isRetreatReasonInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_RETREAT_REASON_INPUT_LEGAL, input)
    }

    /**
     * 菜品备注
     *
     * @param input
     * @return
     */
    fun isDishesRemarks(input: CharSequence): Boolean {
        return isMatch(REGEX_DISHES_REMARK, input)
    }

    /**
     * 菜品备注，允许保留
     *
     * @param input
     * @return
     */
    fun isDishesRemarksInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_DISHES_REMARK_INPUT_LEGAL, input)
    }

    /**
     * 折扣
     */
    fun isDiscount(input: CharSequence): Boolean {
        return isMatch(REGEX_DISCOUNT, input)
    }

    /**
     * 折扣合法，允许保留
     */
    fun isDiscountInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_DISCOUNT_INPUT_LEGAL, input)
    }

    /**
     * 金额
     */
    fun isMoney(input: CharSequence): Boolean {
        return isMatch(REGEX_MONEY, input)
    }

    /**
     * 正餐金额
     */
    fun isMoneyDinner(input: CharSequence): Boolean {
        return isMatch(REGEX_MONEY_DINNER, input)
    }


    /**
     * 金额合法，允许保留
     */
    fun isMoneyInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_MONEY_INPUT_LEGAL, input)
    }

    /**
     * 正餐金额判断，123456789.99
     */
    fun isMoneyDinnerInputLegal(input: CharSequence):Boolean{
        return isMatch(REGEX_MONEY_INPUT_LEGAL_DINNER, input)
    }




    /**
     * 编号
     */
    fun isCode(input: CharSequence): Boolean {
        return isMatch(REGEX_CODE, input)
    }

    /**
     * 编号合法，允许保留
     */
    fun isCodeInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_CODE_INPUT_LEGAL, input)
    }

    /**
     * 数字
     */
    fun isNumber(input: CharSequence): Boolean {
        return isMatch(REGEX_NUMBER, input)
    }

    /**
     * 数字合法，允许保留
     */
    fun isNumberInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_NUMBER_INPUT_LEGAL, input)
    }

    /**
     * 数字
     */
    fun isPositiveNumber(input: CharSequence): Boolean {
        return isMatch(REGEX_POSITIVE_NUMBER, input)
    }

    /**
     * 数字合法，允许保留
     */
    fun isPositiveNumberInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_POSITIVE_NUMBER_INPUT_LEGAL, input)
    }

    /**
     * 数字
     */
    fun isPwd(input: CharSequence): Boolean {
        return isMatch(REGEX_PWD, input)
    }

    /**
     * 数字合法，允许保留
     */
    fun isPwdInputLegal(input: CharSequence): Boolean {
        return isMatch(REGEX_PWD_INPUT_LEGAL, input)
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return `true`: 匹配<br></br>`false`: 不匹配
     */
    private fun isMatch(regex: String, input: CharSequence): Boolean {
        return Pattern.matches(regex, input)
    }

    /**
     * 手机号脱敏
     * */
    fun cellPhoneNumberDesensitization(input:String):String{
        return input.substring(0,3)+"****"+input.substring(7,11)
    }

}
