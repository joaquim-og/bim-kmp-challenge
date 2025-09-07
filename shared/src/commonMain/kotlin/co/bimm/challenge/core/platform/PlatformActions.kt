package co.bimm.challenge.core.platform

expect class PlatformActions {
    fun openMap(address: String)
    fun openWebsite(url: String)
}