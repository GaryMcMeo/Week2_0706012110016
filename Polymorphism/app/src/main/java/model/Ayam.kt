package model

class Ayam(nama: String, umur: Int, imageUri: String) : Hewan(nama, umur, imageUri) {
    override fun MakeSound(): String {
        super.MakeSound()
        var Toast = "Jangan Jadikan Saya McD :("

        return Toast;
    }

    override fun GiveFood(): String {
        super.GiveFood()
        var Toast = "Kamu memberi makan ayammu KFC"

        return Toast;
    }
}
