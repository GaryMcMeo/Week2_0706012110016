package model

class Sapi(nama: String, umur: Int, imageUri: String) : Hewan(nama, umur, imageUri) {
    override fun MakeSound(): String {
        super.MakeSound()
        var Toast = "Jangan Jadikan Saya Rendang :("

        return Toast;
    }
    override fun GiveFood(): String {
        super.GiveFood()
        var Toast = "Kamu Memberi Makan Sapimu HolyCow"

        return Toast;
    }
}