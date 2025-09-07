import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        KoinIOSKt.doInitKoinIos()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
