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

const val IS_PRESENTER_RESTORED = "IS_PRESENTER_RESTORED"

class LoginFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginFormBinding
    private var viewModel: LoginFormContract.ViewModel? = null

    private var isLoginButtonClicked: Boolean = false

    private var isScreenRotated = false

    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState?.getBoolean(IS_PRESENTER_RESTORED) == true) {
            isScreenRotated = true
        }

        viewModel = onRotateRestoreLoginFormViewModel()

        binding.enterButton.setOnClickListener {
            isLoginButtonClicked = true
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

        viewModel?.isUserLoginSuccess?.subscribe(handler) { isLoginSuccess ->
            viewModel?.loginErrorSuccessMessage?.subscribe(handler) {
                loginErrorSuccessMessage(it, isLoginSuccess)
            }
        }
    }

    private fun loginErrorSuccessMessage(
        loginSuccessErrorMessage: String?,
        isLoginSuccess: Boolean?
    ) {
        if (isLoginSuccess == true) {
            loginSuccessErrorMessage?.let {
                setUserLoginSuccess(
                    it,
                    isScreenRotated
                )
            }
        } else {
            loginSuccessErrorMessage?.let {
                setUserLoginError(
                    it,
                    isScreenRotated
                )
            }
        }
    }

    private fun showLoginProcessLoading(shouldShow: Boolean?) {
        hideKeyboard()
        binding.enterSuccessImage.visibility =
            View.GONE
        binding.enterErrorImage.visibility =
            View.GONE
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
    private fun setUserLoginSuccess(
        enterSuccessText: String,
        isRestored: Boolean
    ) {
        binding.loadingProcessContainer.visibility =
            View.VISIBLE
        binding.enterErrorImage.visibility =
            View.GONE
        binding.enterSuccessImage.visibility =
            View.VISIBLE
        if (!isRestored) Toast.makeText(
            this,
            enterSuccessText,
            Toast.LENGTH_SHORT
        ).show()
        screenBlockUnblockWithTouch()
    }

    @MainThread
    private fun setUserLoginError(
        enterErrorText: String,
        isRestored: Boolean
    ) {
        binding.loadingProcessContainer.visibility =
            View.VISIBLE
        binding.enterSuccessImage.visibility =
            View.GONE
        binding.enterErrorImage.visibility =
            View.VISIBLE
        if (!isRestored) Toast.makeText(
            this,
            enterErrorText,
            Toast.LENGTH_SHORT
        ).show()
        screenBlockUnblockWithTouch()
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
            isLoginButtonClicked = false
            isScreenRotated = false
            loginFormActivityViewsDisable(false)
            false
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

    override fun onSaveInstanceState(
        outState: Bundle
    ) {
        if (isLoginButtonClicked || isScreenRotated) {
            outState.putBoolean(IS_PRESENTER_RESTORED, true)
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

        viewModel?.isUserLoginSuccess?.unsubscribe(handler) { isLoginSuccess ->
            viewModel?.loginErrorSuccessMessage?.unsubscribe(handler) {
                loginErrorSuccessMessage(it, isLoginSuccess)
            }
        }
    }
}