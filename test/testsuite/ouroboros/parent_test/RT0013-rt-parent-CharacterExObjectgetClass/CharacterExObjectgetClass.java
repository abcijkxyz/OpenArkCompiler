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
 * -@TestCaseID: CharacterExObjectgetClass.java
 * -@TestCaseName: Exception in Character:  final Class<?> getClass()
 * -@TestCaseType: Function Test
 * -@RequirementName: 补充重写类的父类方法
 * -@Brief:
 * -#step1: Construct object char1 by new Character (char name)
 * -#step2: Call getClass () on char1
 * -#step3: Confirm that the returned object is correct
 * -@Expect:0\n
 * -@Priority: High
 * -@Source: CharacterExObjectgetClass.java
 * -@ExecuteClass: CharacterExObjectgetClass
 * -@ExecuteArgs:
 */

import java.lang.Thread;

public class CharacterExObjectgetClass {
    static int res = 99;


    public static void main(String argv[]) {
        System.out.println(new CharacterExObjectgetClass().run());
    }

    /**
     * main test fun
     * @return status code
     */
    public int run() {
        int result = 2; /*STATUS_FAILED*/
        try {
            result = characterExObjectgetClass1();
        } catch (Exception e) {
            CharacterExObjectgetClass.res = CharacterExObjectgetClass.res - 20;
        }
        if (result == 4 && CharacterExObjectgetClass.res == 89) {
            result = 0;
        }

        return result;
    }


    private int characterExObjectgetClass1() {
        //  final Class<?> getClass()
        int result1 = 4; /*STATUS_FAILED*/
        char name = 'A';
        Character char1 = new Character(name);

        Class px1 = char1.getClass();

        if (px1.toString().equals("class java.lang.Character")) {
            CharacterExObjectgetClass.res = CharacterExObjectgetClass.res - 10;
        }
        return result1;
    }
}
// EXEC:%maple  %f %build_option -o %n.so
// EXEC:%run %n.so %n %run_option | compare %f
// ASSERT: scan-full 0\n