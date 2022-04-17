package com.lihan.myaccounts.data

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.Gson
import com.lihan.myaccounts.R

data class Account (
    val id : Int?=null,
    var icon : Int,
    var account : String,
    var password : String,
    var description : String,
    var type : String
) : Parcelable {
    constructor():this(null, R.drawable.ic_baseline_web_24,"","","","")

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(icon)
        parcel.writeString(account)
        parcel.writeString(password)
        parcel.writeString(description)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Account> {
        override fun createFromParcel(parcel: Parcel): Account {
            return Account(parcel)
        }

        override fun newArray(size: Int): Array<Account?> {
            return arrayOfNulls(size)
        }
    }
}
