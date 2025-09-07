package co.bimm.chalenge.joaquim

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform