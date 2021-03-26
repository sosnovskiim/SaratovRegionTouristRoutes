package com.sosnowskydevelop.tourroutessaratovregion.data

class RegionRepository {
    fun getRegions(): Array<Region> {
        return arrayOf(
            Region(_name = "Александрово-Гайский район"),
            Region(_name = "Аркадакский район"),
            Region(_name = "Аткарский район"),
            Region(_name = "Базарно-Карабулакский район"),
            Region(_name = "Балаковский район"),
            Region(_name = "Балашовский район"),
            Region(_name = "Балтайский район"),
            Region(_name = "Вольский район"),
            Region(_name = "Воскресенский район"),
            Region(_name = "Дергачёвский район"),
            Region(_name = "Духовницкий район"),
            Region(_name = "Екатериновский район"),
            Region(_name = "Ершовский район"),
            Region(_name = "Ивантеевский район"),
            Region(_name = "Калининский район"),
            Region(_name = "Красноармейский район"),
            Region(_name = "Краснокутский район"),
            Region(_name = "Краснопартизанский район"),
            Region(_name = "Лысогорский район"),
            Region(_name = "Марксовский район"),
            Region(_name = "Новобурасский район"),
            Region(_name = "Новоузенский район"),
            Region(_name = "Озинский район"),
            Region(_name = "Перелюбский район"),
            Region(_name = "Петровский район"),
            Region(_name = "Питерский район"),
            Region(_name = "Пугачёвский район"),
            Region(_name = "Ровенский район"),
            Region(_name = "Романовский район"),
            Region(_name = "Ртищевский район"),
            Region(_name = "Самойловский район"),
            Region(_name = "Саратовский район"),
            Region(_name = "Советский район"),
            Region(_name = "Татищевский район"),
            Region(_name = "Турковский район"),
            Region(_name = "Фёдоровский район"),
            Region(_name = "Хвалынский район"),
            Region(_name = "Энгельсский район"),
        )
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: RegionRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: RegionRepository().also { instance = it }
            }
    }
}