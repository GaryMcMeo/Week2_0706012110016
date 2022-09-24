package model

import android.os.Parcel
import android.os.Parcelable

open class Hewan (
    var nama: String,
    var umur: Int,
    var imageUri: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )

    fun interaction(): String {
        return "a";
    }

    fun feed(): String {
        return "a";
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeInt(umur)
        parcel.writeString(imageUri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hewan> {
        override fun createFromParcel(parcel: Parcel): Hewan {
            return Hewan(parcel)
        }

        override fun newArray(size: Int): Array<Hewan?> {
            return arrayOfNulls(size)
        }
    }

}