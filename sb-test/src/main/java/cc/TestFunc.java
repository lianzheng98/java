/**
 * @(#)TestFunc.java, 8月 13, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cc;

/**
 * @author lianzhengbj
 */
public class TestFunc {

    public static void main(String[] args) {
        String s = "[{\"a\":1\n" + "},{\"b\":2}\n" + "]";
        //        String s = "10.129.20.99\n" + "10.129.10.145\n" + "10.129.23.183\n" + "10.129.19.228\n" + "10.129.10.220\n" +
        //                "10.129.11.228\n" + "10.129.12.130\n" + "10.129.21.58\n" + "10.129.22.146\n" + "10.129.19.205\n" +
        //                "\n" + "10.129.21.99\n" + "10.129.12.178\n" + "10.129.15.32\n" + "10.129.22.0\n" + "10.129.17.184\n" +
        //                "10.129.10.240\n" + "10.129.12.117\n" + "10.129.23.210\n" + "10.129.17.118\n" + "10.129.18.171\n" +
        //                "10.129.8.223\n" + "10.129.12.3\n" + "10.129.22.118\n" + "10.129.14.221\n" + "10.129.22.38\n" +
        //                "10.129.11.120\n" + "10.129.14.52\n" + "10.129.19.221\n" + "10.129.21.104\n" + "10.129.17.178\n" +
        //                "10.129.11.138\n" + "10.129.8.194\n" + "10.129.17.230\n" + "10.129.22.26\n" + "10.129.10.162\n" +
        //                "10.129.9.216\n" + "10.129.19.5\n" + "10.129.10.178\n" + "10.129.20.123";
        //
        //        String[] split = s.split("\n");
        //
        //        for (String s1 : split) {
        //            System.out.println(
        //                    String.format("\n" + "                            \"http://%s:8080/tutor-room-resource/api\",",
        //                            s1));
        //        }
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            ss(i);
        }

    }

    public static void ss(int i) {

        ss1(i);
    }

    public static void ss1(int i) {
        System.out.println(i);
    }

}