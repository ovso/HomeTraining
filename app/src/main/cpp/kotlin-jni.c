#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_io_github_ovso_hometraining_data_api_SearchRequest_stringFromJNI(JNIEnv *env,
                                                                         jobject thiz) {
    return (*env)->NewStringUTF(env, "AIzaSyCDlPMTU-TsKp8k7t6875jkAIRWrl2XCfE");
}


//io.github.ovso.hometraining.data.api