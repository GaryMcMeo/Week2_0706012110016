package listener

interface CardListener {
    fun OnEditClicked(position: Int)
    fun OnDeleteClicked(position: Int)
}