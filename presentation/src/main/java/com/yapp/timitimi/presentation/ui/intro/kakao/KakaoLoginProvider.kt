package com.yapp.timitimi.presentation.ui.intro.kakao

import android.content.Context
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber

class KakaoLoginProvider(
    private val context: Context
) {
    suspend fun login(): KakaoLoginState {
        val result = suspendCancellableCoroutine<KakaoLoginState> { continuation ->
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        Timber.e("카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            continuation.resume(KakaoLoginState.Error(error.localizedMessage), null)
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = { token, error ->
                            if (error != null) {
                                continuation.resume(KakaoLoginState.Error(error.localizedMessage), null)
                            } else if (token != null) {
                                continuation.resume(KakaoLoginState.Succeed(token), null)
                            }
                        })
                    } else if (token != null) {
                        continuation.resume(KakaoLoginState.Succeed(token), onCancellation = null)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = { token, error ->
                    if (error != null) {
                        continuation.resume(KakaoLoginState.Error(error.localizedMessage), null)
                    } else if (token != null) {
                        continuation.resume(KakaoLoginState.Succeed(token), null)
                    }
                })
            }
        }

        return result
    }
}