package com.lihan.myaccounts.mainui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.myaccounts.R
import com.lihan.myaccounts.Resource
import com.lihan.myaccounts.data.Account
import com.lihan.myaccounts.data.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {


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
                    _accounts.value = Resource.Fail(arrayListOf(
                        Account(0, R.drawable.ic_baseline_web_24,"enk23r","123345","web","webside")
                    ),"No Data")
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








}