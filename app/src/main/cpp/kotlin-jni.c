#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_io_github_ovso_hometraining_data_api_SearchRequest_apiKeys(JNIEnv *env,
                                                                jobject thiz) {
    return (*env)->NewStringUTF(env,
                                "AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E//AIzaSyCDlPMTU-TsKp8k7t6875jkAIRWrl2XCfE//AIzaSyCe8fJ3dw_8YzFq1L7X3Iip9Bs_KZ66bNM//AIzaSyBT2wy_F43ouGtgmNBmklik6qYHYFIVtbA"
    );
}


//io.github.ovso.hometraining.data.api