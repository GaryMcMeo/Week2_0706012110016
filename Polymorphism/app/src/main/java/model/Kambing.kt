package model

class Kambing(nama: String, umur: Int, imageUri: String) : Hewan(nama, umur, imageUri) {
    override fun MakeSound(): String {
        super.MakeSound()
        var Toast = "Jangan Jadikan Saya Gulai Kambing :("

        return Toast;
    }

    override fun GiveFood(): String {
        super.GiveFood()
        var Toast = "Kamu Memberi Makan Kambingmu Sate Kambing"

        return Toast;
    }
}