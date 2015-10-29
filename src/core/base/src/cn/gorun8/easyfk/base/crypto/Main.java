/*
 * Project:Easy Web Framework
 *
 * Description: This project is based on much more open source projects than ever before,
 *              and can be applied to mostly web development environment.
 * Author:hezhiping   Email:110476592@qq.com
 * 
 * 
 *==========================================================================================
 * 
 */
package cn.gorun8.easyfk.base.crypto;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args[0].equals("-crypt")) {
            System.out.println(HashCrypt.cryptPassword(args[1], args[2]));
        }
    }
}
