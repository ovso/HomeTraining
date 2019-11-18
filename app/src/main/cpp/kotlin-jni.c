#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_io_github_ovso_hometraining_data_api_SearchRequest_stringFromJNI(JNIEnv *env,
                                                                         jobject thiz) {
    return (*env)->NewStringUTF(env, "AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E");
}


//io.github.ovso.hometraining.data.api