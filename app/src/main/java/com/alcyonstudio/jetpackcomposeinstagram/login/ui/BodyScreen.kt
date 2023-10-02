package com.alcyonstudio.jetpackcomposeinstagram.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alcyonstudio.jetpackcomposeinstagram.R

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {
    val email:String by loginViewModel.email.observeAsState(initial = "")
    val password:String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable by loginViewModel.loginEnable.observeAsState(initial = false)

    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email) {
            loginViewModel.onLoginChange(email = it, password = password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(
            password) {
//            password = it
//            isLoginEnable = enableLogin(email, password)
            loginViewModel.onLoginChange(email = email, password = it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable, loginViewModel)
        Spacer(modifier = Modifier.size(16.dp))
        LongDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Facebook",
        modifier = Modifier.size(32.dp))
        Text(
            text = "Continue as Hector",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun LongDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, loginViewModel: LoginViewModel) {
    Button(
        onClick = {loginViewModel.onLoginSelected() },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp) // Adjust the value as needed


    ) {
        Text(text = "Login In")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "Forgot password",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    TextField(
        value = password,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {Text("Password")},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFb2B2B2),
            containerColor = Color(0xFFFAFAFA ),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val imagen = if(passwordVisibility){
                Icons.Filled.Visibility
            }
            else{
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility}) {
                Icon(imageVector = imagen, contentDescription = "show password")
            }
        },
        visualTransformation = if(passwordVisibility){
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth(), // Set background using Modifier
        placeholder = { Text("Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFb2B2B2),
            containerColor = Color(0xFFFAFAFA ),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )



}

@Composable
fun ImageLogo(align: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo",
        modifier = align
    )
}

