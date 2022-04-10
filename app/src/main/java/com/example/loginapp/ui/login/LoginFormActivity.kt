package com.example.loginapp.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.app
import com.example.loginapp.databinding.ActivityLoginFormBinding

const val IS_SCREEN_ROTATED = "IS_SCREEN_ROTATED"

class LoginFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginFormBinding
    private var viewModel: LoginFormContract.ViewModel? = null

    private var isLoginSuccess = false

    private var isScreenRotate = false

    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = onRotateRestoreLoginFormViewModel()

        if (savedInstanceState?.getBoolean(IS_SCREEN_ROTATED) == true) {
            isScreenRotate = true
            isLoginSuccess(isLoginSuccess)
        }

        binding.enterButton.setOnClickListener {
            isScreenRotate = true
            viewModel?.onUserLogin(
                binding.usernameEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        binding.registrationButton.setOnClickListener {
            viewModel?.onUserRegistration()
            viewModel?.onUserRegistration?.subscribe(handler) {
                showUserRegistrationForm(it)
            }
        }

        binding.forgotPasswordButton.setOnClickListener {
            viewModel?.onUserForgotPassword()
            viewModel?.onUserForgotPassword?.subscribe(handler) {
                showUserForgotPasswordForm(it)
            }
        }

        viewModel?.showLoginProcessLoading?.subscribe(handler) {
            showLoginProcessLoading(it)
        }

        viewModel?.isUserLoginSuccess?.subscribe(handler) {
            isLoginSuccess(it)
        }

        viewModel?.loginErrorSuccessMessage?.subscribe(handler) {
            loginErrorSuccessMessage(it)
        }
    }

    @MainThread
    private fun isLoginSuccess(
        isLoginSuccess: Boolean?
    ) {
        this.isLoginSuccess = isLoginSuccess == true
        binding.loadingProcessContainer.visibility =
            View.VISIBLE
        if (isLoginSuccess == true) {
            binding.enterSuccessImage.visibility =
                View.VISIBLE
            binding.enterErrorImage.visibility =
                View.GONE
        } else {
            binding.enterErrorImage.visibility =
                View.VISIBLE
            binding.enterSuccessImage.visibility =
                View.GONE
        }
        screenBlockUnblockWithTouch()
    }

    @MainThread
    private fun loginErrorSuccessMessage(
        loginSuccessErrorMessage: String?
    ) {
        loginSuccessErrorMessage?.let {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showLoginProcessLoading(shouldShow: Boolean?) {
        hideKeyboard()
        if (shouldShow == true) {
            loginFormActivityViewsDisable(true)
            binding.loadingProcessContainer.visibility =
                View.VISIBLE
            binding.loadingProcess.visibility =
                View.VISIBLE
        } else {
            binding.loadingProcess.visibility =
                View.GONE
        }
    }

    @MainThread
    private fun showUserRegistrationForm(registrationMessage: String?) {
        Toast.makeText(
            this,
            registrationMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

    @MainThread
    private fun showUserForgotPasswordForm(forgotPasswordMessage: String?) {
        Toast.makeText(
            this,
            forgotPasswordMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun screenBlockUnblockWithTouch() {
        binding.loadingProcessContainer.setOnTouchListener { _, _ ->
            binding.enterSuccessImage.visibility =
                View.GONE
            binding.enterErrorImage.visibility =
                View.GONE
            binding.loadingProcessContainer.visibility =
                View.GONE
            loginFormActivityViewsDisable(false)
            true
        }
    }

    private fun loginFormActivityViewsDisable(
        isDisable: Boolean
    ) {
        binding.activityLoginFormContainer.isEnabled =
            !isDisable
    }

    private fun onRotateRestoreLoginFormViewModel(): LoginFormViewModel {
        val currentViewModel =
            lastCustomNonConfigurationInstance
                    as? LoginFormViewModel

        return currentViewModel ?: LoginFormViewModel(
            app.loginFormUsecase
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(
            currentFocus ?: View(this)
        )
    }

    private fun Context.hideKeyboard(
        view: View
    ) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE)
                    as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            view.windowToken,
            0
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (isScreenRotate) {
            outState.putBoolean(IS_SCREEN_ROTATED, true)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel?.onUserRegistration?.unsubscribe(handler) {
            showUserRegistrationForm(it)
        }

        viewModel?.onUserForgotPassword?.unsubscribe(handler) {
            showUserForgotPasswordForm(it)
        }

        viewModel?.showLoginProcessLoading?.unsubscribe(handler) {
            showLoginProcessLoading(it)
        }

        viewModel?.isUserLoginSuccess?.unsubscribe(handler) {
            isLoginSuccess(it)
        }

        viewModel?.loginErrorSuccessMessage?.unsubscribe(handler) {
            loginErrorSuccessMessage(it)
        }
    }
}