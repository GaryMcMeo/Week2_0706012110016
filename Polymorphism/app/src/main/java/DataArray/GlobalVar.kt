package DataArray

import model.Hewan

class GlobalVar {
    companion object {
            val STORAGEWrite_PERMISSION_CODE: Int = 1
            val STORAGERead_PERMISSION_CODE: Int = 0
            val listDataAnimal = ArrayList<Hewan>()
        }
    }