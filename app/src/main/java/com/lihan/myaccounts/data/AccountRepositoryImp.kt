package com.lihan.myaccounts.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountRepositoryImp (
    private val dao: AccountDao
    ) : AccountRepository{
    override suspend fun insert(account: Account) {
        dao.insert(accountEntity = account.toAccountEntity())
    }

    override suspend fun delete(account: Account) {
        dao.delete(accountEntity = account.toAccountEntity())
    }

    override fun getAllAccount(): Flow<List<Account>> {
        return dao.getAllAccount().map {
            it.map { entity ->
                entity.toAccount()
            }
        }
    }


}