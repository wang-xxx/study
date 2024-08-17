package com.demo.mp;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.EnumUtil;
import com.demo.mp.constants.GenderEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangxing
 * @date 2024-08-06 13:52
 */
@Slf4j
@SpringBootTest
public class HutoolTest {

    @Test
    void testEnum() {
        // 字符串转换为枚举
        log.info("字符串转枚举：{}", EnumUtil.getBy(GenderEnum.class, genderEnum -> "男".equals(genderEnum.getDesc())), GenderEnum.OTHER);
        log.info("字符串转枚举：{}", EnumUtil.getBy(GenderEnum.class, genderEnum -> 2 == genderEnum.getValue()), GenderEnum.OTHER);
    }

    @Test
    void testDate() {
        DateTime date = DateUtil.date();
        log.info("当前时间：{}", date);
        log.info("当前时间：{}", DateUtil.now());

        log.info("当前时间，年：{}，月：{}，日：{}", DateUtil.year(date), DateUtil.month(date), DateUtil.dayOfMonth(date));
        log.info("当前时间，年：{}，枚举月：{}，日：{}", DateUtil.year(date), DateUtil.monthEnum(date), DateUtil.dayOfMonth(date));

        log.info("日期转换：{}", DateUtil.parse("2022-6-4"));

        log.info("日期时间差：{}", DateUtil.between(DateUtil.parse("2022-1-1 10:00:00"), date, DateUnit.DAY));

    }

}
