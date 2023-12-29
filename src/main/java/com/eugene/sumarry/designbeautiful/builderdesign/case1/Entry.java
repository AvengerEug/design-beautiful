package com.eugene.sumarry.designbeautiful.builderdesign.case1;

/**
 * @author muyang
 * @create 2023/12/29 15:58
 */
public class Entry {

    public static void main(String[] args) {
        // ref为false，arg和type都为空
//        ConstructorArg constructorArg = new ConstructorArg.Builder()
//                .setRef(false)
//                .builder();

        // ref为false，arg为空
//        ConstructorArg constructorArg1 = new ConstructorArg.Builder()
//                .setRef(false)
//                .setType("123")
//                .builder();

        // ref为false，type为空
//        ConstructorArg constructorArg2 = new ConstructorArg.Builder()
//                .setRef(false)
//                .setArg("123")
//                .builder();

        // ref为false，arg、type都不为空  --> 通过
//        ConstructorArg constructorArg3 = new ConstructorArg.Builder()
//                .setRef(false)
//                .setArg("123")
//                .setType("N")
//                .builder();

        // ref为true，arg为空
//        ConstructorArg constructorArg4 = new ConstructorArg.Builder()
//                .setRef(true)
//                .builder();

        // ref为true，arg不为空
        ConstructorArg constructorArg5 = new ConstructorArg.Builder()
                .setRef(true)
                .setArg("123")
                .builder();

    }

}
