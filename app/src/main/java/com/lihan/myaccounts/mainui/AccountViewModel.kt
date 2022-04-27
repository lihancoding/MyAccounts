package com.lihan.myaccounts.mainui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.myaccounts.data.Account
import com.lihan.myaccounts.data.AccountInsertEvent
import com.lihan.myaccounts.data.AccountRepository
import com.lihan.myaccounts.data.AccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    var accountInsertState by mutableStateOf(Account())
    var accountListState by mutableStateOf(AccountState())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            accountRepository.getAllAccount().collectLatest {
                    accountListState = accountListState.copy(accountList = it)
            }
        }
    }
    fun insertAccount(){
        viewModelScope.launch {
            accountRepository.insert(accountInsertState)
        }
    }

    fun deleteAccount(account: Account){
        viewModelScope.launch {
            accountRepository.delete(account)
        }
    }

    fun inputEvent(accountInsertEvent: AccountInsertEvent){
        accountInsertState = when(accountInsertEvent){
            is AccountInsertEvent.Account ->{
                accountInsertState.copy(account = accountInsertEvent.account)
            }
            is AccountInsertEvent.Password->{
                accountInsertState.copy(password = accountInsertEvent.password)
            }
            is AccountInsertEvent.Description->{
                accountInsertState.copy(description = accountInsertEvent.description)
            }
            is AccountInsertEvent.Icon->{
                accountInsertState.copy(icon = accountInsertEvent.icon)
            }
        }
    }


}