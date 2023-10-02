package com.alcyonstudio.jetpackcomposeinstagram.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alcyonstudio.jetpackcomposeinstagram.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase):ViewModel() {

//    val loginUseCase = LoginUseCase()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> =_email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> =_password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> =_loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> =_isLoading

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String)=
        Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length > 6

    fun onLoginSelected(){
        viewModelScope.launch{
            _isLoading.value = true
            val result = loginUseCase(email.value!!, password.value!!)
            if(result){
                Log.i("Tzil", "result OK")
            }
            _isLoading.value = false
        }
    }

}