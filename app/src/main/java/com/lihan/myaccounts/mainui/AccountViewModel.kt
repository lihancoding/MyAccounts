package com.lihan.myaccounts.mainui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.myaccounts.R
import com.lihan.myaccounts.Resource
import com.lihan.myaccounts.data.Account
import com.lihan.myaccounts.data.AccountRepository
import com.lihan.myaccounts.data.AccountRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _accountString = MutableStateFlow("")
    val accountString = _accountString

    private val _passwordString = MutableStateFlow("")
    val passwordString = _passwordString

    private val _descriptionString = MutableStateFlow("")
    val descriptionString = _descriptionString

    private val _iconInt = MutableStateFlow(R.drawable.ic_baseline_bank_24)
    val iconInt = _iconInt

    private val _accounts = MutableStateFlow<Resource>(Resource.Loading)
    val accounts = _accounts

    init {
        getData()
    }

    private fun getData() {
        _accounts.value = Resource.Loading
        viewModelScope.launch {
            accountRepository.getAllAccount().collectLatest {
                if (it.isEmpty()){
                    _accounts.value = Resource.Fail(arrayListOf(),"No Data")
                }else{
                    _accounts.value = Resource.Success(it)
                }
            }
        }
    }

    fun insertAccount(account : Account){
        viewModelScope.launch {
            accountRepository.insert(account)
        }
    }

    fun deleteAccount(account: Account){
        viewModelScope.launch {
            accountRepository.delete(account)
        }
    }


    fun setAccountString(string : String){
        _accountString.value = string
    }

    fun setPasswordString(string : String){
        _passwordString.value = string
    }
    fun setDescriptionString(string : String){
        _descriptionString.value = string
    }
    fun setIconInt(intResource : Int){
        _iconInt.value = intResource
    }








}