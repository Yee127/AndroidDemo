//
// Created by Yee Van on 2022/3/15.
//



//引入上面生成的头文件，并实现头文件中声明的方法
#include "com_example_jnidemo_MainActivity.h"
JNIEXPORT jstring JNICALL Java_me_jockio_myapplication_MainActivity_getStringFromNative
        (JNIEnv *env, jobject obj){
    char *str="String from native C";
    return (*env).NewStringUTF(str);
}