/*
 * Copyright (c) [2020] Huawei Technologies Co.,Ltd.All rights reserved.
 *
 * OpenArkCompiler is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 *
 *     http://license.coscl.org.cn/MulanPSL
 *
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR
 * FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v1 for more details.
 * -@TestCaseID: UnsafearrayBaseOffsetTest
 * -@TestCaseName: Unsafe api: arrayBaseOffset()
 * -@TestCaseType: Function Test
 * -@RequirementName: VMRuntime_registerNativeAllocation接口实现
 * -@Brief:
 * -#step1:Prepare array
 * -#step2:get offset of array
 * -#step3:check return value by getInt,compare step1
 * -@Expect:0\n
 * -@Priority: High
 * -@Source: UnsafearrayBaseOffsetTest.java
 * -@ExecuteClass: UnsafearrayBaseOffsetTest
 * -@ExecuteArgs:
 */

import sun.misc.Unsafe;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class UnsafearrayBaseOffsetTest {
    private static int res = 99;
    public static void main(String[] args) {
        System.out.println(run(args, System.out));
    }

    private static int run(String[] args, PrintStream out) {
        int result = 2/*STATUS_FAILED*/;
        try {
            result = UnsafearrayFieldOffsetTest_1();
        } catch (Exception e) {
            e.printStackTrace();
            UnsafearrayBaseOffsetTest.res = UnsafearrayBaseOffsetTest.res - 10;
        }
//        System.out.println(result);
//        System.out.println(UnsafearrayBaseOffsetTest.res);
        if (result == 3 && UnsafearrayBaseOffsetTest.res == 97) {
            result =0;
        }
        return result;
    }

    private static int UnsafearrayFieldOffsetTest_1() {
        Unsafe unsafe;
        Field field;
        Object obj;
        long offset;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            obj = new int[]{101, 2, 3};
            offset = unsafe.arrayBaseOffset(obj.getClass());
            int result = unsafe.getInt(obj, offset);
            if (result == 101) {
                UnsafearrayBaseOffsetTest.res -= 2;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return 40;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 41;
        }
        return 3;
    }
}

// EXEC:%maple  %f %build_option -o %n.so
// EXEC:%run %n.so %n %run_option | compare %f
// ASSERT: scan-full 0\n