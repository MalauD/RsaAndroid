#include <jni.h>
#include <string>
#include "./RsaCPP/Rsa/Rsa.h"

extern "C" JNIEXPORT jstring JNICALL Java_com_malaurydtr_rsaandroid_MainActivity_EncodeJNI(
        JNIEnv* env,
        jobject,
        jstring textToEncode,
        jlong n,
        jlong e
        ){
    Rsa::RsaKey* rsaKey = new Rsa::RsaKey(Dodecahedron::Bigint(n),Dodecahedron::Bigint(0),Dodecahedron::Bigint(e));
    Rsa::Rsa* rsaC = new Rsa::Rsa();
    return env->NewStringUTF(rsaC->Encode(env->GetStringUTFChars(textToEncode,NULL),rsaKey).c_str());
}

extern "C" JNIEXPORT jstring JNICALL Java_com_malaurydtr_rsaandroid_MainActivity_DecodeJNI(
        JNIEnv* env,
        jobject,
        jstring textToDecode,
        jlong n,
        jlong d
){
    Rsa::RsaKey* rsaKey = new Rsa::RsaKey(Dodecahedron::Bigint(n),Dodecahedron::Bigint(d),Dodecahedron::Bigint(0));
    Rsa::Rsa* rsaC = new Rsa::Rsa();
    return env->NewStringUTF(rsaC->Decode(env->GetStringUTFChars(textToDecode,NULL),rsaKey).c_str());
}